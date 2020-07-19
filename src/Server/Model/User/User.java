package Server.Model.User;



import Server.Model.Game.GameRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    public String name;
    public String password;
    public int score=0;
    public List<GameRecord> gameRecordList = new ArrayList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
