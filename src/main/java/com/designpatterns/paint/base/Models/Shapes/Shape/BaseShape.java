package com.designpatterns.paint.base.Models.Shapes.Shape;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitorSave;
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

public class BaseShape implements IShape {

    private int width, height;
    private Position position;
    private final ShapeType type;
    private boolean isSelected;
    private boolean isMoving;

    private ShapeDrawContext shapeDrawContext;
    private ShapeContourContext shapeContourContext;
    private ShapeContainsContext shapeContainsContext;

    private DrawPanel drawPanel = DrawPanel.getInstance();

    public BaseShape(ShapeType type, Position position, int width, int height) {
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

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMovingPosition(int mousePositionX, int mousePositionY) {
        //this.position = new Position(
        //        mousePositionX + (mousePositionX - position.x),mousePositionY + (mousePositionY - position.y));
        this.position = (new Position(
                (position.x + mousePositionX) - drawPanel.getCursorSelectedPosition().x,
                (position.y + mousePositionY) - drawPanel.getCursorSelectedPosition().y)
        );
    }
  
    public void setPosition(Position position) {
        this.position = position;
    }

    public final Position getPosition() {
        return position;
    }

    public final int getWidth() {
        return width;
    }

    @Override
    public int getY() {
        return position.y;
    }

    public final int getHeight() {
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

    @Override
    public void setSelected(boolean bool) {
        isSelected = bool;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public int getX() {
        return position.x;
    }

    @Override
    public void accept(ShapeVisitor v) {
        v.visit( this );
    }

    @Override
    public void moveShape(Position oldPos) {
        this.position = oldPos;
    }

    @Override
    public void setMoving(boolean bool){isMoving = bool;}

    @Override
    public boolean isMoving() {return isMoving;}

    @Override
    public String toString() {
        return type + " " + position.x + " " + position.y + " " + height + " " + width;
    }

}
