package utils.database.game;

import quizz.model.Game;
import quizz.model.Question;

public class GameRepository {
    GameDAO dao = new GameDAO();

    public boolean add(Game item) {
        int id = dao.add(item);
        if (id > -1) {
            item.setId(id);
            return true;
        }
        return false;
    }
}
