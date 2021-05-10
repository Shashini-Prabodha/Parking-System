import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.lang.*;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PaymentViewFormController implements Initializable {
    private static String dtime;
    public JFXTextField txtcname;
    public JFXTextField txtVno;
    public JFXTextField txtAtime;
    public JFXTextField txtDtim;
    public JFXTextField txtTotP;
    public JFXTextField txtpaymethod;
    public JFXButton btnPC;
    public JFXButton btnSearch;
    public JFXButton btnAdd;
    public JFXButton btnExit;
    public static int pw;

    public void btnPC(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ParkingDetails park = ParkingDetailsController.searchVNo(txtVno.getText());
        boolean isAdded =  ParkingDetailsController.updtStatus(park,"EXIT");
        if (isAdded) {
            ImageIcon icon = new ImageIcon("src/image/happy.png");
            // JOptionPane.showMessageDialog(null, "Added Success","",JOptionPane.INFORMATION_MESSAGE, icon);
            JOptionPane.showMessageDialog(null, "Payment Success","",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Payment Fail","",JOptionPane.ERROR_MESSAGE);
        }
        Stage stage = (Stage) btnPC.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnSearch(ActionEvent actionEvent) {
        try {
            Vehicle veh = VehicleController.searchVNo(txtVno.getText());
            ParkingDetails park = ParkingDetailsController.searchVNo(txtVno.getText(),WelcomePageViewController.getDt());
            Payment pay = PaymentController.searchVNo(txtVno.getText());


            if ((park != null) && (pay != null) && (veh != null)) {
                txtcname.setText(veh.getOwnerName());
                txtAtime.setText(park.getArrival());

                int c=PaymentController.getCountOfPayID();
                new Alert(Alert.AlertType.INFORMATION,"Vehicle no : "+txtVno.getText()+"\nPassword is:"+c+"\nPlease Inform Customer for choose payment method").show();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnAdd(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ParkingDetails park = ParkingDetailsController.searchVNo(txtVno.getText(),WelcomePageViewController.getDt());
        Payment pay = PaymentController.searchVNo(txtVno.getText());

        String dd=txtDtim.getText();
       System.out.println(pay+" "+park);
        try {
            if ((park != null) && (pay!=null) ) {
                System.out.println(dd );
                boolean isAdded = ParkingDetailsController.updtDepature(park,dd);

                    getdatediff();
                    txtpaymethod.setText(pay.getPayment_method());
                    ImageIcon icon = new ImageIcon("src/image/happy.png");
                    JOptionPane.showMessageDialog(null, "Departure Time Added","",JOptionPane.INFORMATION_MESSAGE, icon);


                //txtTotP.setText(PaymentController.getTotal(veh) + "");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void getdatediff() throws SQLException, ClassNotFoundException, ParseException {
        ParkingDetails park = ParkingDetailsController.searchVNo(txtVno.getText(),WelcomePageViewController.getDt());
        Payment p=PaymentController.searchVNo(txtVno.getText());
        Vehicle veh = VehicleController.searchVNo(txtVno.getText());
        VehicleType v=VehicleTypeController.searchVNo(veh.getvType());

        String time1 = park.getArrival()+":00";
        String time2 = park.getDeparture()+":00";

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {

            Date d1 = sdf.parse(time1);
            Date d2 = sdf.parse(time2);
            long elapsed = d2.getTime() - d1.getTime();
            int hours = (int) Math.floor(elapsed / 3600000);
            double minutes = (double) Math.floor((elapsed - hours * 3600000) / 60000);
            System.out.println(elapsed+" "+minutes);
            double tot = 0;
            tot = (minutes)/60*v.getCostOfHour();
            System.out.println("getcosth"+v.getCostOfHour());
            txtTotP.setText(tot+"");
            String d=WelcomePageViewController.getDt();
            int c=PaymentController.getCountOfPayID();
            System.out.println("pid "+c+" .. tot"+tot);
            boolean isAdded = PaymentController.updtTotal(c,p,tot);
            System.out.println(isAdded );
            if (isAdded) {
                ImageIcon icon = new ImageIcon("src/image/happy.png");
                // JOptionPane.showMessageDialog(null, "Added Success","",JOptionPane.INFORMATION_MESSAGE, icon);
                JOptionPane.showMessageDialog(null, "Payment Method Added...","",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Added Fail","",JOptionPane.ERROR_MESSAGE);
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    public static int getPw() throws SQLException, ClassNotFoundException {
        int pw=PaymentController.getCountOfPayID();
        return pw;
    }
}
