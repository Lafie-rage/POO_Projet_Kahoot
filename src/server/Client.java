package Server;

import java.io.*;
import java.net.Socket;

public class Client extends Thread
{
    Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos ;
    private String iD;


/*    public Client(Socket socket) throws IOException
    {

    }*/

    public Client(Socket socket) throws IOException
    {
        this.socket=socket;
        try
        {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public ObjectInputStream getOis()
    {
        return ois;
    }

    public ObjectOutputStream getOos()
    {
        return oos;
    }

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
