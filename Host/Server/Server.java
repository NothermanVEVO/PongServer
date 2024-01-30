package Host.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Host.Ball.Ball;
import Host.Player.Player;
import Host.StarterPack.Window;

public class Server implements Runnable {

    ServerSocket serverSocket;
    Socket connection;
    ObjectOutputStream output;
    ObjectInputStream input;

    int port;

    Ball ball;
    Player player;

    public Server(int port){
        this.port = port;
    }

    @Override
    public void run() {
        runServer();
    }

    public void runServer(){
        System.out.println("Creating server...");
        try {
            serverSocket = new ServerSocket(port, 100);
        } catch (IOException e) {
            System.out.println("Failed to create server!");
        }
        System.out.println("Server created!");
        waitForConnection();
        getIOstreams();
        processConnection();
    }

    public void waitForConnection(){
        System.out.println("Waiting for another player...");
        try {
            connection = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Failed to accept player!");
        }
        System.out.println("Other player connected!");
    }

    public void getIOstreams(){
        System.out.println("Getting I/O streams...");
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            System.out.println("Failed to get I/O streams!");
        }
        System.out.println("Got I/O streams!");
    }

    public void processConnection(){
        System.out.println("Game started!");
        Window window = new Window();
        while (connection.isConnected() && window.isVisible()) {
            sendData();
            receiveData();
        }
        closeConnection();

    }

    public void closeConnection(){
        System.out.println("Closing connection...");
        try {
            connection.close();
            output.close();
            input.close();
        } catch (IOException e) {
            System.out.println("Failed to close connection!");
        }
        System.out.println("Connection closed!");
    }

    public void sendData(){
        try {
            //Sending position of THIS player
            output.writeObject(Player.player.x);
            output.writeObject(Player.player.y);
            //Sending position of THIS ball
            output.writeObject(Ball.x);
            output.writeObject(Ball.y);
            //Sending pontuation of both players
            output.writeObject(Player.playerPont);
            output.writeObject(Player.otherPlayerPont);
            //Sending velocity of THIS ball
            output.writeObject(Ball.speedX);
            output.writeObject(Ball.speedY);
            output.flush();
        } catch (IOException e) {
        }
    }

    public void receiveData(){
        try {
            Player.otherPlayer.x = (int) input.readObject();
            Player.otherPlayer.y = (int) input.readObject();
            Ball.intersectOtherPlayer = (boolean) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
        }
    }
    
}
