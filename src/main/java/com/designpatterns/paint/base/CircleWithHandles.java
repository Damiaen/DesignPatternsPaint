package com.designpatterns.paint.base;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class CircleWithHandles extends JFrame {
    DrawingCanvas canvas = new DrawingCanvas();
    public CircleWithHandles() {
        getContentPane().add(canvas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String arg[]) {
        new CircleWithHandles();
    }

    class DrawingCanvas extends Canvas {
        double x = 20, y = 20, w = 50, h = 50;

        int x1, y1, x2, y2;

        Ellipse2D ellipse;

        Ellipse2D selectedShape;

        Rectangle2D handleRectangle;

        public DrawingCanvas() {
            setBackground(Color.white);
            addMouseListener(new MyMouseListener());
            addMouseMotionListener(new MyMouseMotionListener());
            setSize(400, 300);
        }

        public void paint(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            ellipse = new Ellipse2D.Double(x, y, w, h);
            g2D.draw(ellipse);
        }

        class MyMouseListener extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                if (ellipse.contains(e.getX(), e.getY())) {
                    selectedShape = ellipse;
                    if (handleRectangle != null)
                        handleRectangle = ellipse.getBounds2D();
                } else {
                    handleRectangle = null;
                }
                canvas.repaint();
                x1 = e.getX();
                y1 = e.getY();
            }
        }

        class MyMouseMotionListener extends MouseMotionAdapter {
            public void mouseDragged(MouseEvent e) {
                if (ellipse.contains(e.getX(), e.getY())) {
                    handleRectangle = null;
                    selectedShape = ellipse;
                    x2 = e.getX();
                    y2 = e.getY();

                    x = x + x2 - x1;
                    y = y + y2 - y1;

                    x1 = x2;
                    y1 = y2;
                }
                canvas.repaint();
            }
        }
    }
}
