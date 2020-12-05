package utils.database.score;

import quizz.model.Score;
import utils.database.category.CategoryDAO;

public class ScoreRepository {
    ScoreDAO dao = new ScoreDAO();

    public boolean add(Score item) {
        return dao.add(item);
    }
}
