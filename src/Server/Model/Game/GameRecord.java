package Server.Model.Game;

import Server.Model.User.User;

import java.io.Serializable;

public class GameRecord implements Serializable {
    private final Game game;
    private final User winner;

    public GameRecord(Game game, User winner) {
        this.game = game;
        this.winner = winner;
    }
}
