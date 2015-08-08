<%@ page language="java" contentType="text/html;charset=UTF-8"  %>
<nav>
    <ul>
        <li>
            <a href="${ pageContext.request.contextPath }/contacts?action=GET_CONTACTS&page=1">Contact List</a>
        </li>
        <li>
            <a href="${ pageContext.request.contextPath }/WEB-INF/jsp/loadFile.jsp">Import contacts</a>
        </li>
    </ul>

</nav>