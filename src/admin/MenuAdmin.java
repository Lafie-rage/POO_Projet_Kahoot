package admin;

import javax.swing.*;
import java.awt.event.*;

/**
 * Vue du menu d'administration  de l'interface graphique de l'admin.
 * Elle contient une reference vers le context (la MainPage) et vers les differents objects swing de la vue.
 * Elle permet de choisir la page à afficher.
 */
public class MenuAdmin {
    private final MainPage context;
    private JPanel contentPane;
    private JButton buttonUser;
    private JButton buttonExit;
    private JButton buttonQuizz;

    /**
     * Definit le context de la vue et les actions liees aux boutons sur cette vue.
     * @param context la référence vers la MainPage de l'application.
     */
    public MenuAdmin(MainPage context) {
        this.context = context;


        buttonUser.addActionListener(e -> {
            context.changePage("user");
        });

        buttonQuizz.addActionListener(e -> {
            context.changePage("quizz");
        });

        buttonExit.addActionListener(e -> {
            context.dispose();
        });


    }

    public JPanel getContentPane() {
        return this.contentPane;
    }



}
