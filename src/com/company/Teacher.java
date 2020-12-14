package com.company;

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
import java.util.Scanner;

public class Teacher {
    private CFrame frame;
    private JMenuItem newClass;
    private JMenuItem students;
    private JList<File> directoryList;
    private String username;
    private static final String CLASSES_PATH = ".\\classes\\";
    private static final String TEACHER_PATH = ".\\teachers\\";
    private static final String INFO_PATH = ".\\user pass\\";
    JTabbedPane tabbedPane = new JTabbedPane();

    public Teacher(String user)
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

        newClass = frame.addToMenu("New class");
        students = frame.addToMenu("Students");
    }

    private void addToTab()
    {
        newClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("New class",createClass());
            }
        });
        students.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addSpecificTab("Students","STUDENTS:");
            }
        });

    }

//    private void initTabbedPane() {
//        tabbedPane = new JTabbedPane();
//        add(tabbedPane, BorderLayout.CENTER);
//    }

    private JScrollPane initDirectoryList() {
        File[] files = FileUtils.getFilesInDirectory(TEACHER_PATH + username + "\\");
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
                System.out.println("Item " + index + " is clicked...");
                File curr[] = FileUtils.getFilesInDirectory(TEACHER_PATH + username + "\\");
                String content = FileUtils.fileReader(curr[index]);
//                String content = FileUtils.streamFileReader(curr[index]);
//                File curr[] = FileUtils.getSerializedFilesInDirectory();
//                String content = FileUtils.readObject(curr[index]);
                openExistingNote(content, tabbedPane);
            }
        }
    }
    public void openExistingNote(String content, JTabbedPane tabbedPane) {
        JTextArea existPanel = createTextPanel();
        existPanel.setText(content);

//        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab(FileUtils.getProperFileName(content) , existPanel);
//        tabbedPane.setSelectedIndex(tabIndex - 1);
    }

    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }

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
        info.add(titlePanel, BorderLayout.NORTH);
        info.add(infoPanel, BorderLayout.CENTER);
        panel.add(frame.getMainPanel().setLabel("Your profile",Color.getHSBColor(160, 50, 100)), BorderLayout.NORTH);
        panel.add(initDirectoryList(), BorderLayout.WEST);
        tabbedPane.add(info,"Username and Password");
        panel.add(tabbedPane, BorderLayout.CENTER);
//        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        return panel;
    }

    public String getPass()
    {
        String  pass = null;
        File file = new File(INFO_PATH+username+".txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        int cnt = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(cnt == 2)
                pass = line;
            cnt++;
        }
        return  pass;
    }

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

    public void setClass(JTextField name, JTextField unit, JTextField capacity,
                         JCheckBox d1, JCheckBox d2, JCheckBox d3, JCheckBox d4, JCheckBox d5,
                         JCheckBox t1, JCheckBox t2, JCheckBox t3)
    {
        frame.getMainPanel().getSubmit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String nameNote = name.getText();
                String unitNote = unit.getText();
                String capacityNote = capacity.getText();
                String daysNote = "days\n";
                String timesNote = "times\n";
                String thisClassPath = CLASSES_PATH+nameNote+"\\";
                if(d1.isSelected())
                    daysNote = daysNote + d1.getText() + "\n";
                if(d2.isSelected())
                    daysNote = daysNote + d2.getText() + "\n";
                if(d3.isSelected())
                    daysNote = daysNote + d3.getText() + "\n";
                if(d4.isSelected())
                    daysNote = daysNote + d4.getText() + "\n";
                if(d5.isSelected())
                    daysNote = daysNote + d5.getText() + "\n";

                if(t1.isSelected())
                    timesNote += t1.getText();
                if(t2.isSelected())
                    timesNote += t2.getText();
                if(t3.isSelected())
                    timesNote += t3.getText();

                if (!nameNote.isEmpty() && !unitNote.isEmpty() && !capacityNote.isEmpty()){
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
                }
                if(!nameNote.isEmpty())
                {
                    String path = TEACHER_PATH + username + "\\";
                    FileUtils.fileWriter(nameNote, path);
                }
            }
        });
    }

}

