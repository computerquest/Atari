package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    public Attari(Canvas input) {
        canvas = new Canvas(750, 400);
        graphics = canvas.getGraphicsContext2D();
        slider = new Slider(graphics);
        ball = new Ball(graphics);
    }

    public void moveBall() {
        System.out.println("moving the ball right now");
        ball.move();
        collisionDetection();
    }

    public void moveSlider(double x) {
        slider.move(x);
        collisionDetection();
    }

    public void collisionDetection() {
        double sliderContact = slider.hit(ball.x, ball.width, ball.bottom);

        if (sliderContact != -100) {
            //need to get the hit position on the slider based on the current corner of the ball
            ball.onSliderContact(sliderContact, slider.centerXPos);
            return;
        }

        double beginX = ball.x;
        double endX = ball.endX;
        double topY = ball.y;
        double bottomY = ball.bottom;

        if (ball.endX > canvas.getWidth() | ball.x < 0) {
            ball.onSideContact(canvas.getWidth(), canvas.getHeight());
        }

        if (ball.y < 0 | ball.y > canvas.getHeight()) {
            ball.onTopContact(canvas.getWidth(), canvas.getHeight());
        }

        for (int i = 0; i < rectangle.size(); i++) {
            Rectangle currentNode = rectangle.get(i);
            if (beginX <= currentNode.x && endX >= currentNode.x && (topY <= currentNode.bottom & topY >= currentNode.y | bottomY <= currentNode.bottom & bottomY >= currentNode.y)) {
                Rectangle rect = rectangle.get(i);

                //need to find if it is hitting top or side
                //i think i need a on bottom contact for when it is hitting bricks from the top
                /*
                if the bottom is inside the top of the brick then it is coming from the top
                if the brick is getting hit by only one side and the y
                 */
                double cornerXdif = ball.x - ball.currentCorner.x; //probably don't need this one
                double cornerYdif = ball.y - ball.currentCorner.y;

                double width = ball.width;

                double yAt = ball.segment.yAt(rect.x);
                double yAtEnd = ball.segment.yAt(rect.x + rect.width);

                double yAtN = yAt;
                double yAtL = yAt - ball.height;
                double yAtNEnd = yAtEnd;
                double yAtEndL = yAtEnd - ball.height;

                boolean yChecker = (yAtN <= rect.y & yAtN > rect.y - rect.height) | (yAtL <= rect.y & yAtL > rect.y - rect.height) |
                        (yAtNEnd <= rect.y & yAtNEnd > rect.y - rect.height) | (yAtEndL <= rect.y & yAtEndL > rect.y - rect.height);
                boolean xChecker = !(ball.x >= rect.x & ball.x <= rect.x + rect.width & ball.endX >= rect.x & ball.endX <= rect.x + rect.width);

                if (yChecker & xChecker) {
                    ball.onSideContact(canvas.getWidth(), canvas.getHeight());
                } else if (yChecker & !xChecker) {
                    ball.onTopContact(canvas.getWidth(), canvas.getHeight());
                }
                rectangle.get(i).delete();
            }
        }
    }

    public void setUp() {
        //rectangle.add(new Rectangle(graphics, 330, 340, 40, 26, true));
        text = new Text(graphics, 338, 50, "0");
        //starting line for drawing
        int y = 220;
        //the initial color (will be changed by row)
        graphics.setFill(Color.RED);

        //method used for drawing
        for (int row = 0; row < 5; row++, y = y + 30) {
            //5 rows
            int x = 5;

            //for changing color by row
            switch (y) {
                //was 60
                case 220:
                    graphics.setFill(Color.ORANGE);
                    break;
                case 190:
                    graphics.setFill(Color.BLUE);
                    break;
                case 160:
                    graphics.setFill(Color.GREEN);
                    break;
                case 130:
                    graphics.setFill(Color.YELLOW);
                    break;
            }

            Random rand = new Random();
            //adds the rectangles left to right
            for (int box = 0; box <= 15; box++, x = x + 50) {
                //draws the rectangle and adds it to the array
                rectangle.add(new Rectangle(graphics, x, y, 40, 26, Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)), true));
            }
        }

        //makes ball
        graphics.setFill(Color.GREY);
        //makes slide
        System.out.println("Set up is complete");
    }
}
