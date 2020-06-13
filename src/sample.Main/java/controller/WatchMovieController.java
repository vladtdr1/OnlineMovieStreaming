package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import model.Movie;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.ViewerController.getSelectedMovie;

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

    public void initialize(URL url, ResourceBundle r){
        Movie m = getSelectedMovie();
        titleText.setText(m.getTitle());
        genreText.setText(m.getGenre());
        uploaderText.setText(m.getUploader());
        urlText.setText(m.getUrl());
        descriptionText.setText(m.getDescription());
    }

}
