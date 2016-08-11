package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Random;

//need to switch all the measurements in slider and ball to normal datapoints instead of having them pre prepared for canvas
public class Ball {
    double speed = -.4;
    //movement of x
    double slope;
    //position of x for ball
    double x = 330;
    //position of y for ball
    double y = 100; // is the top of the ball
    //height of ball
    int height = 31;
    //width of ball
    int width = 31;
    //speed of x
    double speedX = .04;
    Rectangle rectangle;
    GraphicsContext graphics;
    double endX = x + width;
    double centerX = x + (.5 * width);
    double bottom = y + height;
    LineSegment segment;
    boolean forward; // if forward the x is going up by one
    DataPoint currentCorner = new DataPoint(x, y);

    double updateRate;

    public Ball(GraphicsContext context) {
        updateRate = 5;
        Random r = new Random();
        segment = new LineSegment(x, y, r.nextDouble(), r.nextDouble());
        currentCornerCalc();
        forward = false;
        graphics = context;
        rectangle = new Rectangle(graphics, x, y, width, height, Color.GREY, true);
    }

    //calculate slope
    public void onSliderContact(double posOnSlider, double sliderX) {
        //if contact x has a hit
        //calculate slope
        updateRate = x / 10;
        double newAngle = Math.abs(10 * (75 / posOnSlider));
        if (newAngle > 90) {
            newAngle = 80;
        }

        segment = new LineSegment(currentCorner.x, currentCorner.y, sliderX + posOnSlider, Math.tan(newAngle) * posOnSlider);
        currentCornerCalc();
    }

    public void currentCornerCalc() {
        if (forward == false & segment.slope > 0) {
            currentCorner.x = x;
            currentCorner.y = y + height;
        } else if (forward == true & segment.slope > 0) {
            currentCorner.x = x + width;
            currentCorner.y = y;
        } else if (forward == false & segment.slope < 0) {
            currentCorner.x = x + width;
            currentCorner.y = y + height;
        } else if (forward == true & segment.slope < 0) {
            currentCorner.x = x;
            currentCorner.y = y;
        }
    }

    public void onSideContact(double xBounds, double yBounds) {
        currentCornerCalc();

        double beginX = segment.begining.x;
        double beginY = segment.begining.y;

        double opposite = Math.abs(beginX - currentCorner.x);
        double adjacent = Math.abs(beginY - currentCorner.y);

        double oppositeOther = Math.atan(Math.tan(opposite / adjacent)) * (Math.abs(yBounds - currentCorner.y));


        segment = new LineSegment(currentCorner.x, currentCorner.y, forward == false ? (xBounds - oppositeOther) : x + oppositeOther, yBounds);
    }

    public void onTopContact(double xBounds, double yBounds) {
        currentCornerCalc();

        double beginX = segment.begining.x;
        double beginY = segment.begining.y;

        double adjacent = Math.abs(beginX - currentCorner.x);
        double opposite = Math.abs(beginY - currentCorner.y);

        double oppositeOther = Math.atan(Math.tan(opposite / adjacent)) * (Math.abs(currentCorner.x - xBounds));

        double newX;
        if ((segment.slope > 0 & !forward | segment.slope < 0 & forward)) {
            newX = yBounds - oppositeOther;
        } else {
            newX = y + oppositeOther;
        }

        segment = new LineSegment(currentCorner.x, currentCorner.y, 0, newX);
    }

    public double toCenteralCoordinatesX() {
        return currentCorner.x + (x - currentCorner.x); //probably don't need this one
    }

    public double toCenteralCoordinateY() {
        return currentCorner.y + (y - currentCorner.y);
    }

    public void move() {
        rectangle.move(toCenteralCoordinatesX() + updateRate, segment.yAt(toCenteralCoordinateY()));

        x += toCenteralCoordinatesX() + updateRate;
        y = toCenteralCoordinateY();
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
