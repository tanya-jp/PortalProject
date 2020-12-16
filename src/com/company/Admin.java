package com.company;

import gui.CFrame;
import utils.FileUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin extends Person{
    private CFrame frame;
    private JMenuItem meals;
    private JMenuItem students;
    private JMenuItem teachers;
    private JMenuItem classes;
    private JMenuItem addStudent;
    private JMenuItem addTeacher;
    private String username;
    private File files[];
    JTabbedPane tabbedPane;
    private static final String INFO_PATH = "./user pass/";
    private static final String MEALS_PATH = "./meals/";
    private static final String STUDENTS_PATH = "./students/";
    private static final String TEACHERS_PATH = "./teachers/";
    private static final String CLASSES_PATH = ".\\classes\\";
    private JList<File> directoryList;
    private JList<File> dicList;

    public Admin(String user)
    {
        this.username = user;
        frame = new CFrame(user);
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
        addTeacher = frame.addToMenu("Add Teacher");
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
                try {
                    frame.getMainPanel().addPanel("Students",showPeople("student"));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        teachers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.getMainPanel().addPanel("Teachers",showPeople("teacher"));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        addTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Add Teacher",addPeople("teacher"));
            }
        });
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Add Student",addPeople("student"));
            }
        });
        classes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Classes",viewClasses());
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

    public JPanel addPeople(String name)
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
        savePerson(userF, passF, name, panel);
        return panel;
    }

    public JPanel showPeople(String name) throws FileNotFoundException {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JTabbedPane tb = new JTabbedPane();
        panel.add((frame.getMainPanel().setLabel(name, Color.blue)), BorderLayout.NORTH);
        StringBuilder info = new StringBuilder();
//        JList<File> dicList;
        File thisPeople[];
        String path;
        if(name.contains("teacher"))
        {
            thisPeople = FileUtils.getFilesInDirectory(TEACHERS_PATH);
            path = TEACHERS_PATH;
        }
        else
        {
            thisPeople = FileUtils.getFilesInDirectory(STUDENTS_PATH);
            path = STUDENTS_PATH;
        }
        dicList = new JList<>(thisPeople);
        dicList = new JList<>();
        dicList.setBackground(new Color(211, 211, 211));
        dicList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        dicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dicList.setVisibleRowCount(-1);
        dicList.setMinimumSize(new Dimension(130, 100));
        dicList.setMaximumSize(new Dimension(130, 100));
        dicList.setFixedCellWidth(130);
        dicList.setCellRenderer(new MyCellRenderer());
        tabbedPane = new JTabbedPane();
        dicList.addMouseListener(new PeopleMouseAdapter(path, name));

        dicList.setListData(thisPeople);
        JScrollPane dicPanel = new JScrollPane(dicList);

        panel.add(dicPanel, BorderLayout.WEST);
        panel.add(tabbedPane, BorderLayout.CENTER);
//        if(name.contains("teacher"))
//        {
//            Teacher teacher = new Teacher()
//        }

        return  panel;
    }
    private class PeopleMouseAdapter extends MouseAdapter {
        String p;
        String name;
//        JTabbedPane tb;
        PeopleMouseAdapter(String path, String name)
        {
            this.p = path;
            this.name = name;
//            this.tb = tb;
        }
        @Override
        public void mouseClicked(MouseEvent eve) {
            // Double-click detected
            if (eve.getClickCount() == 2) {
                int index = dicList.locationToIndex(eve.getPoint());
//                System.out.println("Item " + index + " is clicked...");
                File curr[] = FileUtils.getFilesInDirectory(p);
                File allFiles[] = FileUtils.getFilesInDirectory(curr[index].toString());
////                viewClasses(files);
                int cnt = 0;
                String content = "";
                while (cnt < allFiles.length)
                {
                    if(!(allFiles[cnt]).toString().contains("meal") && !(allFiles[cnt]).toString().contains("classes"))
                        content += FileUtils.fileReader(allFiles[cnt]) + "\n";

                    else if((allFiles[cnt]).toString().contains("classes"))
                    {
                        File classesName[] = FileUtils.getFilesInDirectory(allFiles[cnt].toString());
                        int i = 0;
                        content += "classes:\n";
                        while (i < classesName.length)
                        {
                            content += FileUtils.fileReader(classesName[i]) + "\n";
                            i++;
                        }
                    }
                    else if((allFiles[cnt]).toString().contains("meal"))
                    {
                        File mealsName[] = FileUtils.getFilesInDirectory(allFiles[cnt].toString());
                        int i = 0;
                        content += "meals:\n";
                        while (i < mealsName.length)
                        {
                            content += FileUtils.fileReader(mealsName[i]) + "\n";
                            i++;
                        }
                    }
                    cnt ++;
                }
                System.out.println(content);
                openExistingNote(content, name);
            }
        }
    }

    public JPanel classesList()
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

    private JScrollPane initDirectoryList() {
        File[] files = FileUtils.getFilesInDirectory(CLASSES_PATH);
        System.out.println(files.length);
        directoryList = new JList<>(files);
        directoryList = new JList<>();

        directoryList.setBackground(new Color(211, 211, 211));
        directoryList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        directoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        directoryList.setVisibleRowCount(-1);
        directoryList.setMinimumSize(new Dimension(130, 100));
        directoryList.setMaximumSize(new Dimension(130, 100));
        directoryList.setFixedCellWidth(130);
        directoryList.setCellRenderer(new MyCellRenderer());
        tabbedPane = new JTabbedPane();
        directoryList.addMouseListener(new MyMouseAdapter());

        directoryList.setListData(files);

        JScrollPane panel = new JScrollPane(directoryList);
        return panel;
    }
    private class MyCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object object, int index, boolean isSelected, boolean cellHasFocus) {
            if (object instanceof File) {
                File file = (File) object;
                setText(file.getName());
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
            }
            return this;
        }
    }
    private class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent eve) {
            // Double-click detected
            if (eve.getClickCount() == 2) {
                int index = directoryList.locationToIndex(eve.getPoint());
//                System.out.println("Item " + index + " is clicked...");
                File curr[] = FileUtils.getFilesInDirectory(CLASSES_PATH);
                files = FileUtils.getFilesInDirectory(curr[index].toString());
//                viewClasses(files);
                int cnt = 0;
                String content = "class\n";
                while (cnt < files.length)
                {
                    if(!FileUtils.fileReader(files[cnt]).toString().contains("name"))
                        content += FileUtils.fileReader(files[cnt]) + "\n";
                    cnt ++;
                }
                openExistingNote(content ,"class");
            }
        }
    }

    public void show(String path)
    {
//        directoryList.setCellRenderer(MyCellRenderer());
//        directoryList.addMouseListener(new MyMouseAdapter());

    }

    public void openExistingNote(String content, String title) {
        JPanel panel = new JPanel(new BorderLayout(5,5));
//        JButton select = new JButton("Select");
        JTextArea existPanel = createTextPanel();
        existPanel.setText(content);
        panel.add(existPanel, BorderLayout.CENTER);
//        panel.add(select, BorderLayout.SOUTH);
        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab(title+" " + (tabbedPane.getTabCount() + 1), panel);
        tabbedPane.setSelectedIndex(tabIndex - 1);
    }
    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }
    public JPanel viewClasses()
    {
        JPanel panel = new JPanel(new BorderLayout(1,2));
//        JButton select = new JButton("Select");
        panel.add(frame.getMainPanel().setLabel("Classes list", Color.white), BorderLayout.NORTH);
        panel.add(initDirectoryList(), BorderLayout.WEST);
        panel.add(tabbedPane, BorderLayout.CENTER);

//        panel.add(select, BorderLayout.SOUTH);
        return panel;
    }

    public void savePerson(JTextField user, JTextField pass, String name, JPanel panel) {
        frame.getMainPanel().getSubmit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String note_user = user.getText();
                String note_pass = pass.getText();
                String note = note_user + "\n" + name + "\n" + note_pass;
                System.out.println(note_user);
                System.out.println(note_pass);
                System.out.println(note);
                if (!note_pass.isEmpty() && !note_user.isEmpty()) {
                    if(frame.getMainPanel().checkUsername(note_user, panel) &&
                    frame.getMainPanel().checkPass(note_pass, panel))
                    {
                        boolean isSuccessful = new File(INFO_PATH).mkdirs();
                        System.out.println("Creating " + INFO_PATH + " directory is successful: " + isSuccessful);
                        FileUtils.fileWriter(note, INFO_PATH);
                        if(name.equals("student"))
                        {
                            String average = "average\n0";
                            String passedUnit = "passed unit\n0";
                            String budget = "budget\n0";
                            String unit = "unit\n0";
                            String password = "pass\n" + note_pass;
                            String path = STUDENTS_PATH+note_user+"/";
                            FileUtils.makeFolder(path);
                            FileUtils.fileWriter(average,path);
                            FileUtils.fileWriter(budget,path);
                            FileUtils.fileWriter(unit,path);
                            FileUtils.fileWriter(password,path);
                            FileUtils.fileWriter(passedUnit, path);
                            FileUtils.makeFolder(path+"classes");
                        }
                        else if(name.equals("teacher"))
                        {
                            String path = TEACHERS_PATH+note_user+"/";
                            String password = "pass\n" + note_pass;
                            FileUtils.makeFolder(path);
                            FileUtils.fileWriter(password,path);
                        }
                    }

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
