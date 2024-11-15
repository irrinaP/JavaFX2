package geometry2d;

import javafx.scene.canvas.GraphicsContext;

public class CircleShape extends Shape {
    private final double radius;

    public CircleShape(double radius) {
        super();
        this.radius = radius;
        this.x = Math.random() * (800 - radius);
        this.y = Math.random() * (500 - radius);
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(color); // Использует цвет фигуры
        gc.fillOval(x, y, radius, radius);
    }

    @Override
    public boolean contains(double mouseX, double mouseY) {
        double centerX = x + radius / 2;
        double centerY = y + radius / 2;
        return Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2) <= Math.pow(radius / 2, 2);
    }
}
