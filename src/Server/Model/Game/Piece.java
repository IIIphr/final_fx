package Server.Model.Game;

enum Color {White, Black}

public abstract class Piece {
    private final Color color;
    protected int score;

    public Piece(Color color) {
        this.color = color;

    }

    public Color getColor() {
        return color;
    }

    public void move() {
    }


}
