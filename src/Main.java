import client.MainPage;
import server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server.main(null);
            MainPage.main(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
