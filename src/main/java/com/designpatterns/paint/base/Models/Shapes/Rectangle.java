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

    public Integer[] position() {
        return new Integer[]{x, y};
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean checkPosition(int x, int y) {
        return rectangle.contains(x, y);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double rectangle = new Rectangle2D.Double(x, y, width, height);

        g2d.setColor(Color.RED);
        g2d.fill(rectangle);
    }

}
