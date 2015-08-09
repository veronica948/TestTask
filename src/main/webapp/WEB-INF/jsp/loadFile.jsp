<%@ page language="java" contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
  <head>
    <title>Import contacts</title>
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/common/menu.jsp" />
    ${successfulOperation}
    ${busy}
    ${emptyFile}
    <section>
        <form action="${ pageContext.request.contextPath }/controller?action=import_contacts" enctype="multipart/form-data" method="post">
           <input id = "file" type="file" name="file"/>
           <input type="submit"/>
        </form>
    </section>
   </body>
 </html>