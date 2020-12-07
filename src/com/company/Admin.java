package com.company;

import gui.CFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin {
    private CFrame frame;
    private JMenuItem meals;
    private JMenuItem students;
    private JMenuItem teachers;
    private JMenuItem classes;
    private JMenuItem add;

    public Admin()
    {
        frame = new CFrame("Admin");
        addFrameMenu();
        addToTab();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addFrameMenu()
    {
        meals = frame.addToMenu("Set Meals");
        students = frame.addToMenu("Students");
        teachers = frame.addToMenu("Teachers");
        classes = frame.addToMenu("Classes");
        add = frame.addToMenu("Add");
    }

    private void addToTab()
    {
        meals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                frame.getMainPanel().addSpecificTab("Meals","Saturday: \n" +
//                        "Sunday: \nMonday: \nTuesday: \nWednesday: \nThursday: ");
                frame.getMainPanel().addMeal(setMeals());
            }
        });
        students.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addSpecificTab("Students","STUDENTS:");
            }
        });
        teachers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addSpecificTab("Teachers","TEACHERS:");
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addSpecificTab("Add","Add new student: \nAdd new teacher: ");
            }
        });
        classes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addSpecificTab("Classes","Teacher: \nStudents: \nTime: ");
            }
        });
    }

    public JFrame setMeals()
    {
        JFrame mealsForm;
        mealsForm = new JFrame();
        mealsForm.setLocationRelativeTo(null);
//        mealsForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout(5, 5));
//        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
//        mealsForm.setContentPane(panel);
//        JLabel label = new JLabel(" Set weakly meal plan ");
//        label.setBackground(Color.cyan);
//        label.setHorizontalAlignment(SwingConstants.CENTER);
//        label.setOpaque(true);
//        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
//        label.setBorder(border);
//        int labelWidth = label.getPreferredSize().width + 20;
//        int labelHeight = label.getPreferredSize().height + 10;
//        label.setPreferredSize(new Dimension(labelWidth, labelHeight));
//
//        JLabel unameLabel = new JLabel(" Username : ");
//        JTextField unameField = new JTextField("Enter Username ");
//        JLabel psswdLabel = new JLabel(" Password : ");
//        JPasswordField psswdField = new JPasswordField();
//
//        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
//        fieldsPanel.add(unameLabel);
//        fieldsPanel.add(unameField);
//        fieldsPanel.add(psswdLabel);
//        fieldsPanel.add(psswdField);
//
//        JButton loginButton = new JButton("Login");
//        int buttonWidth = loginButton.getPreferredSize().width;
//        int buttonHeight = loginButton.getPreferredSize().height + 10;
//        loginButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
//
//        panel.add(label, BorderLayout.NORTH);
//        panel.add(fieldsPanel, BorderLayout.CENTER);
//        panel.add(loginButton, BorderLayout.SOUTH);
//
//        mealsForm.setVisible(true);
        return mealsForm;
    }

}
