package utils.database.answer;

import utils.database.DBHelper;
import model.Answer;
import model.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

  class AnswerDAO {
    private  DBHelper dbHelper;

    public AnswerDAO() {
        try {
            dbHelper = new DBHelper();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Answer> getAll(Question question) {
        List<Answer> items = new ArrayList<>();

        if(dbHelper != null) {
            try {
                String query = "SELECT ID_answer , TEXTE_answer  FROM Answer " +
                        "INNER JOIN PROPOSITION ON answer_ID_answer = ID_answer " +
                        "WHERE QUESTION_ID_QUESTION = ?";
                PreparedStatement preparedStatement = dbHelper.getPreparedStatement(query);
                preparedStatement.setInt(1, question.getId());
                ResultSet result = preparedStatement.executeQuery();
                int i = 0;
                while (result.next()) {
                    items.add(new Answer(result.getInt("ID_answer"), i++, result.getString("TEXTE_answer")));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return items;
    }

    public Answer get(int id) {
        return null;
    }

    public boolean update(int id, Answer item) {
        return false;
    }

    public boolean delete(int id) {
        return false;
    }

    public int add(Answer item) {
        try {
            PreparedStatement preparedStatement = dbHelper.getPreparedStatement("INSERT INTO Answer(TEXTE_answer) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getText());
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

    public boolean link(Answer item, Question question) {
        try {
            PreparedStatement preparedStatement = dbHelper.getPreparedStatement("INSERT INTO PROPOSITION(answer_ID_answer, QUESTION_ID_QUESTION) VALUES (?, ?)");
            preparedStatement.setInt(1, item.getId());
            preparedStatement.setInt(2, question.getId());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            return false;
        }
    }
}
