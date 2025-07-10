<%@page import="Models.DTO.User" %>
<%@page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="userLoggedIn" value="${userLoggedIn}" />
        <c:if test="${userLoggedIn != null}">
            <c:set var="lastName" value="${userLoggedIn.lastName}" />
        </c:if>
        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <h3>Welcome to <text style="color: red">${lastName}</text></h3>
        <form method="post">
            <input type="submit" formaction="LogoutController" value="Logout" /><br/>
        </form>
        <h1>Search user by last name</h1>
        <form action="UserController" method="post" >
            Enter search value
            <input type="text" name="txtSearchValue"
                   value="${searchValue != null?searchValue:""}" /><br/>
            <input type="submit" value="Search" name="action" />
        </form>
        <c:set var="userList" value="${requestScope.SearchResult}" />
        <c:set var="count" value="1" />
        <c:if test="${userList != null}">
            <table border='1'>
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>UserName</th>
                        <th>Password</th>
                        <th>Role</th>
                        <th colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${count}</td>
                            <td>${user.userName}</td>
                            <td>${user.password}</td>
                            <td>${user.lastName}</td>
                            <td><input type="checkbox" <c:if test="${user.isAdmin==true}">checked</c:if> </td>
                            <td><a href='UserController?action=Delete&UserName=${user.userName}&txtSearchValue=${searchValue}'>
                                    Delete
                            </td>
                            <td><a href='UserController?action=Details&UserName=${user.userName}&txtSearchValue=${searchValue}'>
                                    View
                            </td>
                        </tr>
                        <c:set var="count" value="${count+1}" />
                        </c:forEach>
                </tbody>
            </table>
            <c:set var="message" value="${requestScope.message}"/>
            <c:if test="${message != null}">
                ${message}
            </c:if>
            <h3>Number of users: ${userList.size()}</h3>
        </c:if>
        <c:if test="${searchValue != null}">
            <c:if test="${userList.size() == 0 || userList == null}">
                <h3>No users were found.</h3>
            </c:if>
        </c:if>
    </body>
</html>
