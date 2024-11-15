package org.example.javafx2;

import geometry2d.CircleShape;
import geometry2d.RectangleShape;
import geometry2d.Shape;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeometryController {

    @FXML
    private Canvas canvas;

    @FXML
    private Button circleButton;

    @FXML
    private Button rectangleButton;

    private final List<Shape> shapes = new ArrayList<>();
    private Shape selectedShape = null;
    private double offsetX, offsetY;
    private final Random random = new Random();

    @FXML
    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed(this::handleMousePressed);
        canvas.setOnMouseDragged(this::handleMouseDragged);
        canvas.setOnMouseReleased(this::handleMouseReleased);

        circleButton.setOnAction(e -> drawShape(new CircleShape(random.nextDouble() * 90 + 10))); // Случайный радиус от 10 до 100
        rectangleButton.setOnAction(e -> drawShape(new RectangleShape(random.nextDouble() * 90 + 10, random.nextDouble() * 90 + 10)));
    }

    private void drawShape(Shape shape) {
        shapes.add(shape);
        drawShapes(canvas.getGraphicsContext2D());
    }

    private void handleMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            for (Shape shape : shapes) {
                if (shape.contains(event.getX(), event.getY())) {
                    selectedShape = shape;
                    offsetX = event.getX() - shape.getX();
                    offsetY = event.getY() - shape.getY();
                    shapes.remove(shape);
                    shapes.add(shape); // Перемещаем фигуру в конец списка
                    break;
                }
            }
        } else if (event.getButton() == MouseButton.SECONDARY) {
            for (Shape shape : shapes) {
                if (shape.contains(event.getX(), event.getY())) {
                    shape.setColor(Color.color(Math.random(), Math.random(), Math.random()));
                    drawShapes(canvas.getGraphicsContext2D());
                    break;
                }
            }
        }
    }

    private void handleMouseDragged(MouseEvent event) {
        if (selectedShape != null) {
            selectedShape.setPosition(event.getX() - offsetX, event.getY() - offsetY);
            drawShapes(canvas.getGraphicsContext2D());
        }
    }

    private void handleMouseReleased(MouseEvent event) {
        selectedShape = null;
    }

    private void drawShapes(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape shape : shapes) {
            shape.draw(gc, shape.getX(), shape.getY());
        }
    }
}
