package com.company;
/**
 * This class creates login panel and checks username and password, then if they were correct checks who is that
 * person(admin, student or teacher). Then creates new person.
 * Whenever a person logs out this class will be run.
 * @author Tanya Djavaherpour
 * @version 1.0 2020
 */

import utils.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LoginForm {

    private JFrame loginForm;
    private JButton loginButton;
    private JTextField unameField;
    private JPasswordField psswdField;
    private static final String INFO_PATH = ".\\user pass\\";

    /**
     * Creates new login form and sets its properties
     */
    public LoginForm() {
        loginForm = new JFrame("log");
        loginForm.setLocationRelativeTo(null);
        loginForm.setLocation(700, 300);
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(1000, 50));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        loginForm.setContentPane(panel);

        JLabel label = new JLabel(" Please Enter Your Information ");
        label.setBackground(Color.ORANGE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);

        int labelWidth = label.getPreferredSize().width;
        int labelHeight = label.getPreferredSize().height + 10;
        label.setPreferredSize(new Dimension(labelWidth, labelHeight));

        ButtonHandler handler = new ButtonHandler();

        JLabel unameLabel = new JLabel(" Username : ");
        unameField = new JTextField("Enter Username ...");

        unameField.addActionListener(handler);
        unameField.addFocusListener(handler);

        JLabel psswdLabel = new JLabel(" Password : ");
        psswdField = new JPasswordField();

        psswdField.addActionListener(handler);
        psswdField.addFocusListener(handler);

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        fieldsPanel.add(unameLabel);
        fieldsPanel.add(unameField);
        fieldsPanel.add(psswdLabel);
        fieldsPanel.add(psswdField);


        loginButton = new JButton("Login");

        loginButton.addActionListener(handler);

        int buttonWidth = loginButton.getPreferredSize().width;
        int buttonHeight = loginButton.getPreferredSize().height + 10;
        loginButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        panel.add(label, BorderLayout.NORTH);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(loginButton, BorderLayout.SOUTH);
    }

    /**
     * Makes login form visible and shows it.
     */
    public void showGUI() {
        loginForm.pack();
        try {
            loginForm.setIconImage(ImageIO.read(new File("./login.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginForm.setVisible(true);
    }

    /**
     * @return all usernames and passwords files
     */
    public File[] peopleFile()
    {
        File people[] = FileUtils.getFilesInDirectory(INFO_PATH);
        return people;
    }

    /**
     * Checks if the entered username is correct or not
     * @param user as entered username
     * @return true if this username is available in user pass file or false if it is not.
     */
    public boolean findPeople(String user)
    {
        File people[] = peopleFile();
        int person = 0;
        while(person < people.length)
        {
            if (people[person].toString().contains(INFO_PATH+user+".txt"))
                return true;
            person++;
        }
        return false;
    }

    /**
     * After checking username this method checks if the entered password is correct for this username.
     * @param user as entered username
     * @param pass as entered password
     * @return true if password ic correct and false if it is not.
     * @throws FileNotFoundException checks if wanted file is available to scan it
     */
    public boolean checkPass(String user, String pass) throws FileNotFoundException {
        File people[] = peopleFile();
        int person = 0;
        int cnt = 0;
        while(person < people.length)
        {
            if (people[person].toString().contains(INFO_PATH+user+".txt"))
            {
                Scanner scanner = new Scanner(people[person]);
                cnt = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(cnt == 2)
                    {
                        if(line.equals(pass))
                            return true;
                    }

                    cnt++;
                }
            }
            person++;
        }
        return false;
    }

    /**
     * Find the position of the person who wants to login in.
     * @param user as  entered username
     * @return position of person
     * @throws FileNotFoundException checks if wanted file is available to scan it
     */
    public String findPosition(String user) throws FileNotFoundException
    {
        File people[] = peopleFile();
        int person = 0;
        int cnt = 0;
        String pos = null;
        while(person < people.length)
        {
            if (people[person].toString().contains(INFO_PATH+user+".txt"))
            {
                Scanner scanner = new Scanner(people[person]);
                cnt = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(cnt == 1)
                    {
                        pos = line;
                    }

                    cnt++;
                }
            }
            person++;
        }
        return pos;
    }

    /**
     * This class use previous methods to check everything and if it was ok logs in to the panel.
     */
    private class ButtonHandler implements ActionListener, FocusListener  {
        /**
         * After clicking this method will be run.
         * A semantic event which indicates that a component-defined action occurred.
         * @param e as clicked object
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            String user = unameField.getText();
            String pwd = new String(psswdField.getPassword());
            if(!findPeople(user))
                JOptionPane.showMessageDialog(loginForm, "Incorrect username!", "Result", JOptionPane.ERROR_MESSAGE);
            try {
                if (checkPass(user,pwd)) {
                    JOptionPane.showMessageDialog(loginForm, "Successful!", "Result", JOptionPane.INFORMATION_MESSAGE);
                    loginForm.setVisible(false);
                    try {
                        String pos = findPosition(user);
                        if(pos.equals("student"))
                        {
                            Student student = new Student(user);
                        }
                        else if(pos.equals("teacher"))
                        {
                            Teacher teacher = new Teacher(user);
                        }
                        else
                        {
                            Admin admin = new Admin(user);
                        }
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(loginForm, "Incorrect password!", "Result", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }

        /**
         * Displays a message when we focus.
         * @param e
         */
        @Override
        public void focusGained(FocusEvent e) {
            displayMessage("Focus gained", e);

        }

        /**
         * Displays a message when we finish focusing
         * @param e
         */
        @Override
        public void focusLost(FocusEvent e) {
            displayMessage("Focus lost", e);

        }

        /**
         * Displays message
         * @param prefix
         * @param e
         */
        void displayMessage(String prefix, FocusEvent e) {
            System.out.println(prefix
                    + (e.isTemporary() ? " (temporary):" : ":")
                    + e.getComponent().getClass().getName()
                    + "; Opposite component: "
                    + (e.getOppositeComponent() != null ? e.getOppositeComponent().getClass().getName()
                    : "null"));
        }
    }

}
