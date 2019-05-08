package BookStore;

import Backend.ManagerActivities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PromoteUserController implements Initializable {

    @FXML private TextField emailPromote;
    @FXML private Button cancelPromoteBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void promoteUserHandler (ActionEvent event) throws Exception{
        ManagerActivities activity = new ManagerActivities();
        activity.promoteUser(emailPromote.getText());
        closeWindowHandler(event);
    }

    @FXML
    private void closeWindowHandler (ActionEvent event) throws Exception{
        Stage stage = (Stage) cancelPromoteBtn.getScene().getWindow();
        stage.close();
    }


}
