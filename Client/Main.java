package Client;

import Client.Hosted.Hosted;

public class Main {
    public static void main(String[] args) {
        Hosted hosted = new Hosted("localhost", 12345);
        Thread hostedThread = new Thread(hosted);
        hostedThread.start();
    }
}
