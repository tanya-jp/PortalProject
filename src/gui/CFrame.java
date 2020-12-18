package gui;
/**
 * This class inherits from JFrame and sets options to the frame.
 * @author Tanya Djavaherpour
 * @version 1.0 2020
 */

import com.company.LoginForm;
import com.company.Teacher;
import utils.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CFrame extends JFrame implements ActionListener{

    private CMainPanel mainPanel;
    private JMenuItem exitItem;
    private JMenuItem user;
    private JMenuItem pass;
    JMenuBar menuBar;
    JMenu jmenu ;

    /**
     * Creates CFrame
     * @param title as title of the frame
     */
    public CFrame(String title) {
        super(title);
        menuBar = new JMenuBar();
        jmenu = new JMenu("Options");
        setView();

        initMenuBar(); //create menuBar

        initMainPanel(); //create main panel
    }

    /**
     * Sets icon to the frame
     * @param position: admin, student or teacher
     */
    public void setIcon(String position)
    {
        try {
            this.setIconImage(ImageIO.read(new File("./"+ position + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets related profile to the position as makes it visible
     * @param position: admin, student or teacher
     * @param profilePanel as profile panel that is created in Person class
     */
    public void setFrame(String position,JPanel profilePanel)
    {
        this.getMainPanel().addPanel("PROFILE",profilePanel);
        this.setIcon(position);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Sets frame's view properties
     */
    private void setView()
    {
        this.setLocation(350, 150);
        this.setSize(1000,500);
    }

    /**
     * Makes menu bar and sets options of it which are common.
     */
    private void initMenuBar() {


        user = new JMenuItem("Change username");
        pass = new JMenuItem("Change pass");
        exitItem = new JMenuItem("Exit");

        jmenu.add(user);
        jmenu.add(pass);
        jmenu.add(exitItem);

        exitItem.addActionListener(this);
        user.addActionListener(this);
        pass.addActionListener(this);

        menuBar.add(jmenu);
        setJMenuBar(menuBar);
    }

    /**
     * Adds specific string to the menu
     * @param str as title of menu item
     * @return created menu item
     */
    public JMenuItem addToMenu(String str)
    {
        JMenuItem item;
        item = new JMenuItem(str);
        jmenu.add(item);
        item.addActionListener(this);
        return item;
    }

    /**
     * Creates init panel
     */
    private void initMainPanel() {
        mainPanel = new CMainPanel();
        add(mainPanel);
    }

    /**
     * @return mainPanel that items are added to
     */
    public CMainPanel getMainPanel()
    {
        return mainPanel;
    }

    /**
     * Closes the tab and make it invisible.
     */
    public void closeTab()
    {
        this.setVisible(false);
    }

    /**
     * Defines what should menu items do when they are clicked
     * @param e as clicked item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!e.getActionCommand().equals(""))
        {
            if (e.getSource() == exitItem) {
                LoginForm l = new LoginForm();
                try {
                    if(l.findPosition(getTitle()).equals("teacher"))
                    {
                        Teacher teacher = new Teacher(getTitle());
                        teacher.notShowGUI();
                        FileUtils.fileWriter("pass\n"+teacher.getPass(), ".\\teachers\\" + getTitle() + "\\");
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                closeTab();
                LoginForm loginForm = new LoginForm();
                loginForm.showGUI();
            }
            else if(e.getSource() == user)
            {
                mainPanel.addChangeUserPassTab("user", getTitle());
            }
            else if(e.getSource() == pass)
            {
                mainPanel.addChangeUserPassTab("pass",getTitle());
            }
        }

        else {
            System.out.println("Nothing detected...");
        }
    }

    /**
     * Makes a list of files
     * @param dicList as JList that items should be added to
     * @param files as files
     * @return input JList with added items
     */
    public static JList setDictionary(JList dicList, File[] files)
    {
        dicList = new JList<>(files);
        dicList = new JList<>();
        dicList.setBackground(new Color(211, 211, 211));
        dicList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        dicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dicList.setVisibleRowCount(-1);
        dicList.setMinimumSize(new Dimension(130, 100));
        dicList.setMaximumSize(new Dimension(130, 100));
        dicList.setFixedCellWidth(130);
        return dicList;
    }

}