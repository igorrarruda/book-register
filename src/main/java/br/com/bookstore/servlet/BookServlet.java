package br.com.bookstore.servlet;

import br.com.bookstore.model.Book;
import br.com.bookstore.model.BookDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/bookstore")
public class BookServlet extends HttpServlet {

    private BookDao bookDao;

    @Override
    public void init() {
        String url = "jdbc:postgresql://projeto_postgres_1:5432/eesdevops";
        String username = "postgres";
        String password = "postgres";
        bookDao = new BookDao(url, username, password);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertBook(request, response);
                break;
            case "/delete":
                deleteBook(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateBook(request, response);
                break;
            default:
                listBook(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBook = bookDao.listAllBooks();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Book existingBook = bookDao.getBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);

    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");

        Book newBook = new Book(title, author);
        bookDao.insertBook(newBook);
        response.sendRedirect("list");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");

        Book book = new Book(id, title, author);
        bookDao.updateBook(book);
        response.sendRedirect("list");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));

        Book book = new Book(id);
        bookDao.deleteBook(book);
        response.sendRedirect("list");
    }
}