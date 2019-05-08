package BookStore;

import Backend.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddModifyBookHandler implements Initializable {

    @FXML private Button cancelBtn;

    @FXML private TextField ISBN;
    @FXML private TextField publisherName;
    @FXML private TextField publicationYear;
    @FXML private TextField category;
    @FXML private TextField title;
    @FXML private TextField author;
    @FXML private TextField price;
    @FXML private TextField noOfCopies;
    @FXML private TextField threshold;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void closeWindowHandler (ActionEvent event) throws Exception{
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addBookHandler (ActionEvent event) throws Exception{
        Book book = getBook();
        //TODO SQL add book
        closeWindowHandler(event);
    }

    @FXML
    private void modifyBookHandler (ActionEvent event) throws Exception{
        Book book = getBook();
        //TODO SQL modify book
        closeWindowHandler(event);
    }

    private Book getBook () {
        Book book = new Book();
        book.setISBN(Integer.parseInt(ISBN.getText()));
        book.setNoOfCopies(Integer.parseInt(noOfCopies.getText()));
        book.setPrice(Double.parseDouble(price.getText()));
        book.setTitle(title.getText());
        book.setPublicationYear(publicationYear.getText());
        book.setThreshold(Integer.parseInt(threshold.getText()));
        book.setPublisherName(publisherName.getText());

        String[] temp = (category.getText()).split("-");
        for (int i = 0; i < temp.length; i++) {
            book.addCategory(temp[i]);
        }

        temp = (author.getText()).split("-");
        for (int i = 0; i < temp.length; i++) {
            book.addAuthor(temp[i]);
        }

        return book;
    }
}
