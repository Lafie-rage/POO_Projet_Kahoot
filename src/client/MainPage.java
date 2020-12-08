package client;

import model.Player;
import model.Question;
import model.Score;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Class gérant les changement de vues.
 * C'est une simple JFrame chargeant les différents form depuis les autres classes.
 * Elle définit également les fonctions permettant de naviguer entre les pages.
 * Elle stocke également la connection que les différentes pages partagent ainsi que le score du joueur.
 * Ce score est crée lorsque le joueur est login.
 */
public class MainPage extends JFrame {
    private Connection connection;
    private Score score;

    /**
     * Crée la connection et affiche la vue de login.
     */
    public MainPage() {
        this.connection = new Connection();
        setContentPane(new PageLogin(this).getContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Crée le score lié au joueur et affichage la vue d'attente de partie.
     *
     * @param player Le joueur connecté.
     */
    public void loadLobbyPage(Player player) {
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
     * Page de retour au lobby d'attente de partie. Elle permet d'appeler la page lobby en transmettant le score.
     */
    public void returnToLobby() {
        try {
            connection.getOutput().writeObject(score);
            setContentPane(new PageLobby(this, score).getContentPane());
            validate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Méthode d'ajout d'un point au score.
     */
    public void addPoint() {
        this.score.addPoint(1);
    }

    /**
     * Rédéfiniton de fermeture de l'application.
     */
    @Override
    public void dispose() {
        if (connection != null)
            this.connection.close();
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
