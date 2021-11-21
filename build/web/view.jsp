<%-- 
    Document   : test
    Created on : May 18, 2021, 11:51:08 PM
    Author     : Minh Hoang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>

    <body>
        <form action="MainController" method="post" >
            Name: <input type="text" name="txtName" value="${param.txtName}"> 


            Using Date (yyyy-MM-dd): <input type="text" name="from" value="${param.from}"/>
            <input type="submit" name="action" value="Search"/>
            Category:

            <c:forEach items="${CATEGORY}" var="cate">
                <a href="SearchCategoryController?cateID=${cate.cateID}">${cate.cateName}</a>
            </c:forEach>
            <br>


            <c:if test="${sessionScope.LOGIN_USER!=null}">
                <c:if test="${sessionScope.LOGIN_USER.rolID eq '002'}">

                   Hi ${sessionScope.LOGIN_USER.fullName}
                </c:if>
                <c:if test="${sessionScope.LOGIN_USER.rolID eq '001'}">
                    Hi ${sessionScope.LOGIN_USER.fullName}
                </c:if>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER!=null}">
                <c:if test="${sessionScope.LOGIN_USER.rolID eq '002' or sessionScope.LOGIN_USER.rolID eq '001'}">
                    <a href="MainController?action=Logout">Logout</a>
                </c:if>
            </c:if>



        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>RESOURCE ID</th>
                    <th>RESOURCE NAME</th>
                    <th>COLOR</th>
                    <th>IMAGE</th>
                    <th>USING DATE</th>
                    <th>CATE ID</th>
                    <th>REQUEST</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${RESOURCE}">
                    <c:if test="${dto.status eq 'Availble'}">
                        <tr>
                            <td>${dto.resourceID}</td>
                            <td>${dto.resourceName}</td>
                            <td>${dto.color}</td>

                            <td>
                                <img  src="image/${dto.image}" alt="resource" width="100" height="50"> 
                            </td>
                            <td>
                                ${dto.usingDate}
                            </td>
                            <td>${dto.cateID}</td>
                            <td><a href="MainController?action=Request&resourceID=${dto.resourceID}">Request</a></td> 

                        </tr>
                    </c:if>
                </c:forEach>

            </tbody>
        </table>

        <c:forEach begin="1" end="${endPage}" var="i">

            <a href="SearchController?index=${i}">${i}</a>

        </c:forEach>
        <c:set value="${requestScope.NO_RECORD}" var="record"/>
        ${record}
        <c:if test="${not empty record}">
            <script>
                alert('${record}');
            </script>
        </c:if>
        <c:set value="${requestScope.REQUEST_SUCCESS}" var="request"/>
        ${request}
        <c:if test="${not empty requestt}">
            <script>
                alert('${request}');
            </script>
        </c:if>
    </body>
</html>
