package model;

import java.io.Serializable;

/**
 * Classe modele representant la donnee telle qu'elle est stockee dans la BDD.
 */
public class Player implements Serializable {
    private final String pseudo;
    private final String password;
    private int id;

    /**
     * Creation d'un joueur pour l'ajout en BDD, donc sans ID.
     * @param pseudo pseudo du joueur.
     * @param password mot de passe du joueur.
     */
    public Player(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    /**
     * Creation d'un joueur recupere en BDD.
     * @param id id en BDD.
     * @param pseudo pseudo du joueur.
     * @param password mot de passe du joueur.
     */
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

}
