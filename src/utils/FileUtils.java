package utils;
/**
 * This class does things related to files, such as reading a file, writing a file
 * and other things that are clear in the methods.
 * @author Tanya Djavaherpour
 * @version 1.0 2020
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class FileUtils {

    /**
     * Makes a file of all files in the path
     * @param path as path of wanted files
     * @return all files
     */
    public static File[] getFilesInDirectory(String path) {
        return new File(path).listFiles();
    }

    /**
     * Makes new folder.
     * @param folder as path that folder should be created there
     */
    public static void makeFolder(String folder)
    {
        boolean isSuccessful = new File(folder).mkdirs();
        System.out.println("Creating " + folder + " directory is successful: " + isSuccessful);
    }
    /**
     * Write on a file using BufferedWriter
     * @param content String of content to be written on a file
     */
    public static void fileWriter(String content,String p) {
        String fileName = getProperFileName(content);
        File newFile = new File(p+fileName+".txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(newFile));
            writer.write(content);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            try {
                if (writer != null)
                {
                    writer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

    /**
     * Read from files using Buffered reader
     * @param file File to be read
     * @return String of all the file content
     */
    public static String fileReader(File file) {
        String string = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            int cnt;
            char[] buffer = new char[2048];
            while (reader.ready()) {
                cnt = reader.read(buffer);
                string += new String(buffer, 0, cnt);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
        return string;
    }

    /**
     * Gets proper name to the file, this name is first line of the content of the text.
     * @param content as conts that should be saved in the file
     * @return proper name for the file(proper name is first line of the text)
     */
     public static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
        }
        return System.currentTimeMillis() + "_new file.txt";
    }

    /**
     * Scans a file and reads it line by line and finds the wanted line
     * @param file as file
     * @param lineNumber as number of wanted line
     * @return String of wanted line
     */
    public static String scanner(File file, int lineNumber)
    {
        String result = "";
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        int counter = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(counter == lineNumber)
                result = line;
            counter++;
        }
        scanner.close();
        return result;
    }

    /**
     * Open a note without any button
     * @param content as content of the note
     * @param title as title of new tab that should be added to the tabs
     * @param tabbedPane as tabbed pane that shows notes
     * @return tabbed panel with new added tab
     */
    public static JTabbedPane openExistingNote(String content, String title, JTabbedPane tabbedPane) {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        JTextArea existPanel = createTextPanel();
        existPanel.setText(content);
        panel.add(existPanel, BorderLayout.CENTER);
        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab(title, panel);
        tabbedPane.setSelectedIndex(tabIndex - 1);
        return tabbedPane;
    }

    /**
     * Open a note with button
     * @param content as content of the note
     * @param title as title of new tab that should be added to the tabs
     * @param tabbedPane as tabbed pane that shows notes
     * @param button as button of choosing the tab
     * @return tabbed panel with new added tab
     */
    public static JTabbedPane openNoteWithButton(String content, String title, JTabbedPane tabbedPane, JButton button) {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        JTextArea existPanel = createTextPanel();
        existPanel.setText(content);
        panel.add(existPanel, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab(title, panel);
        tabbedPane.setSelectedIndex(tabIndex - 1);
        return tabbedPane;
    }

    /**
     * Creates a new text panel to show texts
     * @return creates text area
     */
    private static JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }

}
