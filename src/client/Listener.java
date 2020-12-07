package client;


public abstract class Listener<T> extends Thread {
    protected T page;
    protected Connection connection;

    public Listener(T page, Connection connection) {
        this.page = page;
        this.connection = connection;
    }
}
