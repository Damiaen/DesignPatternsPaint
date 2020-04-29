package com.designpatterns.paint.base.Models.Shapes;
import com.designpatterns.paint.base.Models.Interfaces.IShapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse implements IShapes {

    int x, y, width, height;

    public Ellipse(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, width, height);

        g2d.setColor(Color.GRAY);
        g2d.fill(circle);
    }
}
