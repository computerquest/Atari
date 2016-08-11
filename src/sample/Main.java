package sample;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

//at some point slider doesn't do anything along with that blocks hit in weird ways
//hitting 2 on the side
/*end method
 *set everything to null
 *delete the rectangles
 *call set up method again
 *wait until hit restart
 */
/*ending
 *transparent background
 *end thread
 *printed score
 *restart button
 */
/*wishlist
 * scoreboard
 * score time based if all blocks are gone
 * possibly screen shot
 */
//top of rectangleyBall > contact.get(0).y
//bottom of rectangle yBall < contact.get(0).y+26
//yball is most likely the top of ball
//yall+26 == top (listed above)

/*class Text {
    int x;
    int y;
    int height;
    int width;
    String message;
    GraphicsContext graphics;

    public Text(GraphicsContext graphics, int width, int height, String message) {
        graphics.setFont(new Font ("ARAIL", 50));
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

class Rectangle {
    //x position
    int x;
    //y position
    int y;
    //size for width
    int xSize;
    //size for height
    int ySize;
    //to see if there is a fill or not
    boolean fill;
    GraphicsContext graphics;

    //used to create a rectangle object
    public Rectangle(GraphicsContext graphics, int x, int y, int xSize, int ySize, boolean fill) {
        //sets fields used for the rectangle
        this.fill = fill;
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
        this.graphics = graphics;

        //to fill or not a rectangle
        //used so that you can use this for the slider and ball as well
        if(fill == true) {
            graphics.fillRect(x, y, xSize, ySize);
        }
        //for the slider and ball
        else {
            graphics.setLineWidth(1);
            graphics.setStroke(Color.RED);
            graphics.strokeRect(x, y, xSize, ySize);
        }
    }

    //the hit detection system
    public LinkedList<Rectangle> hit(Rectangle rectangle, LinkedList<Rectangle> rectangleArray) {
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
    }

    //used to get rid of rectangles
    public void delete(Rectangle rectangle) {
        //erase the rectangle
        (rectangle.graphics).clearRect(rectangle.x, rectangle.y, rectangle.xSize, rectangle.ySize);

        //used to make sure they can't be hit when checking
        rectangle.x = -1;
        rectangle.y = -1;
        rectangle.xSize = -1;
        rectangle.ySize = -1;
    }

    //will redraw the rectangle
    public void reDraw(Rectangle rectangle) {
        (rectangle.graphics).fillRect(rectangle.x, rectangle.y, rectangle.xSize, rectangle.ySize);
    }

    //will move the square
    public void move(Rectangle rectangle, int x, int y) {
        //delete (image)
        (rectangle.graphics).clearRect(rectangle.x, rectangle.y, rectangle.xSize+1, rectangle.ySize+1);

        //check for the fill
        if(rectangle.fill == true) {
            (rectangle.graphics).setFill(Color.GREY);
            (rectangle.graphics).fillRect(x, y, rectangle.xSize, rectangle.ySize);
        }
        else {
            (rectangle.graphics).setStroke(Color.RED);
            (rectangle.graphics).strokeRect(x, y, rectangle.xSize, rectangle.ySize);
        }

        //redifine the fields
        this.x = x;
        this.y = y;
    }
}*/

public class Main extends Application {

