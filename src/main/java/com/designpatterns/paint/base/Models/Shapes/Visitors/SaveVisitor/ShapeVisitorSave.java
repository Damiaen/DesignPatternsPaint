package com.designpatterns.paint.base.Models.Shapes.Visitors.SaveVisitor;

import com.designpatterns.paint.base.Models.File.SaveText;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.BaseShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class ShapeVisitorSave implements SaveShapeVisitor {

    public void export(BaseShape... args) {
        StringBuilder sb = new StringBuilder();
        for (BaseShape baseShape : args) {
            sb.append(baseShape.acceptSave(this)).append("\n");
        }
        System.out.println(sb.toString());
        //Write the generated data to text
        SaveText.getInstance().save(sb.toString());
    }

    public String visitShape(IShape shape) {
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
        for (IShape shape : compositeShape.getBaseShapes()) {
            String obj = shape.acceptSave(this);
            obj = "\t" + obj.replace("\n", "\n\t");
            sb.append(obj).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
