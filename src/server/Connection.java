package server;

import model.Player;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Queue;

public class Connection extends Thread
{
    //private Lobby lobby = new Lobby();

    private Socket socket;                                  // déclaration de la future instance du socket client
    //private String iD;                                      // déclaration de la future instance du socket client

    private Player player;                                  // déclaration de la future instance de player

    private ObjectInputStream ois;                          // déclaration de la future instance do l'objet de flux d'entrée
    private ObjectOutputStream oos ;                        // déclaration de la future instance do l'objet de flux de sortie


    // Méthode permettant de récupérer la valeur stockée dans l'objet de flux d'entrée
    public ObjectInputStream getOis()
    {
        return ois;
    }
    // Méthode permettant de récupérer la valeur stockée dans l'objet de flux de sortie
    public ObjectOutputStream getOos()
    {
        return oos;
    }

    public Player getPlayer() {
        return player;
    }

    // Réécriture de la méthode "run" de thread (décrit le code exécuté par le thread des connections)
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Object line = ois.readObject();
                if (line!=null)
                {
                    if(line instanceof Player)
                    {
                        player = (Player) line;
                        Lobby.addPlayerInLobby(this);

                        //broadcastMessage(line);
                    }

                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }

    // méthode diffusant à tous les joueurs connectés au lobby un objet
    public synchronized void broadcastInLobby(Object message)
    {
        synchronized (Lobby.getListPlayerInLobby())                         // la synchronisation évite les problèmes d'accès à la liste simultanné par différentes instances de classes
        {
            Queue<Connection> liste = Lobby.getListPlayerInLobby();         // récupère la liste des joueurs dans le lobby et les stocke dans la Queue de connenction
            for (Connection con:liste)
            {
                try
                {
                    con.getOos().writeObject(message);
                    con.getOos().flush();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    // méthode diffusant à tous les joueurs connectés à une room un objet
    public synchronized void broadcastInRoom(Object message)
    {
        synchronized (Room.getListPlayerInRoom())                            // la synchronisation évite les problèmes d'accès à la liste simultanné par différentes instances de classes
        {
            List<Connection> liste = Room.getListPlayerInRoom();             // récupère la liste des joueurs dans le lobby et les stocke dans la Queue de connenction
            for (Connection con:liste)
            {
                try
                {
                    con.getOos().writeObject(message);
                    con.getOos().flush();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    // Constructeur de la classe Connection instanciant les objets de flux d'entrées/sorties
    public Connection(Socket socket) throws IOException
    {
        this.socket=socket;
        OutputStream output = socket.getOutputStream();
        oos = new ObjectOutputStream(output);
        InputStream is = socket.getInputStream();
        ois = new ObjectInputStream(is);
        //broadcastMessage(this.Message("--New player--"));
    }

    public Object Message(String message)
    {
        return message ;
    }

    // méthode fermant les flux d'entrée/sortie ainsi que le socket client
    public void close()
    {
        try
        {
            ois.close();
            oos.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
