package carlot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static boolean VerifyDBClassLoaded() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    public static Connection getConnection() {
        try {
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CSC251", "scott", "tiger");
            return conn;
        } catch (SQLException e) {
            return null;
        }
    }
}
