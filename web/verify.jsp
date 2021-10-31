<%-- 
    Document   : verify
    Created on : May 19, 2021, 1:06:14 PM
    Author     : Minh Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Page</title>
    </head>
    <body>
        <a href="MainController?action=loginAccount">Login</a>
        <h5>Verify User</h5>
        <form action="MainController" method="POST">
            Code: <input type="text" name="txtCode" /> ${requestScope.ERROR_CODE}
            </br>
            <input type="submit" name="action" value="Verify"/>
        </form>
       
        
            <c:set value="${requestScope.VERIFY_NOTIFY}" var="notify"></c:set>
            <c:if test="${not empty notify}" >
                <script>
                    alert('${notify}');
                </script>
            </c:if>
        </body>
    </html>
