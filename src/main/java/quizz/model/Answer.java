package quizz.model;

public class Answer extends Option {
    private int index;
    public Answer(int index, String text) {
        super(text);
        this.index = index;
    }

    public Answer(int id, int index, String text) {
        super(id, text);
        this.index = index;
    }


    @Override
    public String toString() {
        return this.index + " - " + this.getText();
    }

}
