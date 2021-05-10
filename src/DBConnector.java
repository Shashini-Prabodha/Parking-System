import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static DBConnector dBConnector;
    private Connection connection;

    private DBConnector() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/parkingManagementSystem", "root", "1023");
    }

    public Connection getConnection(){
        return connection;
    }
    public static DBConnector getInstance() throws ClassNotFoundException, SQLException{
        if (dBConnector==null){
            dBConnector=new DBConnector();
        }
        return dBConnector;
    }
}
