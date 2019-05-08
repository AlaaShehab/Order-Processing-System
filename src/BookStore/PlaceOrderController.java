package BookStore;

import Backend.ManagerActivities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {

    @FXML private Button cancelBtn;
    @FXML private TextField quantity;
    @FXML private TextField ISBN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void closeWindowHandler (ActionEvent event) throws Exception{
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void placeOrderHandler (ActionEvent event) throws Exception{
        ManagerActivities activity = new ManagerActivities();
        activity.placeOrder(ISBN.getText(), Integer.parseInt(quantity.getText()));
        closeWindowHandler(event);
    }
}
