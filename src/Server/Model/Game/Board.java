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

class cell {
    Color color;
    Piece piece;

    public cell(Color color, Piece piece) {
        this.color = color;
        this.piece = piece;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
