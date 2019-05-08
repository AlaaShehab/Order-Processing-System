package BookStore;

import Backend.Book;
import Backend.User;
import Backend.UsersActivities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.jws.soap.SOAPBinding;
import java.awt.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardController {

    @FXML private Button closeBtn;
    @FXML private TextField credircardNo;
    @FXML private TextField securityNo;
    @FXML private TextField expDate;

    @FXML
    private void closeWindowHandler (ActionEvent event) throws Exception{
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void verifyHanlder (ActionEvent event) throws Exception{

        if (verifyCreditcard()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(
                    "view/UserCart.fxml"));
            Parent root = (Parent) loader.load();
            UserCartController controller = loader.getController();
            User user = controller.getUser();

            UsersActivities activity = new UsersActivities();
            activity.userCheckOut(user);

            Stage stage = (Stage) closeBtn.getScene().getWindow();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
            stage.close();
        } else {
            //TODO error
        }

    }

    private boolean verifyCreditcard () {
        Pattern creditPatern = Pattern.compile("[0-9]{16}");
        Matcher creditMatch = creditPatern.matcher((String)credircardNo.getText());
        if(!creditMatch.find() || !creditMatch.group().equals((String)credircardNo.getText())){
            return false;
        }

        Pattern securityPatern = Pattern.compile("[0-9]{4}");
        Matcher securityMatch = securityPatern.matcher((String)securityNo.getText());
        if(!securityMatch.find() || !securityMatch.group().equals((String)securityNo.getText())){
            return false;
        }

        Pattern expPatern = Pattern.compile("^(0[1-9]|1[0-2])\\/?([0-9]{2})$");
        Matcher expMatch = expPatern.matcher((String)expDate.getText());
        if(!expMatch.find() || !expMatch.group().equals((String)expDate.getText())){
            return false;
        }
        return true;
    }
}
