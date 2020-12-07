package com.company;

import gui.CFrame;

import javax.swing.*;

public class Admin {
    private CFrame frame;

    public Admin()
    {
        frame = new CFrame("Admin");
        addFrameMenu();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addFrameMenu()
    {
        frame.addToMenu("Set Meals");
        frame.addToMenu("Students");
        frame.addToMenu("Teachers");
        frame.addToMenu("Classes");
        frame.addToMenu("Add");
    }

}
