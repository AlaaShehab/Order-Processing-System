import java.util.LinkedList;
import java.util.List;

abstract class UsersActivities {
    public void editInfo(User user) {//send info as parameters

    }
    public List<Book> searchForBookByISBN(String ISBN) {

        return new LinkedList<>();
    }
    public List<Book> searchForBookByTitle(String title) {

        return new LinkedList<>();
    }
    public List<Book> searchForBookByAuthor(String author_name) {

        return new LinkedList<>();
    }
    public List<Book> searchForBookByCategory(String category) {

        return new LinkedList<>();
    }
    public List<Book> searchForBookByear(String year) {

        return new LinkedList<>();
    }
    public void viewBookPrice(String ISBN) {

    }
    public void viewTotalPricesInCart(List<OrderItem> cart) {

    }

    public void userCheckOut(User user) {
        //place order
        user.clearCart();
    }

    public void userLogOut(User user){

    }
    public User userSignIn(String email, String password){
        //check email and password
        //log in and create new user
        return new User();
    }

    abstract void addNewBook(Book book);
    abstract void modifyBook(Book book); //send modified parameters
    abstract void placeOrder(Book book, int quantity); //mlhash lazma
    abstract void promoteUser(User user);
    abstract void viewSalesReport(User user);



}
