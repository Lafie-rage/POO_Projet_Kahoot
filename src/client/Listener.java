package client;


import model.Question;

import java.io.IOException;

public class Listener extends Thread {
    private PageGame page;
    private Connection connection;

    public Listener(PageGame page, Connection connection) {
        this.page = page;
        this.connection = connection;
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            try {
                Question question = (Question) connection.getInput().readObject();
                if (question != null) {
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
