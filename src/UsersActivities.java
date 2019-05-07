import java.util.List;
import java.sql.*;
import java.util.ArrayList;

class UsersActivities {

    private Connection connection;

    UsersActivities() {
        DBManager dbManager = DBManager.getInstance("", "");
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
    }

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
            String query = "Select * from BOOK where ISBN ='"+bookISBN+"';";
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
                publicationYear = rs.getNString("publication_year");
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
                publicationYear = rs.getNString("publication_year");
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
            String query = "Select * from BOOK NATURAL JOIN AUTHORS where author ='"+author_name+"';";
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
                publicationYear = rs.getNString("publication_year");
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
            String query = "Select * from BOOK NATURAL JOIN CATEGORIES where category ='"+category+"';";
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
                publicationYear = rs.getNString("publication_year");
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
            String query = "Select * from BOOK where publicationYear ='"+year+"';";
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
                publicationYear = rs.getNString("publication_year");
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

    public void viewBookPrice(String ISBN) throws SQLException {
        try{
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select price from BOOK where ISBN='"+ISBN+"';";
            ResultSet rs = stat.executeQuery(query);
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
            for (int i = 0; i < user.getCart().size(); i++){
                String query = "INSERT INTO ORDER VALUES ('" + user.getCart().get(i).getISBN()
                                + "', " + user.getCart().get(i).getQuantity() + ");";
                Statement stat = connection.createStatement();
                stat.executeUpdate(query);
                connection.commit();
            }
            user.clearCart();
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
    //done testing
    public User userSignIn(String email, String password) throws SQLException{
        try {
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "Select * from User where email='" + email +"' and passowrd='" + password+"';";
            ResultSet rs = stat.executeQuery(query);
            connection.commit();
            if (rs.next()) {
                System.out.println("Login Sucessfully...");
                User user = new User();
                user.setUserID(rs.getInt("user_id"));
                user.setManager((short)rs.getInt("is_manger"));
                user.setEmail(rs.getNString("email"));
                user.setFirstName(rs.getNString("first_name"));
                user.setLastName(rs.getNString("last_name"));
                user.setPassword(rs.getNString("passowrd"));
                user.setPhoneNumber(rs.getNString("phone_number"));
                user.setShippingAddress(rs.getNString("shipping_address"));
                user.setUserName(rs.getNString("user_name"));
                return user;
            } else {
                System.out.println("Incorrect username and password");
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
        //check email and password
        //log in and create new user
        return null;
    }

    public boolean userSignUp(User user) throws SQLException{
        String checkQuery = "SELECT email FROM USER WHERE email = '" + user.getEmail() + "';";
        try {
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(checkQuery);
            connection.commit();
            rs.last();
            if (rs.getRow() != 0) {
                //Email is used
                return false;
            } else {
                String insertQuery = "INSERT INTO USER VALUES (" +user.getUserID()+ ",'"+ user.getPassword()
                        + "', '" + user.getFirstName() + "', '" + user.getLastName()
                        + "', '" + user.getPhoneNumber() + "', '" + user.getShippingAddress()
                        + "', " + user.isManager() + ",'" + user.getEmail() + "','" +user.getUserName() +"');";
                stat.executeUpdate(insertQuery);
                connection.commit();
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
        return true;
    }
}
