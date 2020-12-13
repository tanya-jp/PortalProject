package com.company;

import gui.CFrame;
import utils.FileUtils;

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
import java.util.Scanner;

public class LoginForm {

    private JFrame loginForm;
    private JButton loginButton;
    private JTextField unameField;
    private JPasswordField psswdField;
    private static final String INFO_PATH = ".\\user pass\\";

    public LoginForm(String title) {
        loginForm = new JFrame(title);
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

    public void showGUI() {
        loginForm.pack();
        loginForm.setVisible(true);
    }

    public File[] peopleFile()
    {
        File people[] = FileUtils.getFilesInDirectory(INFO_PATH);
        return people;
    }
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
    private class ButtonHandler implements ActionListener, FocusListener  {

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
                            Teacher teacher = new Teacher();
                        }
                        else
                        {
                            Admin admin = new Admin();
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

        @Override
        public void focusGained(FocusEvent e) {
            displayMessage("Focus gained", e);

        }

        @Override
        public void focusLost(FocusEvent e) {
            displayMessage("Focus lost", e);

        }

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
