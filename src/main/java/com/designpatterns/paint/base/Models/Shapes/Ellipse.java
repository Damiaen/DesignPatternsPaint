package com.designpatterns.paint.base.Models.Shapes;

import com.designpatterns.paint.base.Models.Shapes.Figure.Shape;
import com.designpatterns.paint.base.Models.Shapes.Figure.ShapeType;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape {

    private Ellipse2D.Double circle;

    public Ellipse(int x, int y, int width, int height) {
        super(ShapeType.Ellipse, x, y, width, height);
    }

    @Override
    public boolean checkPosition(int x, int y) {
        return circle.contains(x, y);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        circle = new Ellipse2D.Double((this.getX() - (this.getWidth() / 2.0D)), (this.getY() - (this.getHeight() / 2.0D)), this.getWidth(), this.getHeight());

        g2d.setColor(Color.GREEN);
        g2d.fill(circle);
    }

    @Override
    public void drawContour(Graphics g, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        g2d.fillOval((int) (this.getX() - (this.getWidth() / 2.0D) - 3), (int) (this.getY() - (this.getHeight() / 2.0D) - 3), this.getWidth() + 6, this.getHeight() + 6);
    }
}
