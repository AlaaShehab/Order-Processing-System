package BookStore;

import Backend.Book;
import Backend.ManagerActivities;
import Backend.User;
import Backend.UsersActivities;
import com.sun.org.apache.regexp.internal.RE;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserActivitiesController implements Initializable {
    //TODO manager is different from normal user

    @FXML private MenuBar menu;
    private static User user;

    private static List<Book> searchedBooks;

    private static ManagerActivities mActivity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mActivity = new ManagerActivities();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "view/SignIn.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
        } catch (Exception e) {

        }
        SignInController controller = loader.getController();
        if (controller.getUser() != null) {
            user = controller.getUser();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(
                "view/SignUp.fxml"));
        Parent parentRoot;
        try {
            parentRoot = (Parent) fxmlLoader.load();
        } catch (Exception e) {

        }
        SignUpController ucontroller = fxmlLoader.getController();
        user = ucontroller.getUser();
        //disable some of user functions if not manager

        //initialize table
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        ISBN.setCellValueFactory(new PropertyValueFactory<Book, Integer>("ISBN"));
        publisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisherName"));
        price.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        copies.setCellValueFactory(new PropertyValueFactory<Book, Integer>("noOfCopies"));
        year.setCellValueFactory(new PropertyValueFactory<Book, String>("publicationYear"));
        author.setCellValueFactory(new PropertyValueFactory<Book, Integer>("authorStr"));
        category.setCellValueFactory(new PropertyValueFactory<Book, String>("categoryStr"));

        addToCartBtn.setOnAction(new AddItemsListener());

        //just for testing
        searchedBooks = new ArrayList<>();

    }

    public User getUser () {
        return user;
    }
    public void setUser (User user1) {
        user = user1;
    }
    @FXML
    private void editProfileHanlder (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/EditProfile.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = new Stage();
        app_stage.setTitle("Edit Profile");
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void promotUserHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/PromoteUser.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = new Stage();
        app_stage.setTitle("Promoting User");
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void logoutHandler (ActionEvent event) throws Exception{
        //first we remove all lists in the cart
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "view/UserCart.fxml"));
        Parent root = (Parent) loader.load();
        UserCartController controller = loader.getController();
        controller.clearList();

        //then we return to homepage
        Parent parent = FXMLLoader.load(getClass().getResource("View/HomePage.fxml"));
        Scene scene = new Scene(parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void addModiftyBookHanlder (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/AddModifyBook.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = new Stage();
        app_stage.setTitle("Place Order");
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void PlaceOrderHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/PlaceOrder.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = new Stage();
        app_stage.setTitle("Place Order");
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void confirmOrderHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/ConfirmOrder.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = new Stage();
        app_stage.setTitle("Confirm Order");
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void CartHandler (ActionEvent event) throws Exception{
        Parent parent = FXMLLoader.load(getClass().getResource("View/UserCart.fxml"));
        Scene scene = new Scene(parent);
        Stage app_stage = (Stage) menu.getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void totalSalesHanlder (ActionEvent event) throws Exception{
        mActivity.viewSalesReport();
    }

    @FXML
    private void topCustomerHandler (ActionEvent event) throws Exception{
        mActivity.viewTop5Customers();
    }

    @FXML
    private void topBookHandler (ActionEvent event) throws Exception{
        mActivity.viewTop10BooksSold();
    }


    @FXML private TableColumn title;
    @FXML private TableColumn ISBN;
    @FXML private TableColumn price;
    @FXML private TableColumn copies;
    @FXML private TableColumn year;
    @FXML private TableColumn publisher;
    @FXML private TableColumn author;
    @FXML private TableColumn category;

    @FXML private TableView booksTable;

    @FXML private Button addToCartBtn;

    @FXML private TextField searchValue;
    @FXML private MenuButton menuButton;

    private String menuItemSelected = "";

    @FXML
    private void SearchBookHandler (ActionEvent event) throws Exception{
        UsersActivities uActivity = new UsersActivities();

        if (menuItemSelected.equals("Book ISBN")) {
            searchedBooks = uActivity.searchForBookByISBN(searchValue.getText());
        } else if (menuItemSelected.equals("Book Title")) {
            searchedBooks = uActivity.searchForBookByTitle(searchValue.getText());
        } else if (menuItemSelected.equals("Book Author")) {
            searchedBooks = uActivity.searchForBookByAuthor(searchValue.getText());
        } else if (menuItemSelected.equals("Publication Year")) {
            searchedBooks = uActivity.searchForBookByear(searchValue.getText());
        } else {
            searchedBooks = uActivity.searchForBookByCategory(searchValue.getText());
        }

        booksTable.setItems(FXCollections.observableList(new ArrayList<>()));
        booksTable.getItems().addAll(getTableData());

    }

    @FXML
    private void setButtonName (ActionEvent event) throws Exception {

        menuItemSelected = ((MenuItem)event.getSource()).getText();
        menuButton.setText(menuItemSelected);
    }
    private ObservableList getTableData() {
        ObservableList data = FXCollections.observableList(searchedBooks);
        return data;
    }

    public List<Book> getSearchedBooks () {
        return searchedBooks;
    }

    private class AddItemsListener implements EventHandler {
        @Override
        public void handle(Event event){

            int ix = booksTable.getSelectionModel().getSelectedIndex();
            Book book = (Book) booksTable.getSelectionModel().getSelectedItem();
            int quantity = showInputTextDialog();
            book.setNoOfCopies(quantity);
            user.insertItem(book);

        }
        private int showInputTextDialog() {

            TextInputDialog dialog = new TextInputDialog();

            dialog.setTitle("");
            dialog.setHeaderText(null);
            dialog.setContentText("Quantity : ");

            Optional<String> result = dialog.showAndWait();

            return Integer.parseInt(result.get());
        }
    }
}
