package controller;

import exception.MovieAlreadyExistsException;
import exception.TitleFieldEmptyException;
import exception.UrlFieldEmptyException;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Movie;
import model.Request;
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


public class NewRequestTest extends ApplicationTest {
    private final String test_movie_title = "test";
    private final String test_movie_url = "test";
    private NewRequest controller;

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


        controller = new NewRequest();
        controller.titleText = new TextField();
        controller.yearText = new TextField();
        controller.registrationResponse = new Text();

    }


    @Test
    public void test_no_title() {
        controller.titleText.setText("");
        UserService.setConnectedUser(new User("","","","viewer"));
        controller.okButton(null);
        assertEquals(new TitleFieldEmptyException().getMessage(),controller.registrationResponse.getText());
    }

    @Test
    public void test_request_or_movie_already_exists() {
        controller.titleText.setText(test_movie_title);
        UserService.setConnectedUser(new User("","","","viewer"));
        controller.okButton(null);
        controller.okButton(null);
        assertEquals(new MovieAlreadyExistsException().getMessage(),controller.registrationResponse.getText());
    }


    @Test
    public void test_create_request() {
        controller.titleText.setText(test_movie_title);
        UserService.setConnectedUser(new User("","","","viewer"));
        controller.okButton(null);
        assertEquals(new Request(UserService.getConnectedUser().getUsername(),test_movie_title,"").toString(),MovieService.getRequest(test_movie_title).toString());
    }





}