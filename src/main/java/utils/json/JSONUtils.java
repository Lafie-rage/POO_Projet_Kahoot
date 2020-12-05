package utils.json;


import model.Answer;
import model.Category;
import model.Question;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.database.category.CategoryRepository;
import utils.database.question.QuestionRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONUtils {

    public static boolean lectureJson(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));

            String categoryString = (String) jsonObject.get("thème");
            categoryString = categoryString.substring(0, categoryString.indexOf("(")-1);
            Category cat = new Category(categoryString);
            if(!CategoryRepository.add(cat))return false;

            JSONArray tableauFrDeb = (JSONArray)((JSONObject)((JSONObject) jsonObject.get("quizz")).get("fr")).get("débutant");
            Iterator iteratorQuestion = tableauFrDeb.iterator();

            while(iteratorQuestion.hasNext()){

                JSONObject questionBloc = (JSONObject) iteratorQuestion.next();
                String stringBonneReponse = (String) questionBloc.get("réponse");

                JSONArray propTab= (JSONArray) questionBloc.get("propositions");
                List<Answer> proposals = new ArrayList<>();
                Answer goodAnswer =null;
                for (int i = 0; i < propTab.size(); i++) {

                    String proposition = (String) propTab.get(i);
                    if(proposition.equals(stringBonneReponse))
                    {
                        goodAnswer = new Answer(i,proposition);
                        proposals.add(goodAnswer);
                    }
                    else {
                        proposals.add(new Answer(i,proposition));
                    }

                }
                String questionString = (String) questionBloc.get("question");
                Question question = new Question(questionString,proposals,goodAnswer,cat);
                if(!QuestionRepository.add(question))return false;

            }

        }  catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
