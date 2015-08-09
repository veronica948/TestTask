<%@ page language="java" contentType="text/html;charset=UTF-8"  %>
<nav>
    <ul>
        <li>
            <a href="${ pageContext.request.contextPath }/controller?action=get_contacts&page=1">Contact List</a>
        </li>
        <li>
            <a href="${ pageContext.request.contextPath }/controller?action=to_import_page">Import contacts</a>
        </li>
    </ul>
</nav>