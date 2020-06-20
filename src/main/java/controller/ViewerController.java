package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Movie;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static service.MovieService.getMovies;
import static service.UserService.getUsers;
import static service.UserService.setConnectedUser;

public class ViewerController implements Initializable {

    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private TableColumn<Movie, String> titleColumn;
    @FXML
    private TableColumn<Movie, String> yearColumn;
    @FXML
    private TableColumn<Movie, String> genreColumn;
    @FXML
    private ChoiceBox genreBox;
    @FXML
    private ImageView LOGOutIMG;
    private static Movie selectedMovie;

    public static Movie getSelectedMovie() {
        return selectedMovie;
    }

    public void initialize(URL url, ResourceBundle r){
        titleColumn.setCellValueFactory(new PropertyValueFactory<Movie, String >("title"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Movie, String >("launchyear"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Movie, String >("genre"));
        List<Movie> movies = getMovies();
        ObservableList<Movie> MovieList = FXCollections.observableArrayList();
        for(Movie m: movies)
            if(m.getGenre().equals(genreBox.getValue())||genreBox.getValue().equals("All"))
                MovieList.add(m);
        movieTable.setItems(MovieList);
        LOGOutIMG.setPickOnBounds(true);
        LOGOutIMG.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleLogOut(event);
            }
        });
    }
    public void handleMouseClicked(MouseEvent mouseEvent) {
        selectedMovie=movieTable.getSelectionModel().getSelectedItem();
        if(mouseEvent.getClickCount()>1&&selectedMovie!=null)
            try {
                Parent p= FXMLLoader.load(getClass().getResource("/fxml/watchMovie.fxml"));
                Scene scene=new Scene(p,600,400);
                Stage window=(Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }catch(IOException e)
            {
                e.printStackTrace();
            }
    }

    public void handleRefresh(ActionEvent mouseEvent) {
        initialize(null,null);
    }

    public void handleAddRequest(ActionEvent actionEvent) {
        try {
            Parent p= FXMLLoader.load(getClass().getResource("/fxml/newRequest.fxml"));
            Scene scene=new Scene(p,600,400);
            Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleLogOut(MouseEvent actionEvent) {
        try {
            setConnectedUser(null);
            Parent p= FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Scene scene=new Scene(p,500,300);
            scene.getStylesheets().add("Style.css");
            Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setTitle("FletNix login");
            window.setScene(scene);
            window.show();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
