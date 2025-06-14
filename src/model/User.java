package model;

public class User {
    private String username;
    private String password;
    private String role; // "admin", "manager", "salesrep", "viewer"

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role.toLowerCase();
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; } // 🔐 later encrypt this
    public String getRole() { return role; }

    public boolean hasPermission(String action) {
        switch (role) {
            case "admin":
                return true;
            case "manager":
                return !action.equals("manage_users");
            case "salesrep":
                return action.equals("view_inventory") || action.equals("make_transaction");
            case "viewer":
                return action.equals("view_inventory");
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return String.format("User: %s (%s)", username, role);
    }
}
