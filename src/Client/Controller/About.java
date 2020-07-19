package Client.Controller;

import Client.Model.Main;
import Client.Model.Page_loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class About {

    public void back(ActionEvent actionEvent) throws IOException {
        new Page_loader().load("../View/panel.fxml");
    }

}
