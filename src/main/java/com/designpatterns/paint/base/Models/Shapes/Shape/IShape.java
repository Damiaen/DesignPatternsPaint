package com.designpatterns.paint.base.Models.Shapes.Shape;

import com.designpatterns.paint.base.Models.Position;

import java.awt.*;

public interface IShape
{
    public boolean checkPosition(Position position);
    public void draw(Graphics g);
    public void drawContour(Graphics g, Color color);
    public void setSelected(boolean bool);
    public boolean isSelected();
}
