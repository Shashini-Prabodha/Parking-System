import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentController {
    private static double tot;
    private static int count;

    static Payment searchVNo(String vNo) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select * From payment where vNo=?");
        stm.setObject(1, vNo);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            Payment paymnt=new Payment(rst.getString("vNo"), rst.getInt("payID"), rst.getDouble("total"),rst.getString("payment_method"));
            //ParkingDetails park=ParkingDetailsController.searchVNo(vNo);

            return paymnt;
        }
        return null;
    }
    public static double getTotal(Payment p) throws SQLException, ClassNotFoundException {

            Connection connection = (Connection) DBConnector.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("Select total as tot from payment where vNo=?");
            stm.setObject(1, p.getvNo());
            ResultSet rst = stm.executeQuery();
            rst.next();
            double t = rst.getDouble("tot") ;
            System.out.println(t);

            return t;

    }
    public static double getTotal(){
        return tot;
    }

    public static int getCountOfPayID() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select count(payID) AS countPayId From payment");
        ResultSet rst = stm.executeQuery();
        rst.next();
        int count = rst.getInt("countPayId") ;

        return count;
    }

    public static boolean addDataPayment(Payment payment) throws SQLException, ClassNotFoundException {
        Connection connection = (Connection) DBConnector.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Insert into payment (vNo,total,payment_method)Values(?,?,?)");

        stm.setObject(1, payment.getvNo());
        stm.setObject(2, payment.getTotal());
        stm.setObject(3, payment.getPayment_method());

        System.out.println(payment.getvNo()+" "+payment.getTotal()+" "+payment.getPayment_method());

        int res1 = stm.executeUpdate();

        return res1>0 ;
    }
    public static boolean updtPaymentMethod(String paymethod,Payment pay) throws SQLException, ClassNotFoundException {
        Connection connection = (Connection) DBConnector.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update payment SET payment_method=? WHERE payment_method=? and vNo=?");

        stm.setObject(1, paymethod);
        stm.setObject(2, pay.getPayment_method());
        stm.setObject(3,pay.getvNo());
        int res1 = stm.executeUpdate();

        return res1>0 ;
    }
    public static boolean updtTotal(int pid,Payment p,double tot) throws SQLException, ClassNotFoundException {
        Connection connection = (Connection) DBConnector.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update payment SET total=? WHERE total=? and payID=? and vNo=?");
        System.out.println("pp"+p.getTotal());
        stm.setObject(1, tot);
        stm.setObject(2, "0.0");
        stm.setObject(3, pid);
        stm.setObject(4, p.getvNo());
        int res1 = stm.executeUpdate();

        return res1>0 ;
    }
    public static int getNoSlotCar() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select count(status) AS carslot From parkingDetails WHERE status='IN' and parkingID=1");
        ResultSet rst = stm.executeQuery();
        rst.next();
        int count = rst.getInt("carslot") ;

        return count;
    }
    public static int getNoSlotVan() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select count(status) AS vanslot From parkingDetails WHERE status='IN' and parkingID=2  ");
        ResultSet rst = stm.executeQuery();
        rst.next();
        int count = rst.getInt("vanslot") ;

        return count;
    }
    public static int getNoSlotBus() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select count(status) AS busslot From parkingDetails WHERE status='IN' and parkingID=3");
        ResultSet rst = stm.executeQuery();
        rst.next();
        int count = rst.getInt("busslot") ;

        return count;
    }
    public static int getNoSlotLorry() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnector.getInstance().getConnection().prepareStatement("Select count(status) AS lorryslot From parkingDetails WHERE status='IN' and parkingID=4");
        ResultSet rst = stm.executeQuery();
        rst.next();
        int count = rst.getInt("lorryslot") ;

        return count;
    }

}
