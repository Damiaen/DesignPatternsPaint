package com.designpatterns.paint.base.Models.Shapes;
import com.designpatterns.paint.base.Models.Interfaces.IShapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle implements IShapes {

    int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double circle = new Rectangle2D.Double(x, y, width, height);

        g2d.setColor(Color.GRAY);
        g2d.fill(circle);
    }

}
