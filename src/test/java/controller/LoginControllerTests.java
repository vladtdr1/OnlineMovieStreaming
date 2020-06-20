package controller;
import exception.PasswordFieldEmptyException;
import exception.UsernameAlreadyExistsException;
import exception.UsernameFieldEmptyException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.control.TextField;
import service.FileSystemService;
import service.MovieService;
import service.UserService;

import static org.junit.Assert.assertEquals;


public class LoginControllerTests extends ApplicationTest {
    private final String test_admin_username = "admin";
    private final String test_admin_password = "admin";
    private final String test_uploader_username = "uploader";
    private final String test_uploader_password = "uploader";
    private final String test_viewer_username = "uploader";
    private final String test_viewer_password = "uploader";

    private LoginController controller;

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

        controller = new LoginController();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.loginResponse=new Text();
    }

    @Test
    public void test_login_fail() {
        controller.usernameField.setText("unknownUser");
        controller.passwordField.setText("unknownUser");
        controller.tryLogin(null);
        assertEquals(null,UserService.getConnectedUser());
    }

    @Test
    public void test_login_admin() {
        try {
            UserService.addUser(test_admin_username, test_admin_password, "", "admin");
        } catch (UsernameAlreadyExistsException | UsernameFieldEmptyException | PasswordFieldEmptyException e) {
            e.printStackTrace();
        }
        controller.usernameField.setText(test_admin_username);
        controller.passwordField.setText(test_admin_password);
        controller.tryLogin(null);
        assertEquals("admin",UserService.getConnectedUser().getRole());
    }

    @Test
    public void test_login_uploader() {
        try {
            UserService.addUser(test_uploader_username, test_uploader_password, "", "uploader");
        } catch (UsernameAlreadyExistsException | UsernameFieldEmptyException | PasswordFieldEmptyException e) {
            e.printStackTrace();
        }
        controller.usernameField.setText(test_uploader_username);
        controller.passwordField.setText(test_uploader_password);
        controller.tryLogin(null);
        assertEquals("uploader",UserService.getConnectedUser().getRole());
    }

    @Test
    public void test_login_viewer() {
        try {
            UserService.addUser(test_viewer_username, test_viewer_password, "", "viewer");
        } catch (UsernameAlreadyExistsException | UsernameFieldEmptyException | PasswordFieldEmptyException e) {
            e.printStackTrace();
        }
        controller.usernameField.setText(test_viewer_username);
        controller.passwordField.setText(test_viewer_password);
        controller.tryLogin(null);
        assertEquals("viewer",UserService.getConnectedUser().getRole());
    }




}