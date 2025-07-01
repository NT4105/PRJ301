<%@page import="java.util.List"%>
<%@page import="Models.Entities.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Books</title>
</head>
<body>
    <h1>View Books</h1>
    <%
        List<Book> bookList = (List<Book>)request.getAttribute("BookList");
        if(bookList == null){
            response.sendRedirect("BookController?action=ViewBookList");
        }
        else if(bookList != null){
    %>
    <form action="CartController">
        Please, choose the books<br>
        <table border="1" style="width:300px">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Price</th>
                    <th colspan="2">Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (Book book : bookList) {
                %>
                <tr>
                    <td><%=(++count)%></td>
                    <td><%=book.getTitle()%></td>
                    <td><%=book.getUnitPrice()%></td>
                    <td style="text-align: center;">
                        <a href="CartController?action=Add&BookId=<%=book.getId()%>">Add To Cart</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%    
            if(request.getAttribute("Message") != null){
        %>
            <div style="color: red;"><%=request.getAttribute("Message")%></div>
        <%
            }
        %>
        <input type="submit" value="View Cart" name="action"/>
    </form>
    <% } %>
</body>
</html>
