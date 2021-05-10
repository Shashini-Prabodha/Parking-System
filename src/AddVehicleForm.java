import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.glass.ui.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddVehicleForm implements Initializable{
    public JFXTextField txtcname;
    public JFXTextField txtVno;
    public JFXButton btnSave;
    public JFXButton btnExit;
    public JFXRadioButton rbtnCar;
    public JFXRadioButton rbtnVan;
    public JFXRadioButton rbtnBus;
    public JFXRadioButton rbtnLorry;
    private static String type;
    RadioButton button;
    private static String Vtype;

    public void btnSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Vehicle vehicle=new Vehicle(txtVno.getText(),button.getText(),txtcname.getText());
        Payment pay=new Payment(txtVno.getText(),PaymentController.getTotal(),"cash");
        ParkingDetails p=new ParkingDetails(txtVno.getText(),WelcomePageViewController.getDt(),ParkController.getType(),
                "00.00","00.00","IN");

        try {
            boolean isAdded = AddVehicleViewController.addVehicle(vehicle) && PaymentController.addDataPayment(pay) && ParkingDetailsController.addParkingDetails(p);
            if (isAdded) {
                ImageIcon icon = new ImageIcon("src/image/happy.png");
                JOptionPane.showMessageDialog(null, "Added Success","",JOptionPane.INFORMATION_MESSAGE, icon);
            }else{
                JOptionPane.showMessageDialog(null, "Added Fail","",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.WARNING,ex.getMessage()).show();
        } catch (ClassNotFoundException ex) {
            new Alert(Alert.AlertType.WARNING,"Driver Not Found... ").show();
        }
    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public void rbtn(ActionEvent actionEvent) {
        ToggleGroup group = new ToggleGroup();
        rbtnLorry.setToggleGroup(group);
        rbtnVan.setToggleGroup(group);
        rbtnCar.setToggleGroup(group);
        rbtnBus.setToggleGroup(group);

        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (group.getSelectedToggle() != null) {
                button = (RadioButton) group.getSelectedToggle();
                Vtype=button.getText();
            }
        });
    }
    public static String getType(){
        return Vtype;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
