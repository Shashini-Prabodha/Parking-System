import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleTypeController {
    static VehicleType searchVNo(String type) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select * From vehicleType where type=?");
        stm.setObject(1, type);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            VehicleType vehicleType=new VehicleType(rst.getString("type"), rst.getInt("slot"), rst.getInt("costOfHour"));
            //ParkingDetails park=ParkingDetailsController.searchVNo(vNo);

            return vehicleType;
        }
        return null;
    }

}
