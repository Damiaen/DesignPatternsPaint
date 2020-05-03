package com.designpatterns.paint.base.Models.UserInterface;

import com.designpatterns.paint.base.Models.Shapes.Ellipse;
import com.designpatterns.paint.base.Models.Shapes.Figure.Figure;
import com.designpatterns.paint.base.Models.Shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    // Store shapes here, so we can call on them later
    private List<Object> shapes = new ArrayList<>();

    // Store selectedShape indexes here, so we can select multiple and loop through selected
    private List<Integer> selectedShapes = new ArrayList<>();

    int cursorSelectedX, cursorSelectedY;

    /**
     * Initiate and set up panel
     */
    public DrawPanel() {
        setPreferredSize(new Dimension(940,420));
        setBackground(new Color(255, 255, 255));
    }

    /**
     * Check what we need to draw and repaint the panel
     */
    public void addShape(String shapeName, int mousePosX, int mousePosY, int width, int height) {
        switch (shapeName) {
            case "Ellipse":
                shapes.add(new Ellipse(mousePosX, mousePosY, width, height));
                break;
            case "Rectangle":
                shapes.add(new Rectangle(mousePosX, mousePosY, width, height));
                break;
        }
        repaint();
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
     * Remove selected shape and remove it from the selected list
     */
    public void removeShape(int mousePosX, int mousePosY) {
        Object shape = getShapeByCoordinates(mousePosX, mousePosY);
        selectedShapes.remove(shapes.indexOf(shape));
        shapes.remove(shape);
        repaint();
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
     * get the selected shapes, so we can display these
     */
    public List<String> getSelectedShapes() {
        List<String> selectedShapesNames = new ArrayList<>();

        for (Integer i : selectedShapes) {
            selectedShapesNames.add("Index of selected shape:" + i);
        }
        return selectedShapesNames;
    }

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
    public void clearShapes() {
        shapes = new ArrayList<>();
        selectedShapes = new ArrayList<>();
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

    public void moveShape(MouseEvent e) {
        for (Object s : shapes) {
            if (s instanceof Ellipse) {
                if (((Ellipse) s).checkPosition(e.getX(), e.getY())) {
                    int cursorPosX = e.getX();
                    int cursorPosY = e.getY();

                    int originalX = ((Ellipse) s).getX();
                    int originalY = ((Ellipse) s).getY();

                    int newPosX = originalX + cursorPosX - cursorSelectedX;
                    int newPosY = originalY + cursorPosY - cursorSelectedY;

                    ((Ellipse) s).setPosition(newPosX, newPosY);

                    cursorSelectedX = cursorPosX;
                    cursorSelectedY = cursorPosY;
                    repaint();
                }
            }
            if (s instanceof com.designpatterns.paint.base.Models.Shapes.Rectangle) {
                if (((Rectangle) s).checkPosition(e.getX(), e.getY())) {
                    int cursorPosX = e.getX();
                    int cursorPosY = e.getY();

                    int originalX = ((Rectangle) s).getX();
                    int originalY = ((Rectangle) s).getY();

                    int newPosX = originalX + cursorPosX - cursorSelectedX;
                    int newPosY = originalY + cursorPosY - cursorSelectedY;

                    ((Rectangle) s).setPosition(newPosX, newPosY);

                    cursorSelectedX = cursorPosX;
                    cursorSelectedY = cursorPosY;
                    repaint();
                }
            }
        }
    }

//    class MouseListener extends MouseAdapter {
//        public void mousePressed(MouseEvent e) {
//            for (Object s : shapes) {
//                if (s instanceof Ellipse) {
//                    if (((Ellipse) s).checkPosition(e.getX(), e.getY())) {
//                        repaint();
//                        cursorSelectedX = e.getX();
//                        cursorSelectedY = e.getY();
//                    }
//                }
//                if (s instanceof Rectangle) {
//                    if (((Rectangle) s).checkPosition(e.getX(), e.getY())) {
//                        repaint();
//                        cursorSelectedX = e.getX();
//                        cursorSelectedY = e.getY();
//                    }
//                }
//            }
//        }
//    }
//
//    class MouseMotionListener extends MouseMotionAdapter {
//        public void mouseDragged(MouseEvent e) {
//            for (Object s : shapes) {
//                if (s instanceof Ellipse) {
//                    if (((Ellipse) s).checkPosition(e.getX(), e.getY())) {
//                        int cursorPosX = e.getX();
//                        int cursorPosY = e.getY();
//
//                        int originalX = ((Ellipse) s).getX();
//                        int originalY = ((Ellipse) s).getY();
//
//                        int newPosX = originalX + cursorPosX - cursorSelectedX;
//                        int newPosY = originalY + cursorPosY - cursorSelectedY;
//
//                        ((Ellipse) s).setPosition(newPosX, newPosY);
//
//                        cursorSelectedX = cursorPosX;
//                        cursorSelectedY = cursorPosY;
//                        repaint();
//                    }
//                }
//                if (s instanceof com.designpatterns.paint.base.Models.Shapes.Rectangle) {
//                    if (((Rectangle) s).checkPosition(e.getX(), e.getY())) {
//                        int cursorPosX = e.getX();
//                        int cursorPosY = e.getY();
//
//                        int originalX = ((Rectangle) s).getX();
//                        int originalY = ((Rectangle) s).getY();
//
//                        int newPosX = originalX + cursorPosX - cursorSelectedX;
//                        int newPosY = originalY + cursorPosY - cursorSelectedY;
//
//                        ((Rectangle) s).setPosition(newPosX, newPosY);
//
//                        cursorSelectedX = cursorPosX;
//                        cursorSelectedY = cursorPosY;
//                        repaint();
//                    }
//                }
//            }
//        }
//    }

}
