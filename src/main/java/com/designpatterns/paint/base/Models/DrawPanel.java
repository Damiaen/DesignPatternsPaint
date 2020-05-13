package com.designpatterns.paint.base.Models;

import com.designpatterns.paint.base.Models.Actions.Invoker;
import com.designpatterns.paint.base.Models.Shapes.Ellipse;
import com.designpatterns.paint.base.Models.Shapes.Figure.Shape;
import com.designpatterns.paint.base.Models.Shapes.Figure.ShapeType;
import com.designpatterns.paint.base.Models.Shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    private static DrawPanel drawPanel;

    // Store shapes here, so we can call on them later
    private List<Shape> shapes = new ArrayList<>();

    // Store which shapes the user has selected in edit mode
    private List<Integer> selectedShapes = new ArrayList<>();

    // Store which shapes have been selected for merging, select these from the side-menu
    private List<Integer> selectedMergeShapes = new ArrayList<>();

    int cursorSelectedX, cursorSelectedY;

    public final Invoker invoker = new Invoker(); // undo redo of all commands

    /**
     * Initiate and set up panel
     */
    public DrawPanel() {
        setPreferredSize(new Dimension(940, 420));
        setBackground(new Color(255, 255, 255));
    }

    public static DrawPanel getInstance() {
        if (drawPanel == null) drawPanel = new DrawPanel();
        return drawPanel;
    }

    /**
     * Check what we need to draw and repaint the panel
     */
    public Shape addShape(ShapeType type, int mousePosX, int mousePosY, int width, int height)
    {
        Shape shape = null;
        switch (type) {
            case Ellipse:
                shape = new Ellipse(mousePosX, mousePosY, width, height);
                break;
            case Ornament:
                break;
            case Rectangle:
                shape = new Rectangle(mousePosX, mousePosY, width, height);
                break;
        }
        shapes.add(shape);
        System.out.println(shapes.get(shapes.size()-1));
        repaint();
        return shape;
    }

    /**
     * Remove shape based from the shapes list
     */
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        repaint();
    }

    /**
     * Check if the user has selected a shape in the drawPanel view, if false we reset the selected lists
     */
    public void checkIfSelectedShape(int mousePosX, int mousePosY) {
        if (!checkIfClickedShape(mousePosX, mousePosY)) {
            clearSelectedShapes();
        }
    }

    /**
     * Update all shapes based on values in selected shapes array
     */
    public void updateShapes(int newWidth, int newHeight) {
        for (Integer selectedShapesIndex : selectedShapes) {
            if (shapes.get(selectedShapesIndex) != null) {
                shapes.get(selectedShapesIndex).setSize(newWidth, newHeight);
                repaint();
            }
        }
    }

    /**
     * Remove selected shape and check if it was selected, if so remove it from the selected list
     * Action: Delete
     */
    public void removeSelectedShape(int mousePosX, int mousePosY) {
        Object shape = getShapeByCoordinates(mousePosX, mousePosY);
        if (shape != null) {
            if (!selectedShapes.isEmpty() && selectedShapes.contains(shapes.indexOf(shape))) {
                selectedShapes.remove(shapes.indexOf(shape));
            }
            shapes.remove(shape);
            repaint();
        }
    }

    public void mergeLayers() {
        // Shapes selected from the side-menu
        System.out.println("SelectedMergeShapes: " + selectedMergeShapes);
        // Shapes selected from the drawPanel view
        System.out.println("SelectedShapes: " + selectedShapes);
    }

    public void createScreenshot() throws IOException {
        BufferedImage bufImage = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_RGB);
        this.paint(bufImage.createGraphics());
        SaveScreenshot.getInstance().store(bufImage);
    }

    /**
     * Add the selected shapes form the merge tab to the selectedMergeShapes.
     * CLear the list before we fill, due to how JList works
     */
    public void setSelectedMergeShapes(int[] shapeIndices) {
        selectedMergeShapes.clear();
        for(Integer i: shapeIndices) {
            selectedShapes.remove(i);
            selectedMergeShapes.add(i);
        }
        repaint();
    }

    /**
     * add shape to the selected shape index, check for duplicates here.
     */
    public void setSelectedShapes(int shapeIndex) {
        if (!selectedShapes.contains(shapeIndex) && !selectedMergeShapes.contains(shapeIndex)) {
            selectedShapes.add(shapeIndex);
        } else if(!selectedShapes.contains(shapeIndex) && selectedMergeShapes.contains(shapeIndex)) {
            selectedMergeShapes.remove(shapeIndex);
            selectedShapes.add(shapeIndex);
        }
        repaint();
    }

    /**
     * Get all shapes/groups that we have, so we can display these
     */
    public List<String> getAllShapesForView() {
        List<String> drawingObjectsNames = new ArrayList<>();

        for (Shape s : shapes) {
            drawingObjectsNames.add(s.getType().name() + " on position x: " + s.getX() + " and y: " + s.getY());
        }
        repaint();
        return drawingObjectsNames;
    }

    /**
     * Get which shape is on which coordinate
     */
    public Shape getShapeByCoordinates(int mousePosX, int mousePosY) {
        for (Shape s : shapes) {
            if (s.checkPosition(mousePosX, mousePosY)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Remove all the shapes from the ui by resetting the object list and then repainting
     */
    public void clearSelectedShapes() {
        selectedShapes = new ArrayList<>();
        selectedMergeShapes = new ArrayList<>();
        repaint();
    }

    /**
     * Remove all the shapes from the ui by resetting the object list and then repainting
     */
    public void clearShapes() {
        shapes = new ArrayList<>();
        selectedShapes = new ArrayList<>();
        selectedMergeShapes = new ArrayList<>();
        repaint();
    }

    /**
     * Save the drawings data to json
     */
    public void saveDrawing() {
        if (!shapes.isEmpty()) {
            SaveFile.getInstance().save(shapes);
        }
    }

    /**
     * Load saved drawing data, if none selected we do nothing
     */
    public void loadDrawing() {
        List<Shape> loadedShapes = LoadFile.getInstance().load();
        if (loadedShapes != null) {
            shapes = loadedShapes;
            repaint();
        }
    }

    /**
     * Check if user clicked on a shape, returns bool
     */
    public boolean checkIfClickedShape(int mousePosX, int mousePosY) {
        for (Shape s : shapes) {
            if (s.checkPosition(mousePosX, mousePosY)) {
                setSelectedShapes(shapes.indexOf(s));
                repaint();
                cursorSelectedX = mousePosX;
                cursorSelectedY = mousePosY;
                return true;
            }
        }
        return false;
    }


    /**
     * Check which shape has been selected and move it
     */
    public void moveShape(int mouseX, int mouseY) {
        Shape s = getShapeByCoordinates(mouseX, mouseY);
        s.setPosition(
                (s.getX() + mouseX) - cursorSelectedX,
                (s.getY() + mouseY) - cursorSelectedY
        );
        cursorSelectedX = mouseX;
        cursorSelectedY = mouseY;
        repaint();
    }

    public void moveShapeBack(Shape shape, int x,int y)
    {
        if (shape != null) {
            shape.setPosition(x,y);
            repaint();
        }
    }

    public List<Shape> getShapes(){
        return new ArrayList<>(shapes);
    }


    /**
     * Override the paintComponent method so we can use our own code to draw shit
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape s : shapes) {
            System.out.println(selectedMergeShapes);
            if (selectedShapes.contains(shapes.indexOf(s))) {
                s.drawContour(g, Color.darkGray);
            } else if(selectedMergeShapes.contains(shapes.indexOf(s))) {
                s.drawContour(g, Color.blue);
            }
            s.draw(g);
        }
    }

}
