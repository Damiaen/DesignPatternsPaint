package com.designpatterns.paint.base.Models;

import com.designpatterns.paint.base.Models.Actions.Action;
import com.designpatterns.paint.base.Models.Actions.ActionHistory;
import com.designpatterns.paint.base.Models.Actions.ActionType;
import com.designpatterns.paint.base.Models.Shapes.Ellipse;
import com.designpatterns.paint.base.Models.Shapes.Figure.Figure;
import com.designpatterns.paint.base.Models.Shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    ActionHistory actionHistory = ActionHistory.getInstance();

    // Store shapes here, so we can call on them later
    private List<Object> shapes = new ArrayList<>();
    private int maxHistorySize = 10;

    // Store selectedShape indexes here, so we can select multiple and loop through selected
    private List<Integer> selectedShapes = new ArrayList<>();

    int cursorSelectedX, cursorSelectedY;

    /**
     * Initiate and set up panel
     */
    public DrawPanel() {
        setPreferredSize(new Dimension(940, 420));
        setBackground(new Color(255, 255, 255));
    }

    /**
     * Check what we need to draw and repaint the panel
     */
    public void addShape(String shapeName, int mousePosX, int mousePosY, int width, int height) {
        Object shape;
        switch (shapeName) {
            case "Ellipse":
                shape = shapes.add(new Ellipse(mousePosX, mousePosY, width, height));
                ActionHistory.getInstance().addAction(new Action(ActionType.ADD,shape));
                break;
            case "Rectangle":
                shape = shapes.add(new Rectangle(mousePosX, mousePosY, width, height));
                ActionHistory.getInstance().addAction(new Action(ActionType.ADD,shape));
                break;
        }
        repaint();
        System.out.println(actionHistory.getIndex());
    }

    /**
     * Check if the user has selected a shape, if not reset the selectedShapes List
     */
    public void checkSelectShape(int mousePosX, int mousePosY) {
        if (!checkIfClickedShape(mousePosX, mousePosY)) {
            selectedShapes = new ArrayList<Integer>();
        }
    }

    /**
     * Update all shapes based on values in selected shapes array
     */
    public void updateShapes(int newWidth, int newHeight) {
        for (Integer selectedShapesIndex : selectedShapes) {
            if (shapes.get(selectedShapesIndex) instanceof Figure) {
                ((Figure) shapes.get(selectedShapesIndex)).setSize(newWidth, newHeight);
                repaint();
            }
        }
    }

    /**
     * Remove selected shape and check if it was selected, if so remove it from the selected list
     */
    public void removeShape(int mousePosX, int mousePosY) {
        Object shape = getShapeByCoordinates(mousePosX, mousePosY);
        if (shape != null) {
            if (!selectedShapes.isEmpty() && selectedShapes.contains(shapes.indexOf(shape))) {
                selectedShapes.remove(shapes.indexOf(shape));
            }
            repaint();
        }
    }

    /**
     * add shape to the selected shape index, check for duplicates here.
     */
    public void setSelectedShapes(int selectedShapeIndex) {
        if (!selectedShapes.contains(selectedShapeIndex)) {
            selectedShapes.add(selectedShapeIndex);
        }
        System.out.println("Selected shape indexes: " + selectedShapes);
    }

    /**
     * undo the last action
     */
    public void undo ()
    {
        List<Action> history = actionHistory.getHistory();
        if (history.size() == 0 || actionHistory.getIndex() == -1) return;
        Action action = actionHistory.getLastAction();
        if (action == null) return;
        switch (action.getType()){
            case ADD:
                if (shapes.size() > 0) shapes.remove(shapes.size() - 1);
                break;
            case REMOVE:
                Object object = action.getObject();
                if (object != null) shapes.add(object);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action.getType());
        }
        repaint();
        ActionHistory.getInstance().undo();
        System.out.println(actionHistory.getIndex());
    }

    /**
     *  redo the action that is undone
    */
    public void redo ()
    {
        List<Action> history = actionHistory.getHistory();
        if (actionHistory.getIndex() == history.size() -1) return;
        ActionHistory.getInstance().redo();
        Action action = actionHistory.getLastAction();
        switch (action.getType()){
            case ADD:
                System.out.println(action.getObject());
                shapes.add(action.getObject());
                break;
            case REMOVE:
                if (shapes.size() > 0) shapes.remove(action.getObject());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action.getType());
        }
        repaint();
        System.out.println(actionHistory.getIndex());
    }

    /**
     * get the selected shapes, so we can display these
     */
    public List<String> getSelectedShapes() {
        List<String> selectedShapesNames = new ArrayList<>();

        for (Integer i : selectedShapes) {
            selectedShapesNames.add("Index of selected shape: " + i);
        }
        return selectedShapesNames;
    }

    /**
     * Get which shape is on which coordinate
     */
    private Object getShapeByCoordinates(int mousePosX, int mousePosY) {
        for (Object s : shapes) {
            if (s instanceof Ellipse) {
                if (((Ellipse) s).checkPosition(mousePosX, mousePosY)) {
                    return s;
                }
            }
            if (s instanceof Rectangle) {
                if (((Rectangle) s).checkPosition(mousePosX, mousePosY)) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * Remove all the shapes from the ui by resetting the object list and then repainting
     */
    public void clearSelectedShapes() {
        selectedShapes = new ArrayList<>();
    }

    /**
     * Remove all the shapes from the ui by resetting the object list and then repainting
     */
    public void clearShapes() {
        shapes = new ArrayList<>();
        selectedShapes = new ArrayList<>();
        repaint();
    }

    /**
     * Check if user clicked on a shape, returns bool
     */
    public boolean checkIfClickedShape(int mousePosX, int mousePosY) {
        for (Object s : shapes) {
            if (s instanceof Ellipse) {
                if (((Ellipse) s).checkPosition(mousePosX, mousePosY)) {
                    setSelectedShapes(shapes.indexOf(s));
                    repaint();
                    cursorSelectedX = mousePosX;
                    cursorSelectedY = mousePosY;
                    return true;
                }
            }
            if (s instanceof Rectangle) {
                if (((Rectangle) s).checkPosition(mousePosX, mousePosY)) {
                    setSelectedShapes(shapes.indexOf(s));
                    repaint();
                    cursorSelectedX = mousePosX;
                    cursorSelectedY = mousePosY;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check which shape has been selected and move it
     */
    public void moveShape(MouseEvent e) {
        for (Object s : shapes) {
            if (s instanceof Ellipse) {
                if (((Ellipse) s).checkPosition(e.getX(), e.getY())) {
                    ((Ellipse) s).setPosition(
                            (((Ellipse) s).getX() + e.getX() - cursorSelectedX),
                            (((Ellipse) s).getY() + e.getY() - cursorSelectedY)
                    );
                }
            }
            if (s instanceof Rectangle) {
                if (((Rectangle) s).checkPosition(e.getX(), e.getY())) {
                    ((Rectangle) s).setPosition(
                            (((Rectangle) s).getX() + e.getX() - cursorSelectedX),
                            (((Rectangle) s).getY() + e.getY() - cursorSelectedY)
                    );
                }
            }
        }

        cursorSelectedX = e.getX();
        cursorSelectedY = e.getY();
        repaint();
    }

    /**
     * Override the paintComponent method so we can use our own code to draw shit
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Object s : shapes) {
            if (s instanceof Ellipse) {
                ((Ellipse) s).draw(g);
            }
            if (s instanceof Rectangle) {
                ((Rectangle) s).draw(g);
            }
        }
    }

}
