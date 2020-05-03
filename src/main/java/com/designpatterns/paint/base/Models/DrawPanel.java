package com.designpatterns.paint.base.Models;

import com.designpatterns.paint.base.CircleWithHandles;
import com.designpatterns.paint.base.Models.Shapes.Ellipse;
import com.designpatterns.paint.base.Models.Shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    // Temp for testing, store shapes here so we can call on them later
    private List<Object> shapes = new ArrayList<>();
    private List<Object> shapesHistory = new ArrayList<>();
    private int maxHistorySize = 10;

    int cursorSelectedX, cursorSelectedY;

    public DrawPanel() {
        // set a preferred size for the custom panel.
        setPreferredSize(new Dimension(940,420));
        setBackground(new Color(255, 255, 255));
//        addMouseListener(new MouseListener());
//        addMouseMotionListener(new MouseMotionListener());
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
     * Update selected shape
     * TODO: Laat ellipse en rectangle overerven van Shape, zodat we alleen instanceofshape hoeven aan te roepen, en daar op de size instellen.
     */
    public void updateShape(int mousePosX, int mousePosY, int width, int height) {
        Object selectedShape = getShapeByCoordinates(mousePosX, mousePosY);
        if (selectedShape instanceof Ellipse) {
                ((Ellipse) selectedShape).setSize(width, height);
                repaint();
        }
        if (selectedShape instanceof Rectangle) {
            ((Rectangle) selectedShape).setSize(width, height);
            repaint();
        }
    }

    /**
     * Remove selected shape
     */
    public void removeShape(int mousePosX, int mousePosY) {
        shapes.remove(getShapeByCoordinates(mousePosX, mousePosY));
        repaint();
    }

    /*
    remove the last added shape from shapes and add to history
     */
    public void undo ()
    {
        if (shapes.size() == 0){
            System.out.println("shapes list empty");
            return;
        }
        shapesHistory.add(shapes.size() - 1);
        if (shapesHistory.size() > maxHistorySize) {
            shapesHistory.remove(0);
        }
        shapes.remove(shapes.size() - 1);
        repaint();
    }
    /*
        get the last item from shapes history and add back to shapes
        TODO shape isn't drawn after redo
    */
    public void redo ()
    {
        if (shapesHistory.size() == 0){
            System.out.println("No history");
            return;
        }
        shapes.add(shapesHistory.get(shapesHistory.size() - 1));
        shapesHistory.remove(shapesHistory.size() -1);
        repaint();
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

    public void clickedShape(MouseEvent e) {
        for (Object s : shapes) {
            if (s instanceof Ellipse) {
                if (((Ellipse) s).checkPosition(e.getX(), e.getY())) {
                    repaint();
                    cursorSelectedX = e.getX();
                    cursorSelectedY = e.getY();
                }
            }
            if (s instanceof Rectangle) {
                if (((Rectangle) s).checkPosition(e.getX(), e.getY())) {
                    repaint();
                    cursorSelectedX = e.getX();
                    cursorSelectedY = e.getY();
                }
            }
        }
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
