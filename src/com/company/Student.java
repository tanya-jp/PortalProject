package com.company;
import gui.CFrame;
import utils.FileUtils;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Student extends Person{
    private CFrame frame;
    private JMenuItem increaseBudget;
    private JMenuItem meals;
    private JMenuItem classes;
    private JMenuItem budgetTransfer;
    private JList<File> directoryList;
    private static final String MEALS_PATH = "./meals/";
    private static final String INFO_PATH = ".\\user pass\\";
    private static final String STUDENTS_PATH = "./students/";
    private static final String CLASSES_PATH = ".\\classes\\";
    private static final String TEACHER_PATH = ".\\teachers\\";
    private String username;
    private float money;
    JTabbedPane tabbedPane = new JTabbedPane();
    private File files[];
    JButton select = new JButton("Select");

    public Student(String user)
    {
        super(user);
        this.frame = super.getFrame();
        this.username = user;
        frame.setFrame("student", profilePanel());
        addFrameMenu();
        addToTab();
    }

    public void addFrameMenu()
    {

        meals = frame.addToMenu("Set Meals");
        classes = frame.addToMenu("Classes");
        increaseBudget = frame.addToMenu("Increase budget");
        budgetTransfer = frame.addToMenu("Budget transfer");
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
                openExistingNote(content);
            }
        }
    }

    public void openExistingNote(String content) {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        JTextArea existPanel = createTextPanel();
        existPanel.setText(content);
        panel.add(existPanel, BorderLayout.CENTER);
        panel.add(select, BorderLayout.SOUTH);
        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab("class " + (tabbedPane.getTabCount() + 1), panel);
        tabbedPane.setSelectedIndex(tabIndex - 1);
    }
    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }
    public void addToTab()
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
                frame.getMainPanel().addPanel("Increase budget",budgetOptions("increase"));
            }
        });
        budgetTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Budget transfer",budgetOptions("transfer"));
            }
        });

        classes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getMainPanel().addPanel("Choose class",viewClasses());
            }
        });

    }

    public JPanel setMeals() throws FileNotFoundException {
        if(getAverage() >= 17)
        {
            JOptionPane.showMessageDialog(frame, "YOU CAN BUY 50% CHEAPER!", "notification", JOptionPane.INFORMATION_MESSAGE);
        }
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JCheckBox saturday = new JCheckBox("Saturday ");
        JCheckBox sunday = new JCheckBox("Sunday ");
        JCheckBox monday = new JCheckBox("Monday ");
        JCheckBox tuesday = new JCheckBox("Tuesday ");
        JCheckBox wednesday = new JCheckBox("Wednesday ");
        JCheckBox thursday = new JCheckBox("Thursday ");
        String m[] = {null};
        JComboBox saturdayBox = new JComboBox(m);
        JComboBox sundayBox = new JComboBox(m);
        JComboBox mondayBox = new JComboBox(m);
        JComboBox tuesdayBox = new JComboBox(m);
        JComboBox wednesdayBox = new JComboBox(m);
        JComboBox thursdayBox = new JComboBox(m);
        int flag1 = 0;

        JPanel mealsPanel =new JPanel(new GridLayout(6, 1, 5, 5));
        int days = 0;
        File curr[] = FileUtils.getFilesInDirectory(MEALS_PATH);
        while(days < curr.length)
        {
            String food1 = FileUtils.scanner(curr[days], 1);
            food1 += "     " + FileUtils.scanner(curr[days], 2);
            String food2 = FileUtils.scanner(curr[days], 3);
            food2 += "     " + FileUtils.scanner(curr[days], 4);

            String m1[] = { food1, food2};
            if(curr[days].toString().contains("Saturday"))
            {
                saturdayBox = new JComboBox(m1);
                mealsPanel.add(saturdayBox,0);
                flag1++;
            }
            else if(curr[days].toString().contains("Sunday"))
            {
                sundayBox = new JComboBox(m1);
                mealsPanel.add(sundayBox,1);
            }
            else if(curr[days].toString().contains("Monday"))
            {
                mondayBox = new JComboBox(m1);
                mealsPanel.add(mondayBox,2,0);
            }
            else if(curr[days].toString().contains("Tuesday"))
            {
                tuesdayBox = new JComboBox(m1);
                mealsPanel.add(tuesdayBox,3);
            }
            else if(curr[days].toString().contains("Wednesday"))
            {
                wednesdayBox = new JComboBox(m1);
                mealsPanel.add(wednesdayBox,4);
            }
            else if(curr[days].toString().contains("Thursday"))
            {
                thursdayBox = new JComboBox(m1);
                mealsPanel.add(thursdayBox);
            }
            days++;

        }

        JPanel daysPanel = new JPanel(new GridLayout(6, 1, 5, 5));

        JPanel labels = new JPanel(new BorderLayout(5, 5));

        Icon icon = new ImageIcon(".\\food2.png");
        JLabel pic = new JLabel(icon);
        daysPanel.add(saturday);
        daysPanel.add(sunday);
        daysPanel.add(monday);
        daysPanel.add(tuesday);
        daysPanel.add(wednesday);
        daysPanel.add(thursday);
        labels.add(frame.getMainPanel().setLabel(" Set weekly meal plan ",Color.getHSBColor(122,13,14)),
                BorderLayout.CENTER);
        labels.add(frame.getMainPanel().setLabel("Budget: "+getBudget(username), Color.red),BorderLayout.EAST);
//        panel.add(labels, BorderLayout.NORTH);
        panel.add(daysPanel, BorderLayout.WEST);
        panel.add(mealsPanel, BorderLayout.CENTER);
        JPanel finalPanel = new JPanel(new BorderLayout(5,4));
        finalPanel.add(labels, BorderLayout.NORTH);
        finalPanel.add(panel, BorderLayout.CENTER);
        finalPanel.add(pic, BorderLayout.EAST);
        finalPanel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
//        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        saveMeals(saturday, saturdayBox);
        saveMeals(sunday, sundayBox);
        saveMeals(monday, mondayBox);
        saveMeals(tuesday, tuesdayBox);
        saveMeals(wednesday, wednesdayBox);
        saveMeals(thursday, thursdayBox);
        return finalPanel;
    }

    public JPanel budgetOptions(String type)
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel destination = new JLabel(" destination: ");
        JLabel id = new JLabel(" ID: ");
        JLabel pass = new JLabel(" password: ");
        JLabel amount = new JLabel(" amount: ");

        JTextField destinationF = new JTextField();
        JTextField idF = new JTextField();
        JTextField amountF = new JTextField();
        JPasswordField passF = new JPasswordField();

        JPanel labelPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 1, 5, 5));


        labelPanel.add(id);
        fieldsPanel.add(idF);

        labelPanel.add(pass);
        fieldsPanel.add(passF);

        if(type.equals("transfer"))
        {
            labelPanel.add(destination);
            fieldsPanel.add(destinationF);
        }

        labelPanel.add(amount);
        fieldsPanel.add(amountF);

        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(labelPanel, BorderLayout.WEST);
        panel.add(frame.getMainPanel().setButtons(), BorderLayout.SOUTH);
        if(type.equals("increase"))
        {
            Icon icon = new ImageIcon(".\\money1.jpg");
            JLabel pic = new JLabel(icon);
//            fieldsPanel.add(pic);
            panel.add(frame.getMainPanel().setLabel(" Increase budget ",
                    Color.green), BorderLayout.NORTH);
            panel.add(pic, BorderLayout.EAST);
            charge(amountF, username);
        }
        else
        {
            Icon icon = new ImageIcon(".\\money2.jpg");
            JLabel pic = new JLabel(icon);
            panel.add(frame.getMainPanel().setLabel(" Transfer budget ",
                    Color.GREEN), BorderLayout.NORTH);
            panel.add(pic, BorderLayout.EAST);
            transfer(amountF, destinationF);
        }
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
        Border border = BorderFactory.createLineBorder(Color.gray, 2);
        JTextField nameF = new JTextField(username);
        nameF.setBackground(Color.DARK_GRAY);
        nameF.setBorder(border);
        JTextField passF = new JTextField(getPass());
        passF.setBackground(Color.DARK_GRAY);
        passF.setBorder(border);
        JTextField budgetF = new JTextField(String.valueOf(getBudget(username)));
        budgetF.setBackground(Color.DARK_GRAY);
        budgetF.setBorder(border);
        JTextField averageF = new JTextField(String.valueOf(getAverage()));
        averageF.setBackground(Color.DARK_GRAY);
        averageF.setBorder(border);

        infoPanel.add(name);
        infoPanel.add(nameF);

        infoPanel.add(pass);
        infoPanel.add(passF);

        infoPanel.add(budget);
        infoPanel.add(budgetF);

        infoPanel.add(average);
        infoPanel.add(averageF);

        infoPanel.setBackground(Color.gray);

        nameF.setEnabled(false);
        passF.setEnabled(false);
        budgetF.setEnabled(false);
        averageF.setEnabled(false);

        String classPath = STUDENTS_PATH + username + "/classes/";
        File[] classFile = FileUtils.getFilesInDirectory(classPath);
        JList classList = new JList<>(classFile);
        classList = new JList<>();

        classList.setVisibleRowCount(-1);
        classList.setCellRenderer(new MyCellRenderer());

        classList.setListData(classFile);

        JScrollPane classF = new JScrollPane(classList);

        Icon icon = new ImageIcon(".\\student.png");
        JLabel pic = new JLabel(icon);

        info.add(infoPanel, BorderLayout.NORTH);
        info.add(classF, BorderLayout.CENTER);
        panel.add(frame.getMainPanel().setLabel("Your profile",Color.getHSBColor(300, 100, 300)), BorderLayout.NORTH);
        panel.add(info, BorderLayout.CENTER);
        panel.add(pic, BorderLayout.WEST);
        return panel;
    }

    public String getPath(String str, String user)
    {
        String path = STUDENTS_PATH+user+"/"+str+".txt";
        return path;
    }
    public float getBudget(String user)
    {
        File budgetFile = new File(getPath("budget", user));
        float budget = Float.parseFloat(FileUtils.scanner(budgetFile, 1));
        return  budget;
    }
    public int getUnit()
    {
        File unitFile = new File(getPath("unit", username));
        int unit = Integer.parseInt(FileUtils.scanner(unitFile, 1));
        return  unit;
    }
    public String getPass()
    {
        File file = new File(INFO_PATH+username+".txt");
        String pass = FileUtils.scanner(file, 2);
        return  pass;
    }
    public float getAverage()
    {
        File averageFile = new File(getPath("average", username));
        float average = Float.parseFloat(FileUtils.scanner(averageFile, 1));
        return  average;
    }
    public int getPassedUnit()
    {
        File passedFile = new File(getPath("passed unit", username));
        int passed = Integer.parseInt(FileUtils.scanner(passedFile, 1));
        return  passed;
    }
    public void charge( JTextField amount, String destination) {
            frame.getMainPanel().getSubmit().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                    System.out.println(amount+ destination);
                    change(amount.getText(), destination);
                }
            });
    }

    public void change(String amount, String destination) {
        float budget;

        budget = getBudget(destination);
        System.out.println(budget);
        budget += Float.parseFloat(amount);
        String note = "budget\n" + budget;
        System.out.println(getPath("budget", destination));
//                System.out.println(note);
        if (!amount.isEmpty()) {
            boolean isSuccessful = new File(getPath("budget", destination)).mkdirs();
            System.out.println("Creating " + getPath("budget", destination) + " directory is successful: " + isSuccessful);
            FileUtils.fileWriter(note, STUDENTS_PATH + destination + "/");
            System.out.println(note);
        }
    }


    public void transfer(JTextField amount, JTextField destination)
    {
        frame.getMainPanel().getSubmit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//
                LoginForm l = new LoginForm();
                if(!l.findPeople(destination.getText()))
                    JOptionPane.showMessageDialog(frame, "Incorrect username!", "Result", JOptionPane.ERROR_MESSAGE);
                else {
                    try {
                        if(!l.findPosition(destination.getText()).equals("student"))
                            JOptionPane.showMessageDialog(frame, "This person is not a student!", "Result", JOptionPane.ERROR_MESSAGE);
                        else if (getBudget(username) < Float.parseFloat(amount.getText()))
                            JOptionPane.showMessageDialog(frame, "Not enough!", "Result", JOptionPane.ERROR_MESSAGE);
                        else
                        {
                            float decrease  = Float.parseFloat(amount.getText()) * (-1);
                            System.out.println(decrease);
        //                    JTextField amountField = new JTextField(Float.toString(decrease));
                            change(Float.toString(decrease), username);
                            change(amount.getText(), destination.getText());

                        }
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
        });

    }

    public JPanel viewClasses()
    {
        JPanel panel = new JPanel(new BorderLayout(1,2));
//        JButton select = new JButton("Select");
        panel.add(frame.getMainPanel().setLabel("Choose class", Color.white), BorderLayout.NORTH);
        panel.add(initDirectoryList(), BorderLayout.WEST);
        panel.add(tabbedPane, BorderLayout.CENTER);

//        panel.add(select, BorderLayout.SOUTH);
        saveClass();
        return panel;
    }

    public void saveClass()
    {
        select.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cnt = 0;
                String content = "class\n";
                String teacher = null;
                String title = null;
                while (cnt < files.length)
                {
//                    content += FileUtils.fileReader(files[cnt]) + "\n";
                    System.out.println(files[cnt]);
                    if(files[cnt].toString().contains("unit"))
                    {
                        int unit = getUnit();
                        unit += Integer.parseInt(FileUtils.scanner(files[cnt], 1));
                        int flag = 0;
                        if(unit>20)
                        {
                            if(getAverage()<17)
                            {
                                JOptionPane.showMessageDialog(frame, "More than 20!", "Error!", JOptionPane.ERROR_MESSAGE);
                                flag++;
                            }
                        }
                        if(flag == 0)
                        {
                            System.out.println(unit);
                            String unitNote = "unit\n"+String.valueOf(unit);
                            String path = STUDENTS_PATH+username+"/";
                            FileUtils.fileWriter(unitNote,path);
                        }
                    }
                    else if(files[cnt].toString().contains("teacher"))
                        teacher = FileUtils.scanner(files[cnt], 1);
                    else if(files[cnt].toString().contains("name"))
                        title = FileUtils.scanner(files[cnt], 1);
                    cnt ++;
                }
                System.out.println(title);

                if(!title.equals(null) && !teacher.equals(null))
                {
                    int flag = 0;
                    String students = "";
                    String path = TEACHER_PATH + teacher + "\\" ;
                    File className[] = FileUtils.getFilesInDirectory(path);
                    int counter = 0;
                    int classNumber = 0;
                    while (counter < className.length)
                    {
                        if(className[counter].toString().contains(title))
                        {
                            Scanner scanner = null;
                            try {
                                scanner = new Scanner(className[counter]);
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }
                            while (scanner.hasNextLine()) {

                                String line = scanner.nextLine();
                                students = students + line + "\n";
                                System.out.println(students);
                                if(line.equals(username))
                                {
                                    JOptionPane.showMessageDialog(frame, "You have chosen this class!", "Error!", JOptionPane.ERROR_MESSAGE);
                                    flag++;
                                }
                            }
                        }
                        counter++;
                    }
                    String capacityPath = CLASSES_PATH+title+"/capacity.txt";
                    File capacityFile = new File(capacityPath);
                    int capacity = Integer.parseInt(FileUtils.scanner(capacityFile, 1));
                    if(capacity == 0)
                    {
                        flag++;
                        JOptionPane.showMessageDialog(frame, "THIS CLASS IS FULL!", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        String cp = "capacity\n" + (capacity -1 );
                        String p = CLASSES_PATH + title +"\\";
                        FileUtils.fileWriter(cp, p);
                    }
                    if(flag == 0)
                    {
                        students += username;
                        System.out.println(students);
                        FileUtils.fileWriter(students, TEACHER_PATH + teacher + "\\");
                        JOptionPane.showMessageDialog(frame, "Successful!", "Result", JOptionPane.INFORMATION_MESSAGE);
                        if(title != null)
                        {
                            String classPath = STUDENTS_PATH + username + "/classes/";
//                            boolean isSuccessful = new File(STUDENTS_PATH + username + "\\classes").mkdirs();
                            FileUtils.fileWriter(title, classPath);
                        }
                    }
                }
//                System.out.println(content);
            }

        });
    }
    public void saveMeals(JCheckBox day, JComboBox meals)
    {
        frame.getMainPanel().getSubmit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String dayNote = day.getText();
                if (day.isSelected()) {
                    String path = STUDENTS_PATH + username + "/meals/";
                    FileUtils.makeFolder(path);
                    String mealNote = meals.getSelectedItem().toString();
                    String[] arrOfStr = mealNote.split("     ", 5);
                    if (getBudget(username) < Float.parseFloat(arrOfStr[1])/2 ) {
                        JOptionPane.showMessageDialog(frame, "not enough!", "Result", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(getBudget(username) < Float.parseFloat(arrOfStr[1]) && getAverage()<17) {
                        JOptionPane.showMessageDialog(frame, "not enough!", "Result", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        String note = dayNote + "\n" + mealNote;
                        FileUtils.fileWriter(note, path);
                        System.out.println(note);
                        float price = 0;
                        if(getAverage() >= 17)
                            price = Float.parseFloat(arrOfStr[1])/2;
                        else
                            price = Float.parseFloat(arrOfStr[1]);
                        System.out.println(price);
                        String budgetNote = "budget\n" + (getBudget(username) - price);
                        boolean isSuccessful = new File(getPath("budget", username)).mkdirs();
                        System.out.println("Creating " + getPath("budget", username) + " directory is successful: " + isSuccessful);
                        FileUtils.fileWriter(budgetNote, STUDENTS_PATH + username + "/");
                    }
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