    /*//x position of slide
    static int xSlide = 275;
    //y position of slide
    static int ySlide = 380;
    //height of slide
    static int heightSlide = 5;
    //width of slide
    static int widthSlide = 150;

    static int score = 0;

    //where the ball touches
    static float contactX = 0;
    //the speed of the ball(Y)
    static float speed = (float) -.4;
    //movement of x
    static float slope;

    //the secondary thread
    static Thread t;

    //the thing you draw on
    static Canvas canvas = new Canvas(750, 400);
    //the way you draw
    static GraphicsContext graphics = canvas.getGraphicsContext2D();
    static Stage stage;
    //x slope round
    static int slopeRound;
    //position of x for ball
    static int xBall = 330;
    //position of y for ball
    static int yBall = 280;
    //height of ball
    static int heightBall = 31;
    //width of ball
    static int widthBall = 31;
    //speed of x
    static float speedX = (float) .04;
    //position of slide
    static int slideRefernce = 0;
    //ball object in rectangle
    static Rectangle ball;
    //slide object in rectangle
    static Rectangle slide;
    static Text text;
    //contains the rest of the rectangle
    static LinkedList<Rectangle> rectangle = new LinkedList();

    //used to setup the canvas (draw other rects, slider, ball)
    public static void setUp(GraphicsContext graphics) {
        //rectangle.add(new Rectangle(graphics, 330, 340, 40, 26, true));
        text = new Text(graphics, 338, 50, "0");
        //starting line for drawing
        int y = 60;
        //the initial color (will be changed by row)
        graphics.setFill(Color.RED);

        //method used for drawing
        for(int row = 0; row < 5; row++, y=y+30) {
            //5 rows
            int x = 5;

            //for changing color by row
            switch(y) {
                //was 60
                case 90:
                    graphics.setFill(Color.ORANGE);
                    break;
                case 120:
                    graphics.setFill(Color.BLUE);
                    break;
                case 150:
                    graphics.setFill(Color.GREEN);
                    break;
                case 180:
                    graphics.setFill(Color.YELLOW);
                    break;
            }

            //adds the rectangles left to right
            for(int box = 0; box <= 15; box++, x=x+50) {
                //draws the rectangle and adds it to the array
                rectangle.add(new Rectangle(graphics, x, y, 40, 26, true));
            }
        }

        //makes ball
        ball = new Rectangle(graphics, xBall, yBall, 30, 30, false);
        graphics.setFill(Color.GREY);
        //makes slide
        slide = new Rectangle(graphics, xSlide, ySlide, widthSlide, heightSlide, true);
        slideRefernce = xSlide+ 75;
    }

    //used for moving the slider
    public static void move(GraphicsContext graphics, int x) {
        //used to make it so the mouse is in the center
        if(x <= 75) {
            x = 75;
        }
        x = x <= 675 && x >= 75? x-75: 600;
        //sets xSlide
        xSlide = x;
        //sets up slideRefernce
        slideRefernce = x+ 75;
        //moves slide
        slide.move(slide, x, ySlide);
    }

    //checks for where the ball hits on the slider
    public static float hit(int x, int y) {
        //default to say false
        int answer = -2;

        //checks the edges and hit level
        if(x == 0 || x == 720 || y == 344 || y == 0) {
            //if the ball is at the slider
            if(y == 344) {
                //subtract slideRefernce from the xBall to get where it hits
                answer = xBall - slideRefernce;
            }
        }

        return Math.round(answer);
    }

    //calculate slope
    public static void update() {
        //if contact x has a hit
        if(contactX != 0) {
            //calculate slope
            slope = (float) speedX*contactX;

            //for rounding slope so you can use it with xBall
            if(slope > 0) {
                slopeRound = Math.round(slope) == 0 ? 1: Math.round(slope);
            }

            else {
                slopeRound = Math.round(slope) == 0 ? -1: Math.round(slope);
            }
        }


    }

    //boolean for seeing if there is a hit
    public  boolean contact(Rectangle rectangle) {
        System.out.println("ten for contact");
        System.out.println(this.rectangle.size());
        //default of false
        boolean answer = false;

        //general check
        if(rectangle.y <= 210) {
            //same process as hit in the rectangle class only you check for one because if you hit that their is no need to see if their is others
            for(int i = 0; i < this.rectangle.size(); i++) {
                //System.out.println("the array x value is "+ this.rectangle.get(i).x +" the y value of this is "+ this.rectangle.get(i).y);
                //System.out.println("the value of yBall is "+ rectangle.y +" the value of xBall is "+ rectangle.x);
                //there was a plues one for condition 4
                if(rectangle.x >= this.rectangle.get(i).x-40 && rectangle.x <= this.rectangle.get(i).x + 40 && rectangle.x != -1 && rectangle.y+30 >= this.rectangle.get(i).y && rectangle.y <= this.rectangle.get(i).y+26){
                    System.out.println("VICTORY");
                    answer = true;
                    break;
                }
            }
        }

        //to see if it is hitting the boarders
        if(xBall <= 0 || xBall >= 720 || !(yBall > 0 && yBall < 344)) {
            answer = true;
        }

        return answer;
    }

    public static void score(Rectangle rectangle, LinkedList<Rectangle> rectangleArray) {
        int change = 0;
        for(int i = rectangleArray.size()-1; i >= 0; i--) {
            //bottom
            if(yBall > rectangleArray.get(i).y) {
                change += 10;
            }

            //side
            if(yBall < rectangleArray.get(i).y+26 && yBall > rectangleArray.get(i).y) {
                change += 30;
            }

            //top
            if(yBall+30 < rectangleArray.get(i).y) {
                change += 20;
            }
        }

        if(rectangleArray.size() > 1) {
            change *= 2;
        }

        score += change;
        text.update(""+score +"");
        System.out.println("***********************************************************"+ score +"******************************************************");
    }

    public static void end() throws Exception {
        HBox endGame = new HBox();
        Label endMessage = new Label("To think I let you play this game "+ score +" geez...");
        endGame.getChildren().add(endMessage);
        Scene scene2 = new Scene(endGame);
        stage.setScene(scene2);
    }

    //used to move the ball does all the most compilated method
    public static void moveBall(GraphicsContext graphics) {
        //get the value on the slider
        float hit = (float) hit(xBall, yBall);
        //tells where it hits
        contactX = hit == -2 ? 0: hit;
        //checks for contact
        boolean touching = new Main().contact(ball);
        //updates slope
        update();

        //for when it is hitting nothing
        if(yBall > 0 && yBall < 344 && xBall > 0 && xBall < 720 && touching == false){
            System.out.println("");
            System.out.println("primary route");

            //does simple calculations  for x and sometimes y
            //automaticly does the y rounding
            if(speed > 0) {
                //DONT DELETE this semes to be correct but doesn't work yBall = speed%1 != 0 ? yBall +1: (int) (yBall+speed);
                yBall = speed%1 != 0 ? yBall -1: (int) (yBall-speed);
            }
            else {

                //DONT DELETE semes to be correct but doesn't work yBall = speed%1 != 0 ? yBall -1: (int) (yBall+speed);
                yBall = speed%1 != 0 ? yBall +1: (int) (yBall+speed);
            }

            //xBall changing
            xBall = slope%1 == 0 && slope != 0 ?  (int) (xBall+slope): xBall + slopeRound;
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
                    if(speed < 0 && yBall <= contact.get(0).y+26) {
                        System.out.println("this shoulnd't be happening");
                        speed *= -1;
                    }

                    System.out.println(yBall);
                    System.out.println(contact.get(0).y);
                    if(yBall +30 >= contact.get(0).y && yBall <= contact.get(0).y) {
                        System.out.println("going around the world");
                        speed *= -1;
                    }

                    speed *= -1;


                    //to see if it hits the edge so that if so it can change slope
                    System.out.println("contact y "+ contact.get(0).y);
                    System.out.println("yball is "+ yBall);
                    if(yBall > contact.get(0).y && yBall < contact.get(0).y+26) {
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
                if(xBall == 720 || xBall == 0) {
                    slope *= -1;
                }

                //if it is hitting the edges change slope
                if(yBall >= 344 || yBall <= 0) {
                    speed *= -1;
                }

                //to take it off the border
                if(speed > 0) {
                    yBall -= 1;
                }
                //take it off the boarder
                else {
                    yBall += 1;
                }

                //take it off the boarder and round slope
                if(xBall <= 0) {
                    slopeRound = Math.round(slope) == 0 ? 1: Math.round(slope);
                    xBall += 1;
                }

                //take it off border and round slope
                else {
                    slopeRound = Math.round(slope) == 0 ? -1: Math.round(slope);
                    xBall -= 1;
                }
            }
        }

        //to make sure it stays within its boundries
        if(xBall < 0) {
            xBall = 0;
        }

        //to make sure it stays within its boundries
        if(xBall > 720) {
            xBall = 720;
        }

        System.out.println("the value of xBall is "+ xBall);
        System.out.println("the value of yBall is "+ yBall);

        System.out.println(yBall+30 == 344);
        System.out.println(xSlide < xBall+30);
        System.out.println(xSlide+150 > xBall);
        System.out.println(yBall+30 == 344 && xSlide < xBall+30 || yBall+30 == 344 && xSlide+150 > xBall);
        System.out.println(xSlide);
        if(yBall == 344 && xSlide < xBall+30 || yBall+30 == 344 && xSlide+150 > xBall) {
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
        ball.move(ball, xBall, yBall);
    }*/
    static Thread t;

