import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private List<User> users;

    public AccountManager() {
        users = new ArrayList<>();
    }

    public void createAccount(User user) {
        users.add(user);
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true; // Authentication successful
            }
        }
        return false; // Authentication failed
    }

    public void updateAccount(User user) {
        // Update user account information
    }

    public void deleteAccount(User user) {
        users.remove(user);
    }

    public boolean isAccountExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void confirmAccount(User user, String confirmationCode) {
        // Confirm the user's account using a confirmation code
    }
}
