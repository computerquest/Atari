package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

class Rectangle {
    //x position
    double x;
    //y position
    double y;
    //size for width
    double width;
    //size for height
    double height;
    //to see if there is a fill or not
    boolean fill;
    GraphicsContext graphics;
    double bottom; //ybottom of the rectnalge
    Color fillColor;

    //used to create a rectangle object
    public Rectangle(GraphicsContext graphics, double x, double y, double width, double height, Color fill, boolean fillTF) {
        //sets fields used for the rectangle
        this.fill = fillTF;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.graphics = graphics;
        bottom = y - height;

        //to fill or not a rectangle
        //used so that you can use this for the slider and ball as well
        if (fillTF == true) {
            graphics.setFill(fill);
            this.fillColor = fill;
            graphics.fillRect(x, convertY(y), width, height);
        }
        //for the slider and ball
        else {
            graphics.setLineWidth(1);
            graphics.setStroke(Color.RED);
            graphics.strokeRect(x, convertY(y), width, height);
        }
    }

    //used to get rid of rectangles
    public void delete() {
        //erase the rectangle
        (graphics).clearRect(x, convertY(y), width, height);

        //used to make sure they can't be hit when checking
        x = -1;
        y = -1;
        width = -1;
        height = -1;
    }

    //converts the x to use coordinates like canvas (0 is top of the canvas)
    public double convertY(double y) {
        return graphics.getCanvas().getHeight() - y;
    }

    //will redraw the rectangle
    public void reDraw() {
        if (fill == true) {
            graphics.setFill(fillColor);
            (graphics).fillRect(x, convertY(y), width, height);
        } else {
            (graphics).strokeRect(x, convertY(y), width, height);
        }
    }

    //will move the square
    public void move(double x, double y) {
        double localY = convertY(y);

        //delete (image)
        (graphics).clearRect(this.x, convertY(this.y), width + 1, height + 1);

        this.y = y;
        bottom = y - height;

        //check for the fill
        if (fill == true) {
            (graphics).setFill(fillColor);
            (graphics).fillRect(x, localY, width, height);
        } else {
            (graphics).setStroke(Color.RED);
            (graphics).strokeRect(x, localY, width, height);
        }

        //redifine the fields
        this.x = x;
    }
}