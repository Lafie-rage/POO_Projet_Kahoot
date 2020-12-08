import client.MainPage;
import server.Server;
import utils.Commons;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server.main(null);
            for(int i = 0; i < Commons.MAX_PLAYER_IN_ROOM; i++)
                MainPage.main(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
