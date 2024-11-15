package geometry2d;

import javafx.scene.canvas.GraphicsContext;

public class RectangleShape extends Shape {
    private final double width;
    private final double height;

    public RectangleShape(double width, double height) {
        super();// Вызывает конструктор родительского класса
        this.width = width;
        this.height = height;
        this.x = Math.random() * (800 - width);
        this.y = Math.random() * (500 - height);
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }

    @Override
    public boolean contains(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
