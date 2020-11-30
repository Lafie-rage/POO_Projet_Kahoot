package archive;

import serveur.Connexion;

import java.io.IOException;
import java.net.Socket;

public class testClient2 {
    public static void main(String[] args) throws IOException {
        for(int i=0; i<5; i++) {
            Socket soc = new Socket("localhost", 60000);
        }
    }
}
