package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JButton addNewArrestReportButton;
    private JPanel panel1;
    private JButton addNewCrimeRecordButton;
    private JButton browseReportsButton;

    static JFrame menuFrame;
    private static Menu menuForm;

public Menu() {
    addNewArrestReportButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Arrest.createArrestView();
        }
    });
    addNewCrimeRecordButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Crime.createCrimeView();
        }
    });
    browseReportsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Search search = new Search();
            search.showSearch();;
        }
    });
}

public void showMenu(){
    menuFrame = new JFrame();
    menuForm = new Menu();
    menuFrame.setContentPane(menuForm.panel1);
    menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    menuFrame.pack();
    menuFrame.setVisible(true);
}
}
