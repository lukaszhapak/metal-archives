package source.database;

import java.sql.*;

public class ConnectionHelper {

    private static Connection con;
    private static Statement statement;
    private static Connection connection = getDbConnection();

    static {
        TableHelper.createTables();
    }

    public static void executeUpdate(String sql) {
        try {
            System.out.println(sql);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;
        try {
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    private static Connection getDbConnection() {

        String connectionUrl = "jdbc:mysql://localhost:3306/" + Properties.DATA_BASE_NAME;

        try {
            con = DriverManager.getConnection(connectionUrl, Properties.USER_NAME, Properties.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
