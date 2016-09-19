package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Random;

//somewhere in the contact methods there is one whre it makes the slope 31 because the segment.beginning.x is not working correctly
public class Ball {
    DataPoint centerPos = new DataPoint(330, 100); //drawing coordinates
    int height = 31;
    int width = 31;
    Rectangle rectangle; //the visual ball
    GraphicsContext graphics;
    double endX = centerPos.x + width; // the right x of the ball
    double centerX = centerPos.x + (.5 * width); //center coordinate of x
    double bottom = centerPos.y - height; //the bottom of the ball
    LineSegment segment; // used for movement is always based on the drawing coordinates
    boolean forward; // if forward the x is going up by one
    DataPoint currentCorner = new DataPoint(centerPos.x, centerPos.y); // used for collision
    double updateRate = 5; // rate that the x will go up by

    //i need an update all movement variables because i feel like i keep forgetting to do that to all of them
    public Ball(GraphicsContext context) {
        Random r = new Random();
        segment = new LineSegment(centerPos.x, centerPos.y, r.nextInt(750), r.nextInt((int) Math.round(400 - centerPos.y)) + centerPos.y);// randomly generate destination coordinates

        //make sure it goes up when it spawns
        if (segment.slope > 0) {
            forward = true;
        } else {
            forward = false;
        }

        currentCornerCalc();

        graphics = context;
        rectangle = new Rectangle(graphics, centerPos.x, centerPos.y, width, height, Color.GREY, true); //the visual representation of the ball
    }

    //calculate slope
    public void onSliderContact(double posOnSlider, double velocity) {
        //if contact x has a hit
        //calculate slope
        updateRate = 1 + Math.abs(posOnSlider / 10) + velocity < 15 ? 1 + Math.abs(posOnSlider / 10) + velocity : 15; //changing the refresh

        double newAngle = Math.abs(10 * (75 / posOnSlider)); //changes the angle of the ball so that further on the end it hits the lower the angle

        //makes sure it isn't to big
        if (newAngle > 90) {
            newAngle = 80;
        }

        segment = new LineSegment(centerPos.x, centerPos.y, centerPos.x + posOnSlider, Math.tan(Math.toRadians(newAngle)) * Math.abs(posOnSlider) + centerPos.y);
        if (segment.slope > 10) {
            updateRate = 10 * updateRate < 3.5 ? 10 * updateRate : 3.5;
            segment.slope /= 10;
        }

        //sets forward or not
        if (posOnSlider < 0) {
            forward = false;
        } else {
            forward = true;
        }

        currentCornerCalc();
    }

    //updates the colliosion corner which is determined by the slope and direction so that it is the first corner to hit
    public void currentCornerCalc() {
        if (forward == false & segment.slope > 0) {
            currentCorner.x = centerPos.x;
            currentCorner.y = centerPos.y - height;
        } else if (forward == true & segment.slope > 0) {
            currentCorner.x = centerPos.x + width;
            currentCorner.y = centerPos.y;
        } else if (forward == false & segment.slope < 0) {
            currentCorner.x = centerPos.x;
            currentCorner.y = centerPos.y;
        } else if (forward == true & segment.slope < 0) {
            currentCorner.x = centerPos.x + width;
            currentCorner.y = centerPos.y - height;
        }
    }

    public void onSideContact() {
        currentCornerCalc();

        double adjacent = Math.abs(segment.begining.y - currentCorner.y);

        //changes slope and direction
        segment = new LineSegment(centerPos.x, centerPos.y, segment.begining.x, (segment.slope > 0 & forward) | (segment.slope < 0 & !forward) ? centerPos.y + adjacent : centerPos.y - adjacent);
        if (segment.slope > 10) {
            updateRate = 10 * updateRate < 3.5 ? 10 * updateRate : 3.5;
            segment.slope /= 10;
        }
        forward = forward ? false : true;

        currentCornerCalc();
    }


    //this most likely doesn't work
    public void onTopContact() {
        currentCornerCalc();

        double adjacent = Math.abs(segment.begining.x - currentCorner.x);

        segment = new LineSegment(centerPos.x, centerPos.y, forward ? centerPos.x + adjacent : centerPos.x - adjacent, segment.begining.y);
        if (segment.slope > 10) {
            updateRate = 10 * updateRate < 3.5 ? 10 * updateRate : 3.5;
            segment.slope /= 10;
        }

        currentCornerCalc();
    }

    //?
    public void move() {
        //new x and y
        double newX = centerPos.x + (forward ? 1 : -1); //(toCenteralCoordinatesX(x) + (forward ? updateRate: updateRate*-1));
        double segmentYAt = segment.yAt(newX);
        //System.out.println("we are moving the x to " + newX + " and the y to " + segmentYAt);

        rectangle.move(newX, segmentYAt); //moves the ball

        updateVariables(newX, segmentYAt);
    }

    //updates everything
    public void updateVariables(double x, double y) {
        centerPos.x = x;
        centerPos.y = y;
        endX = centerPos.x + width;
        bottom = centerPos.y - height;
        centerX = centerPos.x + (.5 * width);
        currentCornerCalc(); //this only updates the value for the corner should be the same corner
    }
}