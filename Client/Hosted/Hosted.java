package Client.Hosted;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;

import Client.Ball.Ball;
import Client.Player.Player;
import Client.StarterPack.Window;

public class Hosted implements Runnable {

    Socket connection;
    ObjectOutputStream output;
    ObjectInputStream input;

    InetAddress inetAddress;

    String IP;
    int port;

    Player player;
    Ball ball;

    Date date = new Date();

    public static long latency;

    public Hosted(String IP, int port){
        this.IP = IP;
        this.port = port;
    }

    @Override
    public void run() {
        runClient();
    }

    public void runClient(){
        connectToServer();
        getIOstreams();
        getIP();
        processConnection();
    }

    public void connectToServer(){
        System.out.println("Connecting to server...");
        do {
            try {
                connection = new Socket(IP, port);
            } catch (IOException e) {
                // System.out.println("Failed to connect to server!");
            }
        } while (connection == null);
        System.out.println("Connected to server!");
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

    public void getIP(){
        System.out.println("Getting IP...");
        inetAddress = connection.getInetAddress();
        System.out.println("Got IP: " + inetAddress.getHostAddress());
    }

    public void getLatency(){
        // int maxTime = 1000; //How much time to reach the Address in miliseconds
        // long finishTime = 0;
        // long startTime = System.nanoTime();
        // try {
        //     if(inetAddress.isReachable(maxTime)){
        //         finishTime = System.nanoTime();
        //     } else {
        //         finishTime = System.nanoTime();
        //     }
        //     latency = finishTime - startTime;
        // } catch (IOException e) {
        // }
        int timeOut = 999;
        long finishTime = 0;
        long startTime = System.nanoTime();
        if(isReachable(IP, port, timeOut)){
            finishTime = System.nanoTime();
        } else {
            finishTime = System.nanoTime();
        }
        latency = finishTime - startTime;
    }

    public boolean isReachable(String address, int port, int timeOut){
        Socket pseudoSocket = new Socket();
        try {
            pseudoSocket.connect(new InetSocketAddress(address, port), timeOut);
            pseudoSocket.close();
            return true;
        } catch (IOException e) {
        }
        return false;
    }

    public void processConnection(){
        System.out.println("Game started!");
        Window window = new Window();
        while (connection.isConnected() && window.isVisible()) {
            getLatency();
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
            output.writeObject(Ball.ballIntersectPlayer(Player.player));
            output.flush();
        } catch (IOException e) {
        }
    }

    public void receiveData(){
        try {
            date = new Date();
            Player.otherPlayer.x = (int) input.readObject();
            Player.otherPlayer.y = (int) input.readObject();
            Ball.x = (int) input.readObject();
            Ball.y = (int) input.readObject();
            Player.otherPlayerPont = (int) input.readObject();
            Player.playerPont = (int) input.readObject();
            Ball.speedX = (int) input.readObject();
            Ball.speedY = (int) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
        }
    }

}
