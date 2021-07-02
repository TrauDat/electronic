package com.traudat.app.views;

import com.traudat.app.ElicApplication;
import com.traudat.app.entity.Account;
import com.traudat.app.model.ElicException;
import com.traudat.app.service.AccountService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class Login {

    private static Account loginUser;

    @Autowired
    private AccountService accountService;

    @FXML
    private PasswordField password;

    @FXML
    private Label message;

    @FXML
    private TextField loginId;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnLogin;

    public static void loadView(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(ElicApplication.class.getClassLoader().getResource("Login.fxml"));
            loader.setControllerFactory(ElicApplication.getApplicationContext()::getBean);
            Parent root = loader.load();
            stage.setScene(new Scene(root));

            Login controller = loader.getController();
            controller.attachEvent();

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void attachEvent() {

        loginId.getScene().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                if (btnClose.isFocused()) {
                    close();
                }
                if (btnLogin.isFocused()) {
                    login();
                }
            }
        });
    }

    @FXML
    private void close() {
        btnClose.getScene().getWindow().hide();
    }

    @FXML
    private void login() {
        try {
            loginUser = accountService.login(loginId.getText(), password.getText());

            // open application
            MainFrame.show();

            // close Login view
            close();
        } catch (ElicException el) {
            message.setText(el.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            close();
        }
    }

}
