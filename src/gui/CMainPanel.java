package gui;

import utils.FileUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CMainPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private JButton submit;
    private JButton cancel;
    private static final String INFO_PATH = "./user pass/";

    public CMainPanel() {

        setLayout(new BorderLayout());

        initTabbedPane(); // add TabbedPane to main panel

    }

    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }


    public JLabel setLabel(String labelStr,Color color)
    {
        JLabel label = new JLabel(labelStr);
        label.setBackground(color);
        label.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        label.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.gray, 2);
        label.setBorder(border);
        int labelWidth = label.getPreferredSize().width + 20;
        int labelHeight = label.getPreferredSize().height + 20;
        label.setPreferredSize(new Dimension(labelWidth, labelHeight));
        return label;
    }

    public JPanel setButtons()
    {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        submit = new JButton("submit");
        cancel = new JButton("cancel");
        int buttonWidth = submit.getPreferredSize().width;
        int buttonHeight = submit.getPreferredSize().height + 10;
        submit.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        int button2Width = cancel.getPreferredSize().width;
        int button2Height = cancel.getPreferredSize().height + 10;
        submit.setPreferredSize(new Dimension(button2Width, button2Height));
        buttonPanel.add(cancel);
        buttonPanel.add(submit);
        return buttonPanel;
    }

    public JButton getSubmit()
    {
        return submit;
    }


    public JPanel addChangeUserPassTab(String str, String username)
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel curr = new JLabel(" your current "+str+": ");
        JLabel newOne = new JLabel(" new "+str+": ");
        JTextField currF = new JTextField();
        JTextField newF = new JTextField();

        JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JPanel fieldsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        labelPanel.add(curr);
        fieldsPanel.add(currF);

        labelPanel.add(newOne);
        fieldsPanel.add(newF);

        panel.add(setLabel(" Change "+str, Color.getHSBColor(178, 222, 251)), BorderLayout.NORTH);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(labelPanel, BorderLayout.WEST);
        panel.add(setButtons(), BorderLayout.SOUTH);
        addPanel("Change "+str,panel);
        if(str.equals("user"))
            setNewUsername(currF,newF, panel);
        else
            setNewPass(currF, newF, panel, username);
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

    public void setNewPass(JTextField curr, JTextField newPass ,JPanel panel, String user)
    {
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String currPass = curr.getText();
                String newPassText = newPass.getText();
                String position = "";
                int flag = 0;
                File userPasses[] = FileUtils.getFilesInDirectory(INFO_PATH);
                if (checkPass(newPassText, panel))
                {
                    int cnt = 0;
                    while (cnt < userPasses.length)
                    {
                        if((String.valueOf(userPasses[cnt]).contains("\\"+user+".txt")))
                        {
                            Scanner scanner = null;
                            try {
                                scanner = new Scanner(userPasses[cnt]);
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }
                            int counter = 0;
                            while (scanner.hasNextLine()) {
                                String line = scanner.nextLine();
                                if(counter == 2)
                                {
                                    if(!line.equals(currPass))
                                    {
                                        JOptionPane.showMessageDialog(panel, "Incorrect pass!", "Result",
                                                JOptionPane.ERROR_MESSAGE);
                                        flag ++;
                                    }

                                }
                                if (counter == 1)
                                    position = line;
                                counter++;
                            }
                        }
                        cnt++;
                    }
                    if(flag == 0)
                    {
                        String note = user + "\n" + position + "\n" + newPassText;
                        FileUtils.fileWriter(note, INFO_PATH);
                        JOptionPane.showMessageDialog(panel, "Changed!", "Result",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            }
        });
    }

    public void setNewUsername(JTextField curr, JTextField newUsername ,JPanel panel)
    {
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String currText = curr.getText();
                String newUsernameText = newUsername.getText();
                File userPasses[] = FileUtils.getFilesInDirectory(INFO_PATH);
                int cnt = 0;
                String name = null;
                String pass = null;
                checkUsername(newUsernameText, panel);

            }
        });
    }

    public boolean checkUsername(String newUsernameText, JPanel panel)
    {
        File userPasses[] = FileUtils.getFilesInDirectory(INFO_PATH);
        int cnt = 0;
        int flag = 0;
        while (cnt < userPasses.length)
        {
            if((String.valueOf(userPasses[cnt]).contains("\\"+newUsernameText+".txt")))
            {
                JOptionPane.showMessageDialog(panel, "Invalid username!", "Result", JOptionPane.ERROR_MESSAGE);
                flag++;
            }
            cnt++;
        }
        if(flag > 0)
            return false;
        else
            return true;
    }

    public boolean checkPass(String newPass, JPanel panel)
    {
        if(newPass.length()<8)
        {
            JOptionPane.showMessageDialog(panel, "Password should be more than 8 characters!", "Result", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
            return true;
    }
}


