package client;

import javax.swing.*;

public class MainPage extends JFrame {

    public MainPage() {
        setContentPane(new PageLogin(this).getContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void loadGamePage() {
        setContentPane(new PageGame(this).getContentPane());
        validate();
    }

    public static void main(String[] args) {
        MainPage mainPage = new MainPage();
    }

}
