package Server;


import java.util.ArrayList;
import java.util.List;

public class Room extends Thread
{
    //queue
    private List<Connection> playerInRoom;

    public Room()
    {
        playerInRoom = new ArrayList<>();
    }

    public void addPlayerInRoom(Connection con)
    {
        playerInRoom.add(con);
        System.out.println("room");
    }


}
