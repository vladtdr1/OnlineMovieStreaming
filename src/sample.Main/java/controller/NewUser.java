package controller;

import exception.PasswordFieldEmptyException;
import exception.UsernameAlreadyExistsException;
import exception.UsernameFieldEmptyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static service.UserService.addUser;


public class NewUser {
    @FXML
    private TextField usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField emailText;
    @FXML
    private Text registrationResponse;
    @FXML
    private ChoiceBox roleText;

    public void cancelButton(ActionEvent actionEvent) {
        try {
            Parent p= FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
            Scene scene=new Scene(p,600,400);
            Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void okButton(ActionEvent actionEvent) {
        try {
            addUser(usernameText.getText(),passwordText.getText(), emailText.getText(), (String) roleText.getValue());
            Parent p= FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
            Scene scene=new Scene(p,600,400);
            Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }catch(IOException e) {
            e.printStackTrace();
        } catch (PasswordFieldEmptyException |UsernameFieldEmptyException |UsernameAlreadyExistsException e ) {
            registrationResponse.setText(e.getMessage());
        }
    }
}
