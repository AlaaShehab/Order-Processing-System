package Backend;

import java.sql.*;

public class DBManager {

    private static String username, password;

    private DBManager() {
        setConnection();
    }
    static private DBManager instance;

    private String url = "jdbc:mysql://localhost:3306/OrderOnlineProcessing?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";
    private Connection connection;

    public static synchronized DBManager getInstance() {
        username = "root";
        password = "root";
        if(instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private void setConnection () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection () {
        return connection;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        return statement.getResultSet();
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
