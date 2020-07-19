package Server.Model.Game;

public class cell {
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