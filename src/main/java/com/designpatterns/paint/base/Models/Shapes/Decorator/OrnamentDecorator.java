package com.designpatterns.paint.base.Models.Shapes.Decorator;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;
import com.designpatterns.paint.base.Models.Shapes.Visitors.ShapeVisitor;

import java.awt.*;

public class OrnamentDecorator extends ShapeDecorator {

    private final OrnamentPosition ornamentPosition;

    private final String content;

    /**
     * Constructor of ornament decorator
     * @param decoratedShape  decoratedShape
     * @param ornamentPosition ornamentPosition
     * @param content content
     */
    public OrnamentDecorator(IShape decoratedShape, OrnamentPosition ornamentPosition, String content) {
        super(decoratedShape);
        this.ornamentPosition = ornamentPosition;
        this.content = content;
    }

    /**
     * Check position
     * @param position position
     * @return position
     */
    @Override
    public boolean checkPosition(Position position) {
        return decoratedShape.checkPosition(position);
    }

    /**
     * Draw ornament. We also give the shape it belongs to, so we can draw it based on that value
     */
    @Override
    public void draw(Graphics g) {
        decoratedShape.draw(g);
        setOrnament(decoratedShape, g);
    }

    /**
     * set the ornament to the shape
     * @param decoratedShape decoratedShape
     * @param g graphics
     */
    private void setOrnament(IShape decoratedShape, Graphics g) {
        // Setup base and color
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        Position position = decoratedShape.getPosition();

        // Get center X and center Y, so we can place the shape based on the middle of the shape
        int positionX = position.x;
        int positionY = position.y;

        // Check what the position of the ornament is, and change values accordingly
        switch (this.ornamentPosition) {
            case TOP:
                positionY = (position.y - (decoratedShape.getHeight() / 2)) - 20;
                break;
            case BOTTOM:
                positionY = (position.y + (decoratedShape.getHeight() / 2)) + 20;
                break;
            case LEFT:
                positionX = (position.x - (decoratedShape.getWidth() / 2)) - 20;
                break;
            case RIGHT:
                positionX = (position.x + (decoratedShape.getWidth() / 2)) + 20;
                break;
        }
        g2d.drawString(this.content, (float) positionX, (float) positionY);
    }

    /**
     * draw contour
     * @param g Graphics
     * @param color color
     */
    @Override
    public void drawContour(Graphics g, Color color) {
        decoratedShape.drawContour(g, color);
    }

    /**
     * set selected
     * @param bool boolean
     */
    @Override
    public void setSelected(boolean bool) {
        decoratedShape.setSelected(bool);
    }

    /**
     * return true/false
     * @return boolean
     */
    @Override
    public boolean isSelected() {
        return decoratedShape.isSelected();
    }

    /**
     * get width
     * @return width
     */
    @Override
    public int getWidth() {
        return decoratedShape.getWidth();
    }

    /**
     * get height
     * @return height
     */
    @Override
    public int getHeight() {
        return decoratedShape.getHeight();
    }

    /**
     * set size
     * @param newWidth newWidth
     * @param newHeight newHeight
     */
    @Override
    public void setSize(int newWidth, int newHeight) {
        decoratedShape.setSize(newWidth, newHeight);
    }

    /**
     * get type
     * @return type
     */
    @Override
    public ShapeType getType() {
        return ShapeType.Ornament;
    }

    /**
     * set the moving position
     * @param mousePosition mousePosition
     */
    @Override
    public void setMovingPosition(Position mousePosition) {
        decoratedShape.setMovingPosition(mousePosition);
    }

    /**
     * set position
     * @param position position
     */
    @Override
    public void setPosition(Position position) {
        decoratedShape.setPosition(position);
    }

    /**
     * get position
     * @return position
     */
    @Override
    public Position getPosition() {
        return decoratedShape.getPosition();
    }

    /**
     * accept visitor
     * @param v ShapeVisitor
     */
    @Override
    public void accept(ShapeVisitor v) {
        v.visit( this );
    }

    /**
     * move the shape
     * @param oldPos Position
     */
    @Override
    public void moveShape(Position oldPos) {
        decoratedShape.moveShape(oldPos);
    }

    /**
     * set moving
     * @param b boolean
     */
    @Override
    public void setMoving(boolean b) {
        decoratedShape.setMoving(b);
    }

    /**
     * get is moving
     * @return boolean
     */
    @Override
    public boolean isMoving() {
        return decoratedShape.isMoving();
    }

    /**
     * to string method
     * @return string
     */
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
