import com.sun.corba.se.spi.oa.OADefault;

import java.sql.SQLException;
import java.util.List;

public class main {
    public static void main(String args[]) throws SQLException {

        UsersActivities usersActivities = new UsersActivities();
        ManagerActivities managerActivities = new ManagerActivities();
        User user = new User();

        user.setEmail("bassant4@gmail.com");
        user.setFirstName("Bassant");
        user.setLastName("Ahmed");
        user.setPassword("12345");
        user.setPhoneNumber("11111");
        user.setShippingAddress("Egypt");
        user.setUserName("Bassant2");
        user.setManager((short) 1);
        usersActivities.userSignUp(user);
        User user_2 = usersActivities.userSignIn("bassant2@gmail.com", "12345");
      //  managerActivities.confirmOrder("5");
        usersActivities.addBookToCart(user_2, 5, 8);
        usersActivities.userCheckOut(user_2);

    }
}
