package com.designpatterns.paint.base.Models.Shapes;

import com.designpatterns.paint.base.Models.Commands.Reshape;
import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitor;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * CompositeShape class is used to store multiple shapes and control them as a group object
 * The composite shape is used to implement the composite pattern
 */
public class CompositeShape extends BaseShape {

    /**
     * Shapes
     */
    private final List<IShape> shapes;

    /**
     * Count of all figures that are present in the group
     */
    private final Integer count;

    /**
     * Used to check if a position is inside of the rectangle
     */
    private Rectangle2D rectangle2D;

    /**
     * CompositeShape constructor
     */
    public CompositeShape(List<IShape> shapes, ShapeType type) {
        super(type,new Position(0,0),0,0);
        this.count = shapes.size();
        this.shapes = shapes;
        int[] bounds = getBounds();
        setPosition(new Position((int)bounds[0],(int)bounds[1]));
        setWidth(bounds[2]);
        setHeight(bounds[3]);
        System.out.println("width: " + getWidth() + " height: " + getHeight());
    }

    /**
     * Get all the shapes which are inside the group
     * @return shapes from within this group
     */
    public List<IShape> getBaseShapes() {
        return shapes;
    }

    /**
     * get count
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * update the bounds of the group
     */
    public void updateBounds() {
        int[] bounds = getBounds();
        setPosition(new Position(bounds[0], bounds[1]));
        setWidth(bounds[2]);
        setHeight(bounds[3]);
    }

    /**
     * calculates the bounds of the group
     * @return int[]
     */
    public int[] getBounds () {
        int[] bounds = new int[4];

        int minx = 9999;
        int miny = 9999;
        int maxx = 0;
        int maxy = 0;

        int newminx = 0;
        int newminxy = 0;
        int newmaxx = 0;
        int newmaxy = 0;
        for (IShape shape : shapes)
        {
            Position position = shape.getPosition();
            int width = shape.getWidth();
            int height = shape.getHeight();
            if (position.x < minx) {
                minx = position.x;
                newminx = position.x - width / 2;
            }
            if (position.y < miny) {
                miny = position.y;
                newminxy = position.y - height / 2;
            }
            if (position.x > maxx)
            {
                maxx = position.x;
                newmaxx = position.x + width / 2;
            }
            if (position.y > maxy)
            {
                maxy = position.y;
                newmaxy = position.y + height / 2;
            }

        }
        bounds[0] = newminx;
        bounds[1] = newminxy;
        bounds[2] = newmaxx - newminx;
        bounds[3] = newmaxy - newminxy;

        return bounds;
    }

    /**
     * check if position is inside
     * @param position Position
     * @return boolean
     */
    @Override
    public boolean checkPosition(Position position)
    {
        updateBounds();
        rectangle2D = new Rectangle2D.Double((this.getPosition().x), (this.getPosition().y), this.getWidth(), this.getHeight());
        return rectangle2D.contains(position.x,position.y);
    }

    /**
     * draw graphics
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
        for (IShape shape : shapes) {
            shape.draw(g);
        }
    }

    /**
     * draw contour
     * @param g Graphics
     * @param color color
     */
    @Override
    public void drawContour(Graphics g, Color color) {
        updateBounds();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        g2d.drawRect(this.getPosition().x - 3, this.getPosition().y - 3, this.getWidth() + 6, this.getHeight() + 6);
    }

    /**
     * accept visitor
     * @param v ShapeVisitor
     */
    @Override
    public void accept(ShapeVisitor v) {
        v.visit( this );
    }

    /**
     * set size
     * @param width int
     * @param height int
     */
    @Override
    public void setSize(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
        for (IShape shape : getBaseShapes()) {
            Reshape reshape = new Reshape(shape, width, height);
            DrawPanel.getInstance().invoker.execute(reshape);
        }
        updateBounds();
    }

    /**
     * to string
     * @return string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getType()).append(" ").append(getCount()).append("\n");

        for (IShape shape : shapes) {
            String[] splitLine = shape.toString().split("\n");
            for (String split: splitLine) {
                // System.out.println("split:" + split);
                stringBuilder.append("\t").append(split).append("\n");
            }
        }

        if ((Character.compare(stringBuilder.charAt(stringBuilder.length() - 1), '\t')) == 1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    @Override
    public void setMovingPosition(Position mousePosition) {
        for (IShape shape : getBaseShapes()) {
            shape.setMovingPosition(mousePosition);
        }
        updateBounds();
    }
}