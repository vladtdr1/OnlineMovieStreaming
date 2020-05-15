package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static service.UserService.loadUsersFromFile;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        loadUsersFromFile();
        /*addUser("viewer","viewer","viewer@viewrw.viewer","viewer);
        addUser("uploader","uploader","uploader@uploader.uploader","uploader);
        addUser("admin","admin","admin@admin.admin","admin);*///(added placeholder accs)
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Movie Streaming Platform");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
