package gui;
/**
 * This class inherits from JFrame and sets options to the frame.
 * @author Tanya Djavaherpour
 * @version 1.0 2020
 */

import com.company.LoginForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

}