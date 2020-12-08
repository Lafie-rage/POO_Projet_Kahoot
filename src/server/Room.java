package server;


import model.Category;
import model.Question;
import utils.database.category.CategoryRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Room extends Thread
{
    //queue
    private List<Connection> playerInRoom;

    public Room()
    {
        playerInRoom = new ArrayList<>();
    }

    // méthode retournant la liste des connexions des joueurs présent dans la salle de jeu
    public static synchronized List<Connection> getListPlayerInRoom()
    {
        return getListPlayerInRoom();
    }

    // méthode d'ajout de la connexion d'un joueur à la room
    public void addPlayerInRoom(Connection con) {
        playerInRoom.add(con);
        System.out.println("room");

        if (playerInRoom.size() == Server.maxPlayerInRoom)
        {
            System.out.println("traitement SQL");
            /*List<Category> listCat = CategoryRepository.getAll();

            model.Category cat = (listCat.get(0));
            List<Question> questionList = cat.getQuestions(10);
            con.broadcastInLobby(questionList);                             // broadcast une question*/
        }
    }

    // méthode d'envoit d'objet aux joueurs de la liste présents dans la salle de jeu
    public void broadcastObjectRoom(Object object)
    {
        Iterator iterator = playerInRoom.iterator();

        int i=0;
        while (iterator.hasNext() && i<playerInRoom.size())
        {
            Connection currentCon = (Connection) iterator.next();
            currentCon.broadcastInRoom(object);
        }
    }


}
