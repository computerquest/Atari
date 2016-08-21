package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class Text {
    int x;
    int y;
    int height;
    int width;
    String message;
    GraphicsContext graphics;

    public Text(GraphicsContext graphics, int width, int height, String message) {
        graphics.setFont(new Font("ARAIL", 50));
        graphics.setFill(Color.BLUE);
        this.height = height;
        this.width = width;
        this.message = message;
        this.graphics = graphics;

        graphics.fillText(message, width, height);
    }

    public void update(String message) {
        graphics.clearRect(x, y, 100, 50);
        graphics.setFill(Color.BLUE);
        graphics.fillText(message, width, height);
    }
}