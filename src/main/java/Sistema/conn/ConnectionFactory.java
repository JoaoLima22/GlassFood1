package Sistema.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConecction() throws SQLException {
        String url="jdbc:mysql://localhost:3306/glass_food";
        String username="root";
        String password="";

        return DriverManager.getConnection(url, username, password);
    }
}
