package Server.Model.Communications;



import Server.Model.DB.DataBase;
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

    public Server() {
        AllUsers = new ArrayList<>();
        AllGames = new ArrayList<>();
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
                new Thread(new ClientHandler(serverSocket.accept())).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

