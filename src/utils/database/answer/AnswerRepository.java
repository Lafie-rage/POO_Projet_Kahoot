package utils.database.answer;

import model.Answer;
import model.Question;

import java.util.List;

public class AnswerRepository {
    private static final AnswerDAO dao = new AnswerDAO();

    public static List<Answer> getAll(Question question) {
        return dao.getAll(question);
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

    public static boolean removeFromCategory(int id){
        if (dao.removeFromCategory(id)>0) return true;
        return false;
    }
}
