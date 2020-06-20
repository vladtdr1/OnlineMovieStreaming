package controller;

import exception.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Movie;
import model.User;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import service.FileSystemService;
import service.MovieService;
import service.UserService;

import static org.junit.Assert.assertEquals;


public class NewMovieTest extends ApplicationTest {
    private final String test_movie_title = "test";
    private final String test_movie_url = "test";
    private NewMovie controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-FletNix";
        FileSystemService.initApplicationHomeDir();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();
        MovieService.loadMoviesFromFile();
        MovieService.loadRequestsFromFile();


        controller = new NewMovie();
        controller.titleText = new TextField();
        controller.descriptionText = new TextArea();
        controller.urlText = new TextField();
        controller.genreText = new ChoiceBox();
        controller.yearText = new TextField();
        controller.registrationResponse = new Text();

    }


    @Test
    public void test_fail_no_title() {
        controller.titleText.setText("");
        controller.okButton(null);
        controller.genreText.setValue("");
        UserService.setConnectedUser(new User("","","","uploader"));
        assertEquals(new TitleFieldEmptyException().getMessage(),controller.registrationResponse.getText());
    }

    @Test
    public void test_fail_no_url() {
        controller.titleText.setText("notImportant");
        controller.urlText.setText("");
        controller.genreText.setValue("");
        UserService.setConnectedUser(new User("","","","uploader"));
        controller.okButton(null);
        assertEquals(new UrlFieldEmptyException().getMessage(),controller.registrationResponse.getText());
    }

    @Test
    public void test_movie_already_exists() {
        controller.titleText.setText(test_movie_title);
        controller.urlText.setText(test_movie_url);
        controller.okButton(null);
        controller.okButton(null);
        assertEquals(new MovieAlreadyExistsException().getMessage(),controller.registrationResponse.getText());
    }

    @Test
    public void test_create_movie() {
        controller.titleText.setText(test_movie_title);
        controller.urlText.setText(test_movie_url);
        UserService.setConnectedUser(new User("","","","uploader"));
        controller.genreText.setValue("");
        controller.okButton(null);
        assertEquals(new Movie(UserService.getConnectedUser().getUsername(),test_movie_title,"","",test_movie_url,"").toString(),MovieService.getMovie(test_movie_title).toString());
    }





}