package com.designpatterns.paint.base.Models.Shapes.Shape;

import java.awt.geom.Rectangle2D;

public class BoundingBox
{

    private Rectangle2D rectangle2D;

    double x;
    double y;
    double width;
    double heigth;

    public BoundingBox(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = height;
        rectangle2D = new Rectangle2D.Double(x,y,width,height);
    }

    public boolean Contains(double x, double y){
        return rectangle2D.contains(x,y);
    }


}
