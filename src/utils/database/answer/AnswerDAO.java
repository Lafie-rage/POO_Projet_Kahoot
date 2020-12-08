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

/**
 * Classe permettant d'acceder  à la base de donnees pour les
 * requêtes inérantes aux objets Answer.
 */
class AnswerDAO {
    private DBHelper dbHelper;

    /**
     * Constructeur du DAO pour les reponses.
     */
    public AnswerDAO() {
        try {
            dbHelper = new DBHelper();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Méthode permettant de récupérer dans la BDD  toutes les réponses d'une question donnee
     * @param question la question pour laquelle on veut recuperer les reponses
     * @return liste des réponses de la question
     */
    public List<Answer> getAll(Question question) {
        List<Answer> items = new ArrayList<>();

        if (dbHelper != null) {
            try {
                String query = "SELECT ID_answer , TEXTE_answer  FROM Answer " +
                        "INNER JOIN Proposition ON answer_ID_answer = ID_answer " +
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

    /**
     * Méthode permettant d'ajouter une reponse dans la BDD
     * @param item reponse à ajouter
     * @return l'id de l'insertion si insertion réussi sinon un entier négatif
     */
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

    /**
     * Methode permettant de lier une reponse et une question en faisant une insertion dans la BDD
     * @param answer
     * @param question
     * @return true si l'insertion c'est bien passé sinon false
     */
    public boolean link(Answer answer, Question question) {
        try {
            PreparedStatement preparedStatement = dbHelper.getPreparedStatement("INSERT INTO Proposition(answer_ID_answer, QUESTION_ID_QUESTION) VALUES (?, ?)");
            preparedStatement.setInt(1, answer.getId());
            preparedStatement.setInt(2, question.getId());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            return false;
        }
    }

    /**
     * Methode permettant de supprimer les réponses associé à une categorie
     * @param id
     * @return
     */
    public int removeFromCategory(int id) {

        if (dbHelper != null) {
            String query = "DELETE Answer FROM Answer " +
                    "INNER JOIN Proposition ON answer.ID_answer = Proposition.answer_ID_answer " +
                    "INNER JOIN question ON Proposition.QUESTION_ID_QUESTION = question.ID_QUESTION " +
                    "WHERE question.Category_ID_Category = ?";
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