    static Canvas canvas = new Canvas();
    static Attari attari;

    //starter method for javafx
    public void start(Stage arg0) throws Exception {
        attari = new Attari(canvas);

/////////////////////////////////////nodes///////////////////////////////////////////
        Stage stage = new Stage();
        HBox hbox = new HBox();

        attari.canvas.setOnMouseMoved(e -> {
            attari.moveSlider(e.getX());
        });
////////////////////generate elements
        attari.setUp();
///////////////////////////////////////add elements///////////////////////////////////////
        hbox.getChildren().add(attari.canvas);

//////////////////////////////////////////////////events/////////////////////////////
/////////////////////////////////////////final elements//////////////////////////////////
        Scene scene = new Scene(hbox, 750, 400);

        stage.setScene(scene);
        stage.setTitle("atari breakout");
        stage.show();

    }

    //main method
    public static void main(String[] args) {

        //setting up a new thread
       /* t = new Thread( () -> {
            while (! Thread.currentThread().isInterrupted()) {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        attari.moveBall();
                    }
                });
                try {
                    //default 25
                    //the interval for the thread
                    t.sleep(25);
                } catch (InterruptedException exc) {
                    break ;
                }
            }
        });
        t.start();*/
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                attari.moveBall();
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
        launch();
    }
}
