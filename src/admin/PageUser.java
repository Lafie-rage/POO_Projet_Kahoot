package admin;



import model.Player;
import utils.database.player.PlayerRepository;

import javax.swing.*;
import java.util.List;

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


    public PageUser(MainPage context) {
        this.context = context;
        filluserField();

        deleteButton.addActionListener(e -> {
            ComboKeyValue currentKey = (ComboKeyValue) comboBox1.getSelectedItem();
            PlayerRepository.remove(currentKey.getKey());
            resetField();
            filluserField();
            JFileChooser choix = new JFileChooser();
            int retour=choix.showOpenDialog(context);
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
    }

    private void resetField(){
        userField.setText("");
        comboBox1.removeAllItems();
        errorTextArea.setText("");
        loginField.setText("");
        passwordField.setText("");
    }
    private void  filluserField(){
        List<Player> listPlayer=PlayerRepository.getAll();
        for (Player item:listPlayer) {
            userField.append(item.getPseudo()+"\n");
            comboBox1.addItem(new ComboKeyValue(item.getId(),item.getPseudo()));
        }


    }

    public JPanel getContentPane() {
        return this.contentPane;
    }
}
