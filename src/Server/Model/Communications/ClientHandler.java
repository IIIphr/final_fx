package Server.Model.Communications;

import Server.Model.Game.*;
import Server.Model.DB.DataBase;
import Server.Model.User.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private Server server;
    Thread game;
    public DataInputStream inputStream;
    public DataOutputStream outputStream;

    public ClientHandler(Socket clientSocket,Server server) {
        this.clientSocket = clientSocket;
        this.server=server;
    }

    @Override
    public void run() {
        try {
            boolean in_game=false;
            DataBase database=DataBase.getInstance();
            inputStream=new DataInputStream(clientSocket.getInputStream());
            outputStream=new DataOutputStream(clientSocket.getOutputStream());
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
                            logged_in.clientHandler=this;
                            database.SaveUsers();
                        }
                    }
                    else{
                        if(database.addUser(new User(username,password))){
                            outputStream.writeUTF("success");
                            logged_in=database.login(username,password);
                            logged_in.clientHandler=this;
                            database.SaveUsers();
                        }
                        else{
                            outputStream.writeUTF("failed");
                        }
                    }
                }
                else {
                    while (logged_in != null) {
                        database.SaveUsers();
                        String action = inputStream.readUTF();
                        if (action.equals("cp")) {
                            String pass = inputStream.readUTF();
                            database.changepass(pass, logged_in);
                            database.SaveUsers();
                        } else if (action.equals("lo")) {
                            logged_in.clientHandler = null;
                            logged_in = null;
                            break;
                        } else if (action.equals("up")) {
                            database.SaveUsers();
                        } else if (action.equals("game")) {
                            server.add_gamer(logged_in);
                            while (!server.create_game(logged_in)) {
                            }
                            game.start();
                        }
                        else if(action.equals("board")){
                            cell[][] cells=((Game)game).getBoard().getBoard();
                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    if(cells[i][j].getPiece()==null){
                                        outputStream.writeInt(0);
                                    }
                                    else if(cells[i][j].getPiece() instanceof Pawn){
                                        if(cells[i][j].getPiece().getColor()== Color.White) {
                                            outputStream.writeInt(1);
                                        }
                                        else{
                                            outputStream.writeInt(2);
                                        }
                                    }
                                    else if(cells[i][j].getPiece() instanceof Bishop){
                                        if(cells[i][j].getPiece().getColor()== Color.White) {
                                            outputStream.writeInt(3);
                                        }
                                        else{
                                            outputStream.writeInt(4);
                                        }
                                    }
                                    else if(cells[i][j].getPiece() instanceof Knight){
                                        if(cells[i][j].getPiece().getColor()== Color.White) {
                                            outputStream.writeInt(5);
                                        }
                                        else{
                                            outputStream.writeInt(6);
                                        }
                                    }
                                    else if(cells[i][j].getPiece() instanceof Rook){
                                        if(cells[i][j].getPiece().getColor()== Color.White) {
                                            outputStream.writeInt(7);
                                        }
                                        else{
                                            outputStream.writeInt(8);
                                        }
                                    }
                                    else if(cells[i][j].getPiece() instanceof King){
                                        if(cells[i][j].getPiece().getColor()== Color.White) {
                                            outputStream.writeInt(9);
                                        }
                                        else{
                                            outputStream.writeInt(10);
                                        }
                                    }
                                    else if(cells[i][j].getPiece() instanceof Queen){
                                        if(cells[i][j].getPiece().getColor()== Color.White) {
                                            outputStream.writeInt(11);
                                        }
                                        else{
                                            outputStream.writeInt(12);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
