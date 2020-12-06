package client;


import model.Answer;
import model.Question;

import java.io.IOException;
import java.util.List;

public class Listener extends Thread {
    private PageGameattendez page;
    private Connection connection;

    public Listener(PageGameattendez page, Connection connection) {
        this.page = page;
        this.connection = connection;
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            try {
                Question question = (Question) connection.getInput().readObject();
                if(question != null) {
                    page.updateQuestionText(question.getText());
                    List<Answer> proposals = question.getProposals();
                    page.updatePropField1Text(proposals.get(0).getText());
                    page.updatePropField2Text(proposals.get(1).getText());
                    page.updatePropField3Text(proposals.get(2).getText());
                    page.updatePropField4Text(proposals.get(3).getText());
                }
            } catch(IOException e) {
                this.interrupt();
                e.printStackTrace();
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        this.interrupt();
    }
}
