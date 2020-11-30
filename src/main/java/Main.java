import quizz.model.Answer;
import quizz.model.Category;
import quizz.model.Player;
import quizz.model.Question;
import utils.database.answer.AnswerRepository;
import utils.database.category.CategoryRepository;
import utils.database.player.PlayerRepository;
import utils.database.question.QuestionRepository;
import quizz.*;

import java.util.ArrayList;
import java.util.List;


public class Main {

    private static Quizz quizz;

    public static void main(String[] args) {
        quizz = new quizz.Quizz();
    }


}
