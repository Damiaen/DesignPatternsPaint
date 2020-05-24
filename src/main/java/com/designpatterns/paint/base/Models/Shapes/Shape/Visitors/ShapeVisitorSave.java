package com.designpatterns.paint.base.Models.Shapes.Shape.Visitors;

import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;

public class ShapeVisitorSave implements ShapeVisitor {


    public String export(Shape... args) {
        StringBuilder sb = new StringBuilder();
        for (Shape shape : args) {
            sb.append(shape.accept(this)).append("\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String visitShape(Shape shape) {
        return shape.toString();
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
            obj = "    " + obj.replace("\n", "\n\t");
            sb.append(obj).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
