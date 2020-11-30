package utils.database.game;

import quizz.model.Game;
import utils.database.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDAO {
    DBHelper dbHelper;

    public GameDAO() {
        try {
            dbHelper = new DBHelper();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int add(Game item) {
        if(dbHelper != null) {
            try {
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement("INSERT INTO `Game`(`date`) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDate(1, item.getDate());
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
