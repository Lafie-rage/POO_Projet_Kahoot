package utils.database.category;

import model.Category;
import model.Question;
import utils.database.question.QuestionRepository;

import java.util.List;

public class CategoryRepository {
    private static final CategoryDAO dao = new CategoryDAO();

    public static List<Category> getAll() {
        return dao.getAll();
    }

    public static Category get(int id) {
        QuestionRepository questionRepository = new QuestionRepository();
        Category item = dao.get(id);
        item.setQuestions(questionRepository.getAll(item));
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
}
