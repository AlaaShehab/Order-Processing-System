package Backend;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

public class UsersActivities {

    private Connection connection;

    public UsersActivities() {
        DBManager dbManager = DBManager.getInstance();
        connection = dbManager.getConnection();
    }
//Done testing
    public void editInfo(User user) throws SQLException {//send info as parameters
        // TODO select before update
        String query = "UPDATE User SET username = '"+ user.getUserName() + "', passowrd = '" + user.getPassword()
                + "', first_name = '" + user.getFirstName() + "', last_name = '" + user.getLastName()
                + "', email = '" + user.getEmail() + "', phone_number = '" + user.getPhoneNumber()
                + "', shipping_address = '" + user.getShippingAddress()
                + "' WHERE user_id = " + user.getUserID() + ";";
        try {
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            stat.executeUpdate(query);
            connection.commit();
        }  catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            connection.setAutoCommit(true);

        }
    }
//Done testing
    public List<Book> searchForBookByISBN(String bookISBN) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        try{
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select * from Book where ISBN ="+bookISBN+";";
            ResultSet rs = stat.executeQuery(query);

            while (rs.next()) {
                int ISBN, noOfCopies, threshold, publicationYear;
                String title, publisherName;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("copies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisher_name");
                publicationYear = rs.getInt("publication_year");
                price = rs.getDouble("price");
                Book book = new Book();
                book.setISBN(ISBN);
                book.setNoOfCopies(noOfCopies);
                book.setThreshold(threshold);
                book.setPrice(price);
                book.setPublicationYear(String.valueOf(publicationYear));
                book.setTitle(title);
                book.setPublisherName(publisherName);
                books.add(book);
            }
            for (Book book : books) {
                String query2 = "Select distinct  author_name from Author where ISBN =" + book.getISBN() + ";";
                ResultSet rs2 = stat.executeQuery(query2);
                while (rs2.next()) {
                    book.addAuthor(rs2.getString(1));
                }
                String query3 = "Select distinct  Category_Name from Category where ISBN =" + book.getISBN() + ";";
                ResultSet rs3 = stat.executeQuery(query3);
                while (rs3.next()) {
                    book.addCategory(rs3.getString(1));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            connection.setAutoCommit(true);

        }
        return books;
    }

    public List<Book> searchForBookByTitle(String bookTitle) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        try{
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select * from Book where Title ='"+bookTitle+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold, publicationYear;
                String title, publisherName;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("copies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisher_name");
                publicationYear = rs.getInt("publication_year");
                price = rs.getDouble("price");
                Book book = new Book();
                book.setISBN(ISBN);
                book.setNoOfCopies(noOfCopies);
                book.setThreshold(threshold);
                book.setPrice(price);
                book.setPublicationYear(String.valueOf(publicationYear));
                book.setTitle(title);
                book.setPublisherName(publisherName);
                books.add(book);
            }
            for (Book book : books) {
                String query2 = "Select distinct  author_name from Author where ISBN =" + book.getISBN() + ";";
                ResultSet rs2 = stat.executeQuery(query2);
                while (rs2.next()) {
                    book.addAuthor(rs2.getString(1));
                }
                String query3 = "Select distinct  Category_Name from Category where ISBN =" + book.getISBN() + ";";
                ResultSet rs3 = stat.executeQuery(query3);
                while (rs3.next()) {
                    book.addCategory(rs3.getString(1));
                }
            }
            connection.commit();
        }  catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            connection.setAutoCommit(true);

        }
        return books;
    }

    public List<Book> searchForBookByAuthor(String author_name) throws SQLException{
        List<Book> books = new ArrayList<Book>();
        try{
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select * from Book NATURAL JOIN Author where author_name ='"+author_name+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold, publicationYear;
                String title, publisherName;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("copies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisher_name");
                publicationYear = rs.getInt("publication_year");
                price = rs.getDouble("price");
                Book book = new Book();
                book.setISBN(ISBN);
                book.setNoOfCopies(noOfCopies);
                book.setThreshold(threshold);
                book.setPrice(price);
                book.setPublicationYear(String.valueOf(publicationYear));
                book.setTitle(title);
                book.setPublisherName(publisherName);
                books.add(book);
            }
            for (Book book : books) {
                String query2 = "Select distinct  author_name from Author where ISBN =" + book.getISBN() + ";";
                ResultSet rs2 = stat.executeQuery(query2);
                while (rs2.next()) {
                    book.addAuthor(rs2.getString(1));
                }
                String query3 = "Select distinct  Category_Name from Category where ISBN =" + book.getISBN() + ";";
                ResultSet rs3 = stat.executeQuery(query3);
                while (rs3.next()) {
                    book.addCategory(rs3.getString(1));
                }
            }
            connection.commit();
        }  catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            connection.setAutoCommit(true);

        }
        return books;
    }

    public List<Book> searchForBookByCategory(String category) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        try{
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select * from Book NATURAL JOIN Category where category_name ='"+category+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold, publicationYear;
                String title, publisherName;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("copies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisher_name");
                publicationYear = rs.getInt("publication_year");
                price = rs.getDouble("price");
                Book book = new Book();
                book.setISBN(ISBN);
                book.setNoOfCopies(noOfCopies);
                book.setThreshold(threshold);
                book.setPrice(price);
                book.setPublicationYear(String.valueOf(publicationYear));
                book.setTitle(title);
                book.setPublisherName(publisherName);
                books.add(book);
            }
            for (Book book : books) {
                String query2 = "Select distinct  author_name from Author where ISBN =" + book.getISBN() + ";";
                ResultSet rs2 = stat.executeQuery(query2);
                while (rs2.next()) {
                    book.addAuthor(rs2.getString(1));
                }
                String query3 = "Select distinct  Category_Name from Category where ISBN =" + book.getISBN() + ";";
                ResultSet rs3 = stat.executeQuery(query3);
                while (rs3.next()) {
                    book.addCategory(rs3.getString(1));
                }
            }
            connection.commit();
        }  catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            connection.setAutoCommit(true);

        }
        return books;
    }

    public List<Book> searchForBookByear(String year) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        try{
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select * from Book where publication_year ="+year+";";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold, publicationYear;
                String title, publisherName;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("copies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisher_name");
                publicationYear = rs.getInt("publication_year");
                price = rs.getDouble("price");
                Book book = new Book();
                book.setISBN(ISBN);
                book.setNoOfCopies(noOfCopies);
                book.setThreshold(threshold);
                book.setPrice(price);
                book.setPublicationYear(String.valueOf(publicationYear));
                book.setTitle(title);
                book.setPublisherName(publisherName);
                books.add(book);
            }
            for (Book book : books) {
                String query2 = "Select distinct  author_name from Author where ISBN =" + book.getISBN() + ";";
                ResultSet rs2 = stat.executeQuery(query2);
                while (rs2.next()) {
                    book.addAuthor(rs2.getString(1));
                }
                String query3 = "Select distinct  Category_Name from Category where ISBN =" + book.getISBN() + ";";
                ResultSet rs3 = stat.executeQuery(query3);
                while (rs3.next()) {
                    book.addCategory(rs3.getString(1));
                }
            }
            connection.commit();
        }  catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            connection.setAutoCommit(true);

        }
        return books;
    }
