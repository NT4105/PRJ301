<%-- 
    Document   : Search
    Created on : Jun 22, 2025, 5:03:13 PM
    Author     : Admin
--%>

<%@page import="Models.DTO.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <c:set var="userLoggedIn" value="${userLoggedIn}"/>
        <c:if test="${userLoggedIn != null}">
            <c:set var="lastName" value="${userLoggedIn.lastName}"/>
        </c:if>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <h3>Welcome <text style="color: red">${lastName}</text></h3>

        <form method="post">
            <input type="submit" formaction="LogoutController" value="Logout"/>
        </form>

        <h1>Search user by last name</h1>
        <form action="UserController" method="post">
            Enter search value 
            <input type="text" name="txtSearchValue" value="${searchValue!=null?searchValue:""}"/>
            <input type="submit" value="Search" name="action"/>
        </form>

        <c:set var="userList" value="${requestScope.SearchResult}"/>
        <c:set var="count" value="1"/>
        <c:if test="${userList != null}">
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
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${count}</td>
                            <td>${user.userName}</td>
                            <td>${user.password}</td>
                            <td>${user.lastName}</td>
                            <td><input type="checkbox" <c:if test="${user.isAdmin == true}">checked</c:if>/></td>
                            <td>
                                <a href="UserController?action=Delete&UserName=${user.userName}&txtSearchValue=${searchValue}">
                                    Delete
                                </a>
                            </td>
                            <td>
                                <a href="UserController?action=Details&UserName=${user.userName}&txtSearchValue=${searchValue}">
                                    View
                                </a>
                            </td>
                        </tr>
                        <c:set var="count" value="${count + 1}"/>
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
