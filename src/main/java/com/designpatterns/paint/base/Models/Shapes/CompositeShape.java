package com.designpatterns.paint.base.Models.Shapes;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitor;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * CompositeShape class
 */
public class CompositeShape extends BaseShape {

    /**
     * Shapes
     */
    private List<IShape> shapes = new ArrayList<>();

    /**
     * Count of all figures that are present in the group
     */
    private final Integer count;


    private Rectangle2D rectangle2D;

    boolean isSelected;

    /**
     * CompositeShape constructor
     */
    public CompositeShape(List<IShape> shapes, ShapeType type) {
        super(type,new Position(0,0),0,0);
        this.count = shapes.size();
        this.shapes = shapes;
        double[] bounds = getBounds();
        setPosition(new Position((int)bounds[0],(int)bounds[1]));
        setSize((int)bounds[2], (int)bounds[3]);
        System.out.println("width: " + getWidth() + " height: " + getHeight());
    }

    public List<IShape> getBaseShapes() {
        return shapes;
    }

    public Integer getCount() {
        return count;
    }

    // TODO: We moeten wel de bounds updaten ivm het kijken of een user iets select, weet niet of dit correct is, maar lijkt te werken
    public void updateBounds() {
        double[] bounds = getBounds();
        setPosition(new Position((int)bounds[0], (int)bounds[1]));
        setSize((int)bounds[2], (int)bounds[3]);
    }

    public double[] getBounds () {
        double[] bounds = new double[4];

        double minx = 9999;
        double miny = 9999;
        double maxx = 0;
        double maxy = 0;

        double newminx = 0;
        double newminxy = 0;
        double newmaxx = 0;
        double newmaxy = 0;
        for (IShape shape : shapes)
        {
            Position position = shape.getPosition();
            double width = shape.getWidth();
            double height = shape.getHeight();
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

    @Override
    public boolean checkPosition(Position position)
    {
        updateBounds();
        rectangle2D = new Rectangle2D.Double((this.getPosition().x), (this.getPosition().y), this.getWidth(), this.getHeight());
        return rectangle2D.contains(position.x,position.y);
    }

    @Override
    public void draw(Graphics g) {
        for (IShape shape : shapes) {
            shape.draw(g);
        }
    }

    @Override
    public void drawContour(Graphics g, Color color) {
        updateBounds();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        g2d.drawRect((int) (this.getPosition().x - 3), (int) (this.getPosition().y - 3), (int)this.getWidth() + 6, (int)this.getHeight() + 6);
    }

    @Override
    public void accept(ShapeVisitor v) {
        v.visitCompositeShape( this );
    }

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
    public void setMovingPosition(Position position, int mousePositionX, int mousePositionY) {
        for (IShape shape : getBaseShapes()) {
            Position pos = shape.getPosition();
            shape.setMovingPosition(pos, mousePositionX, mousePositionY);
        }
        updateBounds();
    }
}