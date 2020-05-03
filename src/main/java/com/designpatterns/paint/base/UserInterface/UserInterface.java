package com.designpatterns.paint.base.UserInterface;

import com.designpatterns.paint.base.Models.UserInterface.DrawPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class UserInterface extends JFrame{
    private JPanel rootPanel;
    private JPanel right_menu_panel;
    private JButton selectShapes;
    private JPanel content_panel;
    private JPanel shape_add_panel;
    private JTextField new_shape_height;
    private JTextField new_shape_width;
    private JComboBox<String> new_shape_combobox;
    private JPanel draw_panel;
    private JRadioButton addRadioButton;
    private JRadioButton editRadioButton;
    private JPanel top_menu_panel;
    private JRadioButton mergeRadioButton;
    private JButton clearDrawingButton;
    private JButton undoButton;
    private JButton redoButton;
    private JButton saveDrawingButton;
    private JRadioButton removeRadioButton;
    private JPanel shape_merge_panel;
    private JList<String> mergeShapeList;
    private JButton mergeLayersButton;
    private JButton updateShapeButton;

    private DrawPanel drawPanel = new DrawPanel();
    private DefaultListModel<String> listModel = new DefaultListModel<>();


    /**
     * Main JFrame IU, everything in the view is stored here
     */
    private JFrame userInterfaceFrame = new JFrame("Design Patterns Paint");

    public UserInterface(){
        // Create required elements for the JFrame
        createInterfaceElements();

        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedAction(e);
            }
        });

        drawPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (editRadioButton.isSelected()) { drawPanel.moveShape(e); }
            }
        });
        clearDrawingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                drawPanel.clearShapes();
                listModel.removeAllElements();
                listModel.addAll(drawPanel.getSelectedShapes());
            }
        });
        updateShapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                drawPanel.updateShapes(Integer.parseInt(new_shape_width.getText()), Integer.parseInt(new_shape_height.getText()));
            }
        });
    }

    /**
     * Do stuff based on selected options from menu bar.
     * Java Swing radioButtonGroups makes it so the user can only select 1 button, so we dont need to check/validate it further.
     */
    private void mousePressedAction(MouseEvent e) {
        // System.out.println(e.getX() + " " + e.getY());

        // Add if statement to check if we need to add a new shape here,
        if (addRadioButton.isSelected()) {
            //Add selected shape to panel, based on xy coords from mouse
            addShapeToPanel(e.getX(), e.getY());
        } else if (editRadioButton.isSelected()) {
            // Check if the values from the size inputs are valid and update selected shape
            if (validateFields()) {
                drawPanel.checkSelectShape(e.getX(), e.getY());
                listModel.removeAllElements();
                listModel.addAll(drawPanel.getSelectedShapes());
            }
        } else if (mergeRadioButton.isSelected()) {
            // 1: voeg toe aan lijst met shapes die we gaan mergen, 2: laat zien in de ui wat we willen mergen, 3: merge knop die het bij elkaar gooit
            combineShapes();
        }
        else if (removeRadioButton.isSelected()) {
            drawPanel.removeShape(e.getX(), e.getY());
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
        // Setting up listModel so we can see the selected shapes
        mergeShapeList.setModel(listModel);
    }

    /**
     * Get selected combobox values and check if xy has been filled, if true continue and add the shape to the JPanel
     * Shapes are as follows: 1 - Circle, 2 - Rectangle
     */
    private void addShapeToPanel(Integer mousePosX, Integer mousePosY) {
        System.out.println("Adding new shape: selected shape: '" + new_shape_combobox.getSelectedItem() + "' with values: x:" + new_shape_width.getText() + " y:" + new_shape_height.getText());

        if (validateFields()) {
            drawPanel.addShape(Objects.requireNonNull(new_shape_combobox.getSelectedItem()).toString(), mousePosX, mousePosY, Integer.parseInt(new_shape_width.getText()), Integer.parseInt(new_shape_height.getText()));
        }
    }

    /**
     * TODO: Spaghetti oplossing om even de fields te validaten, fix dit later even
     */
    private boolean validateFields() {
        try {
            if (new_shape_width.getText().length() != 0 && new_shape_height.getText().length() != 0) {
                Integer.parseInt(new_shape_width.getText());
                Integer.parseInt(new_shape_height.getText());
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Input validator returned false, check if the input is correct");
            return false;
        }
    }

    /**
     * Get get the 2 selected shapes and combine these.
     * TODO: implement
     */
    private void combineShapes() {
        System.out.println("Not implemented");
    }

}

