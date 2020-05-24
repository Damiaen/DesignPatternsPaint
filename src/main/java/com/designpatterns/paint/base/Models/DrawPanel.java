package com.designpatterns.paint.base.Models;

import com.designpatterns.paint.base.Models.Actions.Invoker;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;
import com.designpatterns.paint.base.Models.Shapes.Shape.Visitors.ShapeVisitorSave;

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
    private List<CompositeShape> groups = new ArrayList<>();

    // Store which shapes the user has selected in edit mode
    private List<Integer> selectedShapes = new ArrayList<>();

    // Store which shapes have been selected for merging, select these from the side-menu
    private List<Integer> selectedMergeShapes = new ArrayList<>();

    double cursorSelectedX, cursorSelectedY;

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
    public Shape addShape(ShapeType type, Position position, double width, double height) {
        Shape shape = null;
        switch (type) {
            case Ellipse:
                shape = new Shape(ShapeType.Ellipse, position, width, height);
                break;
            case Ornament:
                break;
            case Rectangle:
                shape = new Shape(ShapeType.Rectangle, position, width, height);
                break;
        }
        shapes.add(shape);
        repaint();
        return shape;
    }
    public void addShape(Shape shape){
        shapes.add(shape);
    }


    /**
     * Remove shape based from the shapes list
     */
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        repaint();
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
     */
    public void removeSelectedShape(Position position) {
        Shape shape = getShapeByCoordinates(position);
        if (shape != null) {
            if (!selectedShapes.isEmpty() && selectedShapes.contains(shapes.indexOf(shape))) {
                selectedShapes.remove(shapes.indexOf(shape));
                shape.setSelected(false);
            }
            shape.setSelected(false);
            shapes.remove(shape);
            repaint();
        }
    }

    /**
     * Loop through selected shapes from paint UI and side menu
     */
    public List<Shape> getSelectedShapes() {
        List<Integer> selectedShapesIndices = new ArrayList<>(selectedShapes);
        selectedShapesIndices.addAll(selectedMergeShapes);
        System.out.println("Selected shapes indices: " + selectedShapesIndices);

        List<Shape> selectedShapes = new ArrayList<>();

        // Loop and add shapes to list, also set selected to false to prevent nested selected errors
        for (Integer i: selectedShapesIndices) {
            shapes.get(i).setSelected(false);
            selectedShapes.add(shapes.get(i));
        }
        return selectedShapes;
    }

    public void createScreenshot() throws IOException {
        BufferedImage bufImage = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_RGB);
        this.paint(bufImage.createGraphics());
        SaveScreenshot.getInstance().store(bufImage);
    }

    /**
     * Add the selected shapes from the merge tab to the selectedMergeShapes.
     * CLear the list before we fill, due to how JList works
     */
    public void setSelectedMergeShapes(int[] shapeIndices) {
        selectedMergeShapes.clear();
        for (Integer i: shapeIndices) {
            selectedShapes.remove(i);
            selectedMergeShapes.add(i);
            shapes.get(i).setSelected(true);
        }
        repaint();
    }

    /**
     * add shape to the selected shape index, check for duplicates here.
     */
    public void setSelectedShapes(int shapeIndex) {
        if (!selectedShapes.contains(shapeIndex) && !selectedMergeShapes.contains(shapeIndex)) {
            selectedShapes.add(shapeIndex);
            shapes.get(shapeIndex).setSelected(true);
        } else if(!selectedShapes.contains(shapeIndex) && selectedMergeShapes.contains(shapeIndex)) {
            selectedMergeShapes.remove(shapeIndex);
            selectedShapes.add(shapeIndex);
            shapes.get(shapeIndex).setSelected(true);
        }
        repaint();
    }

    /**
     * Get all shapes/groups that we have, so we can display these
     */
    public List<String> getAllShapesForView() {
        List<String> drawingObjectsNames = new ArrayList<>();

        for (Shape s : shapes) {
            drawingObjectsNames.add(s.getType().name() + " on position x: " + s.getPosition().x + " and y: " + s.getPosition().y);
        }
        repaint();
        return drawingObjectsNames;
    }

    /**
     * Get which shape is on which coordinate
     * TODO: hier gaat shit fout, denk dat ie niet goed om kan gaan met de boundaries van de groups
     */
    public Shape getShapeByCoordinates(Position position) {
        for (Shape s : shapes)
        {
            if (s.checkPosition(position))
            {
                return s;
            }
        }
        return null;
    }

    /**
     * Remove all the shapes from the ui by resetting the object list and then repainting
     */
    public void clearSelectedShapes() {
        // Reset lists
        selectedShapes = new ArrayList<>();
        selectedMergeShapes = new ArrayList<>();
        // Set all to false
        for (Shape shape: shapes) {
            shape.setSelected(false);
        }
        repaint();
    }

    /**
     * Remove all the shapes from the ui by resetting the object list and then repainting
     */
    public void clearShapes() {
        shapes = new ArrayList<>();
        clearSelectedShapes();
    }

    /**
     * Save the drawings data to json
     */
    public void saveDrawing() {
        // Check if not empty
        if (!shapes.isEmpty()) {
            // Create master group
            CompositeShape shape = new CompositeShape(shapes, ShapeType.CompositeShape,new Position(0,0),0,0);
            // User visitor pattern to get all the shape data
            ShapeVisitorSave saveVisitor = new ShapeVisitorSave();
            saveVisitor.export(shape);
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
     * Check if the user has selected a shape in the drawPanel view, if false we reset the selected lists
     */
    public void checkIfSelectedShape(Position position) {
        if (!checkIfClickedShape(position)) {
            clearSelectedShapes();
        }
    }

    /**
     * Loop through all shapes and return true if one of the shapes contains the mouse coordinates
     */
    public boolean checkIfClickedShape(Position position) {
        for (Shape s : shapes) {
            if (s.checkPosition(position)) {
                setSelectedShapes(shapes.indexOf(s));
                s.setSelected(true);
                cursorSelectedX = position.x;
                cursorSelectedY = position.y;
                repaint();
                return true;
            }
        }
        return false;
    }

    /**
     * Check which shape has been selected and move it
     */
    public void moveShape(Position mousePosition)
    {
        Shape s = getShapeByCoordinates(mousePosition);
        if (s == null) return;
        if (!s.isSelected()) return;
        if (s instanceof CompositeShape) {
            for (Shape shape: ((CompositeShape) s).getShapes()) {
                Position position = shape.getPosition();
                shape.setPosition(new Position(
                        (position.x + mousePosition.x) - cursorSelectedX,
                        (position.y + mousePosition.y) - cursorSelectedY)
                );
            }
            ((CompositeShape) s).updateBounds();
        } else {
            Position position = s.getPosition();
            s.setPosition(new Position(
                    (position.x + mousePosition.x) - cursorSelectedX,
                    (position.y + mousePosition.y) - cursorSelectedY)
            );
        }
        cursorSelectedX = mousePosition.x;
        cursorSelectedY = mousePosition.y;
        repaint();
    }

    public void moveShapeBack(Shape shape, Position position) {
        if (shape != null) {
            shape.setPosition(position);
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
            if (selectedShapes.contains(shapes.indexOf(s))) {
                s.drawContour(g, Color.darkGray);
            } else if(selectedMergeShapes.contains(shapes.indexOf(s))) {
                s.drawContour(g, Color.blue);
            }
            s.draw(g);
        }
    }

}
