package com.designpatterns.paint.base.Models.Shapes;

import com.designpatterns.paint.base.Models.Shapes.Figure.OrnamentPosition;
import com.designpatterns.paint.base.Models.Shapes.Figure.Shape;
import com.designpatterns.paint.base.Models.Shapes.Figure.ShapeType;

import java.awt.*;

public class Ornament extends Shape{

    private final OrnamentPosition ornamentPosition;

    private String content;

    public Ornament(OrnamentPosition ornamentPosition, String content) {
        super(ShapeType.Ornament, 0, 0, 0, 0);
        this.ornamentPosition = ornamentPosition;
        this.content = content;
    }

    /**
     * Draw ornament. We also give the shape it belongs to, so we can draw it based on that value
     * TODO: Test and make positions percentage based / based on shapeType
     */
    public void draw(Graphics g, Shape shape) {
        // Setup base and color
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        // Get center X and center Y, so we can place the shape based on the middle of the shape
        double positionX = shape.getX() + shape.getWidth() / 2.0D;
        double positionY = shape.getY() + shape.getHeight() / 2.0D;

        // Check what the position of the ornament is, and change values accordingly
        switch (this.ornamentPosition) {
            case TOP:
                positionY -= (((double) shape.getHeight() / 2) + 20);
                break;
            case BOTTOM:
                positionY += (((double) shape.getHeight() / 2) - 20);
                break;
            case LEFT:
                positionX -= (((double) shape.getWidth() / 2) + 20);
                break;
            case RIGHT:
                positionX += (((double) shape.getHeight() / 2) - 20);
                break;
        }

        // After getting all the values, we can finally draw the string
        g2d.drawString(this.content, (float) positionX, (float) positionY);
    }

    public OrnamentPosition getOrnamentPosition() {
        return ornamentPosition;
    }

    public String getContent() {
        return content;
    }

}
