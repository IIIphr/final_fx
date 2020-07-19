package Server.Model;

import Server.Model.Communications.Server;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Thread server=new Server();
        Scanner scanner=new Scanner(System.in);
        System.out.printf("port:");
        int port=scanner.nextInt();
        ((Server)server).setPort(port);
        ((Server)server).start_socket();
        server.start();
    }
}
