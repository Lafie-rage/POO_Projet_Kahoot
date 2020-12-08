package client;

/**
 * Listener sur l'input du client.
 * L'utilisation de cette classe abstraite permet de rendre plus lisible l'interaction client server pour chaque vue.
 */
public abstract class Listener<T> extends Thread {
    // Context du listener
    protected T context;
    protected Connection connection;

    public Listener(T context, Connection connection) {
        this.context = context;
        this.connection = connection;
    }
}
