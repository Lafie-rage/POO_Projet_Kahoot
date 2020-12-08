package utils.database.game;

import model.Game;
import utils.database.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe permettant d'acceder  à la base de donnees pour les
 * requêtes inérantes aux objets Game.
 */
class GameDAO {
    private DBHelper dbHelper;
    /**
     * Constructeur du DAO pour les parties.
     */
    public GameDAO() {
        try {
            dbHelper = new DBHelper();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Méthode permettant d'ajouter dans la BDD  une partie
     * @param item partie à ajouter
     * @return l'id de l'insertion si insertion réussi sinon un entier négatif
     */
    public int add(Game item) {
        if(dbHelper != null) {
            try {
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement("INSERT INTO `Game`(`date`,id_category) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDate(1, item.getDate());
                preparedStatement.setInt(2, item.getCategory().getId());
                if (preparedStatement.executeUpdate() == 1) {
                    ResultSet res = preparedStatement.getGeneratedKeys();
                    if (res.first())
                        return res.getInt(1);
                }
            } catch (SQLException throwables) {
                return Integer.MIN_VALUE;
            }
        }
        return -1;
    }
}
