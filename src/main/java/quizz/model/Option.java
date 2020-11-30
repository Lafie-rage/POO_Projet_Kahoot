package quizz.model;

public class Option implements InformationGenerale {
    protected int id;
    private String text;

    public Option(String text) {
        this.text = text;
    }

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

    /*
    public static int getNumberItems() {
        return countItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return noOption == option.noOption &&
                texteOption.equals(option.texteOption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noOption, texteOption);
    }

    @Override
    public String toString() {
        return "quizz.model.Option{" +
                "noOption=" + noOption +
                ", texteOption='" + texteOption + '\'' +
                '}';
    }*/

    @Override
    public boolean estUneQuestion() {
        return text.endsWith("?");
    }
}
