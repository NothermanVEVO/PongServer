package Client;

import Client.Hosted.Hosted;

public class Main {
    public static void main(String[] args) {
        Hosted hosted = new Hosted("25.2.160.69", 12345);
        Thread hostedThread = new Thread(hosted);
        hostedThread.start();
    } 
}
