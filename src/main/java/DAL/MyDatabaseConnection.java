package DAL;

import static com.mysql.cj.protocol.x.XProtocolDecoder.instance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabaseConnection {
    public static Connection connectDB() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/school";
            String username = "root";
            String password = "";

            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        

        return connection;
    }

   
    
    
    

}
