package Client;

import Client.Hosted.Hosted;

public class Main {
    public static void main(String[] args) {
        Hosted hosted = new Hosted("25.5.199.20", 12345);
        Thread hostedThread = new Thread(hosted);
        hostedThread.start();
    }
}
