package util;

import java.util.HashMap;

public class Users {
    private HashMap<String, String> users;

    public Users() {
        users = new HashMap<>();
        users.put("admin", "admin");
        users.put("user", "user");
    }
    public boolean validUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
