import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SlotCheckController implements Initializable {

    public JFXButton btnsrchVehical;
    public static int carSlot;
    public static int vanSlot;
    public static int busSlot;
    public static int lorrySlot;
    public JFXButton btnRefresh;
    public JFXButton btnExit;
    public JFXTextField txtCar;
    public JFXTextField txtVan;
    public JFXTextField txtBus;
    public JFXTextField txtLorry;


    public void btnsrchVehical(ActionEvent actionEvent) throws IOException {

        URL resource = this.getClass().getResource("VehicleNoCheckView.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnRefresh(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        carSlot=PaymentController.getNoSlotCar();
        vanSlot= PaymentController.getNoSlotVan();
        busSlot=PaymentController.getNoSlotBus();
        lorrySlot=PaymentController.getNoSlotLorry();
        System.out.println(carSlot+" "+vanSlot+" "+busSlot+" "+lorrySlot);

        txtCar.setText(40-carSlot+"");
        txtVan.setText(30-vanSlot+"");
        txtBus.setText(20-busSlot+"");
        txtLorry.setText(10-lorrySlot+"");

    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
