package com.company;
import gui.CFrame;
import utils.FileUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Student {
    private CFrame frame;
    private JMenuItem increaseBudget;
    private JMenuItem meals;
    private JMenuItem classes;
    private static final String MEALS_PATH = "./meals/";
    private static final String STUDENTS_PATH = "./students/";

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
                try {
                    frame.getMainPanel().addPanel("Meals",setMeals());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        increaseBudget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Increase budget",changeBudget());
            }
        });

    }

    public JPanel setMeals() throws FileNotFoundException {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JCheckBox saturday = new JCheckBox(" Saturday: ");
        JCheckBox sunday = new JCheckBox(" Sunday: ");
        JCheckBox monday = new JCheckBox(" Monday: ");
        JCheckBox tuesday = new JCheckBox(" Tuesday: ");
        JCheckBox wednesday = new JCheckBox(" Wednesday: ");
        JCheckBox thursday = new JCheckBox(" Thursday: ");

        JPanel mealsPanel =new JPanel(new GridLayout(6, 1, 5, 5));
        int days = 0;
        File curr[] = FileUtils.getFilesInDirectory(MEALS_PATH);
        while(days < curr.length)
        {
            String food1 = null;
            String food2 = null;
            int cnt = 0;
            Scanner scanner = new Scanner(curr[days]);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(cnt == 1)
                    food1 = line;
                else if(cnt == 2)
                    food1 += "     " + line;
                else if(cnt == 3)
                    food2 = line;
                else if(cnt == 4)
                    food2 += "     " + line;
                cnt++;
            }
            String m1[] = { food1, food2};
            if(curr[days].toString().contains("Saturday"))
            {
                JComboBox saturdayBox = new JComboBox(m1);
                mealsPanel.add(saturdayBox,0);
            }
            else if(curr[days].toString().contains("Sunday"))
            {
                JComboBox sundayBox = new JComboBox(m1);
                mealsPanel.add(sundayBox,1);
            }
            else if(curr[days].toString().contains("Monday"))
            {
                JComboBox mondayBox = new JComboBox(m1);
                mealsPanel.add(mondayBox,2,0);
            }
            else if(curr[days].toString().contains("Tuesday"))
            {
                JComboBox tuesdayBox = new JComboBox(m1);
                mealsPanel.add(tuesdayBox,3);
            }
            else if(curr[days].toString().contains("Wednesday"))
            {
                JComboBox wednesdayBox = new JComboBox(m1);
                mealsPanel.add(wednesdayBox,4);
            }
            else if(curr[days].toString().contains("Thursday"))
            {
                JComboBox thursdayBox = new JComboBox(m1);
                mealsPanel.add(thursdayBox);
            }
            days++;

        }



        JPanel daysPanel = new JPanel(new GridLayout(6, 1, 5, 5));

        JPanel labels = new JPanel(new BorderLayout(5, 5));

        daysPanel.add(saturday);
        daysPanel.add(sunday);
        daysPanel.add(monday);
        daysPanel.add(tuesday);
        daysPanel.add(wednesday);
        daysPanel.add(thursday);
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
        JLabel username = new JLabel(" username: ");
        JLabel id = new JLabel(" ID: ");
        JLabel pass = new JLabel(" password: ");
        JLabel amount = new JLabel(" amount: ");

        JTextField usernameF = new JTextField();
        JTextField idF = new JTextField();
        JTextField amountF = new JTextField();
        JPasswordField passF = new JPasswordField();

        JPanel labelPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        labelPanel.add(username);
        fieldsPanel.add(usernameF);

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
        charge(usernameF, amountF);
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
        panel.add(frame.getMainPanel().setLabel("Your profile",Color.getHSBColor(248, 56, 155)), BorderLayout.NORTH);
        panel.add(info, BorderLayout.CENTER);
        return panel;
    }

    public void charge(JTextField user, JTextField amount)
    {
        frame.getMainPanel().getSubmit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String userNote = user.getText();
                String amountNote = amount.getText();
                String path = STUDENTS_PATH+userNote+"/budget.txt";
                int budget = 0;
                File budgetFile = new File(path);
                Scanner scanner = null;
                try {
                    scanner = new Scanner(budgetFile);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                int cnt = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(cnt == 1)
                        budget = Integer.parseInt(line);
                    cnt++;
                }
                budget += Integer.parseInt(amountNote);
                String note = "budget\n" + budget;
                System.out.println(path);
//                System.out.println(note);
                if (!userNote.isEmpty() && !amountNote.isEmpty()){
                    boolean isSuccessful = new File(path).mkdirs();
                    System.out.println("Creating " + path + " directory is successful: " + isSuccessful);
                    FileUtils.fileWriter(note, STUDENTS_PATH+userNote+"/");
                    System.out.println(note);
                }
            }
        });
    }
//    public void chooseClass()
//    {
//        JPanel classPanel = new JPanel(new GridLayout(6, 1, 5, 5));
//        JCheckBox class1 = new JCheckBox(" class1: ");
//        JCheckBox class2 = new JCheckBox(" class2: ");
//
//    }
}
