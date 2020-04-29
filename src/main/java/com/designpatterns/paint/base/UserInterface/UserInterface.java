package com.designpatterns.paint.base.UserInterface;

import com.designpatterns.paint.base.Models.DrawPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class UserInterface extends JFrame{
    private JPanel rootPanel;
    private JPanel menu_panel;
    private JButton selectShapes;
    private JPanel content_panel;
    private JPanel shape_add_panel;
    private JTextField new_shape_x;
    private JTextField new_shape_y;
    private JTextField new_shape_z;
    private JComboBox<String> new_shape_combobox;
    private JPanel draw_panel;
    private JRadioButton addRadioButton;
    private JRadioButton editRadioButton;

    private DrawPanel drawPanel = new DrawPanel();

    /**
     * Main JFrame IU, everything in the view is stored here
     */
    private JFrame userInterfaceFrame = new JFrame("Design Patterns Paint");

    public UserInterface(){
        // Create required elements for the JFrame
        createInterfaceElements();

        draw_panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedAction(e);
            }
        });
    }

    /**
     * Do stuff based on selected options from menu bar
     * TODO: make it so user cant press button multiple times, or break stuff
     */
    private void mousePressedAction(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());

        // Add if statement to check if we need to add a new shape here
        if (addRadioButton.isSelected() && !editRadioButton.isSelected()) {
            addShapeToPanel(e.getX(), e.getY());
        } else if (editRadioButton.isSelected() && !addRadioButton.isSelected()) {
            getSelectedShape(e.getX(), e.getY());
        } else if (editRadioButton.isSelected() && addRadioButton.isSelected()) {
            System.out.println("Fix dat dit niet kan");
        }
    }

    /**
     * Main function that contains all of the base UI settings
     */
    private void createInterfaceElements() {
        userInterfaceFrame.setContentPane(rootPanel);
        userInterfaceFrame.setSize(1280,720);
        userInterfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userInterfaceFrame.setVisible(true);
        // Set Combobox content, fill it with the shapes we need
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(new String[]{"Ellipse", "Rectangle"});
        new_shape_combobox.setModel(model);
        // Add the DrawPanel to the draw_panel so we can draw shit
        draw_panel.setLayout(new BorderLayout());
        draw_panel.add(drawPanel);
    }

    /**
     * Get selected combobox values and check if xyz has been filled, if true continue and add the shape to the JPanel
     * Shapes are as follows: 1 - Circle, 2 - Rectangle, 3 - Square, 4 - Triangle
     */
    private void addShapeToPanel(Integer mousePosX, Integer mousePosY) {
        System.out.println("Adding new shape: selected shape: '" + new_shape_combobox.getSelectedItem() + "' with values: x:" + new_shape_x.getText() + " y:" + new_shape_y.getText() + " z:" + new_shape_z.getText());
        drawPanel.addShape(Objects.requireNonNull(new_shape_combobox.getSelectedItem()).toString(), mousePosX, mousePosY, 100, 100);
    }

    /**
     * Get selected shape based on cursor position
     * TODO: fix dit
     */
    private void getSelectedShape(Integer mousePosX, Integer mousePosY) {
        System.out.println("Checking if user has selected shape");

    }

    /**
     * Get get the 2 selected shapes and combine these.
     * TODO: implement
     */
    private void combineShapes() {

    }

}

