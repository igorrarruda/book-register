package br.com.eesdevop.model;
import java.sql.*;  
import java.util.ArrayList;  
import java.util.List;  
import br.com.eesdevop.model.Book;


public class BookDao {  
  
    public static Connection getConnection(){  
        String url = "jdbc:postgresql://projeto_postgres_1:5432/eesdevops";
        String username = "postgres";
        String password = "postgres";

        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find PostgreSQL JDBC driver: " + e.getMessage());
        }
        return connection;
    }  

    public static int save(Book book){  
        int status=0;  
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement(  
    "insert into books(title,author) values(?,?)");  
            ps.setString(1,book.getTitle());  
            ps.setString(2,book.getAuthor());  
            status=ps.executeUpdate();  
        }catch(Exception e){System.out.println(e);}  
        return status;  
    }  

    public static int update(Book book){  
        int status=0;  
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement(  
    "update books set title=?,author=? where id=?");  
            ps.setString(1,book.getTitle());  
            ps.setString(2,book.getAuthor());  
            ps.setLong(3,book.getId());  
            status=ps.executeUpdate();  
        }catch(Exception e){System.out.println(e);}  
        return status;  
    }  

    public static int delete(Book book){  
        int status=0;  
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from books where id=?");  
            ps.setLong(1,book.getId());  
            status=ps.executeUpdate();  
        }catch(Exception e){System.out.println(e);}  
    
        return status;  
    }  

    public static List<Book> getAllRecords(){  
        List<Book> list=new ArrayList<Book>();  
        
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from books");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                list.add(new Book(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("author")
                ));  
            }  
        }catch(Exception e){System.out.println(e);}  
        return list;  
    }  

    public static Book getRecordById(long id){  
        Book book = null;  
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from books where id=?");  
            ps.setLong(1,id);  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                book = new Book(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("author")
                );  
            }  
        }catch(Exception e){System.out.println(e);}  
        return book;  
    }  
}  