import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ParkController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getType();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static int getType() throws SQLException, ClassNotFoundException {
        String type= AddVehicleForm.getType();
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select parkingID From parking where type=?");
        stm.setObject(1, type);
        ResultSet rst = stm.executeQuery();
        rst.next();
        int count = rst.getInt("parkingID") ;
        return count;
    }
}
