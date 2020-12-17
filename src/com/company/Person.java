package com.company;

import gui.CFrame;

import javax.swing.*;

public abstract class Person {

    private String username;
    private CFrame frame;
    public Person(String user)
    {
        this.username = user;
        frame = new CFrame(user);
//        frame.setFrame(position, profilePanel());
//        addFrameMenu();
//        addToTab();
    }
//
    public CFrame getFrame()
    {
        return frame;
    }
    abstract void addFrameMenu();
//
    abstract void addToTab();
//
    abstract JPanel profilePanel();

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
