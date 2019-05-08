package Backend;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User {

    private int userID;
    private String password;
    private String firstName;
    private String lastName;
    private String email, phoneNumber, shippingAddress, userName;
    private short isManager;

    private List<Book> cart = new ArrayList<>();

    public List<Book> getCart() {
        return cart;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public short isManager() {
        return isManager;
    }

    public void setManager(short manager) {
        isManager = manager;
    }

    public void insertItem(Book item) {
        cart.add(item);
    }

    public void removeItem (Book item) {
        cart.remove(item);
    }

    public void removeItem (int index) {
        cart.remove(index);
    }

    public void clearCart () {
        cart.clear();
    }

   public void setUserName (String userName){
        this.userName = userName;
   }

   public String getUserName () {
        return userName;
   }
}
