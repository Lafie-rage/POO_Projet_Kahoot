package client;

import javax.swing.*;

public interface Page {
    JPanel getContentPane();
    void close();
}
