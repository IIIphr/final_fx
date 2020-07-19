package Client.Controller;

import Client.Model.Main;
import Client.Model.Page_loader;
import Server.Model.DB.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Panel {
    @FXML
    Label password_label;

    @FXML
    TextField password_field;

    public void change_password(ActionEvent actionEvent) throws IOException {
        Main.client.changepass(password_field.getText());
        password_label.setText("password changed !");
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Main.client.logout();
        new Page_loader().load("../View/login.fxml");
    }

    public void game(ActionEvent actionEvent) throws IOException {
    }

    public void about(ActionEvent actionEvent) throws IOException {
        new Page_loader().load("../View/about.fxml");
    }

    public void score_board(ActionEvent actionEvent) throws IOException {
    }

    public void history(ActionEvent actionEvent) throws IOException {
    }

    public void update(ActionEvent actionEvent) throws IOException {
        Main.client.update();
    }

}
