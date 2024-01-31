package Client.Hosted;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Latency implements Runnable {

    public static long latency;

    @Override
    public void run() {
        while (true) {
            getLatency();
        }
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
        long startTime = System.currentTimeMillis();
        if(isReachable(Hosted.IP, Hosted.port, timeOut)){
            finishTime = System.currentTimeMillis();
            latency = finishTime - startTime;
        } else{
            latency = -3;
        }
        // System.out.println("Start time: " + startTime + " Finish time: " + finishTime);
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
    
}
