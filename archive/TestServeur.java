package archive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServeur extends Thread{

    @Override
    public void run() {

        try(ServerSocket serverSocket = new ServerSocket(60000)) {
            System.out.println("Début ecoute");
            while(!Thread.currentThread().isInterrupted()) {
                try(Socket socket = serverSocket.accept();InputStream is = socket.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(is));){
                    System.out.println("Prise en charge de la requete");
                    String line = reader.readLine();
                    System.out.println(line);
                    System.out.println("Fin de la prise en charge\n");
                }catch (IOException e) {
                    System.out.println("erreur avec le socket");
                    e.printStackTrace();
                }
            }
            System.out.println("fin de l'écoute");
        } catch (IOException e) {
            System.out.println("erreur avec le serveur");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestServeur serv = new TestServeur();
        serv.start();
    }
}
