import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
public class AppInitializer extends Application {
    public static void main(String args[]){
        launch(args);
    }
    public void start (Stage primaryStage) throws IOException{
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("WelcomePageView.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }
}