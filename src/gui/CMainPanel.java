package gui;
/**
 * This class creates main panel and sets everything which is common
 * @author Tanya Djavaherpour
 * @version 1.0 2020
 */

import utils.FileUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class CMainPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private JButton submit;
    private JButton cancel;
    private static final String INFO_PATH = "./user pass/";

    /**
     * Creates panel and adds initial things and sets layout
     */
    public CMainPanel() {

        setLayout(new BorderLayout());

        initTabbedPane(); // add TabbedPane to main panel

    }

    /**
     * Sets tabbedPane. New tab will be added to this tabbedPane whenever a new item is selected.
     */
    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Sets label to tab.
     * @param labelStr as string that should be shown as label
     * @param color as color of the label
     * @return JLabel of created label
     */
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

    /**
     * Sets submit and cancel butmn to tabs.
     * @return buttonPanel which is a panel with submit and cancel buttons
     */
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

    /**
     * Gets submit button
     * @return submit
     */
    public JButton getSubmit()
    {
        return submit;
    }

    /**
     * By using this panel, people can change their username or password
     * @param str as thing that should be changed. THis can be user or pass
     * @param username as username of the person who wants to change their information
     * @return a JPanel as panel of changing username or password.
     */
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

    /**
     * Adds specific tab to tabbedPane.
     * @param name as title of new tab.
     * @param text as text that should be shown in the tab.
     */
    public void addSpecificTab(String name, String text)
    {
        JTextArea textPanel = createTextPanel();
        textPanel.setText(text);
        tabbedPane.addTab(name,textPanel);
    }

    /**
     * Adds panel to the tabbedPane.
     * @param name as title of new tab.
     * @param panel as new panel that should be added to the tabbedPane.
     */
    public void addPanel(String name, JPanel panel)
    {
        tabbedPane.addTab(name, panel);
    }

    /**
     * Creates new text area and sets its border.
     * @return created text panel
     */
    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }

    /**
     * When people wants to change their password, if it is possible this class changes it after they click submit button.
     * @param curr as current password to check if it is correct
     * @param newPass as new wanted password to check if it's length is more than 8 and then change it.
     * @param panel as panel that tabs are shown to show warning in the case needed.
     * @param user as username of the person who wants to change their password.
     */
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
    /**
     * When people wants to change their username, if it is possible this class changes it after they click submit button.
     * @param curr as current username.
     * @param newUsername as new wanted username to check if it's available then change it.
     * @param panel as panel that tabs are shown to show warning in the case needed.
     */
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
                String content = "";
                String p1 = "";
                while (cnt < userPasses.length)
                {
                    if(userPasses[cnt].toString().contains("\\"+currText+".txt"))
                    {
                        p1 = userPasses[cnt].toString();
//                        String content = "";
                        Scanner scanner = null;
                        try {
                            scanner = new Scanner(userPasses[cnt]);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        int counter = 0;
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            content += line + "\n";
                            counter++;
                        }
                        FileOutputStream file = null;
                        PrintWriter p = null;
                        try {
                            file = new FileOutputStream(userPasses[cnt]);
                            file.flush();
                            file.close();
                            if(!userPasses[cnt].delete())
                                System.out.println(")))))))))))");
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        finally {
                            // releases all system resources from the streams
                            try {
                                file.close();
                                userPasses[cnt].delete();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                                System.out.println("kir");
                            }
                        }
                        if(!userPasses[cnt].delete())
                            System.out.println(")))))))))))");
//                        File newFile = new File(p+fileName+".txt");

                    }
                    cnt++;
                }
                Path origin = Paths.get(p1);
//                        FileUtils.fileWriter(content, INFO_PATH);

                try {
                    Files.move(origin, origin.resolveSibling(INFO_PATH+newUsernameText+".txt"));

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
//                Path origin = Paths.get(INFO_PATH+currText+".txt");
//                FileUtils.fileReader()
//                try {
//                    Files.move(origin, origin.resolveSibling(INFO_PATH+newUsernameText+".txt"));
//
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }

            }
        });
    }

    /**
     * Checks if wanted username is available
     * @param newUsernameText as new wanted username to check if it's available then change it.
     * @param panel as panel that tabs are shown to show warning in the case needed.
     * @return true if wanted username is available, false when it has been saved before and is not available
     */
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

    /**
     * Checks if new wanted password is acceptable
     * @param newPass as new wanted pass to check if its length more than 8 then change it.
     * @param panel as panel that tabs are shown to show warning in the case needed.
     * @return true if wanted password is acceptable and false when it is not.
     */
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


