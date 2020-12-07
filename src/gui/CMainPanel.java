package gui;

//import utils.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;


public class CMainPanel extends JPanel {

    private JTabbedPane tabbedPane;

    public CMainPanel() {

        setLayout(new BorderLayout());

        initTabbedPane(); // add TabbedPane to main panel

        addNewTab(); // open new empty tab when user open the application
    }

    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }

    public JTextArea makeTextPanel()
    {
        return createTextPanel();
    }

    public void addNewTab() {
        JTextArea textPanel = createTextPanel();
        if(tabbedPane.getTabCount() == 0)
        {
            textPanel.setText("Username: \nPassword: ");
            tabbedPane.addTab("PROFILE",textPanel);
        }
        else
        {
            textPanel.setText("Write Something here...");
            tabbedPane.addTab("Tab " + (tabbedPane.getTabCount() + 1), textPanel);
        }
    }

    public void addSpecificTab(String name, String text)
    {
        JTextArea textPanel = createTextPanel();
        textPanel.setText(text);
        tabbedPane.addTab(name,textPanel);
    }

    public void addMeal(JFrame frame)
    {
        tabbedPane.addTab("Meal",frame);
    }

    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }

}
