import java.util.LinkedList;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

abstract class UsersActivities {

    private String url = "jdbc:mysql://localhost:3306/OrderOnlineProcessing";
    private Connection connection;

    UsersActivities () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "12345");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editInfo(User user) {//send info as parameters

    }

    public List<Book> searchForBookByISBN(String bookISBN) {
        List<Book> books = new ArrayList<Book>();
        try{
            Statement stat = connection.createStatement();
            String query = "Select * from BOOK where ISBN ='"+bookISBN+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold;
                String title, publisherName, publicationYear;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("noOfCopies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisherName");
                publicationYear = rs.getNString("publicationYear");
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
        } catch(Exception e){

        }
        return books;
    }

    public List<Book> searchForBookByTitle(String bookTitle) {
        List<Book> books = new ArrayList<Book>();
        try{
            Statement stat = connection.createStatement();
            String query = "Select * from BOOK where Title ='"+bookTitle+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold;
                String title, publisherName, publicationYear;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("noOfCopies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisherName");
                publicationYear = rs.getNString("publicationYear");
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
        } catch(Exception e){

        }
        return books;
    }

    public List<Book> searchForBookByAuthor(String author_name) {
        List<Book> books = new ArrayList<Book>();
        try{
            Statement stat = connection.createStatement();
            String query = "Select * from BOOK NATURAL JOIN AUTHORS where author ='"+author_name+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold;
                String title, publisherName, publicationYear;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("noOfCopies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisherName");
                publicationYear = rs.getNString("publicationYear");
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
        } catch(Exception e){

        }
        return books;
    }

    public List<Book> searchForBookByCategory(String category) {
        List<Book> books = new ArrayList<Book>();
        try{
            Statement stat = connection.createStatement();
            String query = "Select * from BOOK NATURAL JOIN CATEGORIES where category ='"+category+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold;
                String title, publisherName, publicationYear;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("noOfCopies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisherName");
                publicationYear = rs.getNString("publicationYear");
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
        } catch(Exception e){

        }
        return books;
    }

    public List<Book> searchForBookByear(String year) {
        List<Book> books = new ArrayList<Book>();
        try{
            Statement stat = connection.createStatement();
            String query = "Select * from BOOK where publicationYear ='"+year+"';";
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int ISBN, noOfCopies, threshold;
                String title, publisherName, publicationYear;
                double price;
                ISBN = rs.getInt("ISBN");
                noOfCopies = rs.getInt("noOfCopies");
                threshold = rs.getInt("threshold");
                title = rs.getNString("title");
                publisherName = rs.getNString("publisherName");
                publicationYear = rs.getNString("publicationYear");
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
        } catch(Exception e){

        }
        return books;
    }

    public void viewBookPrice(String ISBN) {
        try{
            Statement stat = connection.createStatement();
            String query = "Select price from BOOK where ISBN='"+ISBN+"';";
            ResultSet rs = stat.executeQuery(query);
            //display price

        } catch(Exception e){

        }
    }

    public void viewTotalPricesInCart(List<OrderItem> cart) {
        Double price = 0.0;
        try{
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
        } catch(Exception e){

        }
    }

    public void userCheckOut(User user) {
        //place order
        user.clearCart();
    }

    public void userLogOut(User user){
        user.clearCart();
    }

    public User userSignIn(String email, String password){
        try {
            Statement stat = connection.createStatement();
            String query = "Select * from Users where email='" + email +"' and password='" + password+"';";
            ResultSet rs = stat.executeQuery(query);
            if (rs.next()) {
                System.out.println("Login Sucessfully...");
                return new User();
            } else {
                System.out.println("Incorrect username and password");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //check email and password
        //log in and create new user
        return null;
    }

    public User userSignUp(String email, String password){
        return null;
    }
    abstract void addNewBook(Book book);
    abstract void modifyBook(Book book); //send modified parameters
    abstract void placeOrder(Book book, int quantity); //mlhash lazma
    abstract void promoteUser(User user);
    abstract void viewSalesReport(User user);



}
