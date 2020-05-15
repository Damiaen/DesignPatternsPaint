package com.designpatterns.paint.base.Models.Shapes;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape {

    private Ellipse2D.Double circle;

    public Ellipse(Position position, double width, double height) {
        super(ShapeType.Ellipse,position, width, height);
    }

    @Override
    public boolean checkPosition(Position position) {
        return circle.contains(position.x, position.y);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        circle = new Ellipse2D.Double((this.getPosition().x - (this.getWidth() / 2.0D)), (this.getPosition().y - (this.getHeight() / 2.0D)), this.getWidth(), this.getHeight());

        g2d.setColor(Color.GREEN);
        g2d.fill(circle);
    }

    @Override
    public void drawContour(Graphics g, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        g2d.fillOval((int) (this.getPosition().x - (this.getWidth() / 2.0D) - 3), (int) (this.getPosition().y - (this.getHeight() / 2.0D) - 3), (int)this.getWidth() + 6, (int)this.getHeight() + 6);
    }
}
