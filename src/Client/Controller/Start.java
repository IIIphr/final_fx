package Client.Controller;

import Client.Model.Main;
import Client.Model.Page_loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Start {
    @FXML
    TextField ip_field;

    @FXML
    TextField port_field;

    @FXML
    Label error;

    public void submit(ActionEvent actionEvent) throws IOException {
        Main.client.setIP(ip_field.getText());
        Main.client.setPort(Integer.parseInt(port_field.getText()));
        if(Main.client.connectToServer()){
            new Page_loader().load("../View/login.fxml");
        }
        else{
            error.setText("Could not connect");
        }
    }

}
