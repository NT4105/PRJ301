<%-- 
    Document   : Search
    Created on : Jun 5, 2025, 10:49:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.DTO.User" %>
<%@page import="Models.DAO.UserDAO" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search User</title>
    </head>
    <body>
        <%
            String lastName = null, searchValue = null;
            User userLoggedIn = (User)session.getAttribute("userLoggedIn");
            if(userLoggedIn != null) {
                lastName = userLoggedIn.getLastName();
            }
            searchValue = request.getParameter("txtSearchValue");
        %>
        <h3>Welcome to <text style="color: red"><%=lastName%></text></h3>
        <h1>Search user by last name</h1>
        <form action="UserController" method="post">
            Enter search value<br/>
            <input type="text" name="txtSearchValue" 
                   value="<%=searchValue==null?"":searchValue%>" /><br/>
            <input type="submit" value="Search" name="action" />
        </form>
    </body>
    <%
    // if (searchValue != null) {
    UserDAO userDao = new UserDAO();
    List<User> userList = (List<User>) request.getAttribute("SearchResult");
    if (userList != null) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>UserName</th>
                <th>Password</th>
                <th>LastName</th>
                <th>Role</th>
                <th colspan="2">Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 0;
                for (User user : userList) {
            %>
            <tr>
                <td><%= (++count) %></td>
                <td><%= user.getUserName() %></td>
                <td><%= user.getPassword() %></td>
                <td><%= user.getLastName() %></td>
                <td><input type="checkbox" <%= user.isIsAdmin() ? "checked" : "" %> /></td>
                <td><a href="UserController?action=Delete&UserName=<%= user.getUserName() %>&txtSearchValue=<%= searchValue %>">
                        Delete</a></td>
                <td><a href="UserController?action=Details&UserName=<%= user.getUserName() %>&txtSearchValue=<%= searchValue %>">
                        View</a></td>
            </tr>
            <%
                } // end for
            %>
        </tbody>
    </table>
    <h3>Number of users found: <%= userList.size() %></h3>
    <%
        } // end if
        else if (searchValue != null) {
    %>
    <h3>No users were found.</h3>
    <%
        }
    %>
    <a href="Login.html">Back</a><br/>
</html>
