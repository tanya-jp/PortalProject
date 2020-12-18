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
        int cnt = 0;
        while (cnt<str.length())
        {
            if((str.charAt(cnt)==('0') || str.charAt(cnt)==('1') || str.charAt(cnt)==('2')
                    || str.charAt(cnt)==('3') || str.charAt(cnt)==('4') || str.charAt(cnt)==('5')
                    || str.charAt(cnt)==('6') || str.charAt(cnt)==('7') || str.charAt(cnt)==('8')
                    || str.charAt(cnt)==('9')))
                cnt++;
            else
                return false;
        }
        return true;
    }

}
