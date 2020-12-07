package client;

import model.Player;
import model.Question;
import model.Score;

import javax.swing.*;
import java.io.ObjectOutputStream;
import java.util.List;

public class MainPage extends JFrame {
    private Connection connection;
    private Score score;

    public MainPage() {
        setContentPane(new PageLogin(this).getContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void loadLobbyPage(Player player) {
        this.score = new Score(player);
        setContentPane(new PageLobby(this).getContentPane());
    }

    public void loadGamePage(List<Question> questions) {
        setContentPane(new PageGame(this, questions).getContentPane());
        validate();
    }

    public void addPoint() {
        this.score.addPoint(1);
    }

    public void close() {
        dispose();
    }

    @Override
    public void dispose() {
        this.connection.close();
        super.dispose();
    }

    public ObjectOutputStream getOutput() {
        return this.connection.getOutput();
    }

    public static void main(String[] args) {
        MainPage mainPage = new MainPage();
    }
}
