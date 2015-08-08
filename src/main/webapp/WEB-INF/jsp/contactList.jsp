<%@ page language="java" contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
  <head>
    <title>Contacts</title>
  </head>
  <body>
    <c:import url="${ pageContext.request.contextPath }/WEB-INF/jsp/common/menu.jsp" />
    <section>
             <a href="${ pageContext.request.contextPath }/contacts?action=GET_CONTACTS&page=1&sort=name">By name</a>
             <a href="${ pageContext.request.contextPath }/contacts?action=GET_CONTACTS&page=1&sort=surname">By surname</a>
             <a href="${ pageContext.request.contextPath }/contacts?action=GET_CONTACTS&page=1&sort=login">By login</a>
             <table id="contactsTable">
             <c:forEach var="contact" items = "${contactList}">
                <tr>
                    <td>${contact.name}</td>
                    <td>${contact.surname}</td>
                    <td>${contact.login}</td>
                    <td>${contact.email}</td>
                    <td>${contact.phoneNumber}</td>
                </tr>
             <c:forEach>
             </table>
       <c:choose>
           <c:when test="${amount != 0}">
               <div class="pager">
                   <ctg:pager generalAmount="${amount}" page = "${page}"/>
               </div>
           </c:when>
           <c:otherwise>
               No contacts
           </c:otherwise>
       </c:choose>
        </section>
  </body>
</html>