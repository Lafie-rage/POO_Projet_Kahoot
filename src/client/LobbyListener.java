package client;

import model.Question;

import java.io.IOException;
import java.util.List;

public class LobbyListener extends Listener<PageLobby> {
    public LobbyListener(PageLobby page, Connection connection) {
        super(page, connection);
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            try {
                Object objectMessage = connection.getInput().readObject();
                if (objectMessage instanceof List &&
                        ((List<?>) objectMessage).size() > 0 && // check if it's a List<Question>
                        ((List<?>) objectMessage).get(0) instanceof Question) {
                    List<Question> questions = (List<Question>) objectMessage;
                    page.launchGame(questions);
                }
            } catch (IOException e) {
                this.interrupt();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
