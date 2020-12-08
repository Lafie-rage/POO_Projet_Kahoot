package model;

import java.io.Serializable;

public class Player implements Serializable {
    private int id = -1; // default id value
    private String pseudo;
    private String password;

    public Player( String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    public Player(int id, String pseudo, String password) {
        this(pseudo, password);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
