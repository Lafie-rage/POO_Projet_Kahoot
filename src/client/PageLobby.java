package client;

import model.Question;
import model.Score;

import javax.swing.*;
import java.util.List;

/**
 * Vue du lobby de l'interface graphique du client.
 * Elle contient une référence vers le context (la MainPage) et vers les différents objects swing de la vue.
 * Elle définit également les fonctions de mise à jour dynamique de la vue ainsi que de changement de vue.
 */
public class PageLobby implements Page {
    private final MainPage context;
    private final LobbyListener listener;
    private JPanel contentPane;
    private JButton buttonExit;
    private JTextArea scoreField;

    /**
     * Définit le context de la vue et les actions liées aux boutons sur cette vue.
     * Ce constructeur permet l'affichage du lobby avant l'accès à la 1ère partie du joueur depuis sa dernière connexion.
     * @param context la MainPage de l'application
     */
    public PageLobby(MainPage context) {
        this.context = context;

        // Clique sur le bouton exit.
        this.listener = new LobbyListener(this, context.getConnection());
        buttonExit.addActionListener(e -> {
            context.dispose();
        });
    }

    /**
     * Définit le context de la vue et les actions liées aux boutons sur cette vue.
     * Ce constructeur permet l'affichage du lobby lors de son accès après avoir finit une partie précédente.
     * @param context la MainPage de l'application
     * @param lastScore le score du joueur connecté
     */
    public PageLobby(MainPage context, Score lastScore) {
        this(context);
        scoreField.setText("Your last score was : " + lastScore.getScore() + ". What an incredible one !");
    }

    /**
     * Affiche la vue de la partie.
     * Cette méthode est appellée lorsque le serveur notifie le lancement de la partie.
     * @param questions la liste des questions de la partie qui va se lancer.
     */
    public void launchGame(List<Question> questions) {
        context.loadGamePage(questions);
    }

    @Override
    public JPanel getContentPane() {
        return this.contentPane;
    }

    /**
     * Permet la fermeture proprement de la vue ainsi que de la connection.
     */
    @Override
    public void close() {
        context.dispose();
    }
}
