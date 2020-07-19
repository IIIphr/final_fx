package Client.Controller;

import Client.Model.Main;
import Client.Model.Page_loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Login {
    @FXML
    TextField username_field;

    @FXML
    TextField password_field;

    @FXML
    Label error;

    public void register(ActionEvent actionEvent) throws IOException {
        if(Main.client.register(username_field.getText(),password_field.getText())){
            new Page_loader().load("../View/panel.fxml");
        }
        else{
            error.setText("Can not register");
        }
    }

    public void login(ActionEvent actionEvent) throws IOException {
        if(Main.client.login(username_field.getText(),password_field.getText())){
            new Page_loader().load("../View/panel.fxml");
        }
        else{
            error.setText("Can not login");
        }
    }
}
