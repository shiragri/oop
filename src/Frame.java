import biuoop.DrawSurface;

import java.awt.*;

public class Frame {
    private Point topLeft;
    private int width;
    private int height;
    private Color color;

    public Frame(Point topLeft, int width,int height, Color color) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void SetColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

   public int getWidth() {
        return width;

   }

   public void setWidth(int width) {
        this.width = width;
   }
   public int getHeight() {
        return height;
   }

   public void setHeight(int height) {
        this.height = height;
   }

    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(this.color);
        drawSurface.fillRectangle((int)topLeft.getX(),(int)topLeft.getY(),getWidth(),getHeight());
    }
}