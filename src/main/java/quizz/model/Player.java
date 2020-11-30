package quizz.model;

public class Player {
    private int id = -1; // default id value
    private String name;
    private String pseudo;
    private String password;

    public Player(String name, String pseudo, String password) {
        this.name = name;
        this.pseudo = pseudo;
        this.password = password;
    }

    public Player(int id, String name, String pseudo, String password) {
        this(name, pseudo, password);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public String getPseudo() {
        return this.pseudo;
    }
    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return id + " - " + pseudo;
    }
}
