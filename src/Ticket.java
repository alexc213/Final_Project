import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ticket{
    private static int totalTickets = 0;
    private int ticketNumber;
    private Image ticket;
    private ArrayList<Image> order;
    private ArrayList<Point> orderLocation;
    private ArrayList<String> orderName;
    private ArrayList<String> allOptions;
    private Rectangle rectangle;
    private Point location;
    private boolean onHolder;
    private String meatType;
    private boolean shrunk;

    public Ticket(){
        totalTickets ++;
        //ticketNumber = totalTickets;
        for(int i = 0;i<totalTickets;i++){
            ticketNumber ++;
        }
        orderName = new ArrayList<>();
        allOptions = new ArrayList<>();
        allOptions.add("cheese");
        allOptions.add("lettuce");
        allOptions.add("onion");
        allOptions.add("pickle");
        allOptions.add("tomato");
        allOptions.add("ketchupSplatter");
        allOptions.add("mayoSplatter");
        allOptions.add("bbqSplatter");
        allOptions.add("mustardSplatter");
        int cooked = (int)(Math.random()*3)+1;
        if(cooked == 1){
            allOptions.add("high");
            meatType = "high";
        }else if(cooked == 2){
            allOptions.add("medium");
            meatType = "medium";
        }else{
            allOptions.add("low");
            meatType = "low";
        }
        order = new ArrayList<>();
        int length = (int)(Math.random()*7)+3;
        int pattyLocation = (int)(Math.random()*(length-2))+1;
        int pattyCount = 0;
        for(int i = 0;i<length;i++){
            String image = "src/";
            if(i==0){
                image += "bottomBun";
            } else if(i == length-1){
                image += "topBun";
            } else if(i == pattyLocation){
                image += meatType;
            } else{
                image += allOptions.get((int)(Math.random()*allOptions.size()));
            }
            image+=".png";
            orderName.add(image);
            ImageIcon icon = new ImageIcon(image);
            if(image.equals("src/" + meatType + ".png")) {
                pattyCount++;
                order.add(icon.getImage().getScaledInstance(175,35,1));
            } else{
                //order.add(icon.getImage());
                order.add(icon.getImage().getScaledInstance(50,25,1));
            }
            if(pattyCount >= 2){
                allOptions.remove(meatType);

            }
            //System.out.println(allOptions);
            //order.add(image);



            //order.add(icon.getImage().getScaledInstance(25,25,1));
        }
        //System.out.println(pattyCount);
        //orderLocation = new ArrayList<>();

        ImageIcon icon = new ImageIcon("src/ticket.png");
        //icon.setImage(icon.getImage().getScaledInstance());
        ticket = icon.getImage().getScaledInstance(175,390,1);
        location = new Point(790,10);

        rectangle = new Rectangle();
        rectangle.setSize(175,390);
        rectangle.setLocation(790,10);

        onHolder = true;
        shrunk = false;
        //System.out.println(order);




    }
    public String getMeatType(){
        return meatType;
    }
    public boolean isShrunk(){
        return shrunk;
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
    public ArrayList<String> getOrderName(){
        return orderName;
    }
    public boolean isOnHolder(){
        return onHolder;
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

    public ArrayList<Image> getOrder(){
        return order;
    }
    public int getTicketNumber(){
        return ticketNumber;
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
        shrunk = true;
        for(int i = 0;i<order.size();i++) {
            if(orderName.get(i).contains(meatType)){
                icon = new ImageIcon(orderName.get(i));
                icon.setImage(icon.getImage().getScaledInstance(50,5,1));
                order.set(i, icon.getImage());
            } else{
                icon = new ImageIcon(orderName.get(i));
                icon.setImage(icon.getImage().getScaledInstance(10,5,1));
                order.set(i, icon.getImage());
            }

        }

    }

    public void reset(){
        rectangle.setSize(175, 390);
        ImageIcon icon = new ImageIcon("src/ticket.png");
        //icon.setImage(icon.getImage().getScaledInstance());
        ticket = icon.getImage().getScaledInstance(175,390,1);
        shrunk = false;
        for(int i = 0;i<order.size();i++) {
            if(orderName.get(i).contains(meatType)){
                ImageIcon tempIcon = new ImageIcon(orderName.get(i));
                tempIcon.setImage(tempIcon.getImage().getScaledInstance(175,35,1));
                order.set(i, tempIcon.getImage());
            } else{
                ImageIcon tempIcon = new ImageIcon(orderName.get(i));
                tempIcon.setImage(tempIcon.getImage().getScaledInstance(50,25,1));
                order.set(i, tempIcon.getImage());
            }

        }
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
