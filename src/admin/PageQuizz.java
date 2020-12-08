package admin;


import model.Category;
import utils.database.category.CategoryRepository;
import utils.json.JSONUtils;

import javax.swing.*;
import java.util.List;


/**
 * Vue de gestion de quizz de l'interface graphique de l'admin.
 * Elle contient une reference vers le context (la MainPage) et vers les differents objects swing de la vue.
 * Elle permet de visualiser les quizz, de les supprimers et de charger de nouveaux quizz depuis un fichier Json.
 */
public class PageQuizz {
    private final MainPage context;
    private JPanel contentPane;
    private JTextArea categoryField;
    private JComboBox comboBox1;
    private JButton deleteButton;
    private JTextField loginField;
    private JButton createButton;
    private JTextArea errorTextArea;
    private JButton searchButton;
    private JButton exitButton;
    private JButton menuButton;


    /**
     * Definit le context de la vue et les actions liees aux boutons sur cette vue.
     * @param context la référence vers la MainPage de l'application.
     */
    public PageQuizz(MainPage context) {
        this.context = context;
        filluserField();

        deleteButton.addActionListener(e -> {
            ComboKeyValue currentKey = (ComboKeyValue) comboBox1.getSelectedItem();
            CategoryRepository.remove(currentKey.getKey());
            resetField();
            filluserField();
        });
        searchButton.addActionListener(e -> {
            JFileChooser choix = new JFileChooser();
            choix.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int retour=choix.showOpenDialog(context);
            if(retour==JFileChooser.APPROVE_OPTION){
                String absolutePath = choix.getSelectedFile().getAbsolutePath();
                loginField.setText(absolutePath);
            }
        });
        createButton.addActionListener(e -> {
            String path = loginField.getText();
            resetField();
            if (!JSONUtils.lectureJson(path))
                errorTextArea.setText("Unable to create new quizz");
            filluserField();

        });
        menuButton.addActionListener(e -> {
           context.changePage("menu");
        });
        exitButton.addActionListener(e -> {
            context.dispose();
        });
    }

    /**
     * Permet de reinitialiser les differents champs de la page
     */
    private void resetField(){
        categoryField.setText("");
        comboBox1.removeAllItems();
        errorTextArea.setText("");
        loginField.setText("");
    }

    /**
     * Permet d'afficher les categories contenues dans la base de donnees
     * Permet de remplir la liste déroulante pour afficher les categories dans celui-ci
     */
    private void  filluserField(){
        List<Category> categoryList = CategoryRepository.getAll();
        for (Category item:categoryList) {
            categoryField.append(item.getName()+"\n");
            comboBox1.addItem(new ComboKeyValue(item.getId(),item.getName()));
        }
    }

    public JPanel getContentPane() {
        return this.contentPane;
    }
}
