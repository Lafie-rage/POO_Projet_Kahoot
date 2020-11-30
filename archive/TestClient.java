package archive;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TestClient {

    private static final String host = "localhost";
    private static final int port = 60000;

    public static void connect(int i) {
        try (Socket socket = new Socket(host, port); OutputStream output = socket.getOutputStream(); PrintWriter writer = new PrintWriter(output, true)) {
            writer.println("This is a message sent to the server, it's message num " + i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            connect(i);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
