<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script src='https://www.google.com/recaptcha/api.js?hl=vi'></script>
        <style>
            .myDiv {
                border: 5px outset #ffffff;
                background-color: lightblue;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="myDiv">

            <h2> Login page</h2>

            <form action="MainController" method="POST">
<img src="C:\\Users\\Minh Hoang\\Desktop\\TheUserManagement\\web\\images\\001.jpg">
                <p class="text-danger">${mess}</p>
                Email:<input type="text" name="txtEmail"  value="${requestScope.EMAIL}" />${requestScope.ERROR}<br>
                Password:<input type="password" name="txtPassword"/><br>

                <div class="g-recaptcha"
                     data-sitekey="6LdZZ9oaAAAAAD-_8NYLN9AjbwHuASZSpYIfb7bn"></div>
                <a href="MainController?action=RegisterAccount"> Register</a> 
                <input type="submit" name="action" value="Login"/>
            </form>

        </div> 
        <c:set value="${requestScope.NOT_LOGIN}"  var="NotLogin"></c:set>

        <c:if test="${NotLogin}" >
            <script>
                alert('${NotLogin}');
            </script>
        </c:if>
        <c:set value="${requestScope.SUCCESS}" var="success"/>
        <c:if test="${not empty success}">
            <script>
                alert('${success}');
            </script>
        </c:if>
        <c:set value="${requestScope.ADMIN_LOGIN}" var="admin"></c:set>
        <c:if test="${not empty admin}">
            <script>
                alert('${admin}');
            </script>
        </c:if>

        <c:set value="${requestScope.NOTIFICATION}" var="notification"></c:set>
        <c:if test="${not empty notification}">
            <script>
                alert('${notification}');
            </script>
        </c:if>
    </body>
</html>
