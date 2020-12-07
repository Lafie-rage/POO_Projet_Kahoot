package utils.database.player;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import utils.database.DBHelper;

import model.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class PlayerDAO {
    private DBHelper dbHelper;

    public PlayerDAO() {
        try {
            dbHelper = new DBHelper();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean pseudoAlreadyTaken(String pseudo) {
        if (dbHelper != null) {
            String query = "SELECT * FROM Player WHERE login = ?";
            try {
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement(query);
                preparedStatement.setString(1, pseudo);
                ResultSet result = preparedStatement.executeQuery();
                return result.first();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }

    public List<Player> getAll() {
        List<Player> items = new ArrayList<>();

        if (dbHelper != null) {
            try {
                Statement database = dbHelper.getStatement();
                ResultSet result = database.executeQuery("SELECT * FROM Player");

                while (result.next()) {
                    items.add(new Player(result.getInt("ID_Player"), result.getString("login"), result.getString("password")));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return items;
    }

    public int getCountPlayer() {
        if (dbHelper != null) {
            try {
                Statement database = dbHelper.getStatement();
                ResultSet result = database.executeQuery("SELECT COUNT(*) as count FROM Player");
                if (result.first())
                    return result.getInt("count");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return -1;
    }

    public Player get(int id) {
        if (dbHelper != null) {
            String query = "SELECT login, password FROM Player WHERE ID_Player = ?";
            try {
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                if (result.first()) {
                    return new Player(id, result.getString("login"), result.getString("password"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public int add(Player item) {
        if (dbHelper != null) {
            try {
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement("INSERT INTO Player(login, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, item.getPseudo());
                preparedStatement.setString(2, item.getPassword());
                if (preparedStatement.executeUpdate() == 1) {
                    ResultSet res = preparedStatement.getGeneratedKeys();
                    if (res.first())
                        return res.getInt(1);
                }
            } catch (SQLException throwables) {
                if (throwables instanceof MySQLIntegrityConstraintViolationException)
                    return -2; // pseudo already taken
                return Integer.MIN_VALUE;
            }
            return -1;
        }
        return -1;
    }

    public int logon(String login,String password){
        if (dbHelper != null) {
            String query = "SELECT ID_Player FROM Player WHERE login = ? and password = ?";
            try {
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement(query);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                ResultSet result = preparedStatement.executeQuery();
                if (result.first()) {
                    return result.getInt("ID_Player");
                }
            } catch (SQLException throwables) {
                return Integer.MIN_VALUE;
            }
        }
        return -1;

    }

    public int remove(int id){
        if (dbHelper != null) {
            String query = "DELETE FROM Player where ID_Player = ?";
            try {
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                return 1;
            } catch (SQLException throwables) {
                return Integer.MIN_VALUE;
            }
        }
        return -1;

    }
}
