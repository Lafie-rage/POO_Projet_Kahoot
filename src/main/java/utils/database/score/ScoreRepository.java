package utils.database.score;

import model.Score;


public class ScoreRepository {
    private static final ScoreDAO dao = new ScoreDAO();

    public static boolean add(Score item) {
        return dao.add(item);
    }
}
