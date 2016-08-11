package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//need to track the position of the center as well as the position on the end of the slider
public class Slider {
    //x position of slide
    double beginX = 275;
    //y position of slide
    double y = 30;
    //height of slide
    double height = 5;
    //width of slide
    double width = 150;
    double contactX = 0;
    double centerX = 0;
    Rectangle slide;
    double endX;
    GraphicsContext graphics;
    double centerXPos = beginX + 75;

    public Slider(GraphicsContext g) {
        centerX = beginX + 75;
        endX = centerX + 75;
        graphics = g;
        slide = new Rectangle(graphics, beginX, y, width, height, Color.GREY, true);
    }

    //used for moving the slider
    public void move(double x) {
        //used to make it so the mouse is in the center
        if (x <= 75) {
            x = 75;
        }
        x = x <= 675 && x >= 75 ? x - 75 : 600;
        //sets beginX
        beginX = x;
        centerXPos = beginX + 75;
        endX = beginX + width;

        //sets up centerX
        centerX = x + 75;
        //moves slide
        slide.move(x, y);
    }

    //checks for where the ball hits on the slider
    public double hit(double x, double inputWidth, double y) {
        //default to say false

        //checks the edges and hit level
        //if the ball is at the slider
        if (y >= this.y & y <= this.y + height & (x >= beginX & x <= endX | x + width >= beginX & x + width < endX)) {
            //subtract centerX from the xBall to get where it hits
            return x - centerX;
        }

        return -100;
    }
}
