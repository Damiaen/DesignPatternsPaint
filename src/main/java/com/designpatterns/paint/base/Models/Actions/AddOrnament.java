package com.designpatterns.paint.base.Models.Actions;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentPosition;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitorResize;

public class AddOrnament implements Command {

    IShape oldShape;
    IShape newShape;
    OrnamentPosition ornamentPosition;
    String content;
    DrawPanel drawPanel = DrawPanel.getInstance();

    public AddOrnament(IShape oldShape, OrnamentPosition ornamentPosition, String content) {
        this.oldShape = oldShape;
        this.content = content;
        this.ornamentPosition = ornamentPosition;
    }

    @Override
    public void execute()
    {
        newShape = new OrnamentDecorator(oldShape, ornamentPosition, content);
        drawPanel.updateShape(oldShape, newShape);
        drawPanel.repaint();
    }

    @Override
    public void undo() {
        drawPanel.updateShape(newShape, oldShape);
        drawPanel.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}
