import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddVehicleViewController implements Initializable {
    public static int typeId=0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public static boolean addVehicle(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        Connection connection = (Connection) DBConnector.getInstance().getConnection();
        PreparedStatement stm1 = connection.prepareStatement("Insert into vehicle Values(?,?,?)");

        stm1.setObject(1, vehicle.getvNo());
        stm1.setObject(2, vehicle.getvType());
        stm1.setObject(3, vehicle.getOwnerName());

        int res1 = stm1.executeUpdate();
        return res1>0 ;
    }





}
