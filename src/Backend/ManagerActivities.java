package Backend;

import Backend.DBManager;

import java.sql.*;

public class ManagerActivities extends UsersActivities {
    private Connection connection;

    public ManagerActivities() {
        DBManager dbManager = DBManager.getInstance();
        connection = dbManager.getConnection();
    }
//Done testing
public void addNewBook(Book book) throws SQLException {
    // TODO fadl part el publisher
    String query = "Insert into Book values (" + book.getISBN() + ",'" + book.getTitle() + "','"
            + book.getPublicationYear() + "'," + book.getPrice()
            + "," + book.getThreshold() + "," + book.getNoOfCopies() + ",'" + book.getPublisherName() + "')";
    try {
        connection.setAutoCommit(false);
        Statement stat = connection.createStatement();
        Statement stat1 = connection.createStatement();
        Statement stat2 = connection.createStatement();
        stat.executeUpdate(query);
        for (int i = 0; i < book.getAuthors().size(); i++) {
            String author_query = "Insert into Author values ('" + book.getAuthors().get(i) +"', " + book.getISBN() + ");";
            stat1.executeUpdate(author_query);
        }
        for (int i = 0; i < book.getCategories().size(); i++) {
            String category_query = "Insert into Category values ('" + book.getCategories().get(i) +
                    "', " + book.getISBN() + ");";
            stat2.executeUpdate(category_query);
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
}

    public void modifyBook(Book book) throws SQLException {
        String query = "UPDATE Book SET title = '" + book.getTitle()
                + "', publication_year = '" + book.getPublicationYear()
                + "', price = " + book.getPrice() + ", threshold = " + book.getThreshold()
                + ", copies = " + book.getNoOfCopies()
                + ", publisher_name = '" + book.getPublisherName()
                + "' WHERE ISBN = " + book.getISBN() + ";";
        String deleteCategory = "Delete From Category where ISBN = " + book.getISBN() + ";";
        String deleteAuthors = "Delete From Author where ISBN = " + book.getISBN() + ";";
        try {
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            Statement stat1 = connection.createStatement();
            Statement stat2 = connection.createStatement();
            Statement stat3 = connection.createStatement();
            Statement stat4 = connection.createStatement();
            stat.executeUpdate(query);
            stat1.executeUpdate(deleteCategory);
            stat2.executeUpdate(deleteAuthors);
            for (int i = 0; i < book.getAuthors().size(); i++) {
                String author_query = "Insert into Author values ('" + book.getAuthors().get(i) +"', " + book.getISBN() + ");";
                stat3.executeUpdate(author_query);
            }
            for (int i = 0; i < book.getCategories().size(); i++) {
                String category_query = "Insert into Category values ('" + book.getCategories().get(i) +
                        "', " + book.getISBN() + ");";
                stat4.executeUpdate(category_query);
            }
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
    }
//Done testing
    public void promoteUser(String email) throws SQLException {
        String query = "UPDATE User set is_manger = 1 where email = '" + email + "';";
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
//Done testing
    public void viewSalesReport()  {
        //Jasber will be used
        Report report = new Report();
        report.totalSales();
    }
//Done Testing
    public void viewTop5Customers() {
        Report report = new Report();
        report.getTop5Customer();
    }
//Done testing
    public void viewTop10BooksSold() {
        Report report = new Report();
        report.getTop10Books();
    }
//Done testing
    public void placeOrder (String ISBN, int quantity) throws SQLException {
        try {
            connection.setAutoCommit(false);
            Statement stat = connection.createStatement();
            String query1 = "select * from `Order` where ISBN = "+ ISBN + ";";
            ResultSet exists = stat.executeQuery(query1);
            connection.commit();
            String query2 = "";
            if(exists.next()){
                query2 = "update Order set quantity = quantity + "+String.valueOf(quantity)+" where ISBN = "+ISBN;
            } else {
                query2 = "Insert into `Order` Values (0," + ISBN + "," + String.valueOf(quantity) + ");";
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
//Done testing
    public void confirmOrder(String ISBN) throws SQLException {
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
