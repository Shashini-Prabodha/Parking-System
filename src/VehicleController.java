import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleController {
    static Vehicle searchVNo(String vNo) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select * From vehicle where vNo=?");
        stm.setObject(1, vNo);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            Vehicle vehicle=new Vehicle(rst.getString("vNo"), rst.getString("vType"), rst.getString("ownerName"));
            //ParkingDetails park=ParkingDetailsController.searchVNo(vNo);

            return vehicle;
        }
        return null;
    }
}
