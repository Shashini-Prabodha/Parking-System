import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerViewFormController implements Initializable {
    public TextField txtDate;
    public TextField txtTot;
    public TextField txtAtime;
    public TextField txtDtime;
    public TextField vNo;
    public TextField name;
    public JFXRadioButton rbtnCard;
    public JFXRadioButton rbtnCash;
    private static RadioButton methodButton;
    private static String  paymeth;
    public JFXButton btnOk;
    public JFXButton btnExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getALl();
    }

    private void getALl(){

        try {
            System.out.println(PaymentController.getCountOfPayID());
            Vehicle selectedVehicle = new CustomerLoginViewFormController().getSelectedVehicle();
            ParkingDetails park = ParkingDetailsController.searchVNo(selectedVehicle.getvNo());
            Payment payment = PaymentController.searchVNo(selectedVehicle.getvNo());


            if ((park != null) ) {
                System.out.println(selectedVehicle.getOwnerName());
                name.setText(selectedVehicle.getOwnerName());
                vNo.setText(selectedVehicle.getvNo());
                txtAtime.setText(park.getArrival());
                txtDtime.setText(park.getDeparture());
                txtDate.setText(park.getDate());
                txtTot.setText(PaymentController.getTotal(payment) + "");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void rbtn(ActionEvent actionEvent) {
        ToggleGroup group = new ToggleGroup();
        rbtnCash.setToggleGroup(group);
        rbtnCard.setToggleGroup(group);

        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (group.getSelectedToggle() != null) {
                methodButton = (RadioButton) group.getSelectedToggle();
                paymeth=methodButton.getText();
            }
        });
    }
    public static String getPayMethod(){

        return paymeth;
    }

    public void btnOk(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Vehicle selectedVehicle = new CustomerLoginViewFormController().getSelectedVehicle();
        //Payment paym = PaymentController.updtPaymentMethod(paymeth);

        Payment pay = PaymentController.searchVNo(selectedVehicle.getvNo());

        if(paymeth!=null && pay!=null){
            try {
                System.out.println(paymeth);
                boolean isAdded = PaymentController.updtPaymentMethod(paymeth,pay);
                if (isAdded) {
                    ImageIcon icon = new ImageIcon("src/image/happy.png");
                   // JOptionPane.showMessageDialog(null, "Added Success","",JOptionPane.INFORMATION_MESSAGE, icon);
                    JOptionPane.showMessageDialog(null, "Payment Method Added...","",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Added Fail","",JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.WARNING,ex.getMessage()).show();
            } catch (ClassNotFoundException ex) {
                new Alert(Alert.AlertType.WARNING,"Driver Not Found... ").show();
            }

        }else{
            new Alert(Alert.AlertType.WARNING,"Please Select Payment Method... ").show();
        }

    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
