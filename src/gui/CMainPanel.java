package gui;

import utils.FileUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CMainPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private JButton submit;
    private JButton cancel;
    private static final String INFO_PATH = "./user pass/";

    public CMainPanel() {

        setLayout(new BorderLayout());

        initTabbedPane(); // add TabbedPane to main panel

//        addNewTab(); // open new empty tab when user open the application
    }

    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }

    public JTextArea makeTextPanel()
    {
        return createTextPanel();
    }

//    public void addNewTab() {
//        JTextArea textPanel = createTextPanel();
//        if(tabbedPane.getTabCount() == 0)
//        {
//            textPanel.setText("Username: \nPassword: ");
//            tabbedPane.addTab("PROFILE",textPanel);
//        }
//        else
//        {
//            textPanel.setText("Write Something here...");
//            tabbedPane.addTab("Tab " + (tabbedPane.getTabCount() + 1), textPanel);
//        }
//    }
//
//    public JPanel makeJPanel()
//    {
//        JPanel panel = new JPanel(new BorderLayout(5, 5));
//        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
//    }

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


    public JPanel addChangeUserPassTab(String str)
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel curr = new JLabel(" your current "+str+": ");
        JLabel newOne = new JLabel(" new "+str+": ");
//        JLabel currPass = new JLabel(" current password: ");
//        JLabel newPass = new JLabel(" new password: ");
        JTextField currF = new JTextField();
        JTextField newF = new JTextField();
//        JPasswordField currPassF = new JPasswordField();
//        JPasswordField newPassF = new JPasswordField();

        JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JPanel fieldsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        labelPanel.add(curr);
        fieldsPanel.add(currF);

        labelPanel.add(newOne);
        fieldsPanel.add(newF);

//        labelPanel.add(currPass);
//        fieldsPanel.add(currPassF);
//
//        labelPanel.add(newPass);
//        fieldsPanel.add(newPassF);

        panel.add(setLabel(" Change "+str, Color.getHSBColor(178, 222, 251)), BorderLayout.NORTH);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(labelPanel, BorderLayout.WEST);
        panel.add(setButtons(), BorderLayout.SOUTH);
        addPanel("Change "+str,panel);
        setNewUsername(currF,newF);
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

    public void setNewUsername(JTextField curr, JTextField newUsername)
    {
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String currText = curr.getText();
                String newUsernameText = newUsername.getText();
//                System.out.println(INFO_PATH+currText+" .txt/");
                File userPasses[] = FileUtils.getFilesInDirectory(INFO_PATH);
                int cnt = 0;
                String name = null;
                String pass = null;
//                System.out.println(userPasses[1]);
                while (cnt < userPasses.length)
                {
                    int lineCnt = 0;
                    System.out.println(String.valueOf(userPasses[cnt]));
                    if(String.valueOf(userPasses[cnt]).contains("\\user pass\\"+currText+".txt"))
                    {
                        Scanner scanner = null;
                        try {
                            scanner = new Scanner(userPasses[cnt]);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if(lineCnt == 1)
                                name = line;
                            else if(lineCnt == 2)
                                pass = line;
                            lineCnt++;
                        }

                    }
                    cnt++;
                }
                String note = newUsernameText + "\n" + name + "\n" + pass;
                if ((!currText.isEmpty() && !newUsernameText.isEmpty()))
                  {
                    boolean isSuccessful = new File(INFO_PATH).mkdirs();
                    System.out.println("Creating " + INFO_PATH + " directory is successful: " + isSuccessful);
                    FileUtils.fileWriter(note, INFO_PATH);
                    System.out.println(note);
                }
                System.out.println(note);
            }
        });
    }


}
