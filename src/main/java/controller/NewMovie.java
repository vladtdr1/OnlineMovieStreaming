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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static service.MovieService.addMovie;
import static service.UserService.addUser;
import static service.UserService.getConnectedUser;


public class NewMovie {
    @FXML
    private TextField urlText;
    @FXML
    private TextField yearText;
    @FXML
    private TextField titleText;
    @FXML
    private TextArea descriptionText;
    @FXML
    private Text registrationResponse;
    @FXML
    private ChoiceBox genreText;

    public void cancelButton(ActionEvent actionEvent) {
        try {
            Parent p= FXMLLoader.load(getClass().getResource("/fxml/uploader.fxml"));
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
            addMovie(titleText.getText(),urlText.getText(),(String)genreText.getValue(),descriptionText.getText(),getConnectedUser().getUsername(),yearText.getText());
            Parent p= FXMLLoader.load(getClass().getResource("/fxml/uploader.fxml"));
            Scene scene=new Scene(p,600,400);
            Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e ) {
            registrationResponse.setText(e.getMessage());
        }
    }

    public void onEnter(ActionEvent actionEvent) {
        okButton(actionEvent);
    }
}
