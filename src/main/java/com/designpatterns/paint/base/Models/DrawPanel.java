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

    int cursorSelectedX, cursorSelectedY;

    public DrawPanel() {
        // set a preferred size for the custom panel.
        setPreferredSize(new Dimension(940,420));
        addMouseListener(new MouseListener());
        addMouseMotionListener(new MouseMotionListener());
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

    public void updateShape(int mousePosX, int mousePosY, int width, int height) {
        for (Object s : shapes) {
            if (s instanceof Ellipse) {
                if (((Ellipse) s).checkPosition(mousePosX, mousePosY)) {
                    System.out.println("updating selected shape");
                    ((Ellipse) s).setSize(width, height);
                    repaint();
                }
            }
            if (s instanceof Rectangle) {
                if (((Rectangle) s).checkPosition(mousePosX, mousePosY)) {
                    System.out.println("updating selected shape");
                    ((Rectangle) s).setSize(width, height);
                    repaint();
                }
            }
        }
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

    class MouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
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
    }

    class MouseMotionListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
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
    }

}
