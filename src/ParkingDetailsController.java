import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ParkingDetailsController {

    static ParkingDetails searchVNo(String vNo,String date) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select * From parkingDetails where vNo=? and date=?");
        stm.setObject(1, vNo);
        stm.setObject(2,date);
        ResultSet rst = stm.executeQuery();

        //System.out.println(park.getvNo()+""+park.getDate()+" "+park.getParkingID()+" "+park.getArrival()+" "+park.getDeparture()+" "+park.getStatus());
        if (rst.next()) {

            return new ParkingDetails(rst.getString("vNo"),
                    rst.getString("date"),
                    rst.getInt("parkingID"),
                    rst.getString("arrival"),
                    rst.getString("departure"),
                    rst.getString("status")
            );
        }

        return null;
    }
    static ParkingDetails searchVNo(String vNo) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select * From parkingDetails where vNo=? ");
        stm.setObject(1, vNo);
        ResultSet rst = stm.executeQuery();

        //System.out.println(park.getvNo()+""+park.getDate()+" "+park.getParkingID()+" "+park.getArrival()+" "+park.getDeparture()+" "+park.getStatus());
        if (rst.next()) {

            return new ParkingDetails(rst.getString("vNo"),
                    rst.getString("date"),
                    rst.getInt("parkingID"),
                    rst.getString("arrival"),
                    rst.getString("departure"),
                    rst.getString("status")
            );
        }

        return null;
    }

    static int searchVNo(Vehicle v) throws Exception {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select parkingID as count From parkingDetails WHERE vNo=?");
        stm.setObject(1, v.getvNo());
        ResultSet rst = stm.executeQuery();
        rst.next();
        int count = rst.getInt("count") ;

        return count;
    }
    static List<ParkingDetails> searchVNo(String ar,Vehicle v) throws Exception {
        List ll = new LinkedList();
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select * From parkingDetails where arrival=? and vNo=? ");
        stm.setObject(1, ar);
        stm.setObject(2, v.getvNo());
        ResultSet rst = stm.executeQuery();

        //System.out.println(park.getvNo()+""+park.getDate()+" "+park.getParkingID()+" "+park.getArrival()+" "+park.getDeparture()+" "+park.getStatus());



            while (rst.next()) {
                int Key = rst.getInt(1);
                ObservableList row = FXCollections.observableArrayList();

                for (int i = 1; i <= rst.getMetaData().getColumnCount(); i++) {
                    row.add(rst.getString(i));
                    System.out.println(row);
                }
                ParkingDetails park = new ParkingDetails();
                park.setvNo(Key+"");

                ll.add(row);


        }return ll;
    }

    public static boolean addParkingDetails(ParkingDetails park) throws SQLException, ClassNotFoundException {
        Connection connection = (Connection) DBConnector.getInstance().getConnection();
        PreparedStatement stm1 = connection.prepareStatement("Insert into parkingDetails Values(?,?,?,?,?,?)");

        stm1.setObject(1, park.getvNo());
        stm1.setObject(2, WelcomePageViewController.getDt());
        stm1.setObject(3, park.getParkingID());
        stm1.setObject(4, park.getArrival());
        stm1.setObject(5, park.getDeparture());
        stm1.setObject(6, park.getStatus());
        System.out.println(park.getvNo() + "" + park.getDate() + " " + park.getParkingID() + " " + park.getArrival() + " " + park.getDeparture() + " " + park.getStatus());
        int res1 = stm1.executeUpdate();
        return res1 > 0;
    }

    public static boolean updtStatus(ParkingDetails p, String sts) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Update parkingDetails SET status=? WHERE status=? and vNo=?");
        stm.setObject(1, sts);
        stm.setObject(2, p.getStatus());
        stm.setObject(3, p.getvNo());

        int res1 = stm.executeUpdate();
        return res1 > 0;
    }
    public static boolean updtArrival(String arr,ParkingDetails park) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Update parkingDetails SET arrival=? WHERE arrival=? and vNo=? and date=?");
        stm.setObject(1, arr);
        stm.setObject(2, "00.00");
        stm.setObject(3, park.getvNo());
        stm.setObject(4, WelcomePageViewController.getDt());


        int res1 = stm.executeUpdate();
        return res1 > 0;
    }
    public static boolean updtDepature(ParkingDetails p,String dep) throws SQLException, ClassNotFoundException {
        System.out.println(p.getvNo());
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Update parkingDetails SET departure=? WHERE departure=? and vNo=? ");
        stm.setObject(1, dep);
        stm.setObject(2, "00.00");
        stm.setObject(3,p.getvNo());

        int res1 = stm.executeUpdate();
        return res1 > 0;
    }
    public static int getParkingID(String vno) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select parkingID as pid From parkingDetails where vNo=?");
        stm.setObject(1, vno);
        ResultSet rst = stm.executeQuery();
        rst.next();
        int id = rst.getInt("pid") ;
        return id;
    }


}