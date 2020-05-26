package com.designpatterns.paint.base.Models.Shapes.Shape;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Visitors.SaveVisitor.ShapeVisitorSave;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitor;

import java.awt.*;
import java.util.ArrayList;

public interface IShape
{
    boolean checkPosition(Position position);
    void draw(Graphics g);
    void drawContour(Graphics g, Color color);
    void setSelected(boolean bool);
    boolean isSelected();
    int getX();
    int getWidth();
    int getY();
    int getHeight();
    void setSize(int newWidth, int newHeight);
    ShapeType getType();
    void setMovingPosition(Double mousePositionX, Double mousePositionY, Double cursorSelectedX, Double cursorSelectedY);
    void setPosition(Position position);
    Position getPosition();
    String acceptSave(ShapeVisitorSave v);
    void accept(ShapeVisitor v);
    void moveShape(Position oldPos);
    void setMoving(boolean b);
    boolean isMoving();
}
