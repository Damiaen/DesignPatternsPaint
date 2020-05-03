package com.designpatterns.paint.base.Models.Shapes;
import com.designpatterns.paint.base.Models.Shapes.Figure.Figure;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Figure {

    Rectangle2D.Double rectangle;

    public Rectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean checkPosition(int cursorX, int cursorY) {
        return rectangle.contains(cursorX, cursorY);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        rectangle = new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        g2d.setColor(Color.RED);
        g2d.fill(rectangle);
    }

}
