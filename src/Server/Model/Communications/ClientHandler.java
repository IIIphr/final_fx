package Server.Model.Communications;

import Server.Model.DB.DataBase;
import Server.Model.Game.Game;
import Server.Model.User.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            Thread game=new Game();
            boolean in_game=false;
            DataBase database=DataBase.getInstance();
            DataInputStream inputStream=new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream=new DataOutputStream(clientSocket.getOutputStream());
            User logged_in=null;
            while(true){
                if(logged_in==null){
                    String username=inputStream.readUTF();
                    String password=inputStream.readUTF();
                    String action=inputStream.readUTF();
                    if(action.equals("login")){
                        logged_in=database.login(username,password);
                        if(logged_in==null){
                            outputStream.writeUTF("failed");
                        }
                        else{
                            outputStream.writeUTF("success");
                            database.SaveUsers();
                        }
                    }
                    else{
                        if(database.addUser(new User(username,password))){
                            outputStream.writeUTF("success");
                            logged_in=database.login(username,password);
                            database.SaveUsers();
                        }
                        else{
                            outputStream.writeUTF("failed");
                        }
                    }
                }
                else{
                    while (logged_in!=null){
                        database.SaveUsers();
                        String action=inputStream.readUTF();
                        if(action.equals("cp")){
                            String pass=inputStream.readUTF();
                            database.changepass(pass,logged_in);
                            database.SaveUsers();
                        }
                        else if(action.equals("lo")){
                            logged_in=null;
                            break;
                        }
                        else if(action.equals("up")){
                            database.SaveUsers();
                        }
                        else if(action.equals("game")){
                            //init game
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
