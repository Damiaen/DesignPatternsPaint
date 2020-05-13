package com.designpatterns.paint.base.Models.Shapes.Figure;

import java.awt.*;

public class Shape {

    private int x, y, width, height;
    private final ShapeType type;

    public Shape(ShapeType type, int x, int y, int width, int height) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public final void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public final void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public final ShapeType getType() { return type; }

    public boolean checkPosition(int x, int y) {
        return false;
    }

    public void draw(Graphics g) {}

}
