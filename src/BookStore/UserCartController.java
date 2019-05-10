package BookStore;

import Backend.Book;
import Backend.OrderItem;
import Backend.User;
import Backend.UsersActivities;
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

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserCartController implements Initializable {

    @FXML
    private TableView cartTable;

    @FXML private TableColumn title;
    @FXML private TableColumn isbn;
    @FXML private TableColumn price;
    @FXML private TableColumn pname;
    @FXML private TableColumn quantity;

    @FXML private Button removeBtn;

    @FXML private Label totalCartPrice;

    private static User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        isbn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("ISBN"));
        pname.setCellValueFactory(new PropertyValueFactory<Book, String>("publisherName"));
        price.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Book, Integer>("noOfCopies"));

        removeBtn.setOnAction(new DeleteButtonListener());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "View/UserActivities.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
        } catch (Exception e) {
            System.out.println("cannot load");
        }

        UserActivitiesController controller = loader.getController();
        user = controller.getUser();


        //this is just for testing
      //  dummyTrial();
        refresh ();
    }

    private void calculatePrice () {
        UsersActivities activity = new UsersActivities();
        double price = 0.0;
        try {
            price = activity.viewTotalPricesInCart(user.getCart());
        } catch (Exception e) {

        }
        DecimalFormat df = new DecimalFormat(".###");
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

        user.insertItem(book);
    }

    public List<Book> getCartItems () {
        return user.getCart();
    }

    public void refresh () {
        cartTable.setItems(FXCollections.observableList(new ArrayList<>()));
        cartTable.getItems().addAll(getTableData());
        calculatePrice();
    }
    private ObservableList getTableData() {
        ObservableList data = FXCollections.observableList(user.getCart());
        return data;
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
            if (ix > user.getCart().size()) {
                refresh();
                return;
            }
            Book book = (Book) cartTable.getSelectionModel().getSelectedItem();
            user.removeItem(ix);
            refresh();
        }
    }

    public User getUser () {
        return user;
    }

    private class CloseWindowListener implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent event){
            refresh();
        }
    }
}
