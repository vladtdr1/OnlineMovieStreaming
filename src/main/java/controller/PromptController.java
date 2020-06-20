package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import service.UserService;

import java.io.IOException;

public class PromptController {
    public void handleYesButton(ActionEvent actionEvent) {
        User u = AdminController.getSelectedUser();
        UserService.removeUser(u);
        if(actionEvent==null)
            return;

        Parent p;
        try { //this try-catch makes sure the users table gets updated by reopening it
            p = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
            Scene scene=new Scene(p,600,400);
            Stage window1=AdminController.getStage();
            window1.setScene(scene);
            window1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.close();
    }

    public void handleNoButton(ActionEvent actionEvent) {
        Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.close();
    }
}
