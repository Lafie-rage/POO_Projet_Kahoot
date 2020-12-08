package client;

import javax.swing.*;

/**
 * Défini les méthodes générales partagées par toutes les vues.
 */
public interface Page {
    JPanel getContentPane();
    void close();
}
