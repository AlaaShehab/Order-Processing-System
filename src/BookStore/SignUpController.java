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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.beans.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController implements Initializable {
    private static User user = new User();

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField cpassword;
    @FXML private TextField phone;
    @FXML private TextField address;
    @FXML private TextField email;
    @FXML private CheckBox manager;

    private UserVerification verification;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        verification = new UserVerification();
    }
    @FXML
    private void signUpHandler (ActionEvent event) throws Exception {

        if (validateUser()) {
            UsersActivities activity = new UsersActivities();
            User u = activity.userSignUp(user);
            if (u != null) {
                Parent root = FXMLLoader.load(getClass().getResource("View/UserActivities.fxml"));
                Scene scene = new Scene(root);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();

            } else {
                //TODO show error msg
            }
        } else {
            //TODO show error msg
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

    private boolean validateUser () {
        if (!verification.validName(firstName) || !verification.validName(lastName)
            || !verification.validateEmaill(email) || !verification.validateMobileNo(phone)
            || !password.getText().equals(cpassword.getText()) || password.getText().isEmpty()) {
            return false;
        }
        user.setFirstName((String)firstName.getText());
        user.setLastName((String)lastName.getText());
        user.setUserName(username.getText());
        user.setEmail((String)email.getText());
        user.setPhoneNumber((String)phone.getText());
        user.setPassword((String)password.getText());
        user.setShippingAddress((String)address.getText());
        if (manager.isSelected()) {
            user.setManager((short) 1);
        } else {
            user.setManager((short)0);
        }
        return true;
    }

}
