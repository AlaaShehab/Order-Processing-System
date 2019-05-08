package BookStore;

import Backend.Book;
import Backend.OrderItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jfree.ui.about.SystemPropertiesTableModel;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserCartController implements Initializable {

    private static List<Book> cartItems = new ArrayList<Book>();

    @FXML
    private TableView cartTable;

    @FXML private TableColumn title;
    @FXML private TableColumn isbn;
    @FXML private TableColumn price;
    @FXML private TableColumn pname;
    @FXML private TableColumn quantity;

    @FXML private Button removeBtn;

    @FXML private Label totalCartPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        isbn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("ISBN"));
        pname.setCellValueFactory(new PropertyValueFactory<Book, String>("publisherName"));
        price.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Book, Integer>("noOfCopies"));

        removeBtn.setOnAction(new DeleteButtonListener());

        //this is just for testing
      //  dummyTrial();
        refresh ();
    }

    private void calculatePrice () {
        DecimalFormat df = new DecimalFormat(".###");
        double price = 0.0;
        for (int i = 0; i < cartItems.size(); i++) {
            price += cartItems.get(i).getPrice() * cartItems.get(i).getNoOfCopies();
        }
        totalCartPrice.setText(df.format(price)+ " $");
    }

    @FXML
    private void CheckoutHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/CreditCard.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = new Stage();
        app_stage.setTitle("Credit Card Verification");
        app_stage.setScene(scene);
        app_stage.show();
        app_stage.setOnCloseRequest(new CloseWindowListener());
    }

    public void addItemToCart (Book book) {
        cartItems.add(book);
    }

    public List<Book> getCartItems () {
        return cartItems;
    }

    public void clearList () {
        cartItems.clear();
    }

    public void refresh () {
        cartTable.setItems(FXCollections.observableList(new ArrayList<>()));
        cartTable.getItems().addAll(getTableData());
        calculatePrice();
    }
    private ObservableList getTableData() {
        ObservableList data = FXCollections.observableList(cartItems);
        return data;
    }

    private void dummyTrial () {
        cartItems.add(new Book("The Thief",1, "Fuminori Nakamura", 26.5, 5));
        cartItems.add(new Book("Of Human Bondage",2, "Somerset Maugham", 58, 4));
        cartItems.add(new Book("The Bluest Eye",3, "Toni Morrison", 25, 1));
        addItemToCart(new Book("by the sea", 1, "a@example.com", 26.5, 1));
        addItemToCart(new Book("In the wind", 2, "b@example.com", 85.69, 4));
        addItemToCart(new Book("Bla bla bla", 3, "c@example.com", 78.4, 2));
        addItemToCart(new Book("Just anything", 4, "d@example.com", 41.5, 3));
        addItemToCart(new Book("Write what", 5, "e@example.com", 15.0, 1));
    }

    @FXML
    private void backHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/UserActivities.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    private class DeleteButtonListener implements EventHandler {
        @Override
        public void handle(Event event){

            int ix = cartTable.getSelectionModel().getSelectedIndex();
            if (ix > cartItems.size()) {
                refresh();
                return;
            }
            Book book = (Book) cartTable.getSelectionModel().getSelectedItem();
            cartItems.remove(ix);
            refresh();
        }
    }

    private class CloseWindowListener implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent event){
            refresh();
        }
    }
}
