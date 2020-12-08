package utils.database.question;

import utils.database.DBHelper;
import model.Category;
import model.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'acceder  à la base de donnees pour les
 * requêtes inérantes aux objets Question.
 */
class QuestionDAO {
    private DBHelper dbHelper;

    /**
     * Constructeur du DAO pour les questions.
     */
    public QuestionDAO() {
        try {
            dbHelper = new DBHelper();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Méthode permettant de récupérer dans la BDD  tous les questions d'une categorie
     * @param category la categorie pour laquelle on veut recupérer les questions
     * @return liste des joueurs présents dans la BDD
     */
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

    /**
     * Méthode permettant d'ajouter une question dans la BDD
     * @param item question a ajouter
     * @return l'id de l'insertion si insertion réussi sinon un entier négatif
     */
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
