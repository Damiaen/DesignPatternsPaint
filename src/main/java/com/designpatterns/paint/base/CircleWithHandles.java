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

        Cursor curCursor;

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
            if (handleRectangle != null) {
                drawHighlightSquares(g2D, handleRectangle);
            }
            if (curCursor != null)
                setCursor(curCursor);
        }

        public void drawHighlightSquares(Graphics2D g2D, Rectangle2D r) {
            double x = r.getX();
            double y = r.getY();
            double w = r.getWidth();
            double h = r.getHeight();
            g2D.setColor(Color.black);

            g2D.fill(new Rectangle.Double(x - 3.0, y - 3.0, 6.0, 6.0));
            g2D.fill(new Rectangle.Double(x + w * 0.5 - 3.0, y - 3.0, 6.0, 6.0));
            g2D.fill(new Rectangle.Double(x + w - 3.0, y - 3.0, 6.0, 6.0));
            g2D.fill(new Rectangle.Double(x - 3.0, y + h * 0.5 - 3.0, 6.0, 6.0));
            g2D.fill(new Rectangle.Double(x + w - 3.0, y + h * 0.5 - 3.0, 6.0, 6.0));
            g2D.fill(new Rectangle.Double(x - 3.0, y + h - 3.0, 6.0, 6.0));
            g2D.fill(new Rectangle.Double(x + w * 0.5 - 3.0, y + h - 3.0, 6.0, 6.0));
            g2D.fill(new Rectangle.Double(x + w - 3.0, y + h - 3.0, 6.0, 6.0));
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

            public void mouseReleased(MouseEvent e) {
                if (ellipse.contains(e.getX(), e.getY())) {
                    handleRectangle = ellipse.getBounds2D();
                    selectedShape = ellipse;
                }
                canvas.repaint();
            }

            public void mouseClicked(MouseEvent e) {
                if (ellipse.contains(e.getX(), e.getY())) {
                    selectedShape = ellipse;
                    handleRectangle = ellipse.getBounds2D();
                } else {
                    if (handleRectangle != null)
                        handleRectangle = null;
                }
                canvas.repaint();
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

            public void mouseMoved(MouseEvent e) {
                if (ellipse != null) {
                    if (ellipse.contains(e.getX(), e.getY())) {
                        curCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                    } else {
                        curCursor = Cursor.getDefaultCursor();
                    }
                }
                canvas.repaint();
            }
        }
    }
}
