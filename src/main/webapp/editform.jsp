<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Form</title>
</head>

<body>
    <%@page import="br.com.eesdevop.model.*"%>

    <%  
String id=request.getParameter("id");  
Book book=BookDao.getRecordById(Long.parseLong(id));  
%>

    <h1>Edit Form</h1>
    <form action="editbook.jsp" method="post">
        <input type="hidden" name="id" value="<%=book.getId() %>" />
        <table>
            <tr>
                <td>Title:</td>
                <td>
                    <input type="text" name="title" value="<%= book.getTitle()%>" /></td>
            </tr>
            <tr>
                <td>Author:</td>
                <td>
                    <input type="text" name="author" value="<%= book.getAuthor()%>" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Edit Book" /></td>
            </tr>
        </table>
    </form>

</body>

</html>