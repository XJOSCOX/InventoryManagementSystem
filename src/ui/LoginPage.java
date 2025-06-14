package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends Application {
    private Map<String, User> users = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        // Sample users (username, password, role)
        users.put("admin", new User("admin", "admin123", "admin"));
        users.put("manager", new User("manager", "manager123", "manager"));
        users.put("sales", new User("sales", "sales123", "salesrep"));
        users.put("viewer", new User("viewer", "viewer123", "viewer"));

        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();

        Button loginBtn = new Button("Login");
        Label statusLabel = new Label();

        loginBtn.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            User user = users.get(username);
            if (user != null && user.getPassword().equals(password)) {
                statusLabel.setText("Login successful as " + user.getRole());
                // TODO: Load role-based dashboard here
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setHeaderText("Welcome, " + username);
                alert.setContentText("Role: " + user.getRole());
                alert.showAndWait();
            } else {
                statusLabel.setText("Invalid credentials.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText("Authentication Error");
                alert.setContentText("Invalid username or password.");
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20px;");
        layout.getChildren().addAll(userLabel, userField, passLabel, passField, loginBtn, statusLabel);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setTitle("Inventory System - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
