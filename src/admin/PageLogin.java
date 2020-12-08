package admin;


import model.Player;
import utils.database.player.PlayerRepository;

import javax.swing.*;
/**
 * Vue de login de l'interface graphique de l'admin.
 * Elle contient une reference vers le context (la MainPage) et vers les differents objects swing de la vue.
 * Elle definit egalement les fonctions de mise a jour dynamique de la vue ainsi que de changement de vue.
 */
public class PageLogin {
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

        buttonLogin.addActionListener(e -> {
            String login = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            Player player = PlayerRepository.logon(login,password);
            if(player!=null)
            {
                if (player.getId()==0)
                    context.changePage("menu");
                else
                    JOptionPane.showMessageDialog(context, "You are not authorized to access the admin part.");
            }
            else
                JOptionPane.showMessageDialog(context, "Incorrect username or password.");
            passwordField.setText("");
        });

        buttonExit.addActionListener(e -> {
            context.dispose();
        });
    }

    public JPanel getContentPane() {
        return this.contentPane;
    }
}
