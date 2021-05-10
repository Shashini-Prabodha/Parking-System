import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerLoginViewFormController implements Initializable {
    public  JFXTextField txtvNo;
    public static String VehicleNo;
    public JFXButton btnLogin;
    private static Vehicle vehicle;
    public JFXTextField txtPswrd;
    public JFXButton btnExit;

    public void btnLogin(ActionEvent actionEvent) {
        try {
            vehicle= VehicleController.searchVNo(txtvNo.getText());
            VehicleNo=txtvNo.getText();
           // int pw=Integer.parseInt(txtPswrd.getText());
            //int c=PaymentViewFormController.getPw();

            if(vehicle!=null ){
                URL resource = this.getClass().getResource("CustomerViewForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();

            }else{
                new Alert(Alert.AlertType.WARNING,"Not Registerd Vehicle Number... ").show();
                // JOptionPane.showMessageDialog(null, "Not Registerd Vehicle Number","",JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            new Alert(Alert.AlertType.WARNING,"SQL Exception... ").show();
            //JOptionPane.showMessageDialog(null, "SQL Exception","",JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            new Alert(Alert.AlertType.WARNING,"Error... ").show();
            //JOptionPane.showMessageDialog(null, "Error","",JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static Vehicle getSelectedVehicle(){
        return vehicle;
    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
