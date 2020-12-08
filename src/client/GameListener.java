package client;

import utils.Commons;

import java.io.IOException;
import java.net.SocketException;

/**
 * Listener sur l'input du client pour la vue game.
 */
public class GameListener extends Listener<PageGame> {
    public GameListener(PageGame page, Connection connection) {
        super(page, connection);
    }

    /**
     * Attente d'un message de la part du server.
     * L'utilisation de plusieurs listeners oblige la synchronisation sur la connexion.
     */
    @Override
    public void run() {
        try {
            Object objectMessage;
            synchronized (connection) {
                do {
                    objectMessage = connection.getInput().readObject();
                } while(!currentThread().isInterrupted() && objectMessage == null);
            }

            if (objectMessage instanceof String) {
                String message = (String) objectMessage;
                if (message.equals(Commons.ENDING_CONNECTION_SIGNAL)) {
                    context.close();
                    interrupt();
                }
            }
        } catch (IOException e) {
            this.interrupt();
            if (!(e instanceof SocketException))
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.interrupt();
    }
}
