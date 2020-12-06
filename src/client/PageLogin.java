package client;

import javax.swing.*;

public class PageLogin {
    private final MainPage context;
    private JTextField usernameField;
    private JButton buttonLogin;
    private JButton buttonExit;
    private JPasswordField passwordField;
    private JPanel contentPane;

    public PageLogin(MainPage context) {
        this.context = context;

        buttonLogin.addActionListener(e -> {
            // Gestion des log valides
            context.loadGamePage();
        });

        buttonExit.addActionListener(e -> {
            context.dispose();
        });
    }

    public JPanel getContentPane() {
        return this.contentPane;
    }
}
