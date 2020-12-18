package com.company;
/**
 * This class extends Person class and sets Admin's frame. Admin can save meals, adds student and teacher.
 * He/She can also know about classes, students and teachers.
 * @author Tanya Djavaherpour
 * @version 1.0 2020
 */

import gui.CFrame;
import utils.FileUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;

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

    /**
     * Constructs new admin
     * @param user as username of admin
     */
    public Admin(String user)
    {
        super(user);
        this.frame = super.getFrame();
        this.username = user;
        frame.setFrame("admin", profilePanel());
        addFrameMenu();
        addToTab();
    }

    /**
     * Adds menu's items. Only admin has these items.
     */
    @Override
    public void addFrameMenu()
    {

        meals = frame.addToMenu("Set Meals");
        students = frame.addToMenu("Students");
        teachers = frame.addToMenu("Teachers");
        classes = frame.addToMenu("Classes");
        addStudent = frame.addToMenu("Add Student");
        addTeacher = frame.addToMenu("Add Teacher");
    }

    /**
     * After clicking on menu items this method adds tabs to the frame.
     */
    @Override
    public void addToTab()
    {
        meals.addActionListener(new ActionListener() {
            /**
             * Checks if user has clicked on meals
             * @param e as input of action
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Meals",setMeals());
            }
        });
        students.addActionListener(new ActionListener() {
            /**
             * Checks if user has clicked on students
             * @param e as input of action
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                    frame.getMainPanel().addPanel("Students",showPeople("student"));

            }
        });
        teachers.addActionListener(new ActionListener() {
            /**
             * Checks if user has clicked on teachers
             * @param e as input of action
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                    frame.getMainPanel().addPanel("Teachers",showPeople("teacher"));
            }
        });
        addTeacher.addActionListener(new ActionListener() {
            /**
             * Checks if user has clicked on addTeacher
             * @param e as input of action
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Add Teacher",addPeople("teacher"));
            }
        });
        addStudent.addActionListener(new ActionListener() {
            /**
             * Checks if user has clicked on addStudent
             * @param e as input of action
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Add Student",addPeople("student"));
            }
        });
        classes.addActionListener(new ActionListener() {
            /**
             * Checks if user has clicked on classes
             * @param e as input of action
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Classes",viewClasses());
            }
        });
    }

    /**
     * This method creates proper JPanel to set meals.
     * @return Panel of saving meals
     */
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
        Icon icon = new ImageIcon(".\\food2.png");
        JLabel pic = new JLabel(icon);
        panel.add(pic, BorderLayout.EAST);
        return panel;
    }

    /**
     * This method creates proper JPanel to add people such as teachers and students.
     * @param name as position of person, teacher or student.
     * @return Panel of adding people.
     */
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

        Icon icon;
        if(name.equals("student"))
            icon = new ImageIcon(".\\student3.png");
        else
            icon = new ImageIcon(".\\teacher3.png");
        JLabel pic = new JLabel(icon);
        panel.add(pic, BorderLayout.EAST);
        panel.add(frame.getMainPanel().setLabel(" Add new "+name,Color.PINK), BorderLayout.NORTH);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        savePerson(userF, passF, name, panel);
        return panel;
    }

    /**
     * Lists of people are shown with this panel.
     * @param name as position of person, teacher or student.
     * @return panel of showing people
     */
    public JPanel showPeople(String name) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JTabbedPane tb = new JTabbedPane();
        panel.add((frame.getMainPanel().setLabel(name, Color.blue)), BorderLayout.NORTH);
        StringBuilder info = new StringBuilder();
        File thisPeople[];
        String path;
        Icon icon;
        if(name.contains("teacher"))
        {
            icon = new ImageIcon(".\\teacher2.png");
            thisPeople = FileUtils.getFilesInDirectory(TEACHERS_PATH);
            path = TEACHERS_PATH;
        }
        else
        {
            icon = new ImageIcon(".\\student2.png");
            thisPeople = FileUtils.getFilesInDirectory(STUDENTS_PATH);
            path = STUDENTS_PATH;
        }
        JLabel pic = new JLabel(icon);
        panel.add(pic, BorderLayout.EAST);
        dicList = CFrame.setDictionary(dicList, thisPeople);
        dicList.setCellRenderer(new MyCellRenderer());
        tabbedPane = new JTabbedPane();
        dicList.addMouseListener(new PeopleMouseAdapter(path, name));

        dicList.setListData(thisPeople);
        JScrollPane dicPanel = new JScrollPane(dicList);

        panel.add(dicPanel, BorderLayout.WEST);
        panel.add(tabbedPane, BorderLayout.CENTER);

        return  panel;
    }

    /**
     * This private inner class is used when user clicked on people's name
     * and wants program to show their information.
     * This class checks if user has clicked on items then do sequential tasks.
     */
    private class PeopleMouseAdapter extends MouseAdapter {
        String p;
        String name;
        PeopleMouseAdapter(String path, String name)
        {
            this.p = path;
            this.name = name;
        }

        /**
         * After clicking adds new tab and shows the chosen person's information.
         * @param eve as input action
         */
        @Override
        public void mouseClicked(MouseEvent eve) {
            // Double-click detected
            if (eve.getClickCount() == 2) {
                int index = dicList.locationToIndex(eve.getPoint());
                File curr[] = FileUtils.getFilesInDirectory(p);
                File allFiles[] = FileUtils.getFilesInDirectory(curr[index].toString());
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
                tabbedPane = FileUtils.openExistingNote(content, name+" "+(tabbedPane.getTabCount()+ 1), tabbedPane);
            }
        }
    }

    /**
     * Constructs profile panel
     * @return proper profile panel to admin
     */
    @Override
    public JPanel profilePanel()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel info = new JPanel(new BorderLayout(5, 5));
        JPanel proPic = new JPanel(new GridLayout(1, 5));
        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JLabel name = new JLabel(" Username: ");
        JLabel pass = new JLabel(" Password: ");
        Icon icon = new ImageIcon(".\\admin.png");
        JLabel pic = new JLabel(icon);
        titlePanel.add(name);
        titlePanel.add(pass);
        JTextField nameF = new JTextField();
        JTextField passF = new JTextField();
        nameF.setEnabled(false);
        passF.setEnabled(false);
        infoPanel.add(nameF);
        infoPanel.add(passF);
        proPic.add(pic);
        info.add(titlePanel, BorderLayout.WEST);
        info.add(infoPanel, BorderLayout.CENTER);
        panel.add(proPic, BorderLayout.WEST);
        panel.add(frame.getMainPanel().setLabel("Your profile",Color.getHSBColor(100,240,800)) , BorderLayout.NORTH);
        panel.add(info, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Specifies the item that has been chosen.
     */
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

    /**
     * This inner class extends MouseAdapter and is used when admin wants to visit a class.
     */
    private class ClassMouseAdapter extends MouseAdapter {
        /**
         * After clicking, this method shows class information.
         * @param eve as mouse input
         */
        @Override
        public void mouseClicked(MouseEvent eve) {
            // Double-click detected
            if (eve.getClickCount() == 2) {
                int index = directoryList.locationToIndex(eve.getPoint());
                File curr[] = FileUtils.getFilesInDirectory(CLASSES_PATH);
                files = FileUtils.getFilesInDirectory(curr[index].toString());
                int cnt = 0;
                String content = "class\n";
                while (cnt < files.length)
                {
                    if(!FileUtils.fileReader(files[cnt]).toString().contains("name"))
                        content += FileUtils.fileReader(files[cnt]) + "\n";
                    cnt ++;
                }
                tabbedPane = FileUtils.openExistingNote(content ,"class "+(tabbedPane.getTabCount()+ 1), tabbedPane);
            }
        }
    }

    /**
     * Creates proper panel to show list of classes.
     * @return panel of showing classes
     */
    public JPanel viewClasses()
    {
        JPanel panel = new JPanel(new BorderLayout(1,2));
        panel.add(frame.getMainPanel().setLabel("Classes list", Color.getHSBColor(800,800,1100)), BorderLayout.NORTH);

        File[] files = FileUtils.getFilesInDirectory(CLASSES_PATH);
        directoryList = CFrame.setDictionary(directoryList,files);
        directoryList.setCellRenderer(new MyCellRenderer());
        tabbedPane = new JTabbedPane();
        directoryList.addMouseListener(new ClassMouseAdapter());
        directoryList.setListData(files);
        panel.add(new JScrollPane(directoryList), BorderLayout.WEST);
        panel.add(tabbedPane, BorderLayout.CENTER);
        Icon icon = new ImageIcon(".\\class.png");
        JLabel pic = new JLabel(icon);
        panel.add(pic, BorderLayout.EAST);
        return panel;
    }

    /**
     * When admin wants to add new person, this method after clicking saves information
     * user name should not be available of user lists and password should have more than 8 characters
     * @param user as text field of username of new person
     * @param pass as text field of password of new person
     * @param name as position of person, student or teacher
     * @param panel as panel that is showing fields
     */
    public void savePerson(JTextField user, JTextField pass, String name, JPanel panel) {
        frame.getMainPanel().getSubmit().addMouseListener(new MouseAdapter() {
            /**
             * After clicking, this method checks if username and pass is correct saves them.
             * @param e as input of mouse
             */
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

    /**
     * Saves foods if their fields were not empty.
     * @param f1 as name of first food
     * @param p1 as price of first food
     * @param f2 as name of second food
     * @param p2 as price of second food
     * @param d as label of day
     */
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

    /**
     * Checks if all characters of string are number
     * @param str is input string
     * @return if they were number returns true, else returns false
     */
    @Override
    public boolean checkNumber(String str)
    {
        return super.checkNumber(str);
    }
}
