package admin;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {

    public MainPage() {
        setContentPane(new PageLogin(this).getContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(450, 300));
        this.setResizable(false);
        pack();
        setVisible(true);

    }

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
