package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected double x, y;
    protected Color color;

    public Shape() {
        this.color = Color.color(Math.random(), Math.random(), Math.random());
    }

    public abstract void draw(GraphicsContext gc, double x, double y);

    public abstract boolean contains(double mouseX, double mouseY);

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
