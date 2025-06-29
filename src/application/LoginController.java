package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField visiblePasswordField;
    @FXML private CheckBox showPasswordCheck;
    @FXML private CheckBox rememberCheck;
    @FXML private Button loginBtn;
    @FXML private Label errorLabel;
    @FXML private Label timeLabel;
    @FXML private Hyperlink forgotLink;

    private int attemptCount = 0;
    private final String validEmail = "developer.satyam10@gmail.com";
    private final String validPassword = "Satyam@321";
    private final boolean isLicenseValid = true;

    public void initialize() {
        updateTime();
        emailField.textProperty().addListener((obs, oldVal, newVal) -> validateForm());
        passwordField.textProperty().addListener((obs, oldVal, newVal) -> validateForm());
        visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        showPasswordCheck.selectedProperty().addListener((obs, oldVal, newVal) -> {
            visiblePasswordField.setVisible(newVal);
            passwordField.setVisible(!newVal);
        });

        forgotLink.setOnAction(e -> showForgotPopup());
    }

    private void validateForm() {
        String email = emailField.getText();
        String password = passwordField.getText();
        boolean valid = !email.isEmpty() && isValidEmail(email) && password.length() >= 6;
        loginBtn.setDisable(!valid);
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (!email.equals(validEmail) || !password.equals(validPassword)) {
            attemptCount++;
            errorLabel.setText("Invalid credentials. Attempt: " + attemptCount);
            if (attemptCount >= 5) {
                showAlert("Account Locked", "Please connect with your organization.");
                loginBtn.setDisable(true);
            }
        } else {
            if (!isLicenseValid) {
                showAlert("License Expired", "Please renew your software.");
                return;
            }
            openDashboard();
        }
    }

    private void openDashboard() {
        try {
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard.fxml"));
            Scene scene = new Scene(loader.load(), 800, 500); // set custom width & height
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w\\.-]+@([\\w\\-]+\\.)+[\\w\\-]{2,4}$", email);
    }


    private void updateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        timeLabel.setText("Time: " + dtf.format(LocalDateTime.now()));
        Thread t = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> timeLabel.setText("Time: " + dtf.format(LocalDateTime.now())));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private void showForgotPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Forgot Password");
        alert.setHeaderText("To reset your password, please contact your organization.");
        alert.getButtonTypes().setAll(ButtonType.NEXT, ButtonType.CLOSE);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.NEXT) {
                showAlert("Message Sent", "Message sent to organization.");
            }
        });
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
