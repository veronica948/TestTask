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
        <form action="${ pageContext.request.contextPath }/controller?action=import_contacts"
          enctype="multipart/form-data" method="post">
           <label for="file"> Load file</label>
           <input id = "file" type="file" name="file">
           </form>
    </section>
   </body>
 </html>