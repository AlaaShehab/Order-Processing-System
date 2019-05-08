package BookStore;

import Backend.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private DBManager db;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = DBManager.getInstance();
    }

    @FXML
    private void signInHandler (ActionEvent event) throws Exception {
        Parent signIn_parent = FXMLLoader.load(getClass().getResource("View/SignIn.fxml"));
        Scene signUp_scene = new Scene(signIn_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signUp_scene);
        app_stage.show();
    }

    @FXML
    private void signUpHandler (ActionEvent event) throws Exception {
        Parent signUp_parent = FXMLLoader.load(getClass().getResource("View/SignUp.fxml"));
        Scene signUp_scene = new Scene(signUp_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signUp_scene);
        app_stage.show();
    }
}
