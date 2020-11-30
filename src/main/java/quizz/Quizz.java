package quizz;

import org.json.JSONObject;
import quizz.model.*;
import utils.database.category.CategoryRepository;
import utils.database.game.GameRepository;
import utils.database.player.PlayerRepository;
import utils.database.question.QuestionRepository;
import utils.database.score.ScoreRepository;
import utils.json.JSONUtils;
import utils.json.URLOfQuizz;

import java.util.*;

public class Quizz {

    private static final Scanner scan = new Scanner(System.in);
    private static Game game;
    private static Map<Player, Score> playerScoreDictionary = new HashMap<>();

    public Quizz() {
        PlayerRepository playerRepository = new PlayerRepository();
        ScoreRepository scoreRepository = new ScoreRepository();
        GameRepository gameRepository = new GameRepository();
        List<Player> players = new ArrayList<>();
        putJsonInDatabse();
        int nbPlayers = retrieveNumberOfPlayers();
        for(int i = 0; i < nbPlayers; i++) {
            players.add(retrievePlayerName(playerRepository.getAll()));
            playerRepository.add(players.get(i));
        }
        int nbQuestions = retrieveNumberOfQuestions();
        Category category = chooseCategory();

        game = new Game(nbQuestions, players, category);

        for(Player player : players) {
            playerScoreDictionary.put(player, new Score(player, game));
        }

        gameRepository.add(game);

        List<Question> questions = category.getQuestions(game.getNbQuestions());
        for (Player player : game.getJoueurs())
            askQuestions(questions, player);

        int highScore = -1;
        Player winner = null;
        for(Player player : game.getJoueurs()) {
            scoreRepository.add(playerScoreDictionary.get(player));
            if(highScore < playerScoreDictionary.get(player).getScore()) {
                highScore = playerScoreDictionary.get(player).getScore();
                winner = player;
            }
        }

        System.out.println("Bravo " + winner.getName() + "! Tu as réussis le quizz ! ;)\n" +
                "Et voici ton score : " + highScore + " point(s) sur " + game.getNbQuestions());
        scan.close();
    }

    public void putJsonInDatabse() {
        QuestionRepository questionRepository = new QuestionRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        if(questionRepository.getCountQuestion() > 0) // on évite de réinclure les json s'ils sont déjà inclus
            return; // On pourrait d'ailleur stocker l'url du JSON appelé dans la catégorie afin de pouvoir inclure de nouvelles questions...

        List<JSONObject> jsonObjects = new ArrayList<>();
        for (String url : URLOfQuizz.retrievetURLs()) {
            JSONObject jsonObject= JSONUtils.getJSONObjetcFromUrl(url);
            if (jsonObject != null) // On vérifie qu'on a réussi à parse le JSON même s'il ne devrait pas être null
                jsonObjects.add(jsonObject);
        }
        List<Category> categories = retrieveCategories(jsonObjects);

        for(Category category : categories)
            categoryRepository.add(category);

    }

