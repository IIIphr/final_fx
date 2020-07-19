package Server.Model;

import Server.Model.Communications.Server;

public class Main {
    public static void main(String[] args) {
        Thread server=new Server();
        server.start();
    }
}
