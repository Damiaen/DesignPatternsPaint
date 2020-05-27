package com.designpatterns.paint.base.UserInterface;

import com.designpatterns.paint.base.Models.Actions.*;
import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentPosition;
import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitorMove;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserInterface extends JFrame {
    private JPanel rootPanel;
    private JButton selectShapes;
    private JPanel content_panel;
    private JTextField new_shape_height;
    private JTextField new_shape_width;
    private JComboBox<String> new_shape_combobox;
    private JPanel draw_panel;
    private JRadioButton addRadioButton;
    private JRadioButton editRadioButton;
    private JButton clearDrawingButton;
    private JButton undoButton;
    private JButton redoButton;
    private JButton saveDrawingButton;
    private JRadioButton removeRadioButton;
    private JList<String> mergeShapeList;
    private JButton mergeLayersButton;
    private JButton updateShapeButton;
    private JButton loadDrawingButton;
    private JButton createScreenshotButton;
    private JComboBox ornamentPos;
    private JTextField ornamentText;
    private JButton ornamentButton;

    private final DrawPanel drawPanel = DrawPanel.getInstance();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    /**
     * Main JFrame IU, everything in the view is stored here
     */
    private final JFrame userInterfaceFrame = new JFrame("Design Patterns Paint");

    public UserInterface() {
        // Create required elements for the JFrame
        createInterfaceElements();

        // Setup all the listeners that we require
        setupListeners();
    }

    /**
     * All of the listeners that we require
     */
    private void setupListeners() {
        // Button listeners:
        // Button listeners:
        ornamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                drawPanel.addOrnament(OrnamentPosition.valueOf(Objects.requireNonNull(ornamentPos.getSelectedItem()).toString()), Objects.requireNonNull(ornamentText.getText()));
            }
        });
        clearDrawingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (drawPanel.getShapes().size() != 0) drawPanel.invoker.execute(new ClearDrawing(drawPanel));
            }
        });
        createScreenshotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    drawPanel.createScreenshot();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        clearDrawingButton.addActionListener(actionEvent -> {
            drawPanel.clearShapes();
            // Check if there have been any changes in the shapes list, update side menu list
            updateShapesOverviewList();
        });
        updateShapeButton.addActionListener(actionEvent -> {
            drawPanel.updateShapes(Integer.parseInt(new_shape_width.getText()), Integer.parseInt(new_shape_height.getText()));
        });
        mergeLayersButton.addActionListener(actionEvent -> combineShapes());
        saveDrawingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                drawPanel.saveDrawing();
            }
        });
        loadDrawingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                drawPanel.loadDrawing();
                updateShapesOverviewList();
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
        // ui element listeners
        mergeShapeList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawPanel.setSelectedMergeShapes(mergeShapeList.getSelectedIndices());
            }
        });

        // drawPanel specific listeners
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedAction(e);
            }
        });
        drawPanel.addMouseListener(new MouseAdapter() {
            MoveShape moveShape = null;

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (editRadioButton.isSelected()) {
                        IShape shape = drawPanel.getShapeByCoordinates(new Position(e.getX(), e.getY()));
                        if (shape != null && shape.isSelected()) {
                            System.out.println(shape);
                            drawPanel.checkIfSelectedShape(new Position(e.getX(), e.getY()));
                            moveShape = new MoveShape(new Position(e.getX(), e.getY()), shape, drawPanel);
                            shape.setMoving(true);
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (editRadioButton.isSelected() && moveShape != null) {
                        moveShape.setNewPos(new Position(e.getX(), e.getY()));
                        drawPanel.invoker.execute(moveShape);
                        moveShape = null;
                        repaint();
                    }
                }
            }
        });
        drawPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (editRadioButton.isSelected()) {
                        drawPanel.moveShape(new Position(e.getX(), e.getY()));
                    }
                }
            }
        });

        // When the user switches mode we need to reset certain settings to prevent random shit from happening
        ActionListener listener = actionEvent -> {
            drawPanel.clearSelectedShapes();
            // Check if there have been any changes in the shapes list, update side menu list
            updateShapesOverviewList();
            //Enable or disable certain menu items here
            if (addRadioButton.isSelected()) {
                mergeShapeList.setEnabled(false);
            } else if (editRadioButton.isSelected()) {
                mergeShapeList.setEnabled(true);
            } else if (removeRadioButton.isSelected()) {
                mergeShapeList.setEnabled(true);
            }
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
            // On right mouse button click check if we selected a shape, if we did it will get set to selected
            if (SwingUtilities.isRightMouseButton(e)) {
                drawPanel.checkIfSelectedShape(new Position(e.getX(), e.getY()));
            }
        } else if (removeRadioButton.isSelected()) {
            drawPanel.invoker.execute(new RemoveShape(new Position(e.getX(), e.getY()), drawPanel));
        }
        // Check if there have been any changes in the shapes list, update side menu list
        updateShapesOverviewList();
    }


    /**
     * Main function that contains all of the base UI settings
     */
    private void createInterfaceElements() {
        userInterfaceFrame.setContentPane(rootPanel);
        userInterfaceFrame.setSize(1280,800);
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
        // Setup which ui elements we need to disable on startup
        mergeShapeList.setEnabled(false);
    }

    /**
     * Get selected combobox values and check if xy has been filled, if true continue and add the shape to the JPanel
     */
    private void addShapeToPanel(int mousePosX, int mousePosY) {
        System.out.println("Adding new shape: selected shape: '" + new_shape_combobox.getSelectedItem() + "' with values: x:" + new_shape_width.getText() + " y:" + new_shape_height.getText());

        if (validateFields()) {
            BaseShape baseShape = new BaseShape(ShapeType.valueOf(Objects.requireNonNull(new_shape_combobox.getSelectedItem()).toString()),new Position(mousePosX,mousePosY), Integer.parseInt(new_shape_width.getText()), Integer.parseInt(new_shape_height.getText()));
            drawPanel.invoker.execute(new AddShape(baseShape,drawPanel));
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
     * Update the all the shapes that we have in the view
     */
    private void updateShapesOverviewList() {
        listModel.removeAllElements();
        for (String selectedShape : drawPanel.getAllShapesForView()) {
            listModel.addElement(selectedShape);
        }
    }

    /**
     * Get the selected shapes and combine these into 1 layer
     */
    //TODO: fix merging of new shape/composite shape and composite shape
    private void combineShapes()
    {
        List<IShape> selectedShapes = drawPanel.getSelectedShapes();
        List<IShape> checkedShapes = new ArrayList<>();
        for (IShape s : selectedShapes){
            if (s.getType() == ShapeType.CompositeShape)
            {
                System.out.println(s);
                CompositeShape cs = (CompositeShape) s;
                checkedShapes.addAll(cs.getBaseShapes());
            } else {
                checkedShapes.add(s);
            }
            System.out.println("dwadwaawd" + s);
        }

        System.out.println(checkedShapes);

        drawPanel.invoker.execute(new CombineShapes(checkedShapes));
        drawPanel.clearSelectedShapes();
        updateShapesOverviewList();
    }
}

