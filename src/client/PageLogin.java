package client;

import model.Player;
import utils.database.player.PlayerRepository;

import javax.swing.*;

/**
 * Vue de login de l'interface graphique du client.
 * Elle contient une référence vers le context (la MainPage) et vers les différents objects swing de la vue.
 * Elle définit également les fonctions de mise à jour dynamique de la vue ainsi que de changement de vue.
 */
public class PageLogin implements Page {
    private final MainPage context;
    private JTextField usernameField;
    private JButton buttonLogin;
    private JButton buttonExit;
    private JPasswordField passwordField;
    private JPanel contentPane;

    /**
     * Définit le context de la vue et les actions liées aux boutons sur cette vue.
     * @param context la MainPage de l'application
     */
    public PageLogin(MainPage context) {
        this.context = context;

        // Clique sur le bouton de login.
        buttonLogin.addActionListener(e -> {
            String login = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            Player player = PlayerRepository.logon(login,password);
            if(player!=null)
                context.loadLobbyPage(player);
            else
                JOptionPane.showMessageDialog(context, "Incorrect username or password.");
            passwordField.setText("");
        });

        // Clique sur le bouton exit.
        buttonExit.addActionListener(e -> {
            context.dispose();
        });
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
