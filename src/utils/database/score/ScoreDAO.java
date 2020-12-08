package utils.database.score;

import model.Score;
import utils.database.DBHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe permettant d'acceder  à la base de donnees pour les
 * requêtes inérantes aux objets Score.
 */
class ScoreDAO {
    private DBHelper dbHelper;

    /**
     * Constructeur du DAO pour les score.
     */
    public ScoreDAO() {
        try {
            dbHelper = new DBHelper();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * Méthode permettant d'ajouter un score dans la BDD
     * @param item score a ajouter
     * @return l'id de l'insertion si insertion réussi sinon un entier négatif
     */
    public boolean add(Score item) {
        if (dbHelper != null) {
            String query = "INSERT INTO `score`(`id_game`, `Id_player`, `Score`) VALUES (?, ?, ?)";
            try {
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement(query);
                preparedStatement.setInt(1, item.getGame().getId());
                preparedStatement.setInt(2, item.getPlayer().getId());
                preparedStatement.setInt(3, item.getScore());
                return preparedStatement.execute();
            } catch (SQLException throwables) {
                return false;
            }
        }
        return false;
    }
}
