package controller;

import exception.IncorrectPasswordException;
import exception.UserDoesNotExistException;
import exception.UsernameFieldEmptyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

import static service.UserService.*;

public class LoginController {
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Text loginResponse;

    public void onEnter(ActionEvent ae){
        handleLoginButton(ae);
    }

    public void handleLoginButton(ActionEvent actionEvent) {
        tryLogin((Stage)((Node)actionEvent.getSource()).getScene().getWindow());

    }

    void tryLogin(Stage stage)
    {
        setConnectedUser(null);
        try {
            setConnectedUser(checkUser(usernameField.getText(), passwordField.getText()));
            if(stage==null)
                return;
            if(getConnectedUser().getRole().equals("viewer"))
            {//viewer
                Parent p= FXMLLoader.load(getClass().getResource("/fxml/viewer.fxml"));
                Scene scene=new Scene(p,600,400);
                stage.setTitle("FletNix viewer");
                stage.setScene(scene);
                stage.show();
            }
            if(getConnectedUser().getRole().equals("uploader"))
            {//uploader
                Parent p= FXMLLoader.load(getClass().getResource("/fxml/uploader.fxml"));
                Scene scene=new Scene(p,600,400);
                stage.setTitle("FletNix uploader");
                stage.setScene(scene);
                stage.show();
            }
            if(getConnectedUser().getRole().equals("admin"))
            {//admin
                Parent p= FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
                Scene scene=new Scene(p,600,400);
                stage.setTitle("FletNix admin");
                stage.setScene(scene);
                stage.show();
            }
        }catch(UserDoesNotExistException | IncorrectPasswordException | IOException e) {
            loginResponse.setText(e.getMessage());
        }
    }
}
