import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String password;
    private Set<String> permissions;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.permissions = new HashSet<>();
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void addPermission(String permission){
        permissions.add(permission);
    }
    public void removePermission(String permission) {
        permissions.remove(permission);
    }
    public Set<String> getPermissions() {
        return permissions;
    }
}