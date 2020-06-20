package controller;

import exception.PasswordFieldEmptyException;
import exception.UsernameAlreadyExistsException;
import exception.UsernameFieldEmptyException;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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


public class NewUserTest extends ApplicationTest {
    private final String test_uploader_username = "uploader";
    private final String test_uploader_password = "uploader";
    private final String test_viewer_username = "viewer";
    private final String test_viewer_password = "viewer";

    private NewUser controller;

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


        controller = new NewUser();
        controller.usernameText = new TextField();
        controller.passwordText = new PasswordField();
        controller.emailText = new TextField();
        controller.roleText = new ChoiceBox();
        controller.registrationResponse = new Text();

    }


    @Test
    public void test_fail_no_username() {
        controller.usernameText.setText("");
        controller.okButton(null);
        assertEquals(new UsernameFieldEmptyException().getMessage(),controller.registrationResponse.getText());
    }

    @Test
    public void test_fail_no_password() {
        controller.usernameText.setText("notImportant");
        controller.passwordText.setText("");
        controller.okButton(null);
        assertEquals(new PasswordFieldEmptyException().getMessage(),controller.registrationResponse.getText());
    }

    @Test
    public void test_user_already_exists() {
        controller.usernameText.setText(test_viewer_username);
        controller.passwordText.setText(test_viewer_password);
        controller.roleText.setValue("viewer");
        controller.emailText.setText("");
        controller.okButton(null);
        controller.okButton(null);
        assertEquals(new UsernameAlreadyExistsException().getMessage(),controller.registrationResponse.getText());
    }

    @Test
    public void test_create_viewer() {
        controller.usernameText.setText(test_viewer_username);
        controller.passwordText.setText(test_viewer_password);
        controller.roleText.setValue("viewer");
        controller.emailText.setText("");
        controller.okButton(null);
        assertEquals(new User(test_viewer_username,UserService.encodePassword(test_viewer_username,test_viewer_password),"","viewer").toString(),UserService.getUser(test_viewer_username).toString());
    }

    @Test
    public void test_create_uploader() {
        controller.usernameText.setText(test_uploader_username);
        controller.passwordText.setText(test_uploader_password);
        controller.roleText.setValue("uploader");
        controller.emailText.setText("");
        controller.okButton(null);
        assertEquals(new User(test_uploader_username,UserService.encodePassword(test_uploader_username,test_uploader_password),"","uploader").toString(),UserService.getUser(test_uploader_username).toString());
    }




}