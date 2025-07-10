<%-- 
    Document   : Login
    Created on : Jul 10, 2025, 2:06:27 PM
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
        <h1>Login to website</h1>
        <form action="LoginController" method="post" >
            Username <input type="text" name="txtUserName" /><br/>
            Password <input type="password" name="txtPassword" /><br/>
            <input type="submit" value="Login" name="txtPassword" /><br/>
            <input type="reset" value="Reset" /> <br/>
            <a href='CreateUser.jsp'>Click here to sign up</a><br/>
        </form>
        <c:set var="message" value="${requestScope.message}"/>
        
    </body>
</html>
