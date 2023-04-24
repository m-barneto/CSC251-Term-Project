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
    		return DriverManager.getConnection("jdbc:sqlite:inventory.db");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
