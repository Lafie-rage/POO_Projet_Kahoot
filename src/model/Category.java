package model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe modele representant la donnee telle qu'elle est stockee dans la BDD.
 * Implemente @see Serializable
 */
public class Category implements Serializable {
    private int id;
    private String name;
    private List<Question> questions = new ArrayList<>();

    /**
     * Creation d'une categorie recuperee en BDD.
     * @param id id en BDD.
     * @param name nom de la categorie.
     */
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Creation d'une categorie pour l'ajout en BDD, donc sans ID.
     * @param name nom de la categorie.
     */
    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(this.questions);
    }

    /**
     * Renvoit une liste de questions choisies au hasard parmis celles de la categorie courante.
     * @param nbQuestions nombre de questions à renvoyer.
     * @return une liste de nbQuestions. Si nbQuestions était plus grand que la taille de la liste, retourne toute la liste.
     */
    public List<Question> getQuestions(int nbQuestions) {
        List<Question> questions = new ArrayList<>();
        List<Integer> alreadyUsed = new ArrayList<>();
        int random;
        if(nbQuestions > questions.size())
            return getQuestions();
        for (int i = 0; i < nbQuestions; i++) {
            do {
                random = (int) (Math.random() * this.questions.size());
            } while (alreadyUsed.contains(random));
            questions.add(this.questions.get(random));
            alreadyUsed.add(random);
        }
        Collections.sort(questions);
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        if(questions != null)
            for(Question question : questions)
                if(!this.questions.contains(question))
                    this.questions.add(question);
    }

    /**
     * Ajout d'une question à la liste.
     * @param question question à ajouter.
     */
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

}
