package Client;

import Client.Hosted.Hosted;
import Client.Hosted.Latency;

public class Main {
    public static void main(String[] args) {
        Hosted hosted = new Hosted("25.2.160.69", 12345);
        Latency latency = new Latency();
        Thread hostedThread = new Thread(hosted);
        Thread latencyThread = new Thread(latency);
        hostedThread.start();
        latencyThread.start();
    }
}
