package server;


import model.Category;
import model.Question;
import utils.database.category.CategoryRepository;

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

    public void addPlayerInRoom(Connection con) {
        playerInRoom.add(con);
        System.out.println("room");

        if (playerInRoom.size() == 1)
        {
            List<Category> listCat = CategoryRepository.getAll();

            model.Category cat = (listCat.get(0));
            List<Question> questionList = cat.getQuestions(10);
            con.broadcastMessage(questionList);                             // broadcast une question
        }
    }


}
