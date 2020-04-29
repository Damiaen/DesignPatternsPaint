package com.designpatterns.paint.base.Models.Shapes;
import com.designpatterns.paint.base.Models.Interfaces.IShapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle implements IShapes {

    int x, y, width, height;

    Rectangle2D.Double rectangle;

    public Rectangle(int x, int y, int width, int height) {
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

    public boolean checkPosition(int cursorX, int cursorY) {
        return rectangle.contains(cursorX, cursorY);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        rectangle = new Rectangle2D.Double(x, y, width, height);

        g2d.setColor(Color.RED);
        g2d.fill(rectangle);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
