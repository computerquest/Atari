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
    double bottom;
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

    //the hit detection system
    /*public LinkedList<Rectangle> hit(Rectangle rectangle, LinkedList<Rectangle> rectangleArray) {
        System.out.println("we are checking");
        LinkedList<Rectangle> answer = new LinkedList();

        //this will do a general check to eliminate easy possibilities
        if(rectangle.y <= 210) {
            //this will go through the options
            for(int i = 0, x = 0;  i < rectangleArray.size() && x < 2; i++) {
                //to test if it is a hit
                //rectangle.x >= this.rectangle.get(i).x-40 && rectangle.x <= this.rectangle.get(i).x + 40 && rectangle.x != -1 && rectangle.y+30 >= this.rectangle.get(i).y && rectangle.y <= this.rectangle.get(i).y+26
                if(rectangle.x >= rectangleArray.get(i).x-40 && rectangle.x <= rectangleArray.get(i).x + 40 && rectangle.x != -1 && rectangle.y+30 >= rectangleArray.get(i).y && rectangle.y <= rectangleArray.get(i).y+26){
                    //System.out.println("r "+rectangle.x +" and "+ rectangle.y+30);
                    //System.out.println("ra "+ rectangleArray.get(i).x +" and "+ rectangleArray.get(i).y);

                    //if this is the first
                    if(x == 0) {
                        answer.add(rectangleArray.get(i));

                        //check the one next to it to check for it hitting 2
                        if(rectangle.x >= rectangleArray.get(i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i+1).x-40 && rectangle.x <= rectangleArray.get(i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i+1).x + 40 && rectangle.y+30 >= rectangleArray.get(i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i+1).y && rectangle.y >= rectangleArray.get(i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i+1).y+26){//-26 && rectangle.y <= rectangleArray.get(i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i+1).y+26) {
                            answer.add(rectangleArray.get(i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i> rectangleArray.size()-1? 0: i+1));
                            x = 2;
                            break;
                        }
                    }
                    break;
                }
            }
        }
        //return the array for the 1(2) blocks
        return answer;
    }*/

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