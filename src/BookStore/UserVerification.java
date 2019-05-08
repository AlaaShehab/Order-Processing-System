package BookStore;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserVerification {

    public boolean validName (TextField name) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher((String)name.getText());
        if(m.find() && m.group().equals((String)name.getText())){
            return true;
        }
        return false;
    }
    public boolean validateEmaill(TextField email){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher((String)email.getText());
        if(m.find() && m.group().equals((String)email.getText())){
            return true;
        }
        return false;
    }
    public boolean validateMobileNo(TextField phone){
        Pattern p = Pattern.compile("(01)[0-3][0-9]{8}");
        Matcher m = p.matcher((String)phone.getText());
        if(m.find() && m.group().equals((String)phone.getText())){
            return true;
        }
        return false;
    }
}
