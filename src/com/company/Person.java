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
}
