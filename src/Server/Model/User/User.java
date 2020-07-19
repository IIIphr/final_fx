package Server.Model.User;



import Server.Model.Communications.ClientHandler;
import Server.Model.Game.GameRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
    public String name;
    public String password;
    public int score=0;
    public List<GameRecord> gameRecordList = new ArrayList<>();
    public ClientHandler clientHandler;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }
}
