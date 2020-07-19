package Client.Model;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Client client;

    static {
        try {
            client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Page_loader.initStage(primaryStage);
        new Page_loader().load("../View/start.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}