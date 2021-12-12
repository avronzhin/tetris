package ru.rsreu.tetris.game.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.rsreu.tetris.game.Coords;
import ru.rsreu.tetris.game.Figure;
import ru.rsreu.tetris.game.GameField;

public class CanvasGraphicsModule implements GraphicsModule {

    private final int size;
    private final Canvas canvas;
    private final GraphicsContext gc;

    public CanvasGraphicsModule(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        size = (int) (canvas.getHeight() / GameField.COUNT_CELLS_Y);
    }

    @Override
    public void draw(GameField field) {
        drawBackground();
        drawField(field);
        drawFigure(field.getFigure());
    }

    private void drawBackground() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawField(GameField field) {
        for (int x = 0; x < GameField.COUNT_CELLS_X; x++) {
            for (int y = 0; y < GameField.COUNT_CELLS_Y; y++) {
                drawBlock(field.getBlock(x, y).getColor().darker(), new Coords(x, y));
            }
        }
    }

    private void drawBlock(Color color, Coords coords) {
        gc.setFill(color);
        gc.fillRect(coords.getX() * size + 1.5,
                canvas.getHeight() - (coords.getY() + 1) * size + 1.5, size - 3, size - 3);
    }

    private void drawFigure(Figure figure) {
        for (Coords coords : figure.getCoords()) {
            drawBlock(figure.getColor(), coords);
        }
    }
}
