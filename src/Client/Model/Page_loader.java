package Client.Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Page_loader {

    static final int HEIGHT = 560;
    static final int WIDTH = 330;
    public static Stage stage;

    public static void initStage(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("ThE cHeSs");
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);
    }

    public void load(String url) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(url));
        stage.setScene(new Scene(root , HEIGHT , WIDTH));
        stage.show();
    }
}