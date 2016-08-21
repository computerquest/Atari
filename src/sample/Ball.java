package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Random;

//need to switch the forward boolean because I have not been doing that
//need to switch all the measurements in slider and ball to normal datapoints instead of having them pre prepared for canvas
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
        segment = new LineSegment(centerPos.x, centerPos.y, r.nextInt(750), r.nextInt((int) Math.round(400 - centerPos.y)) + centerPos.y);

        if (segment.slope > 0) {
            forward = true;
        } else {
            forward = false;
        }

        currentCornerCalc();
        forward = true;
        graphics = context;
        rectangle = new Rectangle(graphics, centerPos.x, centerPos.y, width, height, Color.GREY, true);
    }

    //calculate slope
    public void onSliderContact(double posOnSlider, double sliderX) {
        //if contact x has a hit
        //calculate slope
        updateRate = 1 + Math.abs(posOnSlider / 10);
        double newAngle = Math.abs(10 * (75 / posOnSlider));
        if (newAngle > 90) {
            newAngle = 80;
        }

        double newX = centerPos.x + posOnSlider;
        double newY = Math.tan(Math.toRadians(newAngle)) * Math.abs(posOnSlider) + centerPos.y;
        segment = new LineSegment(centerPos.x, centerPos.y, newX, newY);

        if (posOnSlider < 0) {
            forward = false;
        } else {
            forward = true;
        }

        currentCornerCalc();

        move();
    }

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

    public void onSideContact(double xBounds, double yBounds) {
        currentCornerCalc();

        double beginX = segment.begining.x;
        double beginY = segment.begining.y;

        // xBounds = beginX;
        //yBounds = Math.abs(yBounds-currentCorner.y);

        double opposite = Math.abs(beginX - currentCorner.x);
        double adjacent = Math.abs(beginY - currentCorner.y);
        double angle = Math.atan(Math.toRadians(opposite / adjacent)); // not working for some reason
        double adjacentOther = (Math.abs(yBounds - currentCorner.y));
        double oppositeOther = Math.tan(angle) * adjacentOther;

        //segment = new LineSegment(centerPos.x, centerPos.y, beginX, segment.slope > 0 ? centerPos.y+adjacent: centerPos.y-adjacent);
        segment = new LineSegment(centerPos.x, centerPos.y, beginX, !forward ? centerPos.y + adjacent : centerPos.y - adjacent);
        forward = forward ? false : true;

        currentCornerCalc();

        move();
    }

    public void onTopContact(double xBounds, double yBounds) {
        currentCornerCalc();

        double beginX = segment.begining.x;
        double beginY = segment.begining.y;

        double adjacent = Math.abs(beginX - currentCorner.x);
        double opposite = Math.abs(beginY - currentCorner.y);
        double angle = Math.atan(opposite / adjacent);
        double secondAdjacent = (Math.abs(adjacent - xBounds));
        double oppositeOther = Math.tan(angle) * secondAdjacent;

        double newX = 0;
        if ((segment.slope > 0 & forward | segment.slope < 0 & !forward)) { // bounce from top
            newX = yBounds - oppositeOther;
        } else {
            newX = centerPos.y + oppositeOther;
        }

        segment = new LineSegment(centerPos.x, centerPos.y, forward ? centerPos.x + adjacent : centerPos.x - adjacent, beginY);

        currentCornerCalc();

        move();
    }


    public double toCenteralCoordinatesX(double x) {
        return x + (centerPos.x - currentCorner.x);
    }

    public double toCenteralCoordinateY(double y) {
        return y + (centerPos.y - currentCorner.y);
    }

    //?
    public void move() {
        double newX = centerPos.x + (forward ? updateRate : updateRate * -1); //(toCenteralCoordinatesX(x) + (forward ? updateRate: updateRate*-1));
        double segmentYAt = segment.yAt(newX);
        System.out.println("we are moving the x to " + newX + " and the y to " + segmentYAt);
        rectangle.move(newX, segmentYAt);

        updateVariables(newX, segmentYAt);
    }

    public void updateVariables(double x, double y) {
        centerPos.x = x;
        centerPos.y = y;
        endX = centerPos.x + width;
        bottom = centerPos.y - height;
        centerX = centerPos.x + (.5 * width);
        currentCornerCalc(); //this only updates the value for the corner should be the same corner
    }

    //used to move the ball does all the most compilated method
    /*public void moveBall(GraphicsContext graphics) {
        //get the value on the slider
        float hit = (float) hit(x, y);
        //tells where it hits
        contactX = hit == -2 ? 0: hit;
        //checks for contact
        boolean touching = new Main().contact(ball);
        //onSliderContacts slope
        onSliderContact();

        //for when it is hitting nothing
        if(y > 0 && y < 344 && x > 0 && x < 720 && touching == false){
            System.out.println("");
            System.out.println("primary route");

            //does simple calculations  for x and sometimes y
            //automaticly does the y rounding
            if(speed > 0) {
                //DONT DELETE this semes to be correct but doesn't work y = speed%1 != 0 ? y +1: (int) (y+speed);
                y = speed%1 != 0 ? y -1: (int) (y-speed);
            }
            else {

                //DONT DELETE semes to be correct but doesn't work y = speed%1 != 0 ? y -1: (int) (y+speed);
                y = speed%1 != 0 ? y +1: (int) (y+speed);
            }

            //x changing
            x = slope%1 == 0 && slope != 0 ?  (int) (x+slope): x + slopeRound;
        }

        //for when it hits cats
        else{
            System.out.println("");
            System.out.println("secondary");
            //checking it out (touching covers EVERYTHINNG)
            if(touching == true) {
                System.out.println("it is touching");
                //sets an outer array to what hit returns
                LinkedList<Rectangle> contact = ball.hit(ball, rectangle);

                //if the array has anything
                if(contact.size() >= 1) {
                    System.out.println("through the doors");
                    //change speed
                    if(speed < 0 && y <= contact.get(0).y+26) {
                        System.out.println("this shoulnd't be happening");
                        speed *= -1;
                    }

                    System.out.println(y);
                    System.out.println(contact.get(0).y);
                    if(y +30 >= contact.get(0).y && y <= contact.get(0).y) {
                        System.out.println("going around the world");
                        speed *= -1;
                    }

                    speed *= -1;


                    //to see if it hits the edge so that if so it can change slope
                    System.out.println("contact y "+ contact.get(0).y);
                    System.out.println("y is "+ y);
                    if(y > contact.get(0).y && y < contact.get(0).y+26) {
                        System.out.println("we are changing this slope hope it works");
                        //changing slope
                        slope *= -1;
                    }


                    score(ball, contact);
                    //delete the one it hits
                    contact.get(0).delete((contact.get(0)));

                    //check if it gets 2
                    if(contact.size() == 2) {
                        //delete the second one
                        contact.get(1).delete(contact.get(1));
                    }
                }

                //if it is hitting the edges change loop
                if(x == 720 || x == 0) {
                    slope *= -1;
                }

                //if it is hitting the edges change slope
                if(y >= 344 || y <= 0) {
                    speed *= -1;
                }

                //to take it off the border
                if(speed > 0) {
                    y -= 1;
                }
                //take it off the boarder
                else {
                    y += 1;
                }

                //take it off the boarder and round slope
                if(x <= 0) {
                    slopeRound = Math.round(slope) == 0 ? 1: Math.round(slope);
                    x += 1;
                }

                //take it off border and round slope
                else {
                    slopeRound = Math.round(slope) == 0 ? -1: Math.round(slope);
                    x -= 1;
                }
            }
        }

        //to make sure it stays within its boundries
        if(x < 0) {
            x = 0;
        }

        //to make sure it stays within its boundries
        if(x > 720) {
            x = 720;
        }

        System.out.println("the value of x is "+ x);
        System.out.println("the value of y is "+ y);

        System.out.println(y+30 == 344);
        System.out.println(xSlide < x+30);
        System.out.println(xSlide+150 > x);
        System.out.println(y+30 == 344 && xSlide < x+30 || y+30 == 344 && xSlide+150 > x);
        System.out.println(xSlide);
        if(y == 344 && xSlide < x+30 || y+30 == 344 && xSlide+150 > x) {
            System.out.println("numbers");
            try {
                end();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            t.stop();
        }

        //move ball
        ball.move(ball, x, y);
    }*/
}
