package controller;

import exception.PasswordFieldEmptyException;
import exception.UsernameAlreadyExistsException;
import exception.UsernameFieldEmptyException;
import javafx.scene.control.PasswordField;
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


public class AdminControllerTest extends ApplicationTest {
    private final String test_admin_username = "admin";
    private final String test_admin_password = "admin";
    private final String test_uploader_username = "uploader";
    private final String test_uploader_password = "uploader";
    private final String test_viewer_username = "viewer";
    private final String test_viewer_password = "viewer";

    private AdminController controller;

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

        UserService.setConnectedUser(new User(test_admin_username,test_admin_password,"","admin"));
        UserService.addUser(test_viewer_username,test_viewer_password,"","viewer");
        UserService.addUser(test_uploader_username,test_uploader_password,"","uploader");
        MovieService.addMovie("a","a","a","a",test_uploader_username,"a");

        controller = new AdminController();

    }


    @Test
    public void test_delete_viewer() {
        AdminController.setSelectedUser(UserService.getUser(test_viewer_username));
        PromptController pc = new PromptController();
        pc.handleYesButton(null);
        assertEquals(null,UserService.getUser(test_viewer_username));
    }

    @Test
    public void test_delete_uploader() {
        AdminController.setSelectedUser(UserService.getUser(test_uploader_username));
        PromptController pc = new PromptController();
        pc.handleYesButton(null);
        assertEquals(null,UserService.getUser(test_uploader_username));
        assertEquals(0, MovieService.getMovies().size());
    }




}