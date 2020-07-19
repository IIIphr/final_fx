package Server.Model.Communications;



import Server.Model.DB.DataBase;
import Server.Model.Game.Game;
import Server.Model.Game.GameRecord;
import Server.Model.User.User;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread{
    private ServerSocket serverSocket;
    private int port = 8080;
    private List<User> AllUsers;
    private List<GameRecord> AllGames;
    public ArrayList<User> waiting_for_game;

    public Server() {
        waiting_for_game=new ArrayList<>();
        AllUsers = new ArrayList<>();
        AllGames = new ArrayList<>();
    }

    public void add_gamer(User user){
        waiting_for_game.add(user);
    }

    public boolean create_game(User user){
        if(waiting_for_game.size()>1){
            for (User temp :waiting_for_game) {
                if(user!=temp){
                    Game temp_game=new Game();
                    temp_game.setOpponent1(user);
                    temp_game.setOpponent2(temp);
                    user.clientHandler.game=temp_game;
                    temp.clientHandler.game=temp_game;
                    return true;
                }
            }
        }
        return false;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void start_socket(){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        DataBase.getInstance().ReadUsers();
        while (true) {
            try {
                new Thread(new ClientHandler(serverSocket.accept(),this)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

