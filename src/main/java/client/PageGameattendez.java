package client;

import javax.swing.*;
import java.net.Socket;

public class PageGameattendez {
    private JTextArea title = new JTextArea(),
                      question = new JTextArea(),
                      propField1 = new JTextArea(),
                      propField2 = new JTextArea(),
                      propField3 = new JTextArea(),
                      propField4 = new JTextArea(),
                      chooseAnAnswerText = new JTextArea();

    private JButton chooseProp1 = new JButton(),
                    chooseProp2 = new JButton(),
                    chooseProp3 = new JButton(),
                    chooseProp4 = new JButton();

    private Socket server;

    private Connection connection;
    private Listener listener;

    public PageGameattendez(Connection connection) {
        this.connection = connection;
        listener = new Listener(this, this.connection);
    }

    public void updateQuestionText(String text) {
        question.setText(text);
    }

    public void updatePropField1Text(String text) {
        propField1.setText(text);
    }

    public void updatePropField2Text(String text) {
        propField2.setText(text);
    }

    public void updatePropField3Text(String text) {
        propField3.setText(text);
    }

    public void updatePropField4Text(String text) {
        propField4.setText(text);
    }
}
