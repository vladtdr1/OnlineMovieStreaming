package controller;

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
import static service.MovieService.addMovieRequest;
import static service.UserService.getConnectedUser;

public class NewRequest {
    @FXML
    private TextField yearText;
    @FXML
    private TextField titleText;
    @FXML
    private Text registrationResponse;

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
            addMovieRequest(titleText.getText(),getConnectedUser().getUsername(),yearText.getText());
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
