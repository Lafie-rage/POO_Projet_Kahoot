package client;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class ApplicationClient extends JDialog {
    private static final String host = "89.81.102.3";
    private static final int port = 65000;
    private JPanel contentPane;
    private JButton buttonEnvoie;
    private JTextArea zoneMessage;
    private JTextField name;
    private JTextField saisieTexte;
    private Connexion connexion;
    private Ecouteur ecouteur;

    public ApplicationClient() {

        try {
            connexion=new Connexion(new Socket(host, port));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ecouteur = new Ecouteur(zoneMessage,connexion);
        ecouteur.start();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonEnvoie);

        buttonEnvoie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEnvoie();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onEnvoie() {
        String nom=name.getText();
        String line=saisieTexte.getText();
        try {
            connexion.getOos().writeObject(new Message(nom,line));
            connexion.getOos().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saisieTexte.setText("");
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (connexion!=null)
        {
            try {
                connexion.getOos().writeObject(null);
                connexion.getOos().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ecouteur.interrupt();
            connexion.close();
        }
    }

    public static void main(String[] args) {

        ApplicationClient dialog = new ApplicationClient();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
