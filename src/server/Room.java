package server;


import model.*;
import utils.Commons;
import utils.database.category.CategoryRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Room extends Thread
{
    //queue
    private List<Connection> playerInRoom;
    private List<Question> questions;
    private final Game game;

    public Room()
    {
        playerInRoom = new ArrayList<>();
        System.out.println("traitement SQL");
        List<Category> categories = CategoryRepository.getAll();

        Category category = CategoryRepository.getRandomly();
        System.out.println(category.getQuestions());
        questions = category.getQuestions(Commons.NB_QUESTIONS);
        List<Player> players = new ArrayList<>();
        for(Connection connection : playerInRoom) {
            players.add(connection.getPlayer());
        }
        this.game = new Game(Commons.NB_QUESTIONS, players, category);
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
            con.broadcastInLobby(questions);                             // broadcast une question
            System.out.println(questions);
        }
        for (int i = 0; i < Server.maxPlayerInRoom; i++) {
            try {
                Object objectMessage = con.getOis().readObject();
                if(objectMessage instanceof Score) {
                    Score score = (Score)objectMessage;
                    score.setGame(game);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
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
