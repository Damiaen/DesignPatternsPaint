package com.designpatterns.paint.base.Models.Shapes.Decorator;

import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

import java.awt.*;

public abstract class ShapeDecorator implements IShape {
    protected IShape decoratedShape;

    public ShapeDecorator(IShape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(Graphics2D g){
        decoratedShape.draw(g);
    }
}
