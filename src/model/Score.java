package model;

import java.io.Serializable;

/**
 * Classe modele representant la donnee telle qu'elle est stockee dans la BDD.
 * Implemente @see Serializable afin
 */
public class Score implements Serializable {
    private int score = 0;
    private Player player;
    private Game game;

    public Score(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public Score(Player player) {
        this.player = player;
    }

    public int getScore() {
        return this.score;
    }

    public void addPoint(int points) {
        if (points > 0) {
            this.score += points;
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Game getGame() {
        return this.game;
    }
}
