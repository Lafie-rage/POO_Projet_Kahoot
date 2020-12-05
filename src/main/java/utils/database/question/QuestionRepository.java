package utils.database.question;

import utils.database.answer.AnswerRepository;
import quizz.model.Answer;
import quizz.model.Category;
import quizz.model.Question;

import java.util.List;

public class QuestionRepository {
    QuestionDAO dao = new QuestionDAO();

    public int getCountQuestion() {
        return dao.getCountQuestion();
    }

    public List<Question> getAll(Category category) {
        AnswerRepository answerRepository = new AnswerRepository();
        List<Question> questions = dao.getAll(category);
        for(Question question : questions) {
            question.setProposition(answerRepository.getAll(question));
            int idCorrectAnswer = dao.getIdCorrectAnswer(question.getId());
            for(Answer proposal : question.getProposals())
                if (proposal.getId() == idCorrectAnswer)
                    question.setCorrectAnswer(proposal);
        }
        return questions;
    }

    public Question get(int id) {
        return dao.get(id);
    }

    public boolean add(Question item) {
        AnswerRepository answerRepository = new AnswerRepository();
        boolean flag = true;
        for(Answer proposal: item.getProposals())
            if(!answerRepository.add(proposal))
                flag = false;
        if(flag) {
            int id = dao.add(item);
            if(id > -1) {
                item.setId(id);
                for(Answer proposal : item.getProposals())
                    answerRepository.link(proposal, item);
                return true;
            }
            return false;
        }
        return false;
    }
}
