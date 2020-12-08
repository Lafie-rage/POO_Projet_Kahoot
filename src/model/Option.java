package model;

import java.io.Serializable;

/**
 * Classe abstraite dont hérite @see Answer et @see Question.
 */
public class Option implements Serializable {
    protected int id;
    protected String text;

    public Option(){}

    /**
     * Creation d'une option pour l'ajout en BDD, donc sans ID.
     * @param text corps de l'option
     */
    public Option(String text) {
        this.text = text;
    }

    /**
     * Creation d'une option recuperee en BDD.
     * @param id id en BDD.
     * @param text corps de l'option.
     */
    public Option(int id, String text) {
        this(text);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
