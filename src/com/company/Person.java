package com.company;
/**
 * This class creates a person.
 * @author Tanya Djavaherpour
 * @version 1.0 2020
 */

import gui.CFrame;

import javax.swing.*;

public abstract class Person {

    private String username;
    private CFrame frame;
    public Person(String user)
    {
        this.username = user;
        frame = new CFrame(user);
    }

    /**
     * @return frame of information of this person
     */
    public CFrame getFrame()
    {
        return frame;
    }

    /**
     * Adds menu's items.
     */
    abstract void addFrameMenu();
    /**
     * After clicking on menu items this method adds tabs to the frame.
     */
    abstract void addToTab();
    /**
     * Constructs profile panel
     * @return proper profile panel to person
     */
    abstract JPanel profilePanel();
    /**
     * Checks if all characters of string are number
     * @param str is input string
     * @return if they were number returns true, else returns false
     */
    public boolean checkNumber(String str)
    {
        if(!(str.contains("0") || str.contains("1") || str.contains("2") ||
                str.contains("3")|| str.contains("4") || str.contains("5") ||
                str.contains("6")|| str.contains("7") || str.contains("8") || str.contains("9")))
        {
            JOptionPane.showMessageDialog(frame, "Invalid!", "Result", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
