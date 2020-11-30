package client;

import javax.swing.*;
import java.io.IOException;

public class Ecouteur extends Thread {

    private JTextArea zoneMessage;
    private Connexion connexion;

    public Ecouteur(JTextArea zoneMessage,Connexion connexion) {
        this.zoneMessage = zoneMessage;
        this.connexion = connexion;
    }

    @Override
    public void run() {
        while (!this.currentThread().isInterrupted()){
            try {
                Message msg = (Message) connexion.getOis().readObject();
                if (msg!=null){
                   zoneMessage.append(msg.getExpediteur()+" : "+msg.getMessage()+"\n");
                }
            }catch(java.net.SocketException e){
                this.interrupt();
                e.printStackTrace();
            } catch (IOException e) {
                this.interrupt();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        this.interrupt();
    }
}
