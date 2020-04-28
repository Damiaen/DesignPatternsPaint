package com.designpatterns.paint.base.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame{
    private JPanel rootPanel;
    private JButton button_parse;
    private JButton button_build;
    private JPanel buttons_panel;
    private JPanel questions_panel;
    private JButton ask_question;
    private JPanel content_panel;
    private JProgressBar questionProgressBar;

    /**
     * Main JFrame IU, everything in the view is stored here
     */
    private JFrame userInterfaceFrame = new JFrame("Design Patterns Paint");

    public UserInterface(){
        // Create required elements for the JFrame
        createInterfaceElements();


        // Event listeners that respond to button clicks
        button_parse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { System.out.println("pressed"); }
        });
        button_build.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { System.out.println("pressed"); }
        });
        ask_question.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { System.out.println("pressed"); }
        });
    }


    /**
     * Main function that contains all of the base UI settings
     */
    private void createInterfaceElements() {
        userInterfaceFrame.setContentPane(rootPanel);
        userInterfaceFrame.setSize(1280,720);
        userInterfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userInterfaceFrame.setVisible(true);
    }

}
