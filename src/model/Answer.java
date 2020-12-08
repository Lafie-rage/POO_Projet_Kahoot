package model;

import java.io.Serializable;

/**
 * Classe modele representant la donnee telle qu'elle est stockee dans la BDD.
 * Ajoute un index pour simplifier la lecture (index entre 0 et 3).
 * Hérite de @see Option.
 * Implemente @see Serializable
 */
public class Answer extends Option implements Serializable {
    private final int index; // Valeur entre 0 et 3 permettant d'indexer les reponses dans une questions donnees.

    /**
     * Creation d'une reponse pour l'ajout en BDD, donc sans ID.
     * @param index index de la reponse.
     * @param text corps de la reponse.
     */
    public Answer(int index, String text) {
        super(text);
        this.index = index;
    }

    /**
     * Creation d'une reponse recuperee en BDD.
     * @param id id en BDD BDD.
     * @param index index de la reponse.
     * @param text corps de la reponse.
     */
    public Answer(int id, int index, String text) {
        super(id, text);
        this.index = index;
    }

}
