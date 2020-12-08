package Server;

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

        Socket sock1 = new Socket("127.0.0.1",60000);
        Client cli = new Client(sock1);

        Socket sock2 = new Socket("127.0.0.1",60000);
        Client cli2 = new Client(sock2);

        Socket sock3 = new Socket("127.0.0.1",60000);
        Client cli3 = new Client(sock3);

        Socket sock4 = new Socket("127.0.0.1",60000);
        Client cli4 = new Client(sock4);

        Socket sock5 = new Socket("127.0.0.1",60000);
        Client cli5 = new Client(sock5);

        Socket sock6 = new Socket("127.0.0.1",60000);
        Client cli6 = new Client(sock6);

        Socket sock7 = new Socket("127.0.0.1",60000);
        Client cli7 = new Client(sock7);

        Socket sock8 = new Socket("127.0.0.1",60000);
        Client cli8 = new Client(sock8);

        Socket sock9 = new Socket("127.0.0.1",60000);
        Client cli9 = new Client(sock9);

        Socket sock10 = new Socket("127.0.0.1",60000);
        Client cli10 = new Client(sock10);

        Socket sock11 = new Socket("127.0.0.1",60000);
        Client cli11 = new Client(sock11);

        Socket sock12 = new Socket("127.0.0.1",60000);
        Client cli12 = new Client(sock12);
    }

}
