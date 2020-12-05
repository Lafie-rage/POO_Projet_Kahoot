package utils.database.player;

import quizz.model.Player;

import java.util.List;

public class PlayerRepository {
    PlayerDAO dao = new PlayerDAO();

    public List<Player> getAll() {
        return dao.getAll();
    }

    public int getCountPlayer() {
        return dao.getCountPlayer();
    }

    public boolean pseudoAlreadyTaken(String pseudo) {
        return dao.pseudoAlreadyTaken(pseudo);
    }

    public Player get(int id) {
        return dao.get(id);
    }

    public int add(Player item) {
        return dao.add(item);
    }
}
