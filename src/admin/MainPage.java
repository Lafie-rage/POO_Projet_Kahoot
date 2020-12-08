package admin;

import javax.swing.*;
import java.awt.*;

/**
 * La page principale de la partie admin.
 */
public class MainPage extends JFrame {

    /**
     * Constructeur du conteneur de l'app admin, il affiche la page login par défaut.
     */
    public MainPage() {
        setContentPane(new PageLogin(this).getContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(450, 300));
        this.setResizable(false);
        pack();
        setVisible(true);

    }

    /**
     * Méthode permetant de choisir la page a afficher. Si l'argument n'est pas bon, l'application se ferme.
     * @param option nom de la page à afficher (menu , user , quizz).
     */
    public void changePage(String option) {
        switch (option){
            case "menu" :
                setContentPane(new MenuAdmin(this).getContentPane());
                break;
            case "user" :
                setContentPane(new PageUser(this).getContentPane());
                break;
            case "quizz" :
                setContentPane(new PageQuizz(this).getContentPane());
                break;
            default:
                this.dispose();
                break;
        }
        validate();
    }

    public static void main(String[] args) {
        MainPage mainPage = new MainPage();
    }

}
