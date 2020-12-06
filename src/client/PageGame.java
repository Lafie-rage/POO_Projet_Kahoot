package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageGame {
    private final MainPage context;
    private JPanel contentPane;
    private JButton buttonExit;
    private JButton buttonProp1;
    private JButton buttonProp2;
    private JButton buttonProp3;
    private JButton buttonProp4;
    private JTextArea questionField;
    private JTextArea prop1Field;
    private JTextArea prop2Field;
    private JTextArea prop3Field;
    private JTextArea prop4Field;

    public PageGame(MainPage context) {
        this.context = context;
        buttonProp1.addActionListener(e -> {

        });

        buttonProp2.addActionListener(e -> {

        });

        buttonProp3.addActionListener(e -> {

        });

        buttonProp4.addActionListener(e -> {

        });

        buttonExit.addActionListener(e -> {

        });
    }

    public JPanel getContentPane() {
        return this.contentPane;
    }
}
