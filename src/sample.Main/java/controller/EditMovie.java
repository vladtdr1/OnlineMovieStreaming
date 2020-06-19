package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.UploaderController.getSelectedMovie;
import static service.MovieService.*;
import static service.UserService.getConnectedUser;

public class EditMovie implements Initializable {

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
            editMovie(getSelectedMovie(),descriptionText.getText(),titleText.getText(),urlText.getText(),yearText.getText(),(String)genreText.getValue());
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

    public void deleteButton(ActionEvent actionEvent) {
        try {
            removeMovie(getSelectedMovie().getTitle());
            Parent p= FXMLLoader.load(getClass().getResource("/fxml/uploader.fxml"));
            Scene scene=new Scene(p,600,400);
            Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e ) {
            e.printStackTrace();
            registrationResponse.setText(e.getMessage());
        }
    }

    public void onEnter(ActionEvent actionEvent) {
        okButton(actionEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleText.setText(getSelectedMovie().getTitle());
        urlText.setText(getSelectedMovie().getUrl());
        yearText.setText(getSelectedMovie().getLaunchyear());
        descriptionText.setText(getSelectedMovie().getDescription());
        genreText.setValue(getSelectedMovie().getGenre());
    }
}
