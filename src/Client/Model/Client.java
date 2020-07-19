package Client.Model;

import java.io.*;
import java.net.Socket;


public class Client {
    private String IP = "localhost";
    private int port = 8080;
    DataInputStream inputStream;
    DataOutputStream outputStream;

    public Client() throws IOException {
    }

    public boolean connectToServer() {
        try {
            Socket socket = new Socket(IP, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            inputStream=in;
            outputStream=out;
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean login(String username,String password) throws IOException {
        outputStream.writeUTF(username);
        outputStream.writeUTF(password);
        outputStream.writeUTF("login");
        if(inputStream.readUTF().equals("success")){
            return true;
        }
        return false;
    }

    public boolean register(String username,String password) throws IOException {
        outputStream.writeUTF(username);
        outputStream.writeUTF(password);
        outputStream.writeUTF("register");
        if(inputStream.readUTF().equals("success")){
            return true;
        }
        return false;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void changepass(String pass) throws IOException {
        outputStream.writeUTF("cp");
        outputStream.writeUTF(pass);
    }

    public void logout() throws IOException {
        outputStream.writeUTF("lo");
    }

    public void update() throws IOException {
        outputStream.writeUTF("up");
    }
}
