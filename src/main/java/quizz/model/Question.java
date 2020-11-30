package quizz.model;

import java.util.ArrayList;
import java.util.List;

public class Question extends Option implements Comparable<Question>{

    private List<Answer> proposals = new ArrayList<>();
    private Answer correctAnswer;
    private Category category;

    public Question(int id, String textOption, Category category) {
        super(id, textOption);
        this.category = category;
    }

    public Question(String textOption, List<Answer> proposals, Answer correctAnswer, Category category) {
        super(textOption);
        this.category = category;
        setProposition(proposals);
        setCorrectAnswer(correctAnswer);
    }



    public void setProposition(List<Answer> propositions) {
        if(propositions.size() == 4) // On vérifie qu'il y ai unique 4 réponses
            for(Answer prop : propositions) // On vérifie la présence des réponses en un seul exemplaire
                if(!this.proposals.contains(prop))
                    this.proposals.add(prop);
    }

    public Answer getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(Answer bonneAnswer) {
        if(proposals != null && proposals.contains(bonneAnswer)) // On vérifie la présence de la réponse dans les propositions
            this.correctAnswer = bonneAnswer;
    }

    public List<Answer> getProposals() {
        return new ArrayList<>(proposals);
    }

    public Category getCategory() {
        return this.category;
    }

    public boolean isCorrect(int pos) {
        return correctAnswer == proposals.get(pos);
    }

    @Override
    public int compareTo(Question question) {
        return Integer.compare(this.id, question.id);
    }
}
