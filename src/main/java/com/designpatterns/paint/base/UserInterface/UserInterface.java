package com.designpatterns.paint.base.UserInterface;

import com.designpatterns.paint.base.Models.Actions.*;
import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Figure.Shape;
import com.designpatterns.paint.base.Models.Shapes.Figure.ShapeType;
import com.designpatterns.paint.base.Models.Vector2;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class UserInterface extends JFrame {
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

    private final DrawPanel drawPanel = new DrawPanel();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    /**
     * Main JFrame IU, everything in the view is stored here
     */
    private final JFrame userInterfaceFrame = new JFrame("Design Patterns Paint");

    public UserInterface(){
        // Create required elements for the JFrame
        createInterfaceElements();

        // Setup all the listeners that we require
        setupListeners();
    }

    /**
     * All of the listeners that we require
     */
    private void setupListeners() {
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedAction(e);
            }
        });
        undoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawPanel.invoker.undo();
            }
        });
        redoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawPanel.invoker.redo();
            }
        });
        clearDrawingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (drawPanel.getShapes().size() != 0) drawPanel.invoker.execute(new ClearDrawing(drawPanel));
            }
        });
        drawPanel.addMouseListener(new MouseAdapter() {
        MoveShape moveShape;
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (editRadioButton.isSelected())
                {
                    moveShape = new MoveShape(new Vector2(e.getX(),e.getY()),drawPanel.getShapeByCoordinates(e.getX(),e.getY()),drawPanel);
                    System.out.println("pressed");
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (editRadioButton.isSelected())
                {
                    moveShape.setNewPos(new Vector2(e.getX(),e.getY()));
                    drawPanel.invoker.execute(moveShape);
                    moveShape = null;
                    System.out.println("release");
                    repaint();
                }
            }
        });
        drawPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                if (editRadioButton.isSelected())
                {
                    drawPanel.moveShape(e.getX(),e.getY());
                }
            }
        });

        clearDrawingButton.addActionListener(actionEvent -> {
            drawPanel.clearShapes();
            updateSelectedList();
        });
        updateShapeButton.addActionListener(actionEvent -> drawPanel.updateShapes(Integer.parseInt(new_shape_width.getText()), Integer.parseInt(new_shape_height.getText())));
        mergeLayersButton.addActionListener(actionEvent -> combineShapes());

        // When the user switches mode we need to reset certain settings to prevent random shit from happening
        ActionListener listener = actionEvent -> {
            drawPanel.clearSelectedShapes();
            updateSelectedList();
        };
        addRadioButton.addActionListener(listener);
        editRadioButton.addActionListener(listener);
        removeRadioButton.addActionListener(listener);
    }

    /**
     * Do stuff based on selected options from menu bar.
     */
    private void mousePressedAction(MouseEvent e) {
        // Add if statement to check if we need to add a new shape here,
        if (addRadioButton.isSelected()) {
            //Add selected shape to panel, based on xy coords from mouse
            addShapeToPanel(e.getX(), e.getY());
        } else if (editRadioButton.isSelected()) {
            // Check if the values from the size inputs are valid and update selected shape
            if (validateFields()) {
                drawPanel.checkSelectShape(e.getX(), e.getY());
            }
        } else if (removeRadioButton.isSelected()) {
            drawPanel.removeSelectedShape(e.getX(), e.getY());
        }
        updateSelectedList();
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
     */
    private void addShapeToPanel(Integer mousePosX, Integer mousePosY) {
        System.out.println("Adding new shape: selected shape: '" + new_shape_combobox.getSelectedItem() + "' with values: x:" + new_shape_width.getText() + " y:" + new_shape_height.getText());

        if (validateFields()) {
            Shape shape = new Shape(ShapeType.valueOf(Objects.requireNonNull(new_shape_combobox.getSelectedItem()).toString()), mousePosX, mousePosY, Integer.parseInt(new_shape_width.getText()), Integer.parseInt(new_shape_height.getText()));
            drawPanel.invoker.execute(new AddShape(shape,drawPanel));
        }
    }

    /**
     * Validate if input is correct
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
     * Update the selected items/shapes list after doing an operation
     */
    private void updateSelectedList() {
        listModel.removeAllElements();
        listModel.addAll(drawPanel.getSelectedShapes());
    }

    /**
     * Get get the selected shapes and combine these into 1 layer
     * TODO: implement
     */
    private void combineShapes() {
        List<String> selectedShapes = drawPanel.getSelectedShapes();
        if (!selectedShapes.isEmpty()) {
            System.out.println("Should merge here, not implemented yet");
        } else {
            System.out.println("Shapes array empty, no shapes selected!");
        }
    }
}

