package client;

import java.io.Serializable;

public class Message implements Serializable {

    private String expediteur;
    private String message;


    public Message(String expediteur, String message) {
        this.expediteur = expediteur;
        this.message = message;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public String getMessage() {
        return message;
    }

}
