package Host;

import Host.Server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(12345);
        Thread serverThread = new Thread(server);
        serverThread.start();
    }
}
