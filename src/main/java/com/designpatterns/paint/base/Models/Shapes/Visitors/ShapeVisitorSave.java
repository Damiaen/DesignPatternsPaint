package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.File.SaveText;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

public class ShapeVisitorSave implements ShapeVisitor {

    public void export(Shape... args) {
        StringBuilder sb = new StringBuilder();
        for (Shape shape : args) {
            sb.append(shape.accept(this)).append("\n");
        }
        //Write the generated data to text
        SaveText.getInstance().save(sb.toString());
    }

    public String visitShape(Shape shape) {
        return shape.toString();
    }

    public String visitOrnamentDecorator(OrnamentDecorator ornamentDecorator) {
        return ornamentDecorator.toString();
    }

    public String visitCompositeShape(CompositeShape compositeShape) {
        StringBuilder sb = new StringBuilder();
        sb.append(compositeShape.getType()).append(" ").append(compositeShape.getCount()).append("\n");
        sb.append(_visitCompoundGraphic(compositeShape));

        return sb.toString();
    }

    private String _visitCompoundGraphic(CompositeShape compositeShape) {
        StringBuilder sb = new StringBuilder();
        for (Shape shape : compositeShape.getShapes()) {
            String obj = shape.accept(this);
            obj = "\t" + obj.replace("\n", "\n\t");
            sb.append(obj).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
