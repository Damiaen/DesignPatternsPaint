package com.designpatterns.paint.base.Models.Shapes.Decorator;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitorSave;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitor;

import java.awt.*;

public class OrnamentDecorator extends ShapeDecorator {

    private final OrnamentPosition ornamentPosition;

    private String content;

    public OrnamentDecorator(IShape decoratedShape, OrnamentPosition ornamentPosition, String content) {
        super(decoratedShape);
        this.ornamentPosition = ornamentPosition;
        this.content = content;
    }

    @Override
    public boolean checkPosition(Position position) {
        return decoratedShape.checkPosition(position);
    }

    /**
     * Draw ornament. We also give the shape it belongs to, so we can draw it based on that value
     * TODO: Test and make positions percentage based / based on shapeType
     */
    @Override
    public void draw(Graphics g) {
        decoratedShape.draw(g);
        setOrnament(decoratedShape, g);
    }

    private void setOrnament(IShape decoratedShape, Graphics g) {
        // Setup base and color
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);


        // Get center X and center Y, so we can place the shape based on the middle of the shape
        double positionX = decoratedShape.getX() + decoratedShape.getWidth() / 2.0D;
        double positionY = decoratedShape.getY() + decoratedShape.getHeight() / 2.0D;

        // Check what the position of the ornament is, and change values accordingly
        switch (this.ornamentPosition) {
            case TOP:
                positionY -= ((decoratedShape.getHeight() / 2) + 20);
                break;
            case BOTTOM:
                positionY += ((decoratedShape.getHeight() / 2) - 20);
                break;
            case LEFT:
                positionX -= ((decoratedShape.getWidth() / 2) + 20);
                break;
            case RIGHT:
                positionX += ((decoratedShape.getHeight() / 2) - 20);
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

    @Override
    public void drawContour(Graphics g, Color color) {
        decoratedShape.drawContour(g, color);
    }

    @Override
    public void setSelected(boolean bool) {
        decoratedShape.setSelected(bool);
    }

    @Override
    public boolean isSelected() {
        return decoratedShape.isSelected();
    }

    @Override
    public double getX() {
        return decoratedShape.getX();
    }

    @Override
    public double getWidth() {
        return decoratedShape.getWidth();
    }

    @Override
    public double getY() {
        return decoratedShape.getY();
    }

    @Override
    public double getHeight() {
        return decoratedShape.getHeight();
    }

    @Override
    public void setSize(int newWidth, int newHeight) {
        decoratedShape.setSize(newWidth, newHeight);
    }

    @Override
    public ShapeType getType() {
        return ShapeType.Ornament;
    }

    @Override
    public void setMovingPosition(Position position, int mousePositionX, int cursorSelectedX, int mousePositionY, int cursorSelectedY) {
        decoratedShape.setMovingPosition(position, mousePositionX, cursorSelectedX, mousePositionY, cursorSelectedY);
    }

    @Override
    public void setPosition(Position position) {
        decoratedShape.setPosition(position);
    }


    @Override
    public Position getPosition() {
        return decoratedShape.getPosition();
    }

    @Override
    public void accept(ShapeVisitor v) {
        v.visitOrnamentDecorator( this );
    }

    @Override
    public void moveShape(Position oldPos) {
        decoratedShape.moveShape(oldPos);
    }

    @Override
    public void setMoving(boolean b) {
        decoratedShape.setMoving(b);
    }

    @Override
    public boolean isMoving() {
        return decoratedShape.isMoving();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ornament" + " ").append(ornamentPosition).append(" ").append("\"").append(content).append("\"").append("\n");

        stringBuilder.append(decoratedShape.toString());

        if ((Character.compare(stringBuilder.charAt(stringBuilder.length() - 1), '\t')) == 1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

}
