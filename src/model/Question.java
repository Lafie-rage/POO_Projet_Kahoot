package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe modele representant la donnee telle qu'elle est stockee dans la BDD.
 * Herite de @see Option.
 * Implemente @see Comparable afin d'utiliser sort.
 * Implemente @see Serializable afin
 */
public class Question extends Option implements Comparable<Question>, Serializable {
    private List<Answer> proposals = new ArrayList<>();
    private Answer correctAnswer;
    private Category category;

    /**

     * Creation d'une reponse recuperee en BDD.
     * @param id id en bdd.
     * @param textOption corps de la question.
     * @param category categorie a laquelle est liee la question.
     */
    public Question(int id, String textOption, Category category) {
        super(id, textOption);
        this.category = category;
    }

    /**
     * Creation d'une reponse pour l'ajout en BDD, donc sans ID.
     * @param textOption corps de la question.
     * @param proposals list des reponses proposees.
     * @param correctAnswer reference vers la bonne reponse. Elle se trouve forcement dans la liste des proposition.
     * @param category categorie a laquelle est liee la question.
     */
    public Question(String textOption, List<Answer> proposals, Answer correctAnswer, Category category) {
        super(textOption);
        this.category = category;
        setProposition(proposals);
        setCorrectAnswer(correctAnswer);
    }

    /**
     * Ajoute la liste des propositions.
     * Verifie que cette liste est composees d'exactement 4 questions et qu'il n'y ai pas de doublons dedans.
     * @param propositions liste des propositions a ajouter.
     */
    public void setProposition(List<Answer> propositions) {
        if (propositions.size() == 4) // On vérifie qu'il y ai unique 4 réponses
            for (Answer prop : propositions) // On vérifie la présence des réponses en un seul exemplaire
                if (!this.proposals.contains(prop))
                    this.proposals.add(prop);
    }

    /**
     * Retourne la reference vers la bonne reponse sans passer par un constructeur par copie.
     * @return la reference vers la bonne reponse.
     */
    public Answer getCorrectAnswer() {
        return this.correctAnswer;
    }

    /**
     * Ajoute la bonne reponse a la question.
     * Attention, elle doit être de la même instance que l'un de celles contenues dans les propositions.
     * @param bonneAnswer reference vers la bonne reponse.
     */
    public void setCorrectAnswer(Answer bonneAnswer) {
        if (proposals != null && proposals.contains(bonneAnswer)) // On vérifie la présence de la réponse dans les propositions
            this.correctAnswer = bonneAnswer;
    }

    public List<Answer> getProposals() {
        return new ArrayList<>(proposals);
    }

    /**
     * Renvoit l'index de la bonne reponse dans la liste des propositions.
     * Permet de simplifier le code lors de la verification de la reponse envoyee par le client.
     * @return l'index de la bonne reponse dans la liste des proposition.
     */
    public int getCorrectAnswerIndex() {
        return this.proposals.indexOf(correctAnswer);
    }

    public Category getCategory() {
        return this.category;
    }

    /**
     * Permet de reordoner la liste des questons lors de leur tirage aleatoire.
     */
    @Override
    public int compareTo(Question question) {
        return Integer.compare(this.id, question.id);
    }
}
