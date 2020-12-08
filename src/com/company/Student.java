package com.company;
import gui.CFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student {
    private CFrame frame;
    private JMenuItem increaseBudget;
    private JMenuItem meals;
    private JMenuItem classes;

    public Student()
    {
        frame = new CFrame("Student");
        frame.getMainPanel().addPanel("PROFILE",profilePanel());
        addFrameMenu();
        addToTab();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addFrameMenu()
    {

        meals = frame.addToMenu("Set Meals");
        classes = frame.addToMenu("Classes");
        increaseBudget = frame.addToMenu("Increase budget");
    }
    private void addToTab()
    {
        meals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Meals",setMeals());
            }
        });
        increaseBudget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Increase budget",changeBudget());
            }
        });

    }
    public JPanel setMeals()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JCheckBox saturday = new JCheckBox(" Saturday: ");
        JCheckBox sunday = new JCheckBox(" Sunday: ");
        JCheckBox monday = new JCheckBox(" Monday: ");
        JCheckBox tuesday = new JCheckBox(" Tuesday: ");
        JCheckBox wednesday = new JCheckBox(" Wednesday: ");
        JCheckBox thursday = new JCheckBox(" Thursday: ");

        String m1[] = { "first1", "second1"};
        JComboBox saturdayBox = new JComboBox(m1);

        String m2[] = { "first2", "second2"};
        JComboBox sundayBox = new JComboBox(m2);

        String m3[] = { "first3", "second3"};
        JComboBox mondayBox = new JComboBox(m3);

        String m4[] = { "first4", "second4"};
        JComboBox tuesdayBox = new JComboBox(m4);

        String m5[] = { "first5", "second5"};
        JComboBox wednesdayBox = new JComboBox(m5);

        String m6[] = { "first6", "second6"};
        JComboBox thursdayBox = new JComboBox(m6);


        JPanel daysPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        JPanel mealsPanel =new JPanel(new GridLayout(6, 1, 5, 5));
        JPanel labels = new JPanel(new BorderLayout(5, 5));

        daysPanel.add(saturday);
        daysPanel.add(sunday);
        daysPanel.add(monday);
        daysPanel.add(tuesday);
        daysPanel.add(wednesday);
        daysPanel.add(thursday);
        mealsPanel.add(saturdayBox);
        mealsPanel.add(sundayBox);
        mealsPanel.add(mondayBox);
        mealsPanel.add(tuesdayBox);
        mealsPanel.add(wednesdayBox);
        mealsPanel.add(thursdayBox);
        labels.add(frame.getMainPanel().setLabel(" Set weekly meal plan ",Color.getHSBColor(122,13,14)),
                BorderLayout.CENTER);
        labels.add(frame.getMainPanel().setLabel("Budget: ", Color.red),BorderLayout.EAST);
        panel.add(labels, BorderLayout.NORTH);
        panel.add(daysPanel, BorderLayout.WEST);
        panel.add(mealsPanel, BorderLayout.CENTER);
        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        return panel;
    }

    public JPanel changeBudget()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel id = new JLabel(" ID: ");
        JLabel pass = new JLabel(" password: ");
        JLabel amount = new JLabel(" amount: ");

        JTextField idF = new JTextField();
        JTextField amountF = new JTextField();
        JPasswordField passF = new JPasswordField();

        JPanel labelPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        labelPanel.add(id);
        fieldsPanel.add(idF);

        labelPanel.add(pass);
        fieldsPanel.add(passF);

        labelPanel.add(amount);
        fieldsPanel.add(amountF);

        panel.add(frame.getMainPanel().setLabel(" Increase budget ",
                Color.green), BorderLayout.NORTH);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(labelPanel, BorderLayout.WEST);
        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        return panel;
    }

    public JPanel profilePanel()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel info = new JPanel(new BorderLayout(5, 5));
        JPanel infoPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        JLabel name = new JLabel(" Username: ");
        JLabel pass = new JLabel(" Password: ");
        JLabel budget = new JLabel(" budget: ");
        JLabel average = new JLabel(" Average: ");
        JTextField nameF = new JTextField();
        JTextField passF = new JTextField();
        JTextField budgetF = new JTextField();
        JTextField averageF = new JTextField();
        JTextField classesF = new JTextField();

        infoPanel.add(name);
        infoPanel.add(nameF);

        infoPanel.add(pass);
        infoPanel.add(passF);

        infoPanel.add(budget);
        infoPanel.add(budgetF);

        infoPanel.add(average);
        infoPanel.add(averageF);

//        infoPanel.add(classes);

        nameF.setEnabled(false);
        passF.setEnabled(false);
        budgetF.setEnabled(false);
        averageF.setEnabled(false);
        classesF.setEnabled(false);

        info.add(infoPanel, BorderLayout.NORTH);
        info.add(classesF, BorderLayout.CENTER);
        panel.add(frame.getMainPanel().setLabel("Your profile",Color.getHSBColor(160, 50, 100)), BorderLayout.NORTH);
        panel.add(info, BorderLayout.CENTER);
//        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        return panel;
    }

//    public void chooseClass()
//    {
//        JPanel classPanel = new JPanel(new GridLayout(6, 1, 5, 5));
//        JCheckBox class1 = new JCheckBox(" class1: ");
//        JCheckBox class2 = new JCheckBox(" class2: ");
//
//    }
}
