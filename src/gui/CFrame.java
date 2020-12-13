package gui;

import javax.swing.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class CFrame extends JFrame implements ActionListener{

    private CMainPanel mainPanel;

    private JMenuItem newItem;
    private JMenuItem saveItem;
    private JMenuItem exitItem;
    private JMenuItem user;
    private JMenuItem pass;
    JMenuBar menuBar;
    JMenu jmenu ;


    public CFrame(String title) {
        super(title);
//        mainPanel = new CMainPanel(profile);
        menuBar = new JMenuBar();
        jmenu = new JMenu("Options");
        setView();

        initMenuBar(); //create menuBar

        initMainPanel(); //create main panel
    }

    private void setView()
    {
        this.setLocation(350, 150);
        this.setSize(1000,500);
    }

    private void initMenuBar() {


        newItem = new JMenuItem("New");
        user = new JMenuItem("Change username");
        pass = new JMenuItem("Change pass");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        jmenu.add(newItem);
        jmenu.add(user);
        jmenu.add(pass);
        jmenu.add(saveItem);
        jmenu.add(exitItem);

        newItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        user.addActionListener(this);
        pass.addActionListener(this);

        menuBar.add(jmenu);
        setJMenuBar(menuBar);
    }

    public JMenuItem addToMenu(String str)
    {
        JMenuItem item;
        item = new JMenuItem(str);
        jmenu.add(item);
        item.addActionListener(this);
        return item;
    }

    public JMenu addSub(JMenu sub, JMenuItem item)
    {
        sub.add(item);
        return sub;
    }
    private void initMainPanel() {
        mainPanel = new CMainPanel();
        add(mainPanel);
    }

    public CMainPanel getMainPanel()
    {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!e.getActionCommand().equals(""))
        {
            if (e.getSource() == newItem) {
//                mainPanel.addNewTab();
            }
            else if (e.getSource() == saveItem) {
//            mainPanel.saveNote();
//            mainPanel.saveNoteStream();
//            mainPanel.saveSerializedNote();
            }
            else if (e.getSource() == exitItem) {
//            mainPanel.saveAll();
//            mainPanel.saveAllStream();
//            mainPanel.saveAllSerialized();
                System.exit(0);
            }
            else if(e.getSource() == user)
            {
//                mainPanel.addSpecificTab(e.getActionCommand(),null);
                mainPanel.addChangeUserPassTab("user");
            }
            else if(e.getSource() == pass)
            {
//                mainPanel.addSpecificTab(e.getActionCommand(),null);
                mainPanel.addChangeUserPassTab("pass");
            }
        }

        else {
            System.out.println("Nothing detected...");
        }
    }

//    public void changeUsername()
//    {
//        user.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    frame.getMainPanel().addPanel("Meals",setMeals());
//                } catch (FileNotFoundException fileNotFoundException) {
//                    fileNotFoundException.printStackTrace();
//                }
//            }
//        });
//    }
}