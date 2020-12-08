package server;

import utils.Commons;

import java.io.*;


import java.net.ServerSocket;

public class Server extends Thread
{
    public static final int maxPlayerInRoom = 1;          // variable statique désignant le nombre de joueur nécessaires pour lancer une partie

    private Lobby lobby;                                    // déclaration de la future instance de la classe Lobby
    private ServerSocket serverSocket;                      // déclaration de la future instance du socket serveur
    //private static List<Connection> connectionList;

    public Server() throws IOException
    {
        lobby =new Lobby();                                 // Instanciation du lobby
        this.serverSocket = new ServerSocket(Commons.PORT);         // Instanciation du socket serveur sur le port "port" (ici 60 000)
        //connectionList = new ArrayList<>();
    }

    // méthode de fermeture du socket server
    private void closeListeningSocket()
    {
        try
        {
            serverSocket.close();
        }
        catch (IOException e)
        {
            System.err.println("Error while closing server socket");
            e.printStackTrace();
        }
    }

    /*public static synchronized List<Connection> getListConnexion()
    {
        return connectionList;
    }*/

    // Redéfinition de la méthode "run" de thread (décrit le code exécuté par le thread du serveur)
    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                Connection con = new Connection(serverSocket.accept());
                //System.out.println("co");
                //synchronized(connectionList)
                //{
                    //connectionList.add(con);
                    con.start();
                    Lobby.addPlayerInLobby(con);
                    if(Lobby.getListPlayerInLobby().size() == Server.maxPlayerInRoom)
                    {
                        lobby.sendToRoom();
                    }
                //}
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeListeningSocket();
        }
    }

    /*public void removeConnection()
    {

    }*/

    public static void main(String[] args) throws IOException {

        Server serv = new Server();
        serv.start();
    }

}
