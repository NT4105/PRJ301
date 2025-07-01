<%-- 
    Document   : Login.jsp
    Created on : Jun 22, 2025, 5:02:48 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login to website</h1>
        <form action="LoginController" method="POST">
            UserName <input type="text" name="txtUsername" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="action" />
            <input type="reset" value="Reset" /><br/><!-- comment -->
            <a href="CreateUser.jsp">Click here to sign up</a><br/>
        </form>
        <c:set var="messgae"  value="${requestScope.message}"/>
        <c:if test="${message != null}">
              <text style="color: red">${message}</text>
    </c:if>
    </body>
</html>
