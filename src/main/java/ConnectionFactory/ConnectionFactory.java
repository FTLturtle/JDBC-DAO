package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Connect to Database
 * @author hany.said
 */
public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String USER = "jdbcuser";
    public static final String PASS = "jdbcpassword";
    /**
     * Get a connection to database
     * @return Connection object
     */
    public static Connection getConnection()
    {
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(URL, USER, PASS);

            System.out.println("Connection Established to MYSQL Database");

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            e.printStackTrace();

        }
        return conn;
    }
    /**
     * Test Connection
     */
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }
}