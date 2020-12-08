package Server;

import java.util.*;

public class Lobby extends Thread
{
    public final int maxPlayerInRoom = 1 ;


    private Room room ;
    private static Queue<Connection> playerInLobby;

    public Lobby()
    {
        playerInLobby = new LinkedList<>();
    }

    public void addPlayerInLobby(Connection con)
    {
        playerInLobby.add(con);
        if(playerInLobby.size()==maxPlayerInRoom)
        {
           this.sendToRoom();
        }
    }

    private void sendToRoom()
    {
        System.out.println("10 in lobby");
        room = new Room();

        Iterator iterator = playerInLobby.iterator();

        int i=0;
        while (iterator.hasNext() && i<maxPlayerInRoom)
        {
            Connection currentCon = (Connection) iterator.next();
            room.addPlayerInRoom(currentCon);
            i++;
        }
        for(int y=i;y>0;y--)
        {
            try
            {
                playerInLobby.remove();
            }
            catch(NoSuchElementException e)
            {
                System.out.println("Error : empty connection queue");
                break;
            }
        }
    }
}
