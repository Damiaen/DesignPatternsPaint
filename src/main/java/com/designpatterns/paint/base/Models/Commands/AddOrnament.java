package com.designpatterns.paint.base.Models.Commands;

import com.designpatterns.paint.base.Models.DrawPanel;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentDecorator;
import com.designpatterns.paint.base.Models.Shapes.Decorator.OrnamentPosition;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;

public class AddOrnament implements Command {

    IShape oldShape;
    IShape newShape;
    OrnamentPosition ornamentPosition;
    String content;
    final DrawPanel drawPanel = DrawPanel.getInstance();

    /**
     * Constructor for the add ornament command
     * @param oldShape oldShape
     * @param ornamentPosition ornamentPosition
     * @param content content
     */
    public AddOrnament(IShape oldShape, OrnamentPosition ornamentPosition, String content) {
        this.oldShape = oldShape;
        this.content = content;
        this.ornamentPosition = ornamentPosition;
    }

    /**
     * execute the command
     */
    @Override
    public void execute()
    {
        newShape = new OrnamentDecorator(oldShape, ornamentPosition, content);
        drawPanel.updateShape(oldShape, newShape);
        drawPanel.repaint();
    }

    /**
     * Undo the command
     */
    @Override
    public void undo() {
        drawPanel.updateShape(newShape, oldShape);
        drawPanel.repaint();
    }

    /**
     * Redo the command
     */
    @Override
    public void redo() {
        execute();
    }
}
