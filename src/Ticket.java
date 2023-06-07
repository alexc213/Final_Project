import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Ticket{
    private static int ticketNumber = 0;
    private Image ticket;
    private ArrayList<JPanel> order;
    private Rectangle rectangle;
    private Point location;
    private boolean onHolder;

    public Ticket(){
       // setSize(100, 100);
        //setLocation(250, 250);
        //setLayout(new GridLayout());
        ticketNumber ++;
        order = new ArrayList<>();
        ImageIcon icon = new ImageIcon("src/ticket.png");
        //icon.setImage(icon.getImage().getScaledInstance());
        ticket = icon.getImage().getScaledInstance(175,390,1);
        location = new Point(790,10);

        rectangle = new Rectangle();
        rectangle.setSize(175,390);
        rectangle.setLocation(790,10);

        onHolder = true;


    }

    public Image getTicket(){
        return ticket;
    }

    public int getX(){
        return location.x;
    }

    public int getY(){
        return location.y;
    }
    public boolean isOnHolder(){
        return onHolder;
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

    public void setLocation(int x,int y){
        location.setLocation(x,y);
        rectangle.setLocation(x,y);
//        if(x == 790 && y == 10){
//            onHolder = true;
//        }
    }

    public void setOnHolder(boolean newOnHolder){
        onHolder = newOnHolder;
    }

//    public void setRectangleSize(int width, int height){
//        rectangle.setSize(width, height);
//    }

    public void shrink(){
        rectangle.setSize(50, 75);
        ImageIcon icon = new ImageIcon("src/ticket.png");
        //icon.setImage(icon.getImage().getScaledInstance());
        ticket = icon.getImage().getScaledInstance(50,75,1);
    }

    public void reset(){
        rectangle.setSize(175, 390);
        ImageIcon icon = new ImageIcon("src/ticket.png");
        //icon.setImage(icon.getImage().getScaledInstance());
        ticket = icon.getImage().getScaledInstance(175,390,1);
    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        if(rectangle.contains(e.getX(),e.getY())){
//            location.setLocation(e.getX(),e.getY());
//            rectangle.setLocation(e.getX(),e.getY());
//        }
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//
//    }
//    public void paint(Graphics gp){
//        super.paint(gp); // must do this!
//        Graphics2D g2d = (Graphics2D) gp;
//
//
//    }
}
