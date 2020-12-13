package com.company;

import gui.CFrame;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Teacher {
    private CFrame frame;
    private JMenuItem newClass;
    private JMenuItem students;
    private JList<File> directoryList;
    private String username;

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
//        File[] files = FileUtils.getFilesInDirectory();
//        File[] files = FileUtils.getSerializedFilesInDirectory();
//        directoryList = new JList<>(files);
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

        return (new JScrollPane(directoryList));
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
                //TODO: Phase1: Click on file is handled... Just load content into JTextArea
//                File curr[] = FileUtils.getFilesInDirectory();
//                String content = FileUtils.fileReader(curr[index]);
//                String content = FileUtils.streamFileReader(curr[index]);
//                File curr[] = FileUtils.getSerializedFilesInDirectory();
//                String content = FileUtils.readObject(curr[index]);
//                openExistingNote(content);
            }
        }
    }
    public void openExistingNote(String content, JTabbedPane tabbedPane) {
        JTextArea existPanel = createTextPanel();
        existPanel.setText(content);

        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab("Tab " + tabIndex, existPanel);
        tabbedPane.setSelectedIndex(tabIndex - 1);
    }

    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }

    public JPanel profilePanel()
    {
        JTabbedPane tabbedPane = new JTabbedPane();
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
        panel.add(initDirectoryList(), BorderLayout.WEST);
        tabbedPane.add(info,"Username and Password");
        panel.add(tabbedPane, BorderLayout.CENTER);
//        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        return panel;
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

        JCheckBox saturday = new JCheckBox(" Saturday ");
        JCheckBox sunday = new JCheckBox(" Sunday ");
        JCheckBox monday = new JCheckBox(" Monday ");
        JCheckBox tuesday = new JCheckBox(" Tuesday ");
        JCheckBox wednesday = new JCheckBox(" Wednesday ");
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

        return panel;

    }
}

