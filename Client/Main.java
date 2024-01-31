package Client;

import Client.Hosted.Hosted;
import Client.Hosted.Latency;

public class Main {
    public static void main(String[] args) {
        Hosted hosted = new Hosted("localhost", 12345);
        Latency latency = new Latency();
        Thread hostedThread = new Thread(hosted);
        Thread latencyThread = new Thread(latency);
        hostedThread.start();
        latencyThread.start();
    }
}
