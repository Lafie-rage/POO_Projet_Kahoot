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

    public static boolean add(Player item) {
        if (dao.add(item)<0) return false;
        return true;
    }

    public static Player logon(String login,String password){
        int idPlayer=dao.logon(login,password);
        if (idPlayer>=0)
            return PlayerRepository.get(idPlayer);
        return null;
    }

    public static boolean remove(int id){
        if(dao.remove(id)==1)
            return true;
        return false;
    }
}
