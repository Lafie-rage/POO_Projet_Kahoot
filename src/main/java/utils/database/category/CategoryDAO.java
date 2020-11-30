package utils.database.category;

import utils.database.DBHelper;
import quizz.model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    DBHelper dbHelper;

    public CategoryDAO() {
        try {
            dbHelper = new DBHelper();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Category> getAll() {
        List<Category> items = new ArrayList<>();

        if(dbHelper != null) {
            try {
                Statement database = dbHelper.getStatement();
                ResultSet result = database.executeQuery("SELECT * FROM Category");

                while (result.next()) {
                    items.add(new Category(result.getInt("idCategory"), result.getString("name")));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return items;
    }

    public Category get(int id) {
        if(dbHelper != null) {
            String query = "SELECT name FROM Category WHERE idCategory = ?";
            try {
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                if (result.first()) {
                    return new Category(id, result.getString("name"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public int add(Category item) {
        if(dbHelper != null) {
            try {
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement("INSERT INTO Category(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, item.getName());
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
