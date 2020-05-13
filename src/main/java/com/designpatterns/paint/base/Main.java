package com.designpatterns.paint.base;

import com.designpatterns.paint.base.UserInterface.UserInterface;

import javax.swing.*;

public class Main {

    /**
     * @param args;
     */
    public static void main(String[] args) {
        try {
            // Force Windows style for the Swing application
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            // Start the main UI
            new UserInterface();
        } catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
