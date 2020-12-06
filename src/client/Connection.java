package client;

import model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private Player player;
    private Socket server;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public Connection(Socket server) throws IOException {
        this.server = server;
        input = new ObjectInputStream(server.getInputStream());
        output = new ObjectOutputStream(server.getOutputStream());
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