//Done testing
    public double viewBookPrice(String ISBN) throws SQLException {
        double price = 0.0;
        try{
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select price from Book where ISBN="+ISBN+";";
            ResultSet rs = stat.executeQuery(query);
            if (rs.next()){
                price =  rs.getDouble("price");
            } else {
                System.out.print("Book not found");
            }
            connection.commit();

        }  catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            connection.setAutoCommit(true);

        }
        return price;
    }
//Done testing
    public double viewTotalPricesInCart(List<Book> cart) throws SQLException {
        if(cart.isEmpty())
            return 0.0;
        Double price = 0.0;
        try{
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select price from Book where ISBN IN (";
            for (int i = 0; i < cart.size(); i++) {
                query = query + cart.get(i).getISBN() ;
                if (i < cart.size() - 1) {
                    query +=",";
                }
            }
            query +=")";
            ResultSet rs = stat.executeQuery(query);
            int i = 0;
            Collections.sort(cart, new Comparator<Book>() {

                public int compare(Book b1, Book b2) {

                    //ascending order
                    int startComparison = compare(b1.getISBN(), b2.getISBN());
                    return startComparison != 0 ? startComparison: compare(b1.getISBN(), b2.getISBN());
                    //descending order
                    //return fruitName2.compareTo(fruitName1);
                }
                private  int compare(int a, int b) {
                    return a < b ? -1
                            : a > b ? 1
                            : 0;
                }


            });
            while (rs.next() & i < cart.size()) {
                price += (rs.getDouble(1) * cart.get(i).getNoOfCopies());
                i++;
                //display price
            }
            connection.commit();
        }  catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            connection.setAutoCommit(true);

        }
        return price;
    }
