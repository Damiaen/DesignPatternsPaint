package com.designpatterns.paint.base.Models.Shapes;

import com.designpatterns.paint.base.Models.Shapes.Figure.Figure;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure {

    private Ellipse2D.Double circle;

    public Ellipse(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean checkPosition(int x, int y) {
        return circle.contains(x, y);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        circle = new Ellipse2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        g2d.setColor(Color.GREEN);
        g2d.fill(circle);
    }
}
