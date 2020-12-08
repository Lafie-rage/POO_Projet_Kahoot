package utils.database.category;

import model.Answer;
import model.Category;
import model.Question;
import utils.database.answer.AnswerRepository;
import utils.database.question.QuestionRepository;

import java.util.List;

public class CategoryRepository {
    private static final CategoryDAO dao = new CategoryDAO();

    public static List<Category> getAll() {
        return dao.getAll();
    }

    public static Category get(int id) {
        Category item = dao.get(id);
        item.setQuestions(QuestionRepository.getAll(item));
        return item;
    }

    public static boolean add(Category item) {
        QuestionRepository questionRepository = new QuestionRepository();
        boolean flag = true;
        int id = dao.add(item);
        if(id > -1) {
            item.setId(id);
            for (Question question : item.getQuestions())
                if (!questionRepository.add(question))
                    flag = false;
            return flag;
        }
        return false;
    }

    public static boolean remove(int id){

        if(AnswerRepository.removeFromCategory(id))
            if(dao.remove(id)>0)
                return true;
        return false;
    }

    public static Category getRandomly(){
        Category item = dao.getRandomly();
        item.setQuestions(QuestionRepository.getAll(item));
        return item;
    }
}
