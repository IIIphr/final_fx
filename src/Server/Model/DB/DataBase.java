package Server.Model.DB;

import Server.Model.Game.GameRecord;
import Server.Model.User.User;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class DataBase {
    private final static DataBase instance = new DataBase();
    private List<GameRecord> Games;
    private List<User> AllUsers;


    private DataBase() {
        Games = new CopyOnWriteArrayList<>();
        AllUsers = new CopyOnWriteArrayList<>();
    }

    public static DataBase getInstance() {
        return instance;
    }


    public void SaveUsers() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get("src/Server/Model/DB/Users.ser")))) {

            objectOutputStream.writeInt(AllUsers.size());
            for (User user : AllUsers) {
                objectOutputStream.writeObject(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadUsers() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get("src/Server/Model/DB/Users.ser")))) {
            int size = objectInputStream.readInt();
            AllUsers.clear();
            for (int i = 0; i < size; i++) {
                AllUsers.add((User) objectInputStream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User user_in) {
        for (User user :AllUsers) {
            if(user_in.name.equals(user.name)){
                return false;
            }
        }
        AllUsers.add(user_in);
        return true;
    }

    public User login(String name,String password){
        for (User user :AllUsers) {
            if(name.equals(user.name)){
                if(password.equals(user.password)){
                    return user;
                }
                return null;
            }
        }
        return null;
    }

    public  void addGame(GameRecord gameRecord){
        //your code
    }


    public void changepass(String pass,User user){
        user.password=pass;
    }
}
