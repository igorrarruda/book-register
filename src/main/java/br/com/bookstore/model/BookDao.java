package br.com.bookstore.model;

import java.sql.*;  
import java.util.ArrayList;  
import java.util.List;  
import br.com.bookstore.model.Book;

public class BookDao {  
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public BookDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, author) VALUES (?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Book> listAllBooks() throws SQLException {
        List<Book> listBook = new ArrayList<>();
         
        String sql = "SELECT * FROM book";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
             
            Book book = new Book(id, title, author);
            listBook.add(book);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listBook;
    }
     
    public boolean deleteBook(Book book) throws SQLException {
        String sql = "DELETE FROM book where id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setLong(1, book.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author = ? WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setLong(3, book.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Book getBook(long id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setLong(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
             
            book = new Book(id, title, author);
        }
         
        resultSet.close();
        statement.close();
         
        return book;
    }
}  