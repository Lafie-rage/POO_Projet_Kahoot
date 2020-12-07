package client;

import model.Player;
import utils.database.player.PlayerRepository;

import javax.swing.*;

public class PageLogin implements Page {
    private final MainPage context;
    private JTextField usernameField;
    private JButton buttonLogin;
    private JButton buttonExit;
    private JPasswordField passwordField;
    private JPanel contentPane;

    public PageLogin(MainPage context) {
        this.context = context;

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

        buttonExit.addActionListener(e -> {
            context.dispose();
        });
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
