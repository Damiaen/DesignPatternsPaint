package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.File.SaveText;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class ShapeVisitorSave implements ShapeVisitor {

    StringBuilder master = new StringBuilder();

    public void export(CompositeShape compositeShape) {
        compositeShape.accept(this);
        // Write the generated data to text
        SaveText.getInstance().save(master.toString());
    }

    public void visitShape(IShape shape) {
        master.append(shape.toString());
    }

    public void visitOrnamentDecorator(OrnamentDecorator ornamentDecorator) {
        master.append(ornamentDecorator.toString());
    }

    public void visitCompositeShape(CompositeShape compositeShape) {
        StringBuilder sb = new StringBuilder();
        sb.append(compositeShape.getType()).append(" ").append(compositeShape.getCount()).append("\n");
        sb.append(_visitCompoundGraphic(compositeShape));
        master.append(sb.toString());
    }

    private String _visitCompoundGraphic(CompositeShape compositeShape) {
        StringBuilder sb = new StringBuilder();
        for (IShape shape : compositeShape.getBaseShapes()) {
            String obj = shape.toString();
            obj = "\t" + obj.replace("\n", "\n\t");
            sb.append(obj).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
