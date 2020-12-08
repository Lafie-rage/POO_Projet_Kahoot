package server;

import java.util.*;

public class Lobby extends Thread
{

    private Room room ;
    private static Queue<Connection> playerInLobby= new LinkedList<>();;

    public Lobby()
    {
        /*playerInLobby = new LinkedList<>();*/
    }

    // retourne la liste des joueurs présent dans le lobby
    public static synchronized Queue<Connection> getListPlayerInLobby()
    {
        return playerInLobby;
    }

    // ajoute un joueur à la liste de joueurs du lobby
    public static void addPlayerInLobby(Connection con)
    {
        System.out.println("rentre");
        playerInLobby.add(con);
       /* if(playerInLobby.size()==Server.maxPlayerInRoom)
        {
           this.sendToRoom();
        }*/
    }

    // méthode appelée afin de transférer les connexions des joueurs du lobby vers la salle de jeu (et les retire de la liste des connectés au lobby)
    public void sendToRoom()
    {
        System.out.println("10 in lobby");
        room = new Room();


        // ajout des joueurs à la liste des présents dans la salle de jeu
        Iterator iterator = playerInLobby.iterator();
        int i=0;
        while (iterator.hasNext() && i<Server.maxPlayerInRoom)
        {
            Connection currentCon = (Connection) iterator.next();
            room.addPlayerInRoom(currentCon);
            i++;
        }

        // retrait des joueurs de la liste des connexion des joueurs dans le lobby
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

    // méthode d'envoit d'objet aux joueurs de la liste présents dans le lobby
    public void broadcastObjectLobby(Object object)
    {
        Iterator iterator = playerInLobby.iterator();

        int i=0;
        while (iterator.hasNext() && i<playerInLobby.size())
        {
            Connection currentCon = (Connection) iterator.next();
            currentCon.broadcastInLobby(object);
        }
    }
}
