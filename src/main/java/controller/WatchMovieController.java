package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.Movie;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.ViewerController.getSelectedMovie;
import static service.MovieService.addMovie;
import static service.UserService.getConnectedUser;

public class WatchMovieController implements Initializable {
    @FXML
    private Text titleText;
    @FXML
    private Text genreText;
    @FXML
    private Text uploaderText;
    @FXML
    private Text urlText;
    @FXML
    private Text descriptionText;
    @FXML
    private Button startButton;

    public void initialize(URL url, ResourceBundle r){
        Movie m = getSelectedMovie();
        titleText.setText(m.getTitle());
        genreText.setText(m.getGenre());
        uploaderText.setText(m.getUploader());
        descriptionText.setText(m.getDescription());
    }

    public void handleStartButton(ActionEvent actionEvent) {
        WebView movieView = new WebView();
        movieView.getEngine().load(getSelectedMovie().getUrl());
        movieView.setPrefSize(640, 390);
        Stage stage=new Stage();
        stage.setScene(new Scene(movieView));

        stage.show();
    }

    public void handleBack(ActionEvent actionEvent) {
        try {
            Parent p= FXMLLoader.load(getClass().getResource("/fxml/viewer.fxml"));
            Scene scene=new Scene(p,600,400);
            Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
