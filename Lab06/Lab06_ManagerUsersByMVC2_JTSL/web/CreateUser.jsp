
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create User</title>
    </head>
    <body>
        <h1>Create User</h1>
        <c:set var="message" value="${requestScope.message}"/>
        <c:if test="${message != null}">
            ${message}
        </c:if>
        <form action="UserController" method="post">
            <c:set var="error" value="${requestScope.ErrorDetails}"/>
            UserName <input type="text" name="txtUserName" placeholder="Enter username" value="Uxxx"/>
            <c:if test="${not empty error.userNameError}">
                <text style="color: red">${error.userNameError}</text>
            </c:if><br/>
            <c:if test="${not empty error.duplicatedUserName}">
                <text style="color: red">${error.duplicatedUserName}</text>
            </c:if><br/>

            Password <input type="password" placeholder="Enter password" name="txtPassword"/>
            <c:if test="${not empty error.passwordError}">
                <text style="color: red">${error.passwordError}</text>
            </c:if><br/>

            Lastname <input type="text" placeholder="Enter last name" name="txtLastName"/>
            <c:if test="${not empty error.lastNameError}">
                <text style="color: red">${error.lastNameError}</text>
            </c:if><br/>

            <input type="checkbox" name="chkIsAdmin" disabled="true"/>Is Admin<br/>
            <input type="submit" value="Create" name="action"/>
            <a href="Login.jsp">Back</a><br/>
        </form>
    </body>
</html>
