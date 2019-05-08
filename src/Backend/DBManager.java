package Backend;

import java.sql.*;

public class DBManager {

    private DBManager(String username, String pass) {
        this.userName = username;
        this.password = pass;
        setConnection();
    }
    static private DBManager instance;

    private String url = "jdbc:mysql://localhost:3306/OrderOnlineProcessing?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";
    private String userName, password;
    private Connection connection;

    public static synchronized DBManager getInstance(String userName, String password) {
        if(instance == null) {
            instance = new DBManager(userName, password);
        }
        return instance;
    }

    private void setConnection () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
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
