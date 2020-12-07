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
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Add",add());
            }
        });
        classes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addSpecificTab("Classes","Teacher: \nStudents: \nTime: ");
            }
        });
    }

    public JPanel setMeals()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel label = new JLabel(" Set weekly meal plan ");
        label.setBackground(Color.cyan);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.gray, 2);
        label.setBorder(border);
        int labelWidth = label.getPreferredSize().width + 20;
        int labelHeight = label.getPreferredSize().height + 20;
        label.setPreferredSize(new Dimension(labelWidth, labelHeight));

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

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton submit = new JButton("submit");
        JButton cancel = new JButton("cancel");
        int buttonWidth = submit.getPreferredSize().width;
        int buttonHeight = submit.getPreferredSize().height + 10;
        submit.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        int button2Width = cancel.getPreferredSize().width;
        int button2Height = cancel.getPreferredSize().height + 10;
        submit.setPreferredSize(new Dimension(button2Width, button2Height));
        buttonPanel.add(cancel);
        buttonPanel.add(submit);

        panel.add(label, BorderLayout.NORTH);
        panel.add(daysPanel, BorderLayout.WEST);
        panel.add(mealsPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    public JPanel add()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel label = new JLabel(" Add new Student ");
        label.setBackground(Color.PINK);
        label.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        label.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.gray, 2);
        label.setBorder(border);
        int labelWidth = label.getPreferredSize().width + 20;
        int labelHeight = label.getPreferredSize().height + 20;
        label.setPreferredSize(new Dimension(labelWidth, labelHeight));

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

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton submit = new JButton("submit");
        JButton cancel = new JButton("cancel");
        int buttonWidth = submit.getPreferredSize().width;
        int buttonHeight = submit.getPreferredSize().height + 10;
        submit.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        int button2Width = cancel.getPreferredSize().width;
        int button2Height = cancel.getPreferredSize().height + 10;
        submit.setPreferredSize(new Dimension(button2Width, button2Height));
        buttonPanel.add(cancel);
        buttonPanel.add(submit);

        panel.add(label, BorderLayout.NORTH);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

}
