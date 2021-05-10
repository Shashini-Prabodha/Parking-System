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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VehicleNoCheckController implements Initializable {
    private static Vehicle v;
    public JFXButton btnShowDetails;
    public JFXButton btnSearch;
    public JFXButton btnVehicleAdd;
    public JFXTextField vNotxt;
    public JFXButton btnExit;

    public void btnShowDetails(ActionEvent actionEvent) throws IOException {
        try {
            v = VehicleController.searchVNo(vNotxt.getText());

            if (v != null) {

                URL resource = this.getClass().getResource("ShowDetailsViewForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();

            }else{
                new Alert(Alert.AlertType.WARNING, "Not Registerd Vehicle Number Or Field is Empty").show();
                // JOptionPane.showMessageDialog(null, "Not Registerd Vehicle Number","",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.WARNING, "SQL Exception... ").show();
            //JOptionPane.showMessageDialog(null, "SQL Exception","",JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            new Alert(Alert.AlertType.WARNING, "Error... ").show();
            //JOptionPane.showMessageDialog(null, "Error","",JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void btnSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            v= VehicleController.searchVNo(vNotxt.getText());

            if(v!=null){

                URL resource = this.getClass().getResource("ReserveSlotForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
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

    public void btnVehicleAdd(ActionEvent actionEvent) throws IOException {

            URL resource = this.getClass().getResource("AddVehicleViewForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
    }

    public void vNotxt(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            v=VehicleController.searchVNo(vNotxt.getText());

            if(v!=null){

            }else{
                JOptionPane.showMessageDialog(null, "Not Registerd Vehicle Number","",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception","",JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error","",JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public static Vehicle getSelectedVehicle(){
        return v;
    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
