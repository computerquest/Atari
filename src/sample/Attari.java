package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Random;

//so i need teo find the placement of the y at the x sid eof the rectangle and then i do the same for the enteirety of the rest of the rectangle and that will give me where it hit
//at the x position of the rectangle the y would have to be within bounds
//we would use forward and backward to first judge what side hit first
//the top and botton will be determined in a similar fashion after it is determined if the sides are being hit
//i could also use the direction to do the trig for the collisions
public class Attari {
    LinkedList<Rectangle> rectangle = new LinkedList();
    Ball ball;
    Slider slider;
    GraphicsContext graphics;
    Text text;
    Canvas canvas; // need to make everything use canvas and get rid of canvas in the other classes
    boolean end = false;
    Timeline interval = new Timeline();
    public Attari(Canvas input) {
        canvas = new Canvas(750, 400);
        graphics = canvas.getGraphicsContext2D();
        slider = new Slider(graphics);
        ball = new Ball(graphics);

        interval = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                moveBall();
            }
        }));
        interval.setCycleCount(Timeline.INDEFINITE);
        interval.play();
    }

    public void moveBall() {
        ball.move();
        collisionDetection();
    }

    public void moveSlider(double x) {
        slider.move(x);
        collisionDetection();
    }

    public void collisionDetection() {
        double sliderContact = slider.hit(ball.centerPos.x, ball.endX, ball.centerPos.y, ball.bottom);

        if (sliderContact != -100) {
            System.out.println("hit the slider");
            //need to get the hit position on the slider based on the current corner of the ball
            ball.onSliderContact(sliderContact, slider.centerXPos);
            ball.move();
            return;
        }

        if (ball.bottom <= 0) {
            end = true;
            interval.stop();
        }

        double beginX = ball.centerPos.x;
        double endX = ball.endX;
        double topY = ball.centerPos.y;
        double bottomY = ball.bottom;

        if (ball.endX > canvas.getWidth() | ball.centerPos.x < 0) {
            System.out.println("hit side");

            ball.centerPos.x = ball.endX > canvas.getWidth() ? canvas.getWidth() - ball.width : 0;
            ball.centerPos.y = ball.segment.yAt(ball.centerPos.x);

            ball.onSideContact(canvas.getWidth(), canvas.getHeight());
        } else if (ball.centerPos.y < 0 | ball.centerPos.y > canvas.getHeight()) {
            System.out.println("hit top");
            ball.centerPos.y = canvas.getHeight();
            ball.onTopContact(canvas.getWidth(), canvas.getHeight());
        }

        for (int i = 0; i < rectangle.size(); i++) {
            Rectangle currentNode = rectangle.get(i);
            if (((beginX >= currentNode.x & beginX <= currentNode.x + currentNode.width) | (endX >= currentNode.x & endX <= currentNode.x + currentNode.width))
                    && ((topY >= currentNode.bottom & topY <= currentNode.y) | (bottomY >= currentNode.bottom & bottomY <= currentNode.y))) {
                System.out.println("hit brick");
                
                //need to find if it is hitting top or side
                //i think i need a on bottom contact for when it is hitting bricks from the top
                /*
                if the bottom is inside the top of the brick then it is coming from the top
                if the brick is getting hit by only one side and the y
                 */
                double cornerXdif = ball.centerPos.x - ball.currentCorner.x; //probably don't need this one
                double cornerYdif = ball.centerPos.y - ball.currentCorner.y;

                double width = ball.width;

                double yAt = ball.segment.yAt(currentNode.x);
                double yAtEnd = ball.segment.yAt(currentNode.x + currentNode.width);

                double yAtN = yAt; // yat the left side
                double yAtL = yAt - ball.height; //endy on the left side
                double yAtNEnd = yAtEnd; // yat the end
                double yAtEndL = yAtEnd - ball.height; // endy on the end

                double currentNodeEndY = currentNode.y - currentNode.height;


                //these guys work really well but are part of a bug where if it hits to segment is horizontal because it just updated for the last one
                boolean yChecker = (yAtN <= currentNode.y & yAtN >= currentNode.y - currentNode.height) | (yAtL <= currentNode.y & yAtL >= currentNode.y - currentNode.height) |
                        (yAtNEnd <= currentNode.y & yAtNEnd >= currentNode.y - currentNode.height) | (yAtEndL <= currentNode.y & yAtEndL >= currentNode.y - currentNode.height);
                yChecker = (ball.centerPos.y <= currentNode.y & ball.centerPos.y >= currentNodeEndY) | (ball.bottom <= currentNode.y & ball.bottom >= currentNodeEndY);
                boolean xChecker = (ball.centerPos.x >= currentNode.x & ball.centerPos.x <= currentNode.x + currentNode.width & ball.endX >= currentNode.x & ball.endX <= currentNode.x + currentNode.width);

                //this was is super easy and works well except that current corner can ignore somethings
                double xDif = 0;
                double yDif = 0;
                if (ball.segment.slope > 0 & ball.forward == true) {
                    xDif = Math.abs(ball.endX - currentNode.x);
                    yDif = Math.abs(ball.centerPos.y - currentNode.bottom);
                } else if (ball.segment.slope < 0 & ball.forward == true) {
                    xDif = Math.abs(ball.endX - currentNode.x);
                    yDif = Math.abs(ball.bottom - currentNode.y);
                } else if (ball.segment.slope < 0 & ball.forward == false) {
                    xDif = Math.abs(ball.centerPos.x - currentNode.x + currentNode.width);
                    yDif = Math.abs(ball.centerPos.y - currentNode.y - currentNode.height);
                } else if (ball.segment.slope > 0 & ball.forward == false) {
                    xDif = Math.abs(ball.centerPos.x - currentNode.x + currentNode.width);
                    yDif = Math.abs(ball.centerPos.y - currentNode.y);
                }

                if (xDif < yDif) {//if (yChecker & !xChecker) {
                    System.out.println("hit brick side");
                    ball.onSideContact(canvas.getWidth(), canvas.getHeight());
                } else {//if (yChecker & xChecker) {
                    System.out.println("hit brick top");
                    ball.onTopContact(canvas.getWidth(), canvas.getHeight());
                }

                rectangle.get(i).delete();
                ball.rectangle.reDraw();
                return; //i could add a que for the ball for the udated variables so they dont change until all the collisions are reported to fix the bug
            }
        }
    }

    public void setUp() {
        //rectangle.add(new Rectangle(graphics, 330, 340, 40, 26, true));
        text = new Text(graphics, 338, 50, "0");
        //starting line for drawing
        int y = 340;
        //the initial color (will be changed by row)
        graphics.setFill(Color.RED);

        //method used for drawing
        for (int row = 0; row < 5; row++, y = y - 36) {
            //5 rows

            int x = 5;
            Random rand = new Random();
            //adds the rectangles left to right
            for (int box = 0; box <= 15; box++, x = x + 50) {
                //draws the rectangle and adds it to the array
                rectangle.add(new Rectangle(graphics, x, y, 40, 32, Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)), true));
            }
        }

        //makes ball
        graphics.setFill(Color.GREY);
        //makes slide
        System.out.println("Set up is complete");
    }
}
