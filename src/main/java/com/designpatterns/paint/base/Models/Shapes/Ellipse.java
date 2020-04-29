package com.designpatterns.paint.base.Models.Shapes;
import com.designpatterns.paint.base.Models.Interfaces.IShapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse implements IShapes {

    int x, y, width, height;

    Ellipse2D.Double circle;

    public Ellipse(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Integer[] position() {
        return new Integer[]{x, y};
    }

    public boolean checkPosition(int x, int y) {
        return circle.contains(x, y);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        circle = new Ellipse2D.Double(x, y, width, height);

        g2d.setColor(Color.GREEN);
        g2d.fill(circle);
    }
}
