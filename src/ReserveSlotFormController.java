import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReserveSlotFormController implements Initializable {

    public JFXButton btnGetSlot;
    public JFXTextField txtAtime;
    private static String Atime;
    public static String status;
    public JFXButton btnExit;


    public void btnGetSlot(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

            Vehicle v=VehicleNoCheckController.getSelectedVehicle();
            ParkingDetails park=ParkingDetailsController.searchVNo(v.getvNo());
        System.out.println(ParkingDetails.getDate());
            if (txtAtime.getText()!=null) {
                status = "IN";
                try {

                    boolean isAdded = ParkingDetailsController.updtArrival(txtAtime.getText(),park) ;
                    if (isAdded) {

                        ImageIcon icon = new ImageIcon("src/image/happy.png");
                        JOptionPane.showMessageDialog(null, "Added Success","",JOptionPane.INFORMATION_MESSAGE, icon);
                    }else{

                        Payment pay=new Payment(v.getvNo(),PaymentController.getTotal(),"cash");
                        System.out.println(pay.getvNo()+" "+pay.getTotal()+" "+pay.getPayment_method());
                        ParkingDetails p=new ParkingDetails(v.getvNo(),WelcomePageViewController.getDt(),ParkingDetailsController.getParkingID(v.getvNo()),
                                txtAtime.getText(),"00.00","IN");
                        try {
                            boolean isAdded2 = PaymentController.addDataPayment(pay) && ParkingDetailsController.addParkingDetails(p);

                            if (isAdded2) {
                                ImageIcon icon = new ImageIcon("src/image/happy.png");
                               // JOptionPane.showMessageDialog(null, "Added Success","",JOptionPane.INFORMATION_MESSAGE, icon);
                            }else{
                                JOptionPane.showMessageDialog(null, "Added Fail","",JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            new Alert(Alert.AlertType.WARNING,ex.getMessage()).show();
                        } catch (ClassNotFoundException ex) {
                            new Alert(Alert.AlertType.WARNING,"Driver Not Found... ").show();
                        }
                        //JOptionPane.showMessageDialog(null, "Added Fail","",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.WARNING,ex.getMessage()).show();
                } catch (ClassNotFoundException ex) {
                    new Alert(Alert.AlertType.WARNING,"Driver Not Found... ").show();
                }
                ImageIcon icon = new ImageIcon("src/image/happy.png");
                //new Alert(Alert.AlertType.INFORMATION,"Get Slot... ").show();

              //  new Alert(Alert.AlertType.INFORMATION, "Get Slot... ").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Please Enter Arrival Time... ").show();

            }

    }
    public static String getATime(){
        return Atime;
    }
    public void getDialog(){
        JOptionPane.showMessageDialog(null, "Please Enter Arrival Time... ", "", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       getDialog();
    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
