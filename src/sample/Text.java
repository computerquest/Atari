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
        System.out.println("party party party");
        //this.x = x;
        //this.y = y;
        this.height = height;
        this.width = width;
        this.message = message;
        this.graphics = graphics;

        graphics.fillText(message, width, height);
    }

    public void update(String message) {
        graphics.clearRect(338, 0, 100, 50);
        graphics.setFill(Color.BLUE);
        graphics.fillText(message, width, height);
    }
}