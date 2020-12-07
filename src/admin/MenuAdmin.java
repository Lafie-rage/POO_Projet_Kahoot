package admin;

import javax.swing.*;
import java.awt.event.*;

public class MenuAdmin {
    private final MainPage context;
    private JPanel contentPane;
    private JButton buttonUser;
    private JButton buttonExit;
    private JButton buttonQuizz;

    public MenuAdmin(MainPage context) {
        this.context = context;

        buttonUser.addActionListener(e -> {
            context.changePage("user");
        });

        buttonQuizz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });

        buttonExit.addActionListener(e -> {
            context.dispose();
        });


    }

    public JPanel getContentPane() {
        return this.contentPane;
    }



}
