package client;

import model.Player;
import model.Question;
import model.Score;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Class gerant les changement de vues.
 * C'est une simple JFrame chargeant les differents form depuis les autres classes.
 * Elle definit egalement les fonctions permettant de naviguer entre les pages.
 * Elle stocke egalement la connection que les differentes pages partagent ainsi que le score du joueur.
 * Ce score est cree lorsque le joueur est login.
 */
public class MainPage extends JFrame {
    private Connection connection;
    private Score score;

    /**
     * Cree la connection et affiche la vue de login.
     */
    public MainPage() {
        setContentPane(new PageLogin(this).getContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 1000));
        this.setResizable(false);
        pack();
        setVisible(true);
    }

    public int getScore() {
        return score.getScore();
    }

    /**
     * Cree le score lie au joueur et affichage la vue d'attente de partie.
     *
     * @param player Le joueur connecte.
     */
    public void loadLobbyPage(Player player) {
        this.connection = new Connection();
        this.score = new Score(player);
        setContentPane(new PageLobby(this).getContentPane());
        validate();
    }

    /**
     * Affiche la vue de partie en transmettant la liste de questions de celle-ci.
     *
     * @param questions
     */
    public void loadGamePage(List<Question> questions) {
        setContentPane(new PageGame(this, questions).getContentPane());
        validate();
    }

    /**
     * Methode d'ajout d'un point au score.
     */
    public void addPoint() {
        this.score.addPoint(1);
    }

    /**
     * Redefiniton de fermeture de l'application.
     */
    @Override
    public void dispose() {
        if (connection != null)
            this.connection.closeAndNotify();
        super.dispose();
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static void main(String[] args) {
        // ouverture de la l'interface graphique du client.
        MainPage mainPage = new MainPage();
    }
}
