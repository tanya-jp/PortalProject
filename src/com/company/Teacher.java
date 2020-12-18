package com.company;
/**
 * This class extends Person class and sets teacher's frame. Teacher can creates new class, remove a class, set grades.
 * @author Tanya Djavaherpour
 * @version 1.0 2020
 */

import gui.CFrame;
import utils.FileUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Teacher extends Person{
    private CFrame frame;
    private JMenuItem newClass;
    private JMenuItem removeClass;
    private JMenuItem setGrade;
    private JList<File> directoryList;
    private String username;
    private static final String CLASSES_PATH = ".\\classes\\";
    private static final String TEACHER_PATH = ".\\teachers\\";
    private static final String INFO_PATH = ".\\user pass\\";
    private static final String STUDENTS_PATH = "./students/";
    JTabbedPane tabbedPane ;
    JButton select = new JButton("Select");
    File[] files;
    String content;
    /**
     * Constructs new teacher
     * @param user as username of teacher
     */
    public Teacher(String user)
    {
        super(user);
        this.frame = super.getFrame();
        this.username = user;
        frame.setFrame("teacher", profilePanel());
        addFrameMenu();
        addToTab();
    }
    /**
     * Adds menu's items. Only teacher has these items.
     */
    @Override
    public void addFrameMenu()
    {

        newClass = frame.addToMenu("New class");
        setGrade = frame.addToMenu("Set Grades");
        removeClass = frame.addToMenu("Remove class");
    }
    /**
     * After clicking on menu items this method adds tabs to the frame.
     */
    @Override
    public void addToTab()
    {
        newClass.addActionListener(new ActionListener() {
            /**
             * Checks if user has clicked on newClass
             * @param e as input of action
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("New class",createClass());
            }
        });
        removeClass.addActionListener(new ActionListener() {
            /**
             * Checks if user has clicked on removeClass
             * @param e as input of action
             */
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getMainPanel().addPanel("Remove Class",removeClass());
        }
    });
        setGrade.addActionListener(new ActionListener() {
            /**
             * Checks if user has clicked on setGrade
             * @param e as input of action
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Grade",setGrades());
            }
        });
    }
    /**
     * Specifies the item that has been chosen.
     */
    public class MyCellRenderer extends DefaultListCellRenderer {

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
     *  This inner class extends MouseAdapter and is used when admin wants to remove or add classes.
     */
    private class MyMouseAdapter extends MouseAdapter {
        String type;
        /**
         * Constructs new MyMouseAdapter
         * @param t as type of the work, removing or adding
         */
        private MyMouseAdapter(String t)
        {
            this.type = t;
        }

        /**
         * After clicking on class's name shows information.
         * @param eve as input of mouse
         */
        @Override
        public void mouseClicked(MouseEvent eve) {
            // Double-click detected
            if (eve.getClickCount() == 2) {
                int index = directoryList.locationToIndex(eve.getPoint());
                System.out.println("Item " + index + " is clicked...");
                File curr[] = FileUtils.getFilesInDirectory(TEACHER_PATH + username + "\\");
                content = FileUtils.fileReader(curr[index]);
                if(type.equals("add"))
                    tabbedPane = FileUtils.openExistingNote(content, FileUtils.getProperFileName(content), tabbedPane);
                else
                    tabbedPane = FileUtils.openNoteWithButton(content, FileUtils.getProperFileName(content),
                            tabbedPane, select);
            }
        }
    }

    /**
     * Creates proper panel to show this teacher's student and set their gardes
     * @return created panel
     */
    public JPanel setGrades()
    {
        String path = TEACHER_PATH + username;
        File className[] = FileUtils.getFilesInDirectory(path);
        System.out.println(className[1]);
        JPanel mainPanel = new JPanel(new BorderLayout(5,5));
        JPanel titlePanel = new JPanel(new GridLayout(1,4));
        JTextField label1 = new JTextField("name");
        label1.setBackground(Color.YELLOW);
        label1.setEditable(false);
        JTextField label2 = new JTextField("course title");
        label2.setBackground(Color.orange);
        label2.setEditable(false);
        JTextField label3 = new JTextField("grade");
        label3.setBackground(Color.red);
        label3.setEditable(false);
        JTextField label4 = new JTextField("OK");
        label4.setEditable(false);
        label4.setBackground(Color.GREEN);
        titlePanel.add(label1);
        titlePanel.add(label2);
        titlePanel.add(label3);
        titlePanel.add(label4);

        Icon okIcon = new ImageIcon("./OK.PNG");
        int num = 0;
        int cnt = 0;
        while (cnt < className.length)
        {
            String title = "";
            if(!className[cnt].toString().contains("pass.txt"))
            {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(className[cnt]);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                int counter = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(counter != 0)
                    {
                        num++;
                    }
                    counter++;
                }
                scanner.close();
            }
            cnt++;
        }
        JPanel panel = new JPanel(new GridLayout(num, 4));
        cnt = 0;
        while (cnt < className.length)
        {
            String title = "";
            if(!className[cnt].toString().contains("pass.txt"))
            {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(className[cnt]);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                int counter = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(counter == 0)
                    {
                        title = line;
                    }
                    if(counter != 0)
                    {
                        JTextField names = new JTextField(line);
                        names.setBackground(Color.lightGray);
                        JTextField titleF = new JTextField(title);
                        titleF.setEditable(false);
                        names.setEditable(false);
                        JTextField grade = new JTextField();
                        JButton ok = new JButton(okIcon);

                        panel.add(names);
                        panel.add(titleF);
                        panel.add(grade);
                        panel.add(ok);
                        saveGrade(names, titleF, grade, ok);
                    }
                    counter++;
                }
                scanner.close();
            }
            cnt++;
        }
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(panel, BorderLayout.CENTER);
        return mainPanel;
    }

    /**
     * Checks if teacher wants to save the grade, saves it.
     * @param name as student's name
     * @param course as course title
     * @param grade as grade
     * @param ok as OK button
     */
    public void saveGrade(JTextField name,JTextField course, JTextField grade, JButton ok)
    {
        ok.addMouseListener(new MouseAdapter() {
            /**
             * After clicking if the field was not empty and the given number was proper, saves the grade
             * @param e as input of mouse
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                String gradeNote = grade.getText();
                String nameNote = name.getText();
                String courseNote = course.getText();
                if(gradeNote.isEmpty())
                    JOptionPane.showMessageDialog(frame, "Empty!", "Result", JOptionPane.ERROR_MESSAGE);
                else if(checkNumber(gradeNote))
                {
                    if(Float.parseFloat(gradeNote) > 21 || Float.parseFloat(gradeNote) > 21)
                    JOptionPane.showMessageDialog(frame, "Invalid grade!", "Result", JOptionPane.ERROR_MESSAGE);

                else
                {
                    Float gradeNum = Float.parseFloat(gradeNote);
                    int courseUnit = 0;
                    File unitFile = new File(CLASSES_PATH + courseNote + "\\unit.txt");
                    courseUnit = Integer.parseInt(FileUtils.scanner(unitFile, 1));
                    int passedUnit = 0;
                    float average = 0;
                    float res = 0;
                    for(int i = 0; i<2 ; i++)
                    {
                        String str;
                        if(i == 0)
                            str = "average";
                        else
                            str = "passed unit";
                        String path = STUDENTS_PATH+nameNote+"/"+str+".txt";
                        File averageFile = new File(path);
                        if(i == 0)
                            average = Float.parseFloat(FileUtils.scanner(averageFile,1));
                        else
                            passedUnit = Integer.parseInt(FileUtils.scanner(averageFile,1));
                    }
                    System.out.println(average);
                    System.out.println(passedUnit);
                    String path = STUDENTS_PATH + nameNote + "/";
                    average = (average*passedUnit + gradeNum*courseUnit)/(passedUnit+courseUnit);
                    passedUnit += courseUnit;
                    String avg = "average\n" + average;
                    FileUtils.fileWriter(avg, path);

                    String pu = "passed unit\n" + passedUnit;
                    FileUtils.fileWriter(pu, path);
                }
                }

            }
        });
    }
    /**
     * Constructs profile panel
     * @return proper profile panel to teacher
     */
    @Override
    public JPanel profilePanel()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel info = new JPanel(new BorderLayout(5, 5));
        JPanel titlePanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JLabel name = new JLabel(" Username: ");
        JLabel pass = new JLabel(" Password: ");
        Border border = BorderFactory.createLineBorder(Color.gray, 2);
        JTextField nameF = new JTextField(username);
        nameF.setBackground(Color.DARK_GRAY);
        nameF.setBorder(border);
        JTextField passF = new JTextField(getPass());
        passF.setBackground(Color.DARK_GRAY);
        passF.setBorder(border);
        nameF.setEnabled(false);
        passF.setEnabled(false);
        infoPanel.add(nameF);
        infoPanel.add(passF);
        Icon icon = new ImageIcon(".\\teacher.png");
        JLabel pic = new JLabel(icon);
        info.add(titlePanel, BorderLayout.NORTH);
        info.add(infoPanel, BorderLayout.CENTER);
        panel.add(frame.getMainPanel().setLabel("Your profile",Color.getHSBColor(160, 50, 100)), BorderLayout.NORTH);

        panel.add(getClassList("add"), BorderLayout.WEST);
        tabbedPane = new JTabbedPane();
        tabbedPane.add(info,"Username and Password");
        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(pic, BorderLayout.EAST);
        return panel;
    }

    /**
     * Shows all saved classes of this teacher
     * @param type remove or add
     * @return scroll pane of classes list
     */
    public JScrollPane getClassList(String type)
    {
        files = FileUtils.getFilesInDirectory(TEACHER_PATH + username + "\\");
        if(type.equals("remove"))
        {
            int cnt = 0;
            while (cnt<files.length)
            {
                if(files[cnt].toString().contains("pass"))
                    files[cnt].delete();
                cnt++;
            }
            files = FileUtils.getFilesInDirectory(TEACHER_PATH + username + "\\");
        }
        directoryList = new JList<>();
        directoryList = CFrame.setDictionary(directoryList, files);
        directoryList.setCellRenderer(new MyCellRenderer());
        directoryList.addMouseListener(new MyMouseAdapter(type));
        directoryList.setListData(files);
        return (new JScrollPane(directoryList));
    }

    /**
     * Creates proper panel to remove a class
     * @return removing panel
     */
    public JPanel removeClass()
    {
        tabbedPane = new JTabbedPane();
        JPanel panel = new JPanel(new BorderLayout(1,2));
        panel.add(frame.getMainPanel().setLabel("Remove class", Color.getHSBColor(178, 222, 251)), BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(getClassList("remove"));
        panel.add(scrollPane, BorderLayout.WEST);
        panel.add(tabbedPane, BorderLayout.CENTER);
        Icon icon = new ImageIcon(".\\trash.png");
        JLabel pic = new JLabel(icon);
        panel.add(pic, BorderLayout.EAST);
        setRemoveClass();
        return panel;
    }

    /**
     * Removes a class after clicking
     */
    public void setRemoveClass()
    {
        select.addMouseListener(new MouseAdapter() {
            /**
             * After clicking remove the chosen class from all files and folders which include it
             * @param e as input of mouse
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                int cnt = 0;
                String className = "" ;
                int flag = 0;
                while (cnt < files.length && flag == 0) {
                    className = FileUtils.getProperFileName(content);
                    System.out.println(className);
                    if(files[cnt].toString().contains(className))
                    {
                        files[cnt].delete();
                        flag++;
                    }
                    cnt++;
                }
                File index = new File(CLASSES_PATH+className);
                String[] entries = index.list();
                assert entries != null;
                for(String s: entries){
                    File currentFile = new File(index.getPath(),s);
                    currentFile.delete();
                }
                File index2 = new File(CLASSES_PATH);
                String[] filesEntries = index2.list();
                assert filesEntries != null;
                for(String s: filesEntries){
                    if(s.contains(className))
                    {
                        System.out.println(s);
                        File currentFile = new File(index2.getPath(),s);
                        currentFile.delete();
                    }
                }
                File[] students = FileUtils.getFilesInDirectory(STUDENTS_PATH);
                cnt = 0;
                while (cnt<students.length)
                {
                    String classPath = students[cnt].toString()+"/classes/";
                    File i = new File(classPath);
                    System.out.println(i);
                    String[] classesEntries = i.list();
                    assert classesEntries != null;
                    for(String s: classesEntries){
                        System.out.println(s);
                        if(s.contains(className))
                        {
                            System.out.println(s);
                            File currentFile = new File(i.getPath(),s);
                            currentFile.delete();
                        }
                    }
                    cnt++;
                }
            }
        });
    }

    /**
     * Gets this teacher's password
     * @return password
     */
    public String getPass()
    {
        String  pass = null;
        File file = new File(INFO_PATH+username+".txt");
        pass = FileUtils.scanner(file, 2);
        System.out.println(pass);
        return  pass;
    }

    /**
     * Creates proper panel to save a new class
     * @return created panel
     */
    public JPanel createClass()
    {
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new GridLayout(3,1));
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = frame.getMainPanel().setLabel("Set new class", Color.MAGENTA);

        JPanel info = new JPanel(new BorderLayout(5,5));

        JLabel name = new JLabel("Course title: ");
        JLabel unit = new JLabel("Unit: ");
        JLabel capacity = new JLabel("Capacity: ");
        JPanel titles = new JPanel(new GridLayout(3,1));
        titles.add(name);
        titles.add(unit);
        titles.add(capacity);
        titles.setBackground(Color.LIGHT_GRAY);

        JTextField nameF = new JTextField();
        JTextField unitF = new JTextField();
        JTextField capacityF = new JTextField();
        JPanel boxes = new JPanel(new GridLayout(3,1));
        boxes.add(nameF);
        boxes.add(unitF);
        boxes.add(capacityF);

        info.add(titles,BorderLayout.WEST);
        info.add(boxes, BorderLayout.CENTER);

        JCheckBox saturday = new JCheckBox("Saturday ");
        JCheckBox sunday = new JCheckBox("Sunday ");
        JCheckBox monday = new JCheckBox("Monday ");
        JCheckBox tuesday = new JCheckBox("Tuesday ");
        JCheckBox wednesday = new JCheckBox("Wednesday ");
        JPanel days = new JPanel(new GridLayout(1,5));
        days.add(saturday);
        days.add(sunday);
        days.add(monday);
        days.add(tuesday);
        days.add(wednesday);

        JCheckBox time1 = new JCheckBox("8-10 ");
        JCheckBox time2 = new JCheckBox("10-12 ");
        JCheckBox time3 = new JCheckBox("14-16 ");
        JPanel times = new JPanel(new GridLayout(1,3));
        times.add(time1);
        times.add(time2);
        times.add(time3);

        panel1.add(label, BorderLayout.NORTH);
        panel1.add(info, BorderLayout.CENTER);
        panel2.add(days);
        panel2.add(times);
        panel2.add(frame.getMainPanel().setButtons());

        panel.add(panel1, BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.SOUTH);

        setClass(nameF, unitF, capacityF,
                saturday, sunday, monday, tuesday, wednesday,
                time1, time2, time3);
        return panel;

    }

    /**
     * Saves class after clicking
     * @param name as course title
     * @param unit as course unit
     * @param capacity as capacity of this class
     * @param d1 as saturday
     * @param d2 as sunday
     * @param d3 as monday
     * @param d4 as tuesday
     * @param d5 as wednesday
     * @param t1 as 8-10
     * @param t2 as 10-12
     * @param t3 as 14-16
     */
    public void setClass(JTextField name, JTextField unit, JTextField capacity,
                         JCheckBox d1, JCheckBox d2, JCheckBox d3, JCheckBox d4, JCheckBox d5,
                         JCheckBox t1, JCheckBox t2, JCheckBox t3)
    {
        frame.getMainPanel().getSubmit().addMouseListener(new MouseAdapter() {
            /**
             * After clicking submit button saves all information of created class
             * @param e as input of mouse
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                String nameNote = name.getText();
                String unitNote = unit.getText();
                String capacityNote = capacity.getText();
                String daysNote = "days\n";
                String timesNote = "times\n";
                String thisClassPath = CLASSES_PATH + nameNote + "\\";
                int flag = 0;
                if (d1.isSelected()) {
                    daysNote = daysNote + d1.getText() + "\n";
                    flag++;
                }
                if (d2.isSelected()) {
                    daysNote = daysNote + d2.getText() + "\n";
                    flag++;
                }
                if (d3.isSelected()) {
                    daysNote = daysNote + d3.getText() + "\n";
                    flag++;
                }
                if (d4.isSelected()) {
                    daysNote = daysNote + d4.getText() + "\n";
                    flag++;
                }
                if (d5.isSelected()) {
                    daysNote = daysNote + d5.getText() + "\n";
                    flag++;
                }
                if (flag == 0)
                    JOptionPane.showMessageDialog(frame, "Choose a day!", "Result", JOptionPane.ERROR_MESSAGE);
                else {
                    flag = 0;
                    if (t1.isSelected()) {
                        timesNote += t1.getText();
                        flag++;
                    }
                    if (t2.isSelected()) {
                        timesNote += t2.getText();
                        flag++;
                    }
                    if (t3.isSelected()) {
                        timesNote += t3.getText();
                        flag++;
                    }

                    if (flag == 0)
                        JOptionPane.showMessageDialog(frame, "Choose a time!", "Result", JOptionPane.ERROR_MESSAGE);
                    else {
                        flag = 0;
                        if (nameNote.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "No course title!", "Result", JOptionPane.ERROR_MESSAGE);
                            flag++;
                        }
                        if (unitNote.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "No unit!", "Result", JOptionPane.ERROR_MESSAGE);
                            flag++;
                        }
                        else if(!checkNumber(unitNote))
                        {
                            JOptionPane.showMessageDialog(frame, "Invalid unit!", "Result", JOptionPane.ERROR_MESSAGE);
                            flag++;
                        }
                        if (capacityNote.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "No capacity!", "Result", JOptionPane.ERROR_MESSAGE);
                            flag++;
                        }
                        else if(!checkNumber(capacityNote))
                        {
                            JOptionPane.showMessageDialog(frame, "Invalid capacity!", "Result", JOptionPane.ERROR_MESSAGE);
                            flag++;
                        }

                        if (flag == 0) {
                            boolean isSuccessful1 = new File(CLASSES_PATH).mkdirs();
                            boolean isSuccessful2 = new File(thisClassPath).mkdirs();
                            String teacherFile = "teacher\n" + username;
                            FileUtils.fileWriter(teacherFile, thisClassPath);
                            String unitFile = "unit\n" + unitNote;
                            FileUtils.fileWriter(unitFile, thisClassPath);
                            String capacityFile = "capacity\n" + capacityNote;
                            FileUtils.fileWriter(capacityFile, thisClassPath);
                            String nameFile = "name\n" + nameNote;
                            FileUtils.fileWriter(nameFile, thisClassPath);
                            FileUtils.fileWriter(daysNote, thisClassPath);
                            FileUtils.fileWriter(timesNote, thisClassPath);
                            String path = TEACHER_PATH + username + "\\";
                            FileUtils.fileWriter(nameNote, path);
                            System.out.println(nameNote);
                        }
                    }
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

    /**
     * Makes frame of this person invisible
     */
    public void notShowGUI() {
        frame.setVisible(false);
    }

}

