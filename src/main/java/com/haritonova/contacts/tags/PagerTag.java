package com.haritonova.contacts.tags;

import com.haritonova.contacts.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Veronica on 08.08.2015.
 */
public class PagerTag extends SimpleTagSupport {
    private int page;
    private int generalAmount;
    /**
     * Amount of pages' links on page
     */
    private static final int PAGER_AMOUNT = 5;

    @Override
    public void doTag() throws JspException {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            JspWriter out = pageContext.getOut();
            int currentPage = page;
            int generalAmountOfPages = (int) Math.ceil((double) generalAmount / ControllerUtils.CONTACTS_AMOUNT_ON_PAGE);
            int pageNumber;
            int firstPageNumber;
            StringBuffer form = new StringBuffer();
            StringBuffer formCondition = new StringBuffer();
            formCondition.append("<form  action=/controller?action=get_contacts>");
            int pagerHalf = (int) Math.ceil((double) PAGER_AMOUNT / 2);
            if (generalAmountOfPages > 1) {
                if (currentPage > 1) {
                    form.append(formCondition);
                    form.append("<input type=hidden name=page ").append("value=").append(1).append(">")
                            .append("<input type=submit value=").append("\'").append("<<").append("\'").append(">");
                    form.append("</form>");


                }
                if (currentPage <= pagerHalf || generalAmountOfPages <= PAGER_AMOUNT) {
                    firstPageNumber = 1;
                } else if (generalAmountOfPages - currentPage < pagerHalf) {
                    firstPageNumber = generalAmountOfPages - PAGER_AMOUNT + 1;
                } else {
                    firstPageNumber = currentPage - pagerHalf + 1;
                }
                for (int i = 0; i < PAGER_AMOUNT; i++) {
                    pageNumber = firstPageNumber + i;
                    if (pageNumber == currentPage) {
                        form.append(formCondition);
                        form.append("<input type=hidden name=page ").append("value=").append(pageNumber).append(">")
                                .append("<input type=submit value=").append(pageNumber).append(">");
                        form.append("</form>");

                    } else if (pageNumber <= generalAmountOfPages) {
                        form.append(formCondition);
                        form.append("<input type=hidden name=page ").append("value=").append(pageNumber).append(">")
                                .append("<input type=submit class=pagerItem value=").append(pageNumber).append(">");
                        form.append("</form>");
                    }
                }
                if (currentPage < generalAmountOfPages) {


                    form.append(formCondition);
                    form.append("<input type=hidden name=page ").append("value=").append(generalAmountOfPages).append(">")
                            .append("<input type=submit class=pagerItem value=").append("\'").append(">>")
                            .append("(").append(generalAmountOfPages).append(")").append("\'").append(">");
                    form.append("</form>");

                }

                out.write(form.toString());
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage(), e);
        }
    }


    public void setGeneralAmount(int generalAmount) {
        this.generalAmount = generalAmount;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
