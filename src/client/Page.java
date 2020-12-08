package client;

import javax.swing.*;

/**
 * Defini les methodes generales partagees par toutes les vues.
 */
public interface Page {
    JPanel getContentPane();
    void close();
}
