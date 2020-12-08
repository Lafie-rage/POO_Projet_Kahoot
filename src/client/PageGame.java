package client;

import model.Question;
import utils.Commons;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PageGame implements Page {
    private final MainPage context;
    private final int nbQuestion;
    private final GameListener listener;
    private Question currentQuestion;
    private Iterator<Question> iterator;
    private JPanel contentPane;
    private JButton buttonExit;
    private JButton buttonProp1;
    private JButton buttonProp2;
    private JButton buttonProp3;
    private JButton buttonProp4;
    private JTextArea questionField;
    private JTextArea prop1Field;
    private JTextArea prop2Field;
    private JTextArea prop3Field;
    private JTextArea prop4Field;
    private JTextField scoreField;

    /**
     * Definit le context de la vue et les actions liees aux boutons sur cette vue.
     * Ce constructeur definit egalement la liste des questions de la partie ainsi qu'un iterator sur cette liste.
     * @param context la MainPage de l'application.
     * @param questions la liste des questions de la partie en cours.
     */
    public PageGame(MainPage context, List<Question> questions) {
        this.context = context;
        this.listener = new GameListener(this, context.getConnection());
        this.listener.start();
        nbQuestion = questions.size();
        iterator = questions.iterator();
        loadQuestion();
        buttonProp1.addActionListener(e -> {
            checkQuestion(0);
        });

        buttonProp2.addActionListener(e -> {
            checkQuestion(1);
        });

        buttonProp3.addActionListener(e -> {
            checkQuestion(2);
        });

        buttonProp4.addActionListener(e -> {
            checkQuestion(3);
        });

        buttonExit.addActionListener(e -> {
            context.dispose();
        });
    }

    /**
     * Affiche la prochaine question.
     * S'il n'y a plus de question, affichage du score et fermeture de la page.
     */
    private void loadQuestion() {
        if(iterator.hasNext()) {
            currentQuestion = iterator.next();
            questionField.setText(currentQuestion.getText());
            prop1Field.setText(currentQuestion.getProposals().get(0).toString());
            prop2Field.setText(currentQuestion.getProposals().get(1).toString());
            prop3Field.setText(currentQuestion.getProposals().get(2).toString());
            prop4Field.setText(currentQuestion.getProposals().get(3).toString());
        } else {
            JOptionPane.showMessageDialog(context, "Your score is " + context.getScore() + " on " + nbQuestion);
            listener.interrupt();
            context.dispose();
        }
    }

    /**
     * Verifie si la reponse fournie par l'utilisateur est la bonne.
     * Met a jour le champs score
     * @param index
     */
    private void checkQuestion(int index) {
        if (currentQuestion.getCorrectAnswerIndex() == index) {
            context.addPoint();
            scoreField.setText("Score : " + context.getScore());
        }
        loadQuestion();
    }

    @Override
    public JPanel getContentPane() {
        return this.contentPane;
    }

    /**
     * Permet la fermeture de manière propre de la vue ainsi que de la connection lorsque la connexion au serveur est perdue.
     */
    @Override
    public void close() {
        context.dispose();
    }

}
