package client;

import model.Question;

import javax.swing.*;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;

public class PageGame implements Page {
    private final MainPage context;
    private final List<Question> questions;
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

    /**
     * Définit le context de la vue et les actions liées aux boutons sur cette vue.
     * Ce constructeur définit également la liste des questions de la partie ainsi qu'un iterator sur cette liste.
     * @param context la MainPage de l'application
     * @param questions la liste des questions de la partie en cours.
     */
    public PageGame(MainPage context, List<Question> questions) {
        this.context = context;
        this.listener = new GameListener(this, context.getConnection());
        this.questions = questions;
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
     * Affiche la prochaine question ou retourne au lobby si la partie est finie.
     */
    private void loadQuestion() {
        if(iterator.hasNext()) {
            currentQuestion = iterator.next();
            questionField.setText(currentQuestion.getText());
            prop1Field.setText(currentQuestion.getProposals().get(0).getText());
            prop2Field.setText(currentQuestion.getProposals().get(1).getText());
            prop3Field.setText(currentQuestion.getProposals().get(2).getText());
            prop4Field.setText(currentQuestion.getProposals().get(3).getText());
        } else {
            context.returnToLobby();
        }
    }

    /**
     * Vérifie si la réponse fournie par l'utilisateur est la bonne.
     * Met à jour le champs score
     * @param index
     */
    private void checkQuestion(int index) {
        if (currentQuestion.getCorrectAnswerIndex() == index)
            context.addPoint();
        loadQuestion();
    }

    @Override
    public JPanel getContentPane() {
        return this.contentPane;
    }

    /**
     * Permet la fermeture proprement de la vue ainsi que de la connection.
     */
    @Override
    public void close() {
        context.dispose();
    }
}
