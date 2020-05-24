package com.designpatterns.paint.base.Models.Shapes.Shape;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitor;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy.EllipseContour;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy.RectangleContour;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy.ShapeDrawContext;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy.drawEllipse;
import com.designpatterns.paint.base.Models.Shapes.Strategies.DrawShapeStrategy.drawRectangle;
import  com.designpatterns.paint.base.Models.Shapes.Strategies.DrawContourStrategy.ShapeContourContext;
import com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy.EllipseContains;
import com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy.RectangleContains;
import com.designpatterns.paint.base.Models.Shapes.Strategies.ContainsStrategy.ShapeContainsContext;

import java.awt.*;

public class Shape implements IShape {

    private double width, height;
    private Position position;
    private final ShapeType type;
    private boolean isSelected;
    private boolean isMoving;

    private ShapeDrawContext shapeDrawContext;
    private ShapeContourContext shapeContourContext;
    private ShapeContainsContext shapeContainsContext;

    public Shape(ShapeType type, Position position, double width, double height) {
        this.type = type;
        this.position = position;
        this.width = width;
        this.height = height;

        // Bind context to shape
        if (type.equals(ShapeType.Ellipse)) {
            shapeDrawContext = new ShapeDrawContext(new drawEllipse());
            shapeContourContext = new ShapeContourContext(new EllipseContour());
            shapeContainsContext = new ShapeContainsContext(new EllipseContains());
        } else if (type.equals(ShapeType.Rectangle)) {
            shapeDrawContext = new ShapeDrawContext(new drawRectangle());
            shapeContourContext = new ShapeContourContext(new RectangleContour());
            shapeContainsContext = new ShapeContainsContext(new RectangleContains());
        }
    }

    public final void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public final void setPosition(Position position) {
        this.position = position;
    }

    public final Position getPosition() {
        return position;
    }

    public final double getWidth() {
        return width;
    }

    public final double getHeight() {
        return height;
    }

    public final ShapeType getType() { return type; }

    public boolean checkPosition(Position position) {
        return shapeContainsContext.executeShapeDrawStrategy(this, position.x, position.y);
    }

    public void draw(Graphics g) {
        shapeDrawContext.executeShapeDrawStrategy(g, this, Color.GREEN);
    }

    public void drawContour(Graphics g, Color color) {
        shapeContourContext.executeShapeDrawStrategy(g, this, color);
    }

    public void setSelected(boolean bool) {
        isSelected = bool;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public String accept(ShapeVisitor v) {
        return v.visitShape( this );
    }

    public void setMoving(boolean bool){isMoving = bool;}

    public boolean isMoving() {return isMoving;}

    @Override
    public String toString() {
        return type + " " + position.x + " " + position.y + " " + height + " " + width;
    }

    public void moveShape(Position position){
        this.position = position;
    }

}
