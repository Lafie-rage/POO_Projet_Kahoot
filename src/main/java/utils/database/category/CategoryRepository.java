package utils.database.category;

import quizz.model.Category;
import quizz.model.Question;
import utils.database.question.QuestionRepository;

import java.util.List;

public class CategoryRepository {
    CategoryDAO dao = new CategoryDAO();

    public List<Category> getAll() {
        return dao.getAll();
    }

    public Category get(int id) {
        QuestionRepository questionRepository = new QuestionRepository();
        Category item = dao.get(id);
        item.setQuestions(questionRepository.getAll(item));
        return item;
    }

    public boolean add(Category item) {
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
