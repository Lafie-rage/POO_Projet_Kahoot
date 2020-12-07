package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private final String HOST = "127.0.0.1";
    private final int PORT = 60_000;
    private Socket server;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public Connection() {
        try {
            this.server = new Socket(HOST, PORT);
            input = new ObjectInputStream(server.getInputStream());
            output = new ObjectOutputStream(server.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getInput() {
        return this.input;
    }

    public ObjectOutputStream getOutput() {
        return this.output;
    }

    public void close() {
        try {
            input.close();
            output.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
