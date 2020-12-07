package utils.database.category;

import utils.database.DBHelper;
import model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class CategoryDAO {
    private DBHelper dbHelper;

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
                    items.add(new Category(result.getInt("ID_Category"), result.getString("TEXTE_Category")));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return items;
    }

    public Category get(int id) {
        if(dbHelper != null) {
            String query = "SELECT TEXTE_Category FROM Category WHERE ID_Category = ?";
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
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement("INSERT INTO Category(TEXTE_Category) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
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
    public int remove(int id){
        if (dbHelper != null) {
            String query = "DELETE FROM Category where ID_Category = ?";
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
