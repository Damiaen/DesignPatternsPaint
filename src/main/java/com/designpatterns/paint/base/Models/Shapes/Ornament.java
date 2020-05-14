package com.designpatterns.paint.base.Models.Shapes;

import com.designpatterns.paint.base.Models.Shapes.Figure.OrnamentPosition;

import java.awt.*;

public class Ornament {

    private final OrnamentPosition ornamentPosition;

    private String content;

    public Ornament(OrnamentPosition ornamentPosition, String content) {
        this.ornamentPosition = ornamentPosition;
        this.content = content;
    }

    public void draw(Graphics g, double centerX, double centerY) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.drawString(this.content, (float)centerX, (float)centerY);
    }

    public OrnamentPosition getOrnamentPosition() {
        return ornamentPosition;
    }

    public String getContent() {
        return content;
    }

}
