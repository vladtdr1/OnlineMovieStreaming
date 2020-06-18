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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Movie;
import model.Request;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static service.MovieService.*;

public class ViewRequestsController implements Initializable {

    @FXML
    private TableView<Request> requestTable;
    @FXML
    private TableColumn<Request, String> titleColumn;
    @FXML
    private TableColumn<Request, String> yearColumn;
    private static Request selectedRequest;

    public void initialize(URL url, ResourceBundle rb){
        titleColumn.setCellValueFactory(new PropertyValueFactory<Request, String >("title"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Request, String >("launchyear"));
        List<Request> requests = getRequests();
        ObservableList<Request> RequestList = FXCollections.observableArrayList();
        for(Request r: requests)
                RequestList.add(r);
        requestTable.setItems(RequestList);
    }
    public void handleMouseClicked(MouseEvent mouseEvent) {
        selectedRequest=requestTable.getSelectionModel().getSelectedItem();
    }

    public void handleSolveRequest(ActionEvent actionEvent) {
        removeRequest(selectedRequest.getTitle());
        initialize(null,null);
    }

    public void handleBackButton(ActionEvent actionEvent) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/fxml/uploader.fxml"));
            Scene scene = new Scene(p, 600, 400);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}