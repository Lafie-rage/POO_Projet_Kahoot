package quizz.model;

public class Score {
    private int score = 0;
    private Player player;
    private Game game;

    public Score(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public int getScore() {
        return this.score;
    }

    public void addPoint(int points) {
        if (points > 0) {
            this.score += points;
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public Game getGame() {
        return this.game;
    }
}
