package com.designpatterns.paint.base.Models.Shapes.Shape;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitor;

import java.awt.*;

public interface IShape
{
    boolean checkPosition(Position position);
    void draw(Graphics g);
    void drawContour(Graphics g, Color color);
    void setSelected(boolean bool);
    boolean isSelected();
    int getWidth();
    int getHeight();
    void setSize(int newWidth, int newHeight);
    ShapeType getType();
    void setMovingPosition(Position mousePosition);
    void setPosition(Position position);
    Position getPosition();
    void accept(ShapeVisitor v);
    void moveShape(Position oldPos);
    void setMoving(boolean b);
    boolean isMoving();
}
