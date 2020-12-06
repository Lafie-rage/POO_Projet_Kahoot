package utils.database.answer;

import model.Answer;
import model.Question;

import java.util.List;

public class AnswerRepository {
    private static final AnswerDAO dao = new AnswerDAO();

    public static List<Answer> getAll(Question question) {
        return dao.getAll(question);
    }

    public static  Answer get(int id) {
        return dao.get(id);
    }

    public static boolean update(int id, Answer item) {
        return dao.update(id, item);
    }

    public static boolean delete(int id) {
        return dao.delete(id);
    }

    public static boolean add(Answer item) {
        int id = dao.add(item);
        if(id > -1) {
            item.setId(id);
            return true;
        }
        return false;
    }

    public static boolean link(Answer item, Question question) {
        return dao.link(item, question);
    }
}