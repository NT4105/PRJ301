<%-- 
    Document   : CreateUser
    Created on : Jul 10, 2025, 2:16:26 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create user</h1>
        <c:set var="message" value="${requestScope.message}" />
        <c:if test="${message != null}">
            ${message}
        </c:if>
        <form action="UserController" method="post">
            <c:set var="error" value="${requestScope.ErrorDetails}" />
            UserName <input type="text" name="txtUserName" value="Uxxx" />
            <c:if test="${not empty error.userNameError}">
                <text style="color: red">${error.userNameError}</text>
            </c:if>
            <c:if test="${not empty error.duplicateUserName}">
                <text style="color: red">${error.duplicateUserName} </text>
            </c:if><br/>
            Password <input type="password" placeholder="Enter password" name="txtPassword" />
            <c:if test="${not empty error.passwordError}">
                <text style="color: red">${error.passwordError} </text>
            </c:if><br/>
            LastName <input type="text" placeholder="Enter last name" name="txtLastName" />
            <c:if test="${not empty error.lastNameError}">
                <text style="color: red">${error.lastNameError} </text>
            </c:if><br/>
            <input type="checkbox" name="chkIsAdmin" disabled="true" />isAdmin<br/>
            <input type="submit" value="Create" name="action" /></br>
            
            <a href='Login.jsp'>Back</a><br/>
                
        </form>
    </body>
</html>
