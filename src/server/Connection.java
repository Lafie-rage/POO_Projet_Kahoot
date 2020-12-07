package Server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Connection extends Thread
{
    Socket socket;
    private String iD;

    private ObjectInputStream ois;
    private ObjectOutputStream oos ;


    public ObjectInputStream getOis()
    {
        return ois;
    }

    public ObjectOutputStream getOos()
    {
        return oos;
    }



    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Object line = ois.readObject();
                if (line!=null)
                    broadcastMessage(line);
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

    private  synchronized void broadcastMessage(Object message)
    {
        synchronized (Server.getListConnexion())
        {
            List<Connection> liste = Server.getListConnexion();
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
    public Connection(Socket socket) throws IOException
    {
        this.socket=socket;
        OutputStream output = socket.getOutputStream();
        oos = new ObjectOutputStream(output);
        InputStream is = socket.getInputStream();
        ois = new ObjectInputStream(is);
        broadcastMessage(this.Message("--New player--"));
    }

    public Object Message(String message)
    {
        return message ;
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