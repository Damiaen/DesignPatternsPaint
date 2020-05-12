package com.designpatterns.paint.base.Models.Shapes.Figure;

import java.awt.*;

public class Shape {

    private int x, y, width, height;
    private ShapeType type;

    public Shape(ShapeType type, int x, int y, int width, int height) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ShapeType getType() { return type; }

    public boolean checkPosition(int x, int y) {
        return false;
    }

    public void draw(Graphics g) {}

}
