package controller;

import exception.IncorrectPasswordException;
import exception.UserDoesNotExistException;
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
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text loginResponse;

    public void onEnter(ActionEvent ae){
        handleLoginButton(ae);
    }

    public void handleLoginButton(ActionEvent actionEvent) {
        try {
            setConnectedUser(checkUser(usernameField.getText(), passwordField.getText()));
            if(getConnectedUser().getRole().equals("viewer"))
            {//viewer
                Parent p= FXMLLoader.load(getClass().getResource("/fxml/viewer.fxml"));
                Scene scene=new Scene(p,600,400);
                Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                window.setTitle("FletNix viewer");
                window.setScene(scene);
                window.show();
            }
            if(getConnectedUser().getRole().equals("uploader"))
            {//uploader
                Parent p= FXMLLoader.load(getClass().getResource("/fxml/uploader.fxml"));
                Scene scene=new Scene(p,600,400);
                Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                window.setTitle("FletNix uploader");
                window.setScene(scene);
                window.show();
            }
            if(getConnectedUser().getRole().equals("admin"))
            {//admin
                Parent p= FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
                Scene scene=new Scene(p,600,400);
                Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                window.setTitle("FletNix admin");
                window.setScene(scene);
                window.show();
            }
        }catch(UserDoesNotExistException | IncorrectPasswordException | IOException e) {
            loginResponse.setText(e.getMessage());
        }
    }
}
