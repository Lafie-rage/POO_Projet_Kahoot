package client;

import java.io.IOException;

/**
 * Listener sur l'input du client pour la vue game.
 */
public class GameListener extends Listener<PageGame> {
    public GameListener(PageGame page, Connection connection) {
        super(page, connection);
    }

    /**
     * Attente d'un message de la part du server.
     */
    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            try {
                Object objectMessage = connection.getInput().readObject();
                if (objectMessage instanceof String) {
                    String message = (String) objectMessage;
                    if (message.equals("ENDING_CONNECTION")) {
                        context.close();
                    }
                }
            } catch (IOException e) {
                this.interrupt();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        this.interrupt();

    }
}
