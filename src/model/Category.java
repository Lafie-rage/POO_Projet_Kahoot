package model;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category {
    private int id;
    private final String name;
    private List<Question> questions = new ArrayList<>();

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(int id, String name, List<Question> questions) {
        this(id, name);
        setQuestions(questions);
    }

    public Category(String name) {
        this.name = name;
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

    public List<Question> getQuestions(int nbQuestions) {
        List<Question> questions = new ArrayList<>();
        List<Integer> alreadyUsed = new ArrayList<>();
        int random;
        nbQuestions = Math.min(nbQuestions, this.questions.size());
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

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getName();
    }
}
