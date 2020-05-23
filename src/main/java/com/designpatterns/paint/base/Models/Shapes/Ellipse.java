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
}
