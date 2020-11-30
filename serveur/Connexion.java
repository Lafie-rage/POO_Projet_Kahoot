package serveur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Connexion extends Thread{

    Socket socket;

    private ObjectInputStream ois;
    private ObjectOutputStream oos ;

    public ObjectInputStream getOis() {
        return ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    private String iD;


    @Override
    public void run() {
        while (true){
            try {
                Object line = ois.readObject();
                if (line!=null)
                    distriuberMessage(line);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private  synchronized void distriuberMessage(Object message){
        synchronized (Serveur.getListConnexion()){
            List<Connexion> liste  =Serveur.getListConnexion();
            for (Connexion con:liste) {
                try {
                    con.getOos().writeObject(message);
                    con.getOos().flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public Connexion(Socket socket) throws IOException {
        this.socket=socket;
        OutputStream output = socket.getOutputStream();
        oos = new ObjectOutputStream(output);
        InputStream is = socket.getInputStream();
        ois = new ObjectInputStream(is);
        distriuberMessage(new client.Message("Serveur","--Nouvelle personne--"));
    }
    public void close(){
        try {
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
