package com.designpatterns.paint.base.Models.Shapes;
import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {

    private Rectangle2D.Double rectangle;

    public Rectangle(Position position, double width, double height) {
        super(ShapeType.Rectangle, position, width, height);
    }

    @Override
    public boolean checkPosition(Position position) {
        return rectangle.contains(position.x, position.y);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        rectangle = new Rectangle2D.Double((this.getPosition().x - (this.getWidth() / 2.0D)), (this.getPosition().y - (this.getHeight() / 2.0D)), this.getWidth(), this.getHeight());

        g2d.setColor(Color.RED);
        g2d.fill(rectangle);
    }

    @Override
    public void drawContour(Graphics g, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        g2d.fillRect((int) (this.getPosition().x - (this.getWidth() / 2.0D) - 3), (int) ((this.getPosition().y - (this.getHeight() / 2.0D)) - 3), (int)this.getWidth() + 6, (int)this.getHeight() + 6);
    }

}
