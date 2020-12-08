package server;

import java.io.*;


import java.net.Socket;

import java.util.ArrayList;
import java.util.List;

import java.net.ServerSocket;

public class Server extends Thread
{
    private Lobby lobby;

    private static final int port=60000;
    private ServerSocket serverSocket;
    private static List<Connection> connectionList;

    public Server() throws IOException
    {
        lobby =new Lobby();
        this.serverSocket = new ServerSocket(port);
        connectionList = new ArrayList<>();
    }

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

    public static synchronized List<Connection> getListConnexion()
    {
        return connectionList;
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                Connection con = new Connection(serverSocket.accept());
                System.out.println("co");
                synchronized(connectionList)
                {
                    connectionList.add(con);
                    con.start();
                    //lobby.addPlayerInLobby(con);
                }
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

    public void removeConnection()
    {

    }

    public static void main(String[] args) throws IOException {

        Server serv = new Server();
        serv.start();
    }

}
