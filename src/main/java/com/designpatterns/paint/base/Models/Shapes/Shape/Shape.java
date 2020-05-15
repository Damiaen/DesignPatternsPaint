package com.designpatterns.paint.base.Models.Shapes.Shape;

import com.designpatterns.paint.base.Models.Position;

import java.awt.*;

public class Shape implements IShape {

    private double width, height;
    private Position position;
    private final ShapeType type;
    private boolean isSelected;
    private boolean isMoving;

    public Shape(ShapeType type, Position position, double width, double height) {
        this.type = type;
        this.position = position;
        this.width = width;
        this.height = height;
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
        return false;
    }

    public void draw(Graphics g) {}

    public void drawContour(Graphics g, Color color) {}

    public void setSelected(boolean bool) {
        isSelected = bool;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setMoving(boolean bool){isMoving = bool;}

    public boolean isMoving() {return isMoving;}

}
