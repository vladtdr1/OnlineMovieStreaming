package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.User;

public class PromptController {
    public void handleYesButton(ActionEvent actionEvent) {
        User u = AdminController.getSelectedUser();
        System.out.println(u + " shall be deleted");
        Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.close();
    }

    public void handleNoButton(ActionEvent actionEvent) {
        Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.close();
    }
}
