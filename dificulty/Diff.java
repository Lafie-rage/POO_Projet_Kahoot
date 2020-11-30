package dificulty;

public abstract class Diff {
    private int nbQuestion;
    private int temps;

    public Diff(int nbQuestion, int temps) {
        this.nbQuestion = nbQuestion;
        this.temps = temps;
    }

    @Override
    public String toString() {
        return "Diff{" +
                "nbQuestion=" + nbQuestion +
                ", temps=" + temps +
                '}';
    }
}

class Facile extends Diff{
    private static final int a =100;
    public Facile() {
        super(a, 20);
    }
}
class Difficile extends Diff{

    public Difficile() {
        super(10, 20);
    }
}
class Expert extends Diff{

    public Expert() {
        super(10, 20);
    }
}
class main{
    public static void main(String[] args) {
        Diff a = new Expert();
        System.out.println(a);
    }
}