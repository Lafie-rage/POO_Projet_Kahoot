package client;

import model.Question;

import java.io.IOException;

public class GameListener extends Listener<PageGame>{
    public GameListener(PageGame page, Connection connection) {
        super(page, connection);
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            try {
                Object objectMessage = connection.getInput().readObject();
                if (objectMessage instanceof String) {
                    String message = (String) objectMessage;
                    if(message.equals("ENDING_CONNECTION")) {
                        page.close();
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
