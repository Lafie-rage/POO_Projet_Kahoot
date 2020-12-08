package server;


import model.*;
import utils.Commons;
import utils.database.category.CategoryRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Room {
    //queue
    private List<Connection> playerInRoom;
    private List<Question> questions;
    public static Game game;

    public Room() {
        playerInRoom = new ArrayList<>();
        System.out.println("traitement SQL");
        List<Category> categories = CategoryRepository.getAll();

        Category category = CategoryRepository.getRandomly();
        System.out.println(category.getQuestions());
        questions = category.getQuestions(Commons.NB_QUESTIONS);
        List<Player> players = new ArrayList<>();
        for (Connection connection : playerInRoom) {
            players.add(connection.getPlayer());
        }
        this.game = new Game(Commons.NB_QUESTIONS, players, category);
    }

    // méthode retournant la liste des connexions des joueurs présent dans la salle de jeu
    public static synchronized List<Connection> getListPlayerInRoom() {
        return getListPlayerInRoom();
    }

    // méthode d'ajout de la connexion d'un joueur à la room
    public void addPlayerInRoom(Connection con) {
        playerInRoom.add(con);
        System.out.println("room");
        con.start();
        if (playerInRoom.size() == Commons.MAX_PLAYER_IN_ROOM) {
            synchronized (con) {
                con.broadcastInLobby(questions);
                System.out.println(questions);
            }                           // broadcast une question
        }
    }

}
