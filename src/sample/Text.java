package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

class Text {
    int x;
    int y;
    double height;
    double width;
    String message;
    GraphicsContext graphics;

    public Text(GraphicsContext graphics, int x, int y, String message) {
        graphics.setFont(new Font("Arial", 50));
        graphics.setFill(Color.ORANGE);
        this.x = x;
        this.y = y;
        this.message = message;
        this.graphics = graphics;

        height = graphics.getFont().getSize();
        width = message.length() * graphics.getFont().getSize();

        graphics.setTextAlign(TextAlignment.LEFT);

        graphics.fillText(message, x, y);
    }

    public void update(String message) {
        graphics.clearRect(x, y - 50, width, height);
        graphics.setFill(Color.ORANGE);

        height = graphics.getFont().getSize();
        width = message.length() * graphics.getFont().getSize();


        graphics.fillText(message, x, y);

        this.message = message;
    }
}