package Backend;

import Backend.DBManager;

import java.sql.*;

public class ManagerActivities extends UsersActivities {
    private Connection connection;

    ManagerActivities() {
        DBManager dbManager = DBManager.getInstance("root", "root");
        connection = dbManager.getConnection();
    }

    void addNewBook(Book book) throws SQLException {
        String query = "Insert into Book values (" + book.getISBN() + ",'" + book.getTitle() + "','"
                + book.getPublicationYear() + "'," + book.getPrice()
                + "," + book.getThreshold() + "," + book.getNoOfCopies() + ",'" + book.getPublisherName() + "')";
        try {
            connection.setAutoCommit(false);

            Statement stat = connection.createStatement();
            stat.executeUpdate(query);
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
    } // fadl goz2 alaa

    void modifyBook(Book book) throws SQLException {
        String query = "UPDATE Book SET ISBN = "+ book.getISBN() + ", title = '" + book.getTitle()
                + "', publication_year = '" + book.getPublicationYear()
                + "', price = " + book.getPrice() + ", threshold = " + book.getThreshold()
                + ", copies = " + book.getNoOfCopies()
                + ", publisher_name = '" + book.getPublisherName()
                + "' WHERE ISBN = " + book.getISBN() + ";";
        try {
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            stat.executeUpdate(query);
            connection.commit();
        }catch (SQLException e) {
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

    void promoteUser(User user) throws SQLException {
        String query = "UPDATE User set is_manger = 1 where email = '" + user.getEmail() + "';";
        try {
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            stat.executeUpdate(query);
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
    } //test

    void viewSalesReport()  {
        //Jasber will be used
        Report report = new Report();
        report.totalSales();
    }

    void viewTop5Customers() {
        Report report = new Report();
        report.getTop5Customer();
    }

    void viewTop10BooksSold() {
        Report report = new Report();
        report.getTop10Books();
    }

    void placeOrder (String ISBN, int quantity) throws SQLException {
        try {
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query1 = "select * from book_orders where ISBN = "+ ISBN+ ";";
            ResultSet exists = stat.executeQuery(query1);
            connection.commit();
            String query2 = "";
            if(exists.next()){
                query2 = "update Order set quantity = quantity + "+String.valueOf(quantity)+" where isbn = "+ISBN;
            } else {
                query2 = "Insert into Order Values (" + ISBN + "," + String.valueOf(quantity) + ");";
            }

            int number = stat.executeUpdate(query2);
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
    }

    void confirmOrder(String ISBN) throws SQLException {
        try {
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query = "";

            query = "Delete from `Order` where ISBN = " + ISBN + ";";
            int number = stat.executeUpdate(query);
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

    }
}
