package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static service.UserService.getUsers;

public class AdminController implements Initializable {
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private TableColumn<String, String> editColumn;
    public void initialize(URL url, ResourceBundle r){
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String >("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String >("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        editColumn.setCellValueFactory(new PropertyValueFactory<String, String >("edit"));
        List<User> users = getUsers();
        ObservableList<User> UserList = FXCollections.observableArrayList();
        for(User u: users)
            UserList.add(u);
        userTable.setItems(UserList);
    }
    public void handleNewAccountButton(ActionEvent actionEvent) {
        try {
            Parent p= FXMLLoader.load(getClass().getResource("/fxml/newUser.fxml"));
            Scene scene=new Scene(p,600,400);
            Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
