package BookStore;

import Backend.User;
import Backend.UsersActivities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML private TextField email;
    @FXML private TextField password;

    private static User user;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void signInHandler (ActionEvent event) throws Exception{
        UsersActivities activity = new UsersActivities();
        User u = activity.userSignIn(email.getText(), password.getText());
        if (u != null) {
            setUser(u);
            Parent root = FXMLLoader.load(getClass().getResource("View/UserActivities.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
        } else {
            //print error
        }

    }
    @FXML
    private void backHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    public User getUser () {
        return user;
    }
    public void setUser (User user1) {
        user = user1;
    }
}
