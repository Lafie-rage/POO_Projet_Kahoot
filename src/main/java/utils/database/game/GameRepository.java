package utils.database.game;

import model.Game;
import model.Question;

public class GameRepository {
    private static final GameDAO dao = new GameDAO();

    public static boolean add(Game item) {
        int id = dao.add(item);
        if (id > -1) {
            item.setId(id);
            return true;
        }
        return false;
    }
}
