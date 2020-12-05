package model;

public class Option {
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
}
