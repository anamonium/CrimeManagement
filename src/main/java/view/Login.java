package view;

import Officer.Officer;
import entity.OfficerEntity;
import manageDatabase.ManageDatabase;
import reports.CrimeReport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel panel1;
    private JTextField badgeNumberField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton logInButton;

    static JFrame loginFrame;
    private static Login loginForm;

public Login() {
    logInButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String badgeNumber = badgeNumberField.getText();
            String username = usernameField.getText();
            char [] pass = passwordField.getPassword();
            String password = "";

            for(char i: pass){
                password = password + i;
            }

            int badge = Integer.parseInt(badgeNumber);

            ManageDatabase mgd = new ManageDatabase();
            OfficerEntity officer = mgd.getOfficer(badge, username, password);

            if(officer == null){
                JOptionPane.showMessageDialog(null, "Wrong data");
            }
            else{
                Officer.getInstance(officer);

                Menu menu = new Menu();
                menu.showMenu();
                loginFrame.setVisible(false);
            }
        }
    });
}

    public static void main(String [] args){
        loginFrame = new JFrame();
        loginForm = new Login();
        loginFrame.setContentPane(loginForm.panel1);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);
    }
}
