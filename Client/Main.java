package Client;

import Client.Hosted.Hosted;

public class Main {
    public static void main(String[] args) {
        Hosted hosted = new Hosted("25.3.73.169", 12345);
        Thread hostedThread = new Thread(hosted);
        hostedThread.start();
    }
}
