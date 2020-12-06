package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Game {
    private int id;
    private int nbQuestions = 1; // At least one question
    private Date date = new Date(Calendar.getInstance().getTime().getTime()); // Now date
    private List<Player> players = new ArrayList<>();
    private List<Question> questions = new ArrayList<>(); // Questions that will be asked to the whole players of the current game.
    private Category category;

    public Game(int nbQuestions, List<Player> players, Category category) {
        setNbQuestion(nbQuestions);
        setListJoueurs(players);
        this.category = category;
    }

    private void setNbQuestion(int nbQuestions) {
        if (nbQuestions > 0) {
            this.nbQuestions = nbQuestions;
        }
        this.questions = this.category.getQuestions(this.nbQuestions); // defining the list of questions to ask
    }

    public int getNbQuestions() {
        return nbQuestions;
    }

    public List<Player> getJoueurs() {
        return new ArrayList<>(players);
    }


    private void setListJoueurs(List<Player> players) {
        if (players != null)
            for (Player player : players)
                if (!this.players.contains(player))
                    this.players.add(player);
    }


    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }
}
