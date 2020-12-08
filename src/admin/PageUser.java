package admin;



import model.Player;
import utils.database.player.PlayerRepository;

import javax.swing.*;
import java.util.List;

/**
 * Vue de gestion des utilisateurs  de l'interface graphique de l'admin.
 * Elle contient une reference vers le context (la MainPage) et vers les differents objects swing de la vue.
 * Elle permet de visualiser les utilisateurs, de les supprimers et d'en créer des nouveaux'.
 */
public class PageUser {
    private final MainPage context;
    private JPanel contentPane;
    private JTextArea userField;
    private JComboBox comboBox1;
    private JButton deleteButton;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton createButton;
    private JTextArea errorTextArea;
    private JButton menuButton;
    private JButton exitButton;

    /**
     * Definit le context de la vue et les actions liees aux boutons sur cette vue.
     * @param context la référence vers la MainPage de l'application.
     */
    public PageUser(MainPage context) {
        this.context = context;
        filluserField();

        deleteButton.addActionListener(e -> {
            ComboKeyValue currentKey = (ComboKeyValue) comboBox1.getSelectedItem();
            PlayerRepository.remove(currentKey.getKey());
            resetField();
            filluserField();
        });
        createButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = String.valueOf(passwordField.getPassword());
            Player player = new Player(login,password);
            System.out.println(player);
            resetField();
            if (!PlayerRepository.add(player))
                errorTextArea.setText("Unable to create user");
            filluserField();

        });
        menuButton.addActionListener(e -> {
            context.changePage("menu");
        });
        exitButton.addActionListener(e -> {
            context.dispose();
        });
    }
    /**
     * Permet de reinitialiser les differents champs de la page
     */
    private void resetField(){
        userField.setText("");
        comboBox1.removeAllItems();
        errorTextArea.setText("");
        loginField.setText("");
        passwordField.setText("");
    }
    /**
     * Permet d'afficher les utilisateurs  contenus dans la base de donnees
     * Permet de remplir la liste déroulante pour afficher les utilisateurs dans celui-ci
     * Seul le compte admin n'est pas afficher et ne peut être modifié
     */
    private void  filluserField() {
        List<Player> listPlayer = PlayerRepository.getAll();
        for (Player item : listPlayer) {
            if (item.getId() != 0) {
                userField.append(item.getPseudo() + "\n");
                comboBox1.addItem(new ComboKeyValue(item.getId(), item.getPseudo()));
            }
        }
    }
    public JPanel getContentPane() {
        return this.contentPane;
    }
}
