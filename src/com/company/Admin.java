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
    private JMenuItem addStudent;
    private JMenuItem addTeacher;

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
        addStudent = frame.addToMenu("Add Student");
        addTeacher = frame.addToMenu("AddTeacher");
    }

    private void addToTab()
    {
        meals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Meals",setMeals());
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
        addTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Add Teacher",add("teacher"));
            }
        });
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Add Student",add("student"));
            }
        });
        classes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Classes",ClassesList());
            }
        });
    }

    public JPanel setMeals()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel saturday = new JLabel(" Saturday: ");
        JLabel sunday = new JLabel(" Sunday: ");
        JLabel monday = new JLabel(" Monday: ");
        JLabel tuesday = new JLabel(" Tuesday: ");
        JLabel wednesday = new JLabel(" Wednesday: ");
        JLabel thursday = new JLabel(" Thursday: ");
        JTextField saturdayF = new JTextField();
        JTextField sundayF = new JTextField();
        JTextField mondayF = new JTextField();
        JTextField tuesdayF = new JTextField();
        JTextField wednesdayF = new JTextField();
        JTextField thursdayF = new JTextField();

        JPanel daysPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        JPanel mealsPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        daysPanel.add(saturday);
        mealsPanel.add(saturdayF);

        daysPanel.add(sunday);
        mealsPanel.add(sundayF);

        daysPanel.add(monday);
        mealsPanel.add(mondayF);

        daysPanel.add(tuesday);
        mealsPanel.add(tuesdayF);

        daysPanel.add(wednesday);
        mealsPanel.add(wednesdayF);

        daysPanel.add(thursday);
        mealsPanel.add(thursdayF);

        panel.add(frame.getMainPanel().setLabel(" Set weekly meal plan ",Color.cyan), BorderLayout.NORTH);
        panel.add(daysPanel, BorderLayout.WEST);
        panel.add(mealsPanel, BorderLayout.CENTER);
        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        return panel;
    }

    public JPanel add(String name)
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel fName = new JLabel(" first name: ");
        JLabel lName = new JLabel(" last name: ");
        JLabel user = new JLabel(" username: ");
        JLabel pass = new JLabel(" password: ");
        JTextField fNameF = new JTextField();
        JTextField lNameF = new JTextField();
        JTextField userF = new JTextField();
        JTextField passF = new JTextField();

        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        fieldsPanel.add(fName);
        fieldsPanel.add(fNameF);

        fieldsPanel.add(lName);
        fieldsPanel.add(lNameF);

        fieldsPanel.add(user);
        fieldsPanel.add(userF);

        fieldsPanel.add(pass);
        fieldsPanel.add(passF);

        panel.add(frame.getMainPanel().setLabel(" Add new "+name,Color.PINK), BorderLayout.NORTH);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        return panel;
    }

    public JPanel ClassesList()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel name = new JLabel(" Name: ");
        JLabel teacher = new JLabel(" Teacher: ");
        JLabel student = new JLabel(" Students: ");
        JLabel time = new JLabel(" Time: ");
        JLabel units = new JLabel(" Units: ");
        JLabel day = new JLabel(" Days: ");
        JTextField nameF = new JTextField();
        JTextField teacherF = new JTextField();
        JTextField studentF = new JTextField();
        JTextField timeF = new JTextField();
        JTextField unitsF = new JTextField();
        JTextField dayF = new JTextField();

        JPanel titlePanel = new JPanel(new GridLayout(1, 6, 5, 5));
        JPanel infoPanel = new JPanel(new GridLayout(1, 6, 5, 5));

        titlePanel.add(name);
        infoPanel.add(nameF);

        titlePanel.add(teacher);
        infoPanel.add(teacherF);

        titlePanel.add(day);
        infoPanel.add(dayF);

        titlePanel.add(time);
        infoPanel.add(timeF);

        titlePanel.add(student);
        infoPanel.add(studentF);

        titlePanel.add(units);
        infoPanel.add(unitsF);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 5, 5));
        JButton ok = new JButton("OK");
        int buttonWidth = ok.getPreferredSize().width;
        int buttonHeight = ok.getPreferredSize().height + 10;
        ok.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonPanel.add(ok);

        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);
        return panel;
    }
}
