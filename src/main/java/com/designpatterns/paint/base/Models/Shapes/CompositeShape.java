package com.designpatterns.paint.base.Models.Shapes;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.BoundingBox;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CompositeShape class
 */
public class CompositeShape extends Shape {

    /**
     * Shapes
     */
    private List<Shape> shapes = new ArrayList<>();

    /**
     * Count of all figures that are present in the group
     */
    private final Integer count;

    BoundingBox boundingBox;

    boolean isSelected;

    /**
     * CompositeShape constructor
     */
    public CompositeShape(List<Shape> shapes, ShapeType type, Position position, double width, double height) {
        super(type,position,width,height);
        this.count = shapes.size();
        this.shapes = shapes;
        double[] bounds = getBounds();
        boundingBox = new BoundingBox(bounds[0],bounds[1],bounds[2],bounds[3]);
        setPosition(new Position(bounds[0] - bounds[2],bounds[1] - bounds[3]));
        setSize(bounds[2], bounds[3]);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public Integer getCount() {
        return count;
    }

    // TODO: We moeten wel de bounds updaten ivm het kijken of een user iets select, weet niet of dit correct is, maar lijkt te werken
    public void updateBounds() {
        double[] bounds = getBounds();
        boundingBox = new BoundingBox(bounds[0],bounds[1],bounds[2],bounds[3]);
        setPosition(new Position(bounds[0] - bounds[2],bounds[1] - bounds[3]));
        setSize(bounds[2], bounds[3]);
    }

    public double[] getBounds () {
        double[] bounds = new double[4];

        bounds[0] = 0; // X
        bounds[1] = 0; // Y
        bounds[2] = 0; // width
        bounds[3] = 0; // height

        for (Shape shape : shapes)
        {
            Position position = shape.getPosition();
            if (position.x < bounds[0]) continue;
            bounds[0] = position.x;
            if (position.y < bounds[1]) continue;
            bounds[1] = position.y;
            if (shape.getWidth() < bounds[2]) continue;
            bounds[2] = shape.getWidth();
            if (shape.getHeight() < bounds[3]) continue;
            bounds[3] = shape.getHeight();
        }
        return bounds;
    }

    @Override
    public boolean checkPosition(Position position)
    {
        if (shapes.size() != count) {
            double[] bounds = getBounds();
            boundingBox = new BoundingBox(bounds[0],bounds[1],bounds[2],bounds[3]);
        }
        return boundingBox.Contains(position.x,position.y);
    }

    @Override
    public void draw(Graphics g) {
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    @Override
    public void drawContour(Graphics g, Color color) {
        for (Shape shape : shapes)
            shape.drawContour(g,color);
    }

    public void setSelected(boolean bool) {
        isSelected = bool;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
