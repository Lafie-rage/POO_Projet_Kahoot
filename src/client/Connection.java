package client;

import utils.Commons;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class representant la connexion entre le serveur et le client.
 * Cette classe sert uniquement à définir le socket et ses streams d'entrée et sortie.
 */
public class Connection {
    private Socket server;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    /**
     * Ouvre le socket et crée les streams associés.
     * Si le serveur n'est pas joignable, interruption du programme et affichage de l'exception.
     */
    public Connection() {
        try {
            this.server = new Socket(Commons.HOST, Commons.PORT);
            input = new ObjectInputStream(server.getInputStream());
            output = new ObjectOutputStream(server.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getInput() {
        return this.input;
    }

    public ObjectOutputStream getOutput() {
        return this.output;
    }

    /**
     * Ferme le socket et les streams associées à celui-ci.
     */
    public void close() {
        try {
            input.close();
            output.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
