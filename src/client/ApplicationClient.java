package client;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class ApplicationClient extends JFrame {
    private static final String host = "127.0.0.1";
    private static final int port = 65000;
    private Connection connection;
    private Listener listener;
    private JPanel contentPane;

    public ApplicationClient() {
        try {
            connection = new Connection(new Socket(host, port));
        } catch (IOException e) {
            e.printStackTrace();
        }

        contentPane =

    }
}
