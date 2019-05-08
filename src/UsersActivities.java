import com.sun.org.apache.regexp.internal.RE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

class UsersActivities {

    private Connection connection;

    UsersActivities() {
        DBManager dbManager = DBManager.getInstance("root", "root");
        connection = dbManager.getConnection();
    }

    public void editInfo(User user) throws SQLException {//send info as parameters
        String query = "UPDATE USER SET user_id = "+ user.getUserID() + ", passowrd = '" + user.getPassword()
                + "', first_name = '" + user.getFirstName() + "', last_name = '" + user.getLastName()
                + "', email = '" + user.getEmail() + "', phoneNumber = '" + user.getPhoneNumber()
                + ", shipping_address = " + user.getShippingAddress()
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
    } // test

    public void addBookToCart(User user, int ISBN, int quantity){
        // mmkn a-check el awl ezay kan el ISBN mwgod w mmkn n3ml assumption eno msh hyd5l wa7ed msh mwgod

        OrderItem order = new OrderItem();
        order.setISBN(ISBN);
        order.setQuantity(quantity);
        user.insertItem(order);
    }

    public void removeBookFromCart(User user, int ISBN, int quantity){
        // leh hna byb3t el quantity
        OrderItem order = new OrderItem();
        order.setISBN(ISBN);
        order.setQuantity(quantity);
        user.removeItem(order);
    }

    public List<Book> searchForBookByISBN(String bookISBN) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        try{
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select * from BOOK where ISBN ="+bookISBN+";";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold;
                String title, publisherName, publicationYear;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("copies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisher_name");
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                publicationYear = df.format(rs.getDate("publication_year"));
                price = rs.getDouble("price");
                Book book = new Book();
                book.setISBN(ISBN);
                book.setNoOfCopies(noOfCopies);
                book.setThreshold(threshold);
                book.setPrice(price);
                book.setPublicationYear(publicationYear);
                book.setTitle(title);
                book.setPublisherName(publisherName);
                books.add(book);
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
            String query = "Select * from BOOK where Title ='"+bookTitle+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold;
                String title, publisherName, publicationYear;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("copies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisher_name");
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                publicationYear = df.format(rs.getDate("publication_year"));
                price = rs.getDouble("price");
                Book book = new Book();
                book.setISBN(ISBN);
                book.setNoOfCopies(noOfCopies);
                book.setThreshold(threshold);
                book.setPrice(price);
                book.setPublicationYear(publicationYear);
                book.setTitle(title);
                book.setPublisherName(publisherName);
                books.add(book);
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
            String query = "Select * from BOOK NATURAL JOIN AUTHOR where author_name ='"+author_name+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold;
                String title, publisherName, publicationYear;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("copies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisher_name");
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                publicationYear = df.format(rs.getDate("publication_year"));
                price = rs.getDouble("price");
                Book book = new Book();
                book.setISBN(ISBN);
                book.setNoOfCopies(noOfCopies);
                book.setThreshold(threshold);
                book.setPrice(price);
                book.setPublicationYear(publicationYear);
                book.setTitle(title);
                book.setPublisherName(publisherName);
                books.add(book);
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
            String query = "Select * from BOOK NATURAL JOIN CATEGORY where category_name ='"+category+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold;
                String title, publisherName, publicationYear;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("copies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisher_name");
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                publicationYear = df.format(rs.getDate("publication_year"));
                price = rs.getDouble("price");
                Book book = new Book();
                book.setISBN(ISBN);
                book.setNoOfCopies(noOfCopies);
                book.setThreshold(threshold);
                book.setPrice(price);
                book.setPublicationYear(publicationYear);
                book.setTitle(title);
                book.setPublisherName(publisherName);
                books.add(book);
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
            String query = "Select * from BOOK where publication_year ='"+year+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold;
                String title, publisherName, publicationYear;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("copies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisher_name");
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                publicationYear = df.format(rs.getDate("publication_year"));
                price = rs.getDouble("price");
                Book book = new Book();
                book.setISBN(ISBN);
                book.setNoOfCopies(noOfCopies);
                book.setThreshold(threshold);
                book.setPrice(price);
                book.setPublicationYear(publicationYear);
                book.setTitle(title);
                book.setPublisherName(publisherName);
                books.add(book);
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

    public double viewBookPrice(String ISBN) throws SQLException {
        double price = 0.0;
        try{
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select price from BOOK where ISBN="+ISBN+";";
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

    public void viewTotalPricesInCart(List<OrderItem> cart) throws SQLException {
        Double price = 0.0;
        try{
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select SUM(price) from BOOK where ISBN IN (";
            for (int i = 0; i < cart.size(); i++) {
                query = query + "'" +cart.get(i).getISBN() + "'";
                if (i < cart.size() - 1) {
                    query +=",";
                }
            }
            query +=")";
            ResultSet rs = stat.executeQuery(query);
            price = rs.getDouble(1);
            //display price
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

    public void userCheckOut(User user) throws SQLException {
        //place order
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO Cunstomer_Order VALUES ( 0 ," + user.getUserID() +
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
                    String query_2 = "INSERT INTO Order_item VALUES (" + user.getCart().get(i).getISBN()
                            + ", " + user.getCart().get(i).getQuantity() + ", " + String.valueOf(order) +");";
                    Statement stat = connection.createStatement();
                    int success_2 =  stat.executeUpdate(query_2);
                    String updateQuery = "UPDATE BOOK SET copies = copies - " + user.getCart().get(i).getQuantity()
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

    public User userSignUp(User user) throws SQLException{
        String checkQuery = "SELECT email FROM USER WHERE email = '" + user.getEmail() + "';";
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
                String insertQuery = "INSERT INTO USER VALUES (" +user.getUserID()+ ",'"+ user.getPassword()
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
