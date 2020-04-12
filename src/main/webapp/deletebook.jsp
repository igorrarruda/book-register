<%@page import="br.com.eesdevop.model.BookDao"%>
<jsp:useBean id="book" class="br.com.eesdevop.model.Book"></jsp:useBean>
<jsp:setProperty property="*" name="book" />
<%  
BookDao.delete(book);  
response.sendRedirect("index.jsp");  
%>