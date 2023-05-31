import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import static java.awt.Font.BOLD;

// a subclass of JPanel; this panel has been designed entirely in code (not using the UI designer)
public class AnimationPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private boolean isClickingOrangeRect;
    private Timer timer;
    private Rectangle orangeRect;

    private Point delta;
    private Timer condimentTimer;
    private boolean condimentTimerOn;
    private ArrayList<Line> stream;
    private double previousX;

    private double previousY;
    private String direction;
    private JLabel label;

    // constructor
    public AnimationPanel(){
        // initialize variables

        isClickingOrangeRect = false;
        // initialize Timer object, responsible for the animation
        timer = new Timer(10, this); // set timer to have 10ms pulses; each pulse triggers an ActionEvent
        timer.start();

        // initialize and set initial positions of red enemy rectangle and orange rectangle
        orangeRect = new Rectangle(25, 75); // create 70x30 user-movable orange rectangle
        orangeRect.setLocation(20, 20); // start orange rectangle at (20, 20)

        addMouseListener(this);  // this panel should listen for mouse clicks
        addMouseMotionListener(this);  // this panel should listen for mouse drags

        condimentTimer = new Timer(5000,this);
        condimentTimerOn = false;

        stream = new ArrayList<>();

        previousX = -1;
        previousY = 0;
        direction = null;

        label = new JLabel();
        add(label);

        ImageIcon icon = new ImageIcon("src/picture.png");
        Image image = icon.getImage();

        icon.setImage(image.getScaledInstance(25,75,1));
        label.setBounds(orangeRect);
        label.setIcon(icon);
        //label.setVisible(true);

        label.setSize(25,75);

    }

    // important method that is inherited from JComponent and overridden;
    // this method automatically gets called by the graphics engine
    // when the engine it detects that the panel needs to be redrawn/updated
    @Override
    public void paint(Graphics gp) {
        super.paint(gp); // must do this!
        Graphics2D g2d = (Graphics2D) gp; // cast gp to a 2D graphics object so we can do more advanced stuff

        // draw blue message on screen
//        g2d.setColor(Color.blue);
//        Font myFont = new Font("Arial", BOLD, 14);
//        g2d.setFont(myFont);
//        String message = "Move orange square to gray! Dodge the red enemy!";
//        if (won) {
//            message = "YOU WIN!!!!";
//        }
//        if (gameOver) {
//            message = "YOU LOSE!!!";
//        }
//        g2d.drawString(message, 100, 100); // write message at location: (x, 100)

        // draw gray target rectangle on the screen
        // since it doesn't move, we can use a simple fillRect rather than create a Rectangle object
//        g2d.setColor(Color.darkGray);
//        g2d.fillRect(300, 350, 70, 30);  // draw a 70x30 gray rectangle at (300, 350)

        // draw red enemy rectangle and orange rectangle on the screen
        g2d.setStroke(new BasicStroke(3)); // change pen thickness
        g2d.setColor(Color.orange);
        //g2d.draw(orangeRect);  // draw the orange rectangle
        label.setLocation(orangeRect.x,orangeRect.y);
        //new ImageObserver;
//        ImageObserver observer = null;
//        g2d.drawImage("picture.png", 100, 100, observer);

        //label.setBounds(100,100,100,100);
        //label.setBounds(orangeRect);
        //label.setLocation(100,100);
        //g2d.drawImag;
//        Oval oval = new Oval();
//        oval.setPoint1Values(250,250);
//        oval.setPoint2Values(250,250);
//        oval.draw(g2d);
            //g2d.fillO

        if(condimentTimerOn){
            Line line = new Line();
            line.setPoint1Values((int)previousX,(int)(previousY+32.5));
            line.setPoint2Values((int)(orangeRect.getX()+12.5),(int)(orangeRect.getY()+75));
            stream.add(line);
        }
        for(Line line : stream){
            g2d.setStroke(new BasicStroke(5)); // change pen thickness
            g2d.setColor(Color.red);
            //g2d.draw((Shape)oval);

            line.draw(g2d);
            //g2d.drawOval(oval.getMinX(),oval.getMinY(),oval.getWidth(),oval.getHeight());
            //g2d.fillOval(oval.getMinX(),oval.getMinY(),oval.getWidth(),oval.getHeight());
            line.setPoint1Values(line.getMinX(),line.getMinY()+5);
            line.setPoint2Values(line.getMinX(),line.getMinY()+5);
        }

    }

    // updates the x value for the string message by adding a small increment each time,
    // and resetting back to the left edge when it moves off the screen


    // updates the x and y values for the red rectangle by adding a small increment each time,
    // and resetting to edges when it goes off the screen
//    private void moveEnemy() {
//        enemyX += 1;
//        enemyY += 2;
//        if (enemyX > getWidth()) {
//            enemyX = 0; // reset enemy location's x-coordinate
//        }
//        if (enemyY > getHeight()) {
//            enemyY = 0; // reset enemy location's y-coordinate
//        }
//        redEnemy.setLocation(enemyX, enemyY);  // update enemy rect to new position
//        checkCollision();  // call helper method to check for a collision with orange rectangle after each movement
//    }

    // there is no easy way to automatically detect if two rectangles touch,
    // so we must write a method to do that
