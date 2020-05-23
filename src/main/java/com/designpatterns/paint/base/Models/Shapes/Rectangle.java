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
}
