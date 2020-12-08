package client;

import model.Player;
import utils.database.player.PlayerRepository;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Vue de login de l'interface graphique du client.
 * Elle contient une reference vers le context (la MainPage) et vers les differents objects swing de la vue.
 * Elle definit egalement les fonctions de mise a jour dynamique de la vue ainsi que de changement de vue.
 */
public class PageLogin implements Page {
    private final MainPage context;
    private JTextField usernameField;
    private JButton buttonLogin;
    private JButton buttonExit;
    private JPasswordField passwordField;
    private JPanel contentPane;

    /**
     * Definit le context de la vue et les actions liees aux boutons sur cette vue.
     * @param context la MainPage de l'application
     */
    public PageLogin(MainPage context) {
        this.context = context;

        // Clique sur le bouton de login.
        buttonLogin.addActionListener(e -> {
            logMeIn();
        });

        // Clique sur le bouton exit.
        buttonExit.addActionListener(e -> {
            context.dispose();
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    logMeIn();
                }
            }
        });
    }

    private void logMeIn() {
        String login = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        Player player = PlayerRepository.logon(login,password);
        if(player!=null)
            context.loadLobbyPage(player);
        else
            JOptionPane.showMessageDialog(context, "Incorrect username or password.");
        passwordField.setText("");
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
