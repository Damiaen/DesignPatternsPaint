package com.designpatterns.paint.base.Models.Shapes.Visitors;

import com.designpatterns.paint.base.Models.File.SaveText;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class ShapeVisitorSave implements ShapeVisitor {

    StringBuilder master = new StringBuilder();

    /**
     * export
     * @param compositeShape CompositeShape
     */
    public void export(CompositeShape compositeShape) {
        compositeShape.accept(this);
        // Write the generated data to text
        SaveText.getInstance().save(master.toString());
    }

    /**
     * visit
     * @param shape IShape
     */
    public void visit(IShape shape) {
        master.append(shape.toString());
    }

    /**
     * visit
     * @param ornamentDecorator OrnamentDecorator
     */
    public void visit(OrnamentDecorator ornamentDecorator) {
        master.append(ornamentDecorator.toString());
    }

    /**
     * visit
     * @param compositeShape CompositeShape
     */
    public void visit(CompositeShape compositeShape) {
        StringBuilder sb = new StringBuilder();
        sb.append(compositeShape.getType()).append(" ").append(compositeShape.getCount()).append("\n");
        sb.append(_visitCompoundGraphic(compositeShape));
        master.append(sb.toString());
    }

    /**
     * visit
     * @param compositeShape CompositeShape
     */
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
