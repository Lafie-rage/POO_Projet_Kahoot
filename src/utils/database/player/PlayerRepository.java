package utils.database.player;

import model.Player;

import java.util.List;

public class PlayerRepository {
    private static final PlayerDAO dao = new PlayerDAO();

    public static List<Player> getAll() {
        return dao.getAll();
    }

    public static int getCountPlayer() {
        return dao.getCountPlayer();
    }

    public static boolean pseudoAlreadyTaken(String pseudo) {
        return dao.pseudoAlreadyTaken(pseudo);
    }

    public static Player get(int id) {
        return dao.get(id);
    }

    public static int add(Player item) {
        return dao.add(item);
    }

    public static Player logon(String login,String password){
        int idPlayer=dao.logon(login,password);
        if (idPlayer>=0)
            return PlayerRepository.get(idPlayer);
        return null;
    }
}
