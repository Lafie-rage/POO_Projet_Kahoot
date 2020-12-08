package client;

import model.Question;

import java.io.IOException;
import java.util.List;

/**
 * Listener sur l'input du client pour la vue lobby.
 */
public class LobbyListener extends Listener<PageLobby> {
    public LobbyListener(PageLobby page, Connection connection) {
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
                } while (!currentThread().isInterrupted() && objectMessage == null);
            }
            System.out.println(objectMessage.getClass());
            if (objectMessage instanceof List &&
                    ((List<?>) objectMessage).size() > 0 && // Verifie si c'est une List<Question>
                    ((List<?>) objectMessage).get(0) instanceof Question) {
                List<Question> questions = (List<Question>) objectMessage;
                context.launchGame(questions);
                this.interrupt();
            } else if (objectMessage instanceof String) {
                String message = (String) objectMessage;
                if (message.equals("ENDING_CONNECTION")) {
                    context.close();
                    interrupt();
                }
            }
        } catch (IOException e) {
            this.interrupt();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.interrupt();
    }
}
