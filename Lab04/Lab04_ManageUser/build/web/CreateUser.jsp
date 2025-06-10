<%-- 
    Document   : CreateUsẻ
    Created on : Jun 5, 2025, 10:48:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
    </head>
    <body>
        <h1>Create User</h1>
        <form action="UserController" method="POST">
            UserName <input type="text" name="txtUserName" placeholder="Enter user name" /><br/>
            Password <input type="password" name="txtPassword" placeholder="Enter the password" /><br/>
            LastName <input type="text" name="txtLastName" placeholder="Enter the last name" /><br/>
            <input type="checkbox" name="chkIsAdmin" disabled="true" />isAdmin<br/>
            <input type="submit" value="Create" name="action" /><br/>
            <a href="Login.html">Back</a>
        </form>
    </body>
</html>
