import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class ShowDetailsForm extends Application implements Initializable {
    @FXML
    private JFXTextField txtowner;

    @FXML
    private JFXTextField txtvNo;

    @FXML
    private JFXButton btnExit;

    @FXML
    private JFXButton btnRefresh;

    @FXML
    private TableView<ParkingDetails> vdetails;

    @FXML
    private TableColumn<ParkingDetails, String> vnumber;

    @FXML
    private TableColumn<ParkingDetails, Integer> date;

    @FXML
    private TableColumn<ParkingDetails, String> parking;

    @FXML
    private TableColumn<ParkingDetails, String> arrival;

    @FXML
    private TableColumn<ParkingDetails, String> dep;

    @FXML
    private TableColumn<ParkingDetails, String> status;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getAll();

    }
    public void getAll(){
        try {
            Vehicle selectedVehicle = new VehicleNoCheckController().getSelectedVehicle();
            ParkingDetails park = ParkingDetailsController.searchVNo(selectedVehicle.getvNo());
            Payment payment = PaymentController.searchVNo(selectedVehicle.getvNo());


                txtowner.setText(selectedVehicle.getOwnerName());
                txtvNo.setText(selectedVehicle.getvNo());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void btnExit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnRefresh(ActionEvent event) throws SQLException, ClassNotFoundException {
        Vehicle selectedVehicle = new VehicleNoCheckController().getSelectedVehicle();
        vnumber.setCellValueFactory(new PropertyValueFactory<ParkingDetails, String>("vNo"));
        date.setCellValueFactory(new PropertyValueFactory<ParkingDetails, Integer>("date"));
        parking.setCellValueFactory(new PropertyValueFactory<ParkingDetails, String>("vNo"));
        arrival.setCellValueFactory(new PropertyValueFactory<ParkingDetails, String>("vNo"));
        dep.setCellValueFactory(new PropertyValueFactory<ParkingDetails, String>("vNo"));
        status.setCellValueFactory(new PropertyValueFactory<ParkingDetails, String>("vNo"));

        //vdetails.getItems().setAll(new ParkingDetailsController.searchVNo());

        //select.vdetails();


      /*  ParkingDetails parking=new ParkingDetails();

        ArrayList<ArrayList<Object>> arrList=new ArrayList<>();

        for (int i=0;i<vdetails.getItems().size();i++){
            parking= vdetails.getItems().get(i);
            arrList.add(new ArrayList<Object>());
            arrList.get(i).add(parking.getvNo());
            arrList.get(i).add(ParkingDetails.getDate());
            arrList.get(i).add(parking.getParkingID());
            arrList.get(i).add(parking.getArrival());
            arrList.get(i).add(parking.getDeparture());
            arrList.get(i).add(parking.getStatus());


        }
        for (int i=0;i<arrList.size();i++){
            for (int j=0;j<arrList.get(i).size();j++){
                System.out.println(arrList.get(i).get(j));
            }
        }


         /*   ArrayList<ParkingDetails> list= ShowDetailsFormController.getAllCustomers(selectedVehicle.getvNo());

            for (ParkingDetails park : list) {
                System.out.println("\n");
            }


            String full = "";
        int c = ParkingDetailsController.searchVNo(selectedVehicle);
        System.out.println(c);
            while(c>=x) {
                ParkingDetails park = ParkingDetailsController.searchVNo(selectedVehicle.getvNo());

                String date = ParkingDetails.getDate();
                String arrival = park.getArrival();
                String departure = park.getDeparture();

                String d = date + "\t\t\t\t" + arrival + "\t\t\t" + departure + "\t\t\t\t";
                full = full + "\n" + d;
                //data.add (d);
                System.out.print(d);
                ++x;
            }

            txtArea.setText(full);

        */
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

}