//Done testing
    public void userCheckOut(User user) throws SQLException {
        //place order
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO CUNSTOMER_ORDER VALUES ( 0 ," + user.getUserID() +
                    ", '" +new java.sql.Date(Calendar.getInstance().getTime().getTime()) + "' );";
            Statement stat_1 = connection.createStatement();
            int order = 0;
            int success =  stat_1.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stat_1.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("Auto Generated Primary Key " + rs.getInt(1));
                order = rs.getInt(1);
            }
            if(success > 0) {

                for (int i = 0; i < user.getCart().size(); i++) {
                    String query_2 = "INSERT INTO ORDER_ITEM VALUES (" + user.getCart().get(i).getISBN()
                            + ", " + user.getCart().get(i).getNoOfCopies() + ", " + String.valueOf(order) +");";
                    Statement stat = connection.createStatement();
                    int success_2 =  stat.executeUpdate(query_2);
                    String updateQuery = "UPDATE Book SET copies = copies - " + user.getCart().get(i).getNoOfCopies()
                            + " Where ISBN = " + user.getCart().get(i).getISBN() + ";";
                    Statement stat_3 = connection.createStatement();
                    stat_3.executeUpdate(updateQuery);
                }
                connection.commit();
                user.clearCart();
            }
        }  catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            connection.setAutoCommit(true);

        }
    }

    public void userLogOut(User user){
        user.clearCart();
    }
//Done testing
    public User userSignIn(String email, String password) throws SQLException{
        User user = new User();
        try {
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select * from User where email='" + email +"' and passowrd='" + password+"';";
            ResultSet rs = stat.executeQuery(query);
            connection.commit();
            if (rs.next()) {
                System.out.println("Login Sucessfully...");
                user.setUserID(rs.getInt("user_id"));
                user.setManager((short)rs.getInt("is_manger"));
                user.setEmail(rs.getNString("email"));
                user.setFirstName(rs.getNString("first_name"));
                user.setLastName(rs.getNString("last_name"));
                user.setPassword(rs.getNString("passowrd"));
                user.setPhoneNumber(rs.getNString("phone_number"));
                user.setShippingAddress(rs.getNString("shipping_address"));
                user.setUserName(rs.getNString("user_name"));
            } else {
                System.out.println("Incorrect username and password");
                user = null;
            }
        }  catch (SQLException e) {
            e.printStackTrace();
            user = null;
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException ex) {

                    ex.printStackTrace();
                }
            }
        } finally {
            connection.setAutoCommit(true);

        }
        //check email and password
        //log in and create new user
        return user;
    }
//Done testing
    public User userSignUp(User user) throws SQLException{
        String checkQuery = "SELECT email FROM User WHERE email = '" + user.getEmail() + "';";
        try {
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(checkQuery);
            connection.commit();
            rs.last();
            if (rs.getRow() != 0) {
                //Email is used
                user = null;
            } else {
                String insertQuery = "INSERT INTO User VALUES (" +user.getUserID()+ ",'"+ user.getPassword()
                        + "', '" + user.getFirstName() + "', '" + user.getLastName()
                        + "', '" + user.getPhoneNumber() + "', '" + user.getShippingAddress()
                        + "', " + user.isManager() + ",'" + user.getEmail() + "','" +user.getUserName() +"');";
                stat.executeUpdate(insertQuery);
                connection.commit();
            }
        }  catch (SQLException e) {
            user = null;
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            connection.setAutoCommit(true);

        }
        return user;
    }

}