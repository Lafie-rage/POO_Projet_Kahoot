package archive;

import serveur.TestServeur;

import java.io.IOException;
import java.net.Socket;

public class TestThread extends Thread{


    public static void main(String[] args) throws InterruptedException {
        TestServeur serv = new TestServeur();
        serv.run();
        System.out.println("np,kpir");
        Thread.sleep(1000);
        System.out.println("np,kpir");
        try(Socket socket = new Socket("localhost",60000);) {
            System.out.println("bonjour");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
