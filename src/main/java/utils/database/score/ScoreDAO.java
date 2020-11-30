package utils.database.score;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import quizz.model.Score;
import utils.database.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScoreDAO {
    DBHelper dbHelper;

    public ScoreDAO() {
        try {
            dbHelper = new DBHelper();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean add(Score item) {
        if (dbHelper != null) {
            String query = "INSERT INTO `game_has_player`(`Game_idGame`, `Player_idPlayer`, `score`) VALUES (?, ?, ?)";
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
