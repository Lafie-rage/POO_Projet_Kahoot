package utils.database.question;

import utils.database.DBHelper;
import quizz.model.Category;
import quizz.model.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    DBHelper dbHelper;

    public QuestionDAO() {
        try {
            dbHelper = new DBHelper();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Question> getAll(Category category) {
        List<Question> items = new ArrayList<>();

        if (dbHelper != null) {
            try {
                String query = "SELECT * FROM Question " +
                        "WHERE Category_ID_Category = ?";
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement(query);
                preparedStatement.setInt(1, category.getId());
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    items.add(new Question(result.getInt("ID_QUESTION"), result.getString("TEXTE_QUESTION"), category));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return items;
    }

    public int getCountQuestion() {
        if (dbHelper != null) {
            try {
                Statement database = dbHelper.getStatement();
                ResultSet result = database.executeQuery("SELECT COUNT(*) as count FROM Question");
                if (result.first())
                    return result.getInt("count");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return -1;
    }

    public Question get(int id) {
        return null;
    }

    public int getIdCorrectAnswer(int id) {
        if (dbHelper != null) {
            try {
                String query = "SELECT ID_BONNE_answer FROM Question " +
                        "WHERE ID_QUESTION = ?";
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                if (result.first()) {
                    return result.getInt("ID_BONNE_answer");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return -1;
    }

    public int add(Question item) {
        try {
            PreparedStatement preparedStatement = dbHelper.getPreparedStatement("INSERT INTO Question(TEXTE_QUESTION, ID_BONNE_answer, Category_ID_Category) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getText());
            preparedStatement.setInt(2, item.getCorrectAnswer().getId());
            preparedStatement.setInt(3, item.getCategory().getId());
            if (preparedStatement.executeUpdate() == 1) {
                ResultSet res = preparedStatement.getGeneratedKeys();
                if (res.first())
                    return res.getInt(1);
            }
        } catch (SQLException throwables) {
            return Integer.MIN_VALUE;
        }
        return -1;
    }
}
