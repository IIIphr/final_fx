package Client.Controller;

import Client.Model.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    @FXML
    TextArea board_area;

    @FXML
    Label score;


    @FXML
    TextField x_start;
    @FXML
    TextField x_end;
    @FXML
    TextField y_start;
    @FXML
    TextField y_end;

    public void update_board(ActionEvent actionEvent) throws IOException {
        String board=Main.client.get_board();
        Scanner scanner=new Scanner(System.in);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int temp=scanner.nextInt();
                switch (temp){
                    case 0:board+="** ";break;
                    case 1:board+="WP ";break;
                    case 2:board+="BP ";break;
                    case 3:board+="WB ";break;
                    case 4:board+="BB ";break;
                    case 5:board+="WN ";break;
                    case 6:board+="BN ";break;
                    case 7:board+="WR ";break;
                    case 8:board+="BR ";break;
                    case 9:board+="WK ";break;
                    case 10:board+="BK ";break;
                    case 11:board+="WQ ";break;
                    case 12:board+="BQ ";break;
                }
            }
            board+='\n';
        }
        board_area.setText(board);
        score.setText("Score : "+Main.client.get_score());
    }

    public void move(ActionEvent actionEvent) throws IOException {
        Main.client.move(
                Integer.parseInt(x_start.getText()),
                Integer.parseInt(y_start.getText()),
                Integer.parseInt(x_end.getText()),
                Integer.parseInt(y_end.getText())
        );
    }

}
