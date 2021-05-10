import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class WelcomePageViewController {
    public JFXTextField txtDate;
    public JFXButton btnok;
    public static String dt;

    public static String getDt(){
        return dt;
    }
    public void btnok(ActionEvent actionEvent) throws IOException {
        if(txtDate.getText()!=null){
            dt=txtDate.getText();
            ParkingDetails.setDate(txtDate.getText());
            Stage stage1 = (Stage) btnok.getScene().getWindow();
            stage1.close();
            URL resource = this.getClass().getResource("DashBoardView.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }
    }
}
