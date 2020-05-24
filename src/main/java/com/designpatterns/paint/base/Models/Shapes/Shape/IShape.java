package com.designpatterns.paint.base.Models.Shapes.Shape;

import com.designpatterns.paint.base.Models.Position;
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
    double getX();
    double getWidth();
    double getY();
    double getHeight();
    void setSize(int newWidth, int newHeight);
    ShapeType getType();
    void setPosition(Position position);
    Position getPosition();
    ArrayList<String> getSaveData();
    String accept(ShapeVisitor v);
}
