package client;

import model.Question;

import javax.swing.*;
import java.util.List;

public class PageLobby implements Page {
    private final MainPage context;
    private final Connection connection;
    private JPanel contentPane;
    private JButton buttonExit;
    private JTextArea scoreField;

    public PageLobby(MainPage context) {
        this.context = context;
        this.connection = new Connection();
        buttonExit.addActionListener(e -> {
            context.dispose();
        });
    }

    public void launchGame(List<Question> questions) {
        context.loadGamePage(questions);
    }

    @Override
    public JPanel getContentPane() {
        return this.contentPane;
    }

    @Override
    public void close() {
        context.close();
    }
}
