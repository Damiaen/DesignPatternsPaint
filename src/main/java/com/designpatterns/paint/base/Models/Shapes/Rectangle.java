package com.designpatterns.paint.base.Models.Shapes;
import com.designpatterns.paint.base.Models.Shapes.Figure.Shape;
import com.designpatterns.paint.base.Models.Shapes.Figure.ShapeType;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {

    private Rectangle2D.Double rectangle;

    public Rectangle(int x, int y, int width, int height) {
        super(ShapeType.Rectangle, x, y, width, height);
    }

    @Override
    public boolean checkPosition(int cursorX, int cursorY) {
        return rectangle.contains(cursorX, cursorY);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        rectangle = new Rectangle2D.Double((this.getX() - (this.getWidth() / 2.0D)), (this.getY() - (this.getHeight() / 2.0D)), this.getWidth(), this.getHeight());

        g2d.setColor(Color.RED);
        g2d.fill(rectangle);
    }

    @Override
    public void drawContour(Graphics g, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        g2d.fillRect((int) (this.getX() - (this.getWidth() / 2.0D) - 3), (int) ((this.getY() - (this.getHeight() / 2.0D)) - 3), this.getWidth() + 6, this.getHeight() + 6);
    }

}
