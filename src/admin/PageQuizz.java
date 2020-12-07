package admin;


import model.Category;
import utils.database.category.CategoryRepository;
import utils.json.JSONUtils;

import javax.swing.*;
import java.util.List;

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


    public PageQuizz(MainPage context) {
        this.context = context;
        filluserField();

        deleteButton.addActionListener(e -> {
            ComboKeyValue currentKey = (ComboKeyValue) comboBox1.getSelectedItem();
            System.out.println(CategoryRepository.remove(currentKey.getKey()));
            resetField();
            filluserField();
        });
        searchButton.addActionListener(e -> {
            JFileChooser choix = new JFileChooser();
            choix.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int retour=choix.showOpenDialog(context);
            if(retour==JFileChooser.APPROVE_OPTION){
                String nameFile = choix.getSelectedFile().getAbsolutePath();
                loginField.setText(nameFile);
            }
        });
        createButton.addActionListener(e -> {
            String path = loginField.getText();
            resetField();
            if (!JSONUtils.lectureJson(path))
                errorTextArea.setText("Unable to create new quizz");
            filluserField();

        });
    }

    private void resetField(){
        categoryField.setText("");
        comboBox1.removeAllItems();
        errorTextArea.setText("");
        loginField.setText("");
    }
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
