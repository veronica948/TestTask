package com.haritonova.contacts.controller;

import com.haritonova.contacts.action.Action;
import com.haritonova.contacts.action.ActionFactory;
import com.haritonova.contacts.exception.ActionException;
import com.haritonova.contacts.manager.ConfigManager;
import com.haritonova.contacts.pool.Pool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Пользователь on 07.10.2014.
 */
@WebServlet(
        urlPatterns = {"/"},
        name = "controller"
)
@MultipartConfig
public class Controller extends HttpServlet {
    static Logger logger = Logger.getLogger(Controller.class);

    private static final String ATTR_EXCEPTION = "exception";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        Action action = ActionFactory.defineCommand(request);
        try {
            page = action.execute(request);
        } catch (ActionException e) {
            logger.error(e.getMessage(), e.getCause());
            request.setAttribute(ATTR_EXCEPTION, e.getCause());
            page = ConfigManager.getProperty("path.page.error");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String path = this.getServletContext().getRealPath("/");
        new DOMConfigurator().doConfigure(path + "/log4j.xml", LogManager.getLoggerRepository());
    }
    @Override
    public void destroy() {
        super.destroy();
        Pool.getPool().closePool();
    }
}
