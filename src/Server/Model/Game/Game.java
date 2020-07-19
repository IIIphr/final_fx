package Server.Model.Game;

import Server.Model.User.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class Game extends Thread{
    private User opponent1;
    private User opponent2;
    private Board board;

    public void setOpponent1(User opponent1) {
        this.opponent1 = opponent1;
    }

    public void setOpponent2(User opponent2) {
        this.opponent2 = opponent2;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void run() {
        initBoard();
        User current_user=opponent1;
        boolean end=false;
        cell[][] cells=board.getBoard();
        while(!end){
            try {
                int x_start=current_user.clientHandler.inputStream.readInt();
                int y_start=current_user.clientHandler.inputStream.readInt();
                int x_end=current_user.clientHandler.inputStream.readInt();
                int y_end=current_user.clientHandler.inputStream.readInt();
                if(cells[y_end][x_end].piece!=null){
                    if(cells[y_end][x_end].piece.getColor()!=cells[y_start][x_start].piece.getColor()){
                        current_user.score+=cells[y_end][x_end].piece.score;
                    }
                    else{
                        current_user.clientHandler.outputStream.writeUTF("no");
                        continue;
                    }
                }
                cells[y_start][x_start].piece = null;
                cells[y_end][x_end].piece = cells[y_start][x_start].piece;
                current_user.clientHandler.outputStream.writeUTF("next");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(end_check()){
                end=true;
            }
            if(current_user==opponent1){
                current_user=opponent2;
            }
            else{
                current_user=opponent1;
            }
        }
    }

    private boolean end_check(){
        return false;
    }

    private void initBoard() {
        board=new Board();
        cell[][] cells=board.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2==0){
                    cells[i][j].color=Color.Black;
                }
                else{
                    cells[i][j].color=Color.White;
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            cells[1][i].piece=new Pawn(Color.White);
            cells[6][i].piece=new Pawn(Color.Black);
        }
        cells[0][0].piece=new Rook(Color.White);
        cells[0][7].piece=new Rook(Color.White);
        cells[7][0].piece=new Rook(Color.Black);
        cells[7][7].piece=new Rook(Color.Black);

        cells[0][1].piece=new Knight(Color.White);
        cells[0][6].piece=new Knight(Color.White);
        cells[7][1].piece=new Knight(Color.Black);
        cells[7][6].piece=new Knight(Color.Black);

        cells[0][2].piece=new Bishop(Color.White);
        cells[0][5].piece=new Bishop(Color.White);
        cells[7][2].piece=new Bishop(Color.Black);
        cells[7][5].piece=new Bishop(Color.Black);

        cells[0][3].piece=new Queen(Color.White);
        cells[0][4].piece=new King(Color.White);
        cells[7][3].piece=new Queen(Color.Black);
        cells[7][4].piece=new King(Color.Black);
    }
}
