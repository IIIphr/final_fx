package Server.Model.Game;

public class Board {
    private final cell[][] board;

    public Board() {
        this.board = new cell[8][8];
        for (int i = 0; i < 8; i++) {
            board[i] = new cell[8];
        }
    }

    public cell[][] getBoard() {
        return board;
    }
}