    private static int retrieveNumberOfPlayers() {
        System.out.println("Tu vas bientôt pouvoir jouer ! Mais d'abord, combien de joueurs veux-tu dans cette partie ?");
        while (true) {
            String input = scan.nextLine();
            try {
                int nbQuestions = Integer.parseInt(input);
                if (nbQuestions < 1)
                    System.out.println("Ca fait pas beaucoup tout de même... Ca serait trop facile de gagner sinon..! Entre une valeur d'au moins 1 !");
                else if (nbQuestions > 8)
                    System.out.println("C'est peut-être un peu trop... Je sais que tu es un pro mais tu ne pourras avoir qu'au maximum 8 joueurs !");
                else {
                    return nbQuestions;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ce n'est pas un nombre..! Entre un nombre cette fois-ci !");
            }
        }
    }

    private static Player retrievePlayerName(List<Player> players) {
        PlayerRepository playerRepository = new PlayerRepository();
        System.out.println("A tu déjà un compte ?");
        while (true) {
            System.out.println("0 - Non, je veux en créer un !");
            for (Player player : players)
                System.out.println(player.toString());
            String input = scan.nextLine();
            try {
                int choix = Integer.parseInt(input);
                if(choix == 0) {
                    System.out.println("Commence par entrer ton nom :");
                    String name = scan.nextLine().replace("\n", "");
                    System.out.println("Maintenant ton pseudo ! Attention, il ne doit pas être déjà pris !");
                    String pseudo;
                    while(true) {
                        pseudo = scan.nextLine().replace("\n", "");
                        if(!playerRepository.pseudoAlreadyTaken(pseudo))
                            break;
                        System.out.println("Ce pseudo est déjà pris..! Entres en un autre !");
                    }
                    System.out.println("Entre ton mot de passe maintenant !");
                    String password = scan.nextLine().replace("\n", "");
                    return new Player(name, pseudo, password);
                }
                else if (choix < 0)
                    System.out.println("Ca fait pas beaucoup tout de même... Ca serait trop facile de gagner sinon..! Entre une valeur d'au moins 1 !");
                else if (choix > players.size())
                    System.out.println("C'est peut-être un peu trop... Je sais que tu es un pro mais tu ne pourras avoir qu'au maximum 10 questions !");
                else {
                    return playerRepository.get(choix);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ce n'est pas un nombre..! Entre un nombre cette fois-ci !");
            }
        }

    }

    private static List<Category> retrieveCategories(List<JSONObject> jsonObjects) {
        List<Category> categories = new ArrayList<>();
        for (JSONObject json : jsonObjects)
            categories.add(new Category(json.get("thème").toString(), json));
        return categories;
    }

    private static int retrieveNumberOfQuestions() {
        System.out.println("Un dernier petit effort ! Combien de questions veux-tu dans cette partie ?");
        while (true) {
            String input = scan.nextLine();
            try {
                int nbQuestions = Integer.parseInt(input);
                if (nbQuestions < 1)
                    System.out.println("Ca fait pas beaucoup tout de même... Ca serait trop facile de gagner sinon..! Entre une valeur d'au moins 1 !");
                else if (nbQuestions > 10)
                    System.out.println("C'est peut-être un peu trop... Je sais que tu es un pro mais tu ne pourras avoir qu'au maximum 10 questions !");
                else {
                    return nbQuestions;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ce n'est pas un nombre..! Entre un nombre cette fois-ci !");
            }
        }
    }

    private static Category chooseCategory() {
        CategoryRepository repository = new CategoryRepository();
        List<Category> categories = repository.getAll();
        System.out.println("Promis, tu pourras jouer juste après! Il va falloir que tu choisisses la catégorie de questions dans laquelle tu veux jouer ! La voici :");
        while (true) {
            for (Category categ : categories)
                System.out.println("\t" + categ.toString());

            String input = scan.nextLine();
            try {
                int index = Integer.parseInt(input);
                if (index < categories.get(0).getId() || index > categories.get(categories.size() - 1).getId())
                    System.out.println("La valeur n'est pas dans la liste..! Reessaye");
                else {
                    return repository.get(index);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ce n'est pas un nombre..! Entre un nombre cette fois-ci !");
            }
        }
    }

    private static void askQuestions(List<Question> questions, Player player) {
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            while (true) {
                System.out.println(i+1 + " - " + question.getText());
                for (Answer prop : question.getProposals())
                    System.out.println("\t" + prop.toString());

                System.out.println("Entrez le numéro de la réponse souhaitée");
                String userInput = scan.nextLine();
                try {
                    int pos = Integer.parseInt(userInput);
                    if (pos < 0 || pos > 3) {
                        System.out.println("Nombre invalide...");
                    } else {
                        if (question.isCorrect(pos)) {
                            System.out.println("Bonne réponse !");
                            playerScoreDictionary.get(player).addPoint(1);
                        } else
                            System.out.println("Mauvaise réponse..!");
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Cette réponse n'est pas un nombre...");
                }
            }
        }
    }
}
