<%-- 
    Document   : manager
    Created on : May 25, 2021, 10:31:08 PM
    Author     : Minh Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Request</title>

    </head>

    <body>
        <form action="MainController" method="post" >
             <c:if test="${sessionScope.LOGIN_USER!=null}">
                <c:if test="${sessionScope.LOGIN_USER.rolID eq '001'}">
                     Hi ${sessionScope.LOGIN_USER.fullName}
                    <a href="MainController?action=Logout">Logout</a>
                </c:if>
            </c:if>
            Resource Name: <input type="text" name="txtResourceName" value="${param.txtResourceName}"> 
            User Name: <input type="text" name="txtUserName" value="${param.txtUserName}"> 
            Date (yyyy-MM-dd): <input type="text" name="from" id="from" value="${param.from}"/>
            Status: <select name="txtStatus">
                <option value="New">New</option>
                <option value="Accept">Accept</option>
                <option value="Delete">Delete</option>
            </select>
            <input type="submit" name="action" value="SearchManager"/>

        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>REQUEST ID</th>
                    <th>USER ID</th>
                    <th>STATUS</th>
                    <th>CREATE DATE</th>
                    <th>RESOURE ID</th>
                    <th>RESOURCE NAME</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="o" items="${REQUEST}">
                 
                    <tr>
                        <td>${o.requestID}</td>
                        <td>${o.userID}</td>
                        <td>${o.status}</td>

                        <td>
                            ${o.createDate}
                        </td>
                        <td>
                            ${o.dto.resourceID}
                        </td>
                        <td> ${o.dto.resourceName}</td>
                        <td><a href="AcceptController?requestID=${o.requestID}">Accept</a></td> 
                        <td><a href="DeleteRequestController?requestID=${o.requestID}">Delete</a></td> 
                    </tr>

                </c:forEach>

            </tbody>
        </table>

        <c:forEach begin="1" end="${endPage}" var="i">

            <a href="SearchController?index=${i}">${i}</a>

        </c:forEach>
        <c:set value="${requestScope.REQUEST_SUCCESS}" var="request"/>
        ${request}
        <c:if test="${not empty requestt}">
            <script>
                alert('${request}');
            </script>
        </c:if>
    </body>
</html>
