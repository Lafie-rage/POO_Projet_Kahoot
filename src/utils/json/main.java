package utils.json;

import model.Player;
import utils.database.player.PlayerRepository;

public class main {

    public static void main(String[] args) {
        //System.out.println(JSONUtils.lectureJson("C:\\Users\\samue\\Desktop\\quizz\\quizz1.json"));

        System.out.println(PlayerRepository.logon("test","test"));
    }
}
