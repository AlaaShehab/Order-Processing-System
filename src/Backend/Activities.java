package Backend;

import java.util.List;
public abstract class Activities {
    public abstract void editInfo(User user);

    public abstract void addBookToCart(User user, int ISBN, int quantity);

    public abstract void removeBookFromCart(User user, int ISBN, int quantity);

    public abstract List<Book> searchForBookByISBN(String bookISBN);

    public abstract List<Book> searchForBookByTitle(String bookTitle);

    public abstract List<Book> searchForBookByAuthor(String author_name);

    public abstract List<Book> searchForBookByCategory(String category);

    public abstract List<Book> searchForBookByear(String year);

    public abstract void viewBookPrice(String ISBN);

    public abstract void viewTotalPricesInCart(List<OrderItem> cart);

    public abstract void userCheckOut(User user);

    public abstract void userLogOut(User user);

    public abstract User userSignIn(String email, String password);

    public abstract boolean userSignUp(User user);

    abstract void addNewBook(Book book);
    abstract void modifyBook(Book book); //send modified parameters
    abstract void placeOrder(Book book, int quantity); //mlhash lazma
    abstract void promoteUser(User user);
    abstract void viewSalesReport();
    abstract void viewTop5Customers();
    abstract void viewTop10BooksSold();
}
