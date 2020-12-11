package com.company;

import gui.CFrame;
import utils.FileUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Admin {
    private CFrame frame;
    private JMenuItem meals;
    private JMenuItem students;
    private JMenuItem teachers;
    private JMenuItem classes;
    private JMenuItem addStudent;
    private JMenuItem addTeacher;
    private static final String INFO_PATH = "./user pass/";
    private static final String MEALS_PATH = "./meals/";

    public Admin()
    {
        frame = new CFrame("Admin");
        frame.getMainPanel().addPanel("PROFILE",profilePanel());
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

        JLabel saturday = new JLabel("Saturday ");
        JLabel sunday = new JLabel("Sunday ");
        JLabel monday = new JLabel("Monday ");
        JLabel tuesday = new JLabel("Tuesday ");
        JLabel wednesday = new JLabel("Wednesday ");
        JLabel thursday = new JLabel("Thursday ");
        JTextField saturdayF1 = new JTextField();
        JTextField saturdayP1 = new JTextField();
        JTextField saturdayF2 = new JTextField();
        JTextField saturdayP2 = new JTextField();
        JTextField sundayF1 = new JTextField();
        JTextField sundayP1 = new JTextField();
        JTextField sundayF2 = new JTextField();
        JTextField sundayP2 = new JTextField();
        JTextField mondayF1 = new JTextField();
        JTextField mondayP1 = new JTextField();
        JTextField mondayF2 = new JTextField();
        JTextField mondayP2 = new JTextField();
        JTextField tuesdayF1 = new JTextField();
        JTextField tuesdayP1 = new JTextField();
        JTextField tuesdayF2 = new JTextField();
        JTextField tuesdayP2 = new JTextField();
        JTextField wednesdayF1 = new JTextField();
        JTextField wednesdayP1 = new JTextField();
        JTextField wednesdayF2 = new JTextField();
        JTextField wednesdayP2 = new JTextField();
        JTextField thursdayF1 = new JTextField();
        JTextField thursdayP1 = new JTextField();
        JTextField thursdayF2 = new JTextField();
        JTextField thursdayP2 = new JTextField();

        JPanel label = new JPanel(new GridLayout(2,1));
        JPanel title = new JPanel(new GridLayout(1, 5, 5, 5));
        JPanel daysPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        JPanel mealsPanel = new JPanel(new GridLayout(6, 4, 5, 5));
        JLabel days = new JLabel("days");
        JLabel food1 = new JLabel("food");
        JLabel price1 = new JLabel("price");
        JLabel food2 = new JLabel("food");
        JLabel price2 = new JLabel("price");

        title.add(days);
        title.add(food1);
        title.add(price1);
        title.add(food2);
        title.add(price2);
        label.add(frame.getMainPanel().setLabel(" Set weekly meal plan ",Color.cyan));
        label.add(title);

        daysPanel.add(saturday);
        mealsPanel.add(saturdayF1);
        mealsPanel.add(saturdayP1);
        mealsPanel.add(saturdayF2);
        mealsPanel.add(saturdayP2);

        daysPanel.add(sunday);
        mealsPanel.add(sundayF1);
        mealsPanel.add(sundayP1);
        mealsPanel.add(sundayF2);
        mealsPanel.add(sundayP2);

        daysPanel.add(monday);
        mealsPanel.add(mondayF1);
        mealsPanel.add(mondayP1);
        mealsPanel.add(mondayF2);
        mealsPanel.add(mondayP2);

        daysPanel.add(tuesday);
        mealsPanel.add(tuesdayF1);
        mealsPanel.add(tuesdayP1);
        mealsPanel.add(tuesdayF2);
        mealsPanel.add(tuesdayP2);

        daysPanel.add(wednesday);
        mealsPanel.add(wednesdayF1);
        mealsPanel.add(wednesdayP1);
        mealsPanel.add(wednesdayF2);
        mealsPanel.add(wednesdayP2);

        daysPanel.add(thursday);
        mealsPanel.add(thursdayF1);
        mealsPanel.add(thursdayP1);
        mealsPanel.add(thursdayF2);
        mealsPanel.add(thursdayP2);

        panel.add(label, BorderLayout.NORTH);
        panel.add(daysPanel, BorderLayout.WEST);
        panel.add(mealsPanel, BorderLayout.CENTER);
        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        saveMeals(saturdayF1, saturdayP1, saturdayF2, saturdayP2, saturday);
        saveMeals(sundayF1, sundayP1, sundayF2, sundayP2, sunday);
        saveMeals(mondayF1, mondayP1, mondayF2, mondayP2, monday);
        saveMeals(tuesdayF1, tuesdayP1, tuesdayF2, tuesdayP2, tuesday);
        saveMeals(wednesdayF1, wednesdayP1, wednesdayF2, wednesdayP2, wednesday);
        saveMeals(thursdayF1, thursdayP1, thursdayF2, thursdayP2, thursday);
        return panel;
    }

    public JPanel add(String name)
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel user = new JLabel(" username: ");
        JLabel pass = new JLabel(" password: ");
        JTextField userF = new JTextField();
        JTextField passF = new JTextField();

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        fieldsPanel.add(user);
        fieldsPanel.add(userF);

        fieldsPanel.add(pass);
        fieldsPanel.add(passF);

        panel.add(frame.getMainPanel().setLabel(" Add new "+name,Color.PINK), BorderLayout.NORTH);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        savePerson(userF, passF, name);
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
        nameF.setEnabled(false);
        teacherF.setEnabled(false);
        studentF.setEnabled(false);
        unitsF.setEnabled(false);
        timeF.setEnabled(false);
        dayF.setEnabled(false);

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

    public JPanel profilePanel()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel info = new JPanel(new BorderLayout(5, 5));
        JPanel titlePanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JLabel name = new JLabel(" Username: ");
        JLabel pass = new JLabel(" Password: ");
        titlePanel.add(name);
        titlePanel.add(pass);
        JTextField nameF = new JTextField();
        JTextField passF = new JTextField();
        nameF.setEnabled(false);
        passF.setEnabled(false);
        infoPanel.add(nameF);
        infoPanel.add(passF);
        info.add(titlePanel, BorderLayout.NORTH);
        info.add(infoPanel, BorderLayout.CENTER);
        panel.add(frame.getMainPanel().setLabel("Your profile",Color.getHSBColor(160, 50, 100)), BorderLayout.NORTH);
        panel.add(info, BorderLayout.CENTER);
//        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        return panel;
    }

    public void savePerson(JTextField user, JTextField pass, String name) {
        frame.getMainPanel().getSubmit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String note_user = user.getText();
                String note_pass = pass.getText();
                String note = note_user + "\n" + note_pass + "\n" + name;
                System.out.println(note_user);
                System.out.println(note_pass);
                System.out.println(note);
                if (!note_pass.isEmpty() && !note_user.isEmpty()) {
                    boolean isSuccessful = new File(INFO_PATH).mkdirs();
                    System.out.println("Creating " + INFO_PATH + " directory is successful: " + isSuccessful);
                    FileUtils.fileWriter(note, INFO_PATH);
                }
            }
        });
    }

    public void saveMeals(JTextField f1, JTextField p1, JTextField f2, JTextField p2, JLabel d)
    {
        frame.getMainPanel().getSubmit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String food1 = f1.getText();
                String food2 = f2.getText();
                String price1 = p1.getText();
                String price2 = p2.getText();
                String day = d.getText();
                String note = day + "\n" + food1 + "\n" + price1 + "\n" + food2 + "\n" + price2 + "\n";
                System.out.println(note);
                if ((!food1.isEmpty() && !price1.isEmpty())||
                        (!food2.isEmpty() && !price2.isEmpty())) {
                    boolean isSuccessful = new File(MEALS_PATH).mkdirs();
                    System.out.println("Creating " + MEALS_PATH + " directory is successful: " + isSuccessful);
                    FileUtils.fileWriter(note, MEALS_PATH);
                    System.out.println(note);
                }
            }
        });
    }
}
