package utils.database.answer;

import quizz.model.Answer;
import quizz.model.Question;

import java.util.List;

public class AnswerRepository {
    AnswerDAO dao = new AnswerDAO();

    public List<Answer> getAll(Question question) {
        return dao.getAll(question);
    }

    public Answer get(int id) {
        return dao.get(id);
    }

    public boolean update(int id, Answer item) {
        return dao.update(id, item);
    }

    public boolean delete(int id) {
        return dao.delete(id);
    }

    public boolean add(Answer item) {
        int id = dao.add(item);
        if(id > -1) {
            item.setId(id);
            return true;
        }
        return false;
    }

    public boolean link(Answer item, Question question) {
        return dao.link(item, question);
    }
}
