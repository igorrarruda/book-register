<!DOCTYPE html>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View Books</title>
</head>

<body>

    <%@page import="br.com.eesdevop.model.*,java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <h1>Books List</h1>

    <% List<Book> list=BookDao.getAllRecords();  
        request.setAttribute("list",list); %>

    <table border="1" width="90%">
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Author</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${list}" var="book">
            <tr>
                <td>${book.getId()}</td>
                <td>${book.getTitle()}</td>
                <td>${book.getAuthor()}</td>
                <td><a href="editform.jsp?id=${book.getId()}">Edit</a></td>
                <td><a href="deletebook.jsp?id=${book.getId()}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br /><a href="addbookform.jsp">Add New Book</a>

</body>

</html>