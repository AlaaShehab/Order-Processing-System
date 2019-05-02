import java.util.LinkedList;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ManagerActivities extends UsersActivities{

    private String url = "jdbc:mysql://localhost:3306/OrderOnlineProcessing";
    private Connection connection;

    ManagerActivities () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "12345");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void addNewBook(Book book) {
        String query = "Insert into BOOK values (" + book.getISBN() + "," + book.getTitle() + ","
                + book.getPublisherName() + "," + book.getPublicationYear() + "," + book.getPrice()
                + "," + book.getNoOfCopies() + "," + book.getThreshold() + ")";
        try {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void modifyBook(Book book) {

    }

    @Override
    void placeOrder(Book book, int quantity) {

    }

    @Override
    void promoteUser(User user) {
        String query = "UPDATE user set isManager = true where email = '" + user.getEmail() + "';";
        try {
            Statement stat = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void viewSalesReport(User user) {
        //Jasber will be used
    }
}
