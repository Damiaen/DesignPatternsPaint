package com.designpatterns.paint.base.Models;

import com.designpatterns.paint.base.Models.Shapes.Ellipse;
import com.designpatterns.paint.base.Models.Shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    // Temp for testing, store shapes here so we can call on them later
    private List<Object> shapes = new ArrayList<>();

    public DrawPanel() {
        // set a preferred size for the custom panel.
        setPreferredSize(new Dimension(940,420));
        setupListeners();
    }

    /**
     * Check what we need to draw and repaint the panel
     */
    private void setupListeners() {
        // Implement stuff
    }

    public List<Object> getShapes() {
        return shapes;
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
            if (s instanceof com.designpatterns.paint.base.Models.Shapes.Rectangle) {
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

}