//    private void checkCollision() {
//        double orangeLeft = orangeRect.getX();
//        double orangeRight = orangeLeft + orangeRect.getWidth();
//        double orangeTop = orangeRect.getY();
//        double orangeBottom = orangeTop + orangeRect.getHeight();
//        double redLeft = redEnemy.getX();
//        double redRight = redLeft + redEnemy.getWidth();
//        double redTop = redEnemy.getY();
//        double redBottom = redTop + redEnemy.getHeight();
//        // if the bottom of the red box is between the orange box's top and bottom
//        if (redBottom > orangeTop && redBottom < orangeBottom) {
//            // check if left/right edges of the red rectangle are "between" the left/right edges of orange rectangle
//            if ((redRight > orangeLeft && redRight < orangeRight) || (redLeft < orangeRight && redLeft > orangeLeft)) {
//                gameOver = true;
//            }
//        }
//    }

    // -------------- ActionListener interface method --------------
    // called for each Timer event (occurs every 10ms)
    // The timer + this method causes the animation to occur!
    @Override
    public void actionPerformed(ActionEvent e) {
        // only move the message and the red enemy if the game is not over
//        if (!gameOver && !won) {
//            moveMessage();
//            moveEnemy();
//        }
//        if(condimentTimerOn){
//            double newRectX = event.getX() - orangeRect.getWidth()/2;
//            double newRectY = event.getY() - orangeRect.getHeight()/2;
//            Oval oval = new Oval();
//            double ovalX = event.getX() + orangeRect.getWidth()/2;
//            double ovalY = event.getY() + orangeRect.getHeight()/2;
//            oval.setPoint1Values((int)ovalX,(int)ovalY);
//            oval.setPoint2Values((int)ovalX,(int)ovalY);
//            stream.add(oval);
//            System.out.println(stream);
//        }
        if(e.getSource() instanceof Timer){
            if(e.getSource() == condimentTimer){
                condimentTimerOn = false;
                orangeRect.setLocation(20,20);
                //stream.clear();
            }
        }
//        if (e.getSource() instanceof MouseEvent) {
//            MouseEvent me = (MouseEvent) e.getSource();
//            int x = me.getX();
//        }
        repaint(); // this is an inherited method, and calling it forces the "paint" method above to be re-queued in the graphics engine
    }

    // -------------- MouseListener interface methods (5 required, only 2 used) --------------
    // called when the mouse button is pressed in
    @Override
    public void mousePressed(MouseEvent e) {
        // if the mouse click's coordinate occurred in the orange rectangle, then the user has clicked it
        //
//        ActionEvent ae = new ActionEvent(e.getSource(), 0, "");
//        actionPerformed(ae);

        if (!condimentTimerOn && orangeRect.contains(e.getPoint())) {
            // calculate offset for orange rectangle's movement
            int xDiff = e.getX() - orangeRect.x;
            int yDiff = e.getY() - orangeRect.y;
            delta = new Point(xDiff, yDiff);
            isClickingOrangeRect = true; // track that user has clicked orange rectangle (used below in mouseDragged)
        }

    }

    // called when the mouse button is released
    @Override
    public void mouseReleased(MouseEvent e) {

        // check if the orange rectangle contains the center point of the gray target, if so, the player
        // has won and the orange rectangle should "snap" around the gray square

        //orangeRect.setLocation(20,20);
        if(isClickingOrangeRect && !condimentTimerOn){
            //Point p = new Point(e.getX(),e.getY());
            //Rectangle rectangle = new Rectangle(0,0,40,40);
            if(e.getX()<100 && e.getY()<200){
                orangeRect.setLocation(20,20);
            }else{
                condimentTimerOn=true;
                isClickingOrangeRect = false;
                previousX = e.getX();
                previousY = e.getY();
                condimentTimer.restart();
            }

        }

    }
    @Override
    public void mouseClicked(MouseEvent e) { } // unused but needed for interface

    @Override
    public void mouseEntered(MouseEvent e) { } // unused but needed for interface

    @Override
    public void mouseExited(MouseEvent e) { } // unused but needed for interface

    // -------------- MouseMotionListener interface methods (2 required, only 1 used) --------------
    // called when user has clicked in and is dragging the mouse
    @Override
    public void mouseDragged(MouseEvent e) {
        // if the user is clicking the orange rectangle when the dragging begins, the rectangle should move!
        if (isClickingOrangeRect || condimentTimerOn) {
            previousX = e.getX();
            previousY = e.getY();
            double newRectX = e.getX() - orangeRect.getWidth()/2;
            double newRectY = e.getY() - orangeRect.getHeight()/2;
            orangeRect.setLocation((int)newRectX, (int)newRectY);
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(condimentTimerOn){
            previousX = e.getX();
            previousY = e.getY();
            double newRectX = e.getX() - orangeRect.getWidth()/2;
            double newRectY = e.getY() - orangeRect.getHeight()/2;
            orangeRect.setLocation((int)newRectX, (int)newRectY);
            //currentX = newRectX;

//            Line line = new Line();
//            line.setPoint1Values((int)previousX,(int)(previousY+32.5));
//            line.setPoint2Values((int)(orangeRect.getX()+12.5),(int)(orangeRect.getY()+75));
//            stream.add(line);
            repaint();
        }
    }
}