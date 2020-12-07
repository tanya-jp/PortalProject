package gui;

import javax.swing.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CFrame extends JFrame implements ActionListener{

    private CMainPanel mainPanel;

    private JMenuItem newItem;
    private JMenuItem saveItem;
    private JMenuItem exitItem;
    private JMenuItem userPass;
    JMenuBar menuBar;
    JMenu jmenu ;


    public CFrame(String title) {
        super(title);

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
        userPass = new JMenuItem("Change username or password");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        jmenu.add(newItem);
        jmenu.add(userPass);
        jmenu.add(saveItem);
        jmenu.add(exitItem);

        newItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        userPass.addActionListener(this);

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
                mainPanel.addNewTab();
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
            else if(e.getSource() == userPass)
            {
                mainPanel.addSpecificTab(e.getActionCommand(),null);
            }
        }

        else {
            System.out.println("Nothing detected...");
        }
    }
}