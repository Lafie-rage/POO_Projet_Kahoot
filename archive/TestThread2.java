package archive;

import java.io.IOException;
import java.net.Socket;

public class TestThread2 extends Thread{


    public static void main(String[] args) throws InterruptedException {

        try(Socket socket = new Socket("localhost",60000);) {
            System.out.println("bonjour");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
