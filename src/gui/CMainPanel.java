package gui;

//import utils.FileUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
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

    public JPanel addChangeUserPassTab()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel label = new JLabel(" Change username / password ");
        label.setBackground(Color.getHSBColor(178, 222, 251));
        label.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        label.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.gray, 2);
        label.setBorder(border);
        int labelWidth = label.getPreferredSize().width + 20;
        int labelHeight = label.getPreferredSize().height + 20;
        label.setPreferredSize(new Dimension(labelWidth, labelHeight));

        JLabel currName = new JLabel(" your current username: ");
        JLabel newName = new JLabel(" new username: ");
        JLabel currPass = new JLabel(" current password: ");
        JLabel newPass = new JLabel(" new password: ");
        JTextField currNameF = new JTextField();
        JTextField newNameF = new JTextField();
        JPasswordField currPassF = new JPasswordField();
        JPasswordField newPassF = new JPasswordField();

        JPanel labelPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        labelPanel.add(currName);
        fieldsPanel.add(currNameF);

        labelPanel.add(newName);
        fieldsPanel.add(newNameF);

        labelPanel.add(currPass);
        fieldsPanel.add(currPassF);

        labelPanel.add(newPass);
        fieldsPanel.add(newPassF);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton submit = new JButton("submit");
        JButton cancel = new JButton("cancel");
        int buttonWidth = submit.getPreferredSize().width;
        int buttonHeight = submit.getPreferredSize().height + 10;
        submit.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        int button2Width = cancel.getPreferredSize().width;
        int button2Height = cancel.getPreferredSize().height + 10;
        submit.setPreferredSize(new Dimension(button2Width, button2Height));
        buttonPanel.add(cancel);
        buttonPanel.add(submit);

        panel.add(label, BorderLayout.NORTH);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(labelPanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        addPanel("Change username / password",panel);
        return panel;
    }

    public void addSpecificTab(String name, String text)
    {
        JTextArea textPanel = createTextPanel();
        textPanel.setText(text);
        tabbedPane.addTab(name,textPanel);
    }

    public void addPanel(String name, JPanel panel)
    {
        tabbedPane.addTab(name, panel);
    }

    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }

}
