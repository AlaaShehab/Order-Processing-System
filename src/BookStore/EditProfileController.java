package BookStore;

import Backend.Book;
import Backend.ManagerActivities;
import Backend.User;
import Backend.UsersActivities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditProfileController implements Initializable {

    private User user;
    private UserActivitiesController controller;
    private FXMLLoader loader;

    private UserVerification verification;

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField password;
    @FXML private TextField username;
    @FXML private TextField phone;
    @FXML private TextField address;
    @FXML private TextField email;

    @FXML private Button cancelBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        verification = new UserVerification();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "View/UserActivities.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
        } catch (Exception e) {
            System.out.println("cannot load");
        }

        controller = loader.getController();
        user = controller.getUser();

        init();

    }

    private void init() {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        password.setText(user.getPassword());
        username.setText(user.getUserName());
        phone.setText(user.getPhoneNumber());
        address.setText(user.getShippingAddress());
        email.setText(user.getEmail());
    }

    @FXML
    private void closeWindowHandler (ActionEvent event) throws Exception{
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void EditProfileHandler (ActionEvent event) throws Exception{
        boolean validUser = false;
        if (!verification.validName(firstName) || !verification.validName(lastName)
                || !verification.validateEmaill(email) || !verification.validateMobileNo(phone)) {
            validUser = false;
            //TODO set error
            return;
        }
        user.setFirstName(firstName.getText());
        user.setLastName(lastName.getText());
        user.setPassword(password.getText());
        user.setUserName(username.getText());
        user.setPhoneNumber(phone.getText());
        user.setShippingAddress(address.getText());
        user.setEmail(email.getText());

        if (validUser) {
            UsersActivities activity = new UsersActivities();
            activity.editInfo(user);
            //TODO make sure that user returned with no error then set it
            controller.setUser(user);
            closeWindowHandler(event);
        } else {
            //TODO show error
        }

    }
}
