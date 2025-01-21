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

    private JButton order;
    private JButton grill;
    private JButton build;
    private Image background;
    private String backgroundName;
    private Ticket ticket;
    private boolean holdingTicket;
    private Ticket heldTicket;
    private ArrayList<Ticket> allTickets;
    private Timer customerTimer;
    private ArrayList<Customer> allCustomers;
    private ArrayList<Customer> orderedCustomers;
    private JButton orderButton;
    private Patty[][] stove;
    private Point meatLocation;
    private Patty pattyHeld;
    private ArrayList<Patty> allPatties;
    private boolean holdingMeat;
    private int previousRow;
    private int previousColumn;
    private boolean holdingTopping;
    private String toppingHeld;
    private Point toppingLocation;
    private ArrayList<Topping> allToppings;
    private Rectangle redRect;
    private Rectangle yellowRect;
    private Rectangle brownRect;
    private Rectangle whiteRect;
    private boolean holdingCondiment;
    private String condimentHeld;
    private boolean end;
    private ArrayList<Patty> donePatties;
    private double money;
    private ArrayList<Glide> reset;


    public AnimationPanel(){

        ImageIcon backgroundIcon = new ImageIcon("src/orderRoom.png");
        Image backgroundImage = backgroundIcon.getImage();
        backgroundIcon.setImage(backgroundImage.getScaledInstance(1000,600,2));
        background = backgroundIcon.getImage();
        backgroundName = "order";

        isClickingOrangeRect = false;

        timer = new Timer(10, this);
        timer.start();
        customerTimer = new Timer((int)(Math.random()*15000)+30000,this);
        customerTimer.start();
        allCustomers = new ArrayList<>();
        Customer customer = new Customer();
        allCustomers.add(customer);
        orderedCustomers = new ArrayList<>();


        orangeRect = new Rectangle(25, 75);
        orangeRect.setLocation(20, 20);

        addMouseListener(this);
        addMouseMotionListener(this);

        condimentTimer = new Timer(5000,this);
        condimentTimerOn = false;

        stream = new ArrayList<>();

        previousX = -1;
        previousY = 0;
        direction = null;

        holdingTicket = false;
        allTickets = new ArrayList<>();
        heldTicket = null;

        holdingMeat = false;
        stove = new Patty[4][3];
        meatLocation = null;
        pattyHeld = null;
        allPatties = new ArrayList<>();
        previousRow = -1;
        previousColumn = -1;
        donePatties = new ArrayList<>();

        holdingTopping = false;
        toppingHeld = null;
        toppingLocation = null;
        allToppings = new ArrayList<>();
        redRect = new Rectangle(75,175);
        yellowRect = new Rectangle(75,175);
        brownRect = new Rectangle(75,175);
        whiteRect = new Rectangle(75,175);
        redRect.setLocation(650,400);
        yellowRect.setLocation(725,400);
        brownRect.setLocation(800,400);
        whiteRect.setLocation(875,400);
        holdingCondiment = false;
        condimentHeld = null;
        end = false;
        reset = new ArrayList<>();

        money = 0.0;


        orderButton = new JButton(new ImageIcon("src/orderButton.png"));
        add(orderButton);
        orderButton.setLocation(900,650);
        orderButton.setSize(50,50);
        orderButton.addActionListener(this);
        orderButton.setVisible(false);

        order = new JButton(new ImageIcon("src/order.png"));
        grill = new JButton(new ImageIcon("src/grill.png"));
        build = new JButton(new ImageIcon("src/build.png"));

        add(order);
        add(grill);
        add(build);
        order.setLocation(75,650);
        grill.setLocation(225,650);
        build.setLocation(475,650);

        order.setSize(130,50);
        grill.setSize(130,50);
        build.setSize(130,50);

        order.addActionListener(this);
        grill.addActionListener(this);
        build.addActionListener(this);

        ticket = new Ticket();

    }


    @Override
    public void paint(Graphics gp) {

        super.paint(gp);
        Graphics2D g2d = (Graphics2D) gp;

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.orange);

        g2d.drawImage(background, 0,0,null);

        g2d.setColor(Color.BLACK);
        g2d.fillOval(750,-125,300,250);
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval(765,-125,275,230);

        g2d.drawLine(0,10,750,10);
        g2d.drawString("Drag Ticket Here",840,60);
        g2d.drawString("Money : " + ((int)(money*100))/100.0,875,650);

        if(condimentTimerOn){
            if(previousY<=375){
                Line line = new Line();
                line.setPoint1Values((int)previousX,(int)(previousY+32.5));
                line.setPoint2Values((int)(orangeRect.getX()+12.5),(int)(orangeRect.getY()+75));
                stream.add(line);
            }

        }
        for(Line line : stream){
            g2d.setStroke(new BasicStroke(5));
            g2d.setColor(Color.red);

            line.draw(g2d);

            line.setPoint1Values(line.getMinX(),line.getMinY()+5);
            line.setPoint2Values(line.getMinX(),line.getMinY()+5);
        }

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,600,1000,5);
        order.setLocation(185,625);
        grill.setLocation(435,625);
        build.setLocation(685,625);
        order.setSize(130,75);
        grill.setSize(130,75);
        build.setSize(130,75);

        orderButton.setLocation(225,200);
        for(int i = 0;i<allCustomers.size();i++){
            Customer customer = allCustomers.get(i);
            if(backgroundName.equals("order")) {
                g2d.drawImage(customer.getCustomer(), customer.getX(), customer.getY(), null);
            }
            if(customer.getX() > i*100+200){
                customer.setX(customer.getX()-5);
            } else if(customer.getX()<=200){
                if(backgroundName.equals("order")){
                    g2d.drawImage(new ImageIcon("src/orderButton.png").getImage(),235,200,null);
                    orderButton.setVisible(true);
                } else{
                    orderButton.setVisible(false);
                }
                orderButton.setLocation(225,200);
            }
        }
        for(int i = 0;i<orderedCustomers.size();i++){
            Customer customer = orderedCustomers.get(i);
            if(backgroundName.equals("order")) {
                g2d.drawImage(customer.getCustomer(), customer.getX(), customer.getY(), null);
            }
            if(customer.isLeaving()){
                if(customer.getX()>=1000 && !customer.isGotFood()){
                    customer.setLeaving(false);
                    customer.setY(customer.getY()-60);
                } else{
                    customer.setX(customer.getX() + 5);
                }
            } else{
                if(customer.getX() > i*100+300) {
                    customer.setX(customer.getX() - 5);
                }
            }
        }

        if(backgroundName.equals("grill")){
            ImageIcon meatStack = new ImageIcon("src/meatStack.png");
            meatStack.setImage(meatStack.getImage().getScaledInstance(175,150,1));
            g2d.drawImage(meatStack.getImage(),15,425,null);

            if(holdingMeat){
                if(pattyHeld != null){
                    ArrayList<Image> images = pattyHeld.getPatty();
                    g2d.drawImage(images.get(0),(int)(meatLocation.getX()-80), (int)(meatLocation.getY()-40),null);
                    if(images.get(1) != null){
                        g2d.drawImage(images.get(1),(int)(meatLocation.getX()-78), (int)(meatLocation.getY()-37),null);
                    }
                    g2d.drawImage(images.get(2),(int)(meatLocation.getX()-78),  (int)(meatLocation.getY()-15),null);
                } else{
                    ImageIcon b = new ImageIcon("src/rawPatty.png");
                    b.setImage(b.getImage().getScaledInstance(160,80,1));
                    g2d.drawImage(b.getImage(),(int)(meatLocation.getX()-80), (int)(meatLocation.getY()-40),null);

                    b = new ImageIcon("src/rawBottom.png");
                    b.setImage(b.getImage().getScaledInstance(160,60,1));
                    g2d.drawImage(b.getImage(),(int)(meatLocation.getX()-78), (int)(meatLocation.getY()-15),null);
                }


            }
            for(Patty[] row : stove){
                for(Patty tempPatty : row){
                    if(tempPatty != null){
                        ArrayList<Image> images = tempPatty.getPatty();
                        g2d.drawImage(images.get(0),tempPatty.getX()-80, tempPatty.getY()-40,null);
                        if(images.get(1) != null){
                            g2d.drawImage(images.get(1),tempPatty.getX()-78, tempPatty.getY()-37,null);
                        }
                        g2d.drawImage(images.get(2),tempPatty.getX()-78, tempPatty.getY()-15,null);

                        //draw timer
                        g2d.drawOval(tempPatty.getX() - 10, tempPatty.getY() - 50, 25,25);
                        g2d.setStroke(new BasicStroke(1));
                        g2d.drawLine(tempPatty.getX()+2,tempPatty.getY() - 44,tempPatty.getX() + 2,tempPatty.getY() - 50);
                        g2d.drawLine(tempPatty.getX()+2,tempPatty.getY() - 25,tempPatty.getX() + 2,tempPatty.getY() - 32);

                        g2d.drawLine(tempPatty.getX()-11,tempPatty.getY() -37,tempPatty.getX() -4,tempPatty.getY()-37);
                        g2d.drawLine(tempPatty.getX()+8,tempPatty.getY() -37,tempPatty.getX() +15,tempPatty.getY()-37);

                        g2d.setStroke(new BasicStroke(2));
                        if (tempPatty.getBottomTime()<=20000) {
                            double degree = tempPatty.getBottomTime()/3333.0;
                            g2d.drawLine(tempPatty.getX()+2,tempPatty.getY() -37,tempPatty.getX()+2+(int)(12.5*Math.sin(degree)),tempPatty.getY() -37 + (int)(-12.5*Math.cos(degree)));
                        } else {
                            g2d.setColor(Color.RED);
                            g2d.drawOval(tempPatty.getX() - 10, tempPatty.getY() - 50, 25,25);
                            g2d.setColor(Color.BLACK);
                        }
//
                        g2d.setStroke(new BasicStroke(3));
                    }
                }
            }

        }
        if(backgroundName.equals("grill") || backgroundName.equals("build") && !end){
            for(Patty patty : donePatties){
                if(!allToppings.contains(patty)) {
                ArrayList<Image> images = patty.getPatty();
                g2d.drawImage(images.get(0), patty.getX() - 80, patty.getY() - 40, null);
                if (images.get(1) != null) {
                    g2d.drawImage(images.get(1), patty.getX() - 78, patty.getY() - 37, null);
                }
                g2d.drawImage(images.get(2), patty.getX() - 78, patty.getY() - 15, null);
                }
            }
        }
        if(backgroundName.equals("build")){

            if(end){
                ImageIcon d = new ImageIcon("src/endTicket.png");
                d.setImage(d.getImage().getScaledInstance(100,130,1));
                g2d.drawImage(d.getImage(),250,450,null);
            } else {

                    ImageIcon a = new ImageIcon("src/buildOptions.png");
                    g2d.drawImage(a.getImage(), 0, 130, null);

                    ImageIcon condiments = null;
                    if (allToppings.size() != 0 && redRect.getX() >= (allToppings.get(0)).getX() && redRect.getX() <= allToppings.get(0).getX() + 140 && redRect.getY() <= allToppings.size() * (-10) + 500) {
                        condiments = new ImageIcon("src/upsideDownKetchup.png");
                    } else {
                        condiments = new ImageIcon("src/ketchup.png");
                    }
                    condiments.setImage(condiments.getImage().getScaledInstance(75, 175, 1));
                    if (holdingCondiment && condimentHeld.equals("ketchupSplatter")) {
                        redRect.setLocation(toppingLocation.x - 37, toppingLocation.y - 87);
                    } else {
                        redRect.setLocation(650, 400);
                    }
                    g2d.drawImage(condiments.getImage(), (int) (redRect.getX()), (int) (redRect.getY()), null);

                    if (allToppings.size() != 0 && yellowRect.getX() >= allToppings.get(0).getX() && yellowRect.getX() <= allToppings.get(0).getX() + 140 && yellowRect.getY() <= allToppings.size() * (-10) + 500) {
                        condiments = new ImageIcon("src/upsideDownMustard.png");
                    } else {
                        condiments = new ImageIcon("src/mustard.png");
                    }
                    condiments.setImage(condiments.getImage().getScaledInstance(75, 175, 1));
                    if (holdingCondiment && condimentHeld.equals("mustardSplatter")) {
                        yellowRect.setLocation(toppingLocation.x - 37, toppingLocation.y - 87);
                    } else {
                        yellowRect.setLocation(725, 400);
                    }
                    g2d.drawImage(condiments.getImage(), (int) (yellowRect.getX()), (int) (yellowRect.getY()), null);

                    if (allToppings.size() != 0 && brownRect.getX() >= allToppings.get(0).getX() && brownRect.getX() <= allToppings.get(0).getX() + 140 && brownRect.getY() <= allToppings.size() * (-10) + 500) {
                        condiments = new ImageIcon("src/upsideDownBbq.png");
                    } else {
                        condiments = new ImageIcon("src/bbq.png");
                    }
                    condiments.setImage(condiments.getImage().getScaledInstance(75, 175, 1));
                    if (holdingCondiment && condimentHeld.equals("bbqSplatter")) {
                        brownRect.setLocation(toppingLocation.x - 37, toppingLocation.y - 87);
                    } else {
                        brownRect.setLocation(800, 400);
                    }
                    g2d.drawImage(condiments.getImage(), (int) (brownRect.getX()), (int) (brownRect.getY()), null);

                    if (allToppings.size() != 0 && whiteRect.getX() >= allToppings.get(0).getX() && whiteRect.getX() <= allToppings.get(0).getX() + 140 && whiteRect.getY() <= allToppings.size() * (-10) + 500) {
                        condiments = new ImageIcon("src/upsideDownMayo.png");
                    } else {
                        condiments = new ImageIcon("src/mayo.png");
                    }
                    condiments.setImage(condiments.getImage().getScaledInstance(75, 175, 1));
                    if (holdingCondiment && condimentHeld.equals("mayoSplatter")) {
                        whiteRect.setLocation(toppingLocation.x - 37, toppingLocation.y - 87);
                    } else {
                        whiteRect.setLocation(875, 400);
                    }
                    g2d.drawImage(condiments.getImage(), (int) (whiteRect.getX()), (int) (whiteRect.getY()), null);


            }
            if (holdingTopping) {
                ImageIcon c = new ImageIcon("src/" + toppingHeld + ".png");
                c.setImage(c.getImage().getScaledInstance(140, 70, 1));
                g2d.drawImage(c.getImage(), (int) (toppingLocation.getX()) - 70, (int) (toppingLocation.getY()) - 35, null);
            }


            for(int i =0;i<allToppings.size();i++){
                Topping topping = allToppings.get(i);
                if((topping instanceof Topping)) {
                    if (topping.isCondiment()) {
                        if (topping.getY() <= i * (-10) + 500) {
                            ImageIcon c = null;
                            if (topping.getName().equals("ketchupSplatter")) {
                                c = new ImageIcon("src/ketchupDroplet.png");
                            }
                            if (topping.getName().equals("mustardSplatter")) {
                                c = new ImageIcon("src/mustardDroplet.png");
                            }
                            if (topping.getName().equals("bbqSplatter")) {
                                c = new ImageIcon("src/bbqDroplet.png");
                            }
                            if (topping.getName().equals("mayoSplatter")) {
                                c = new ImageIcon("src/mayoDroplet.png");
                            }
                            c.setImage(c.getImage().getScaledInstance(20, 40, 1));
                            g2d.drawImage(c.getImage(), topping.getX() + 60, topping.getY(), null);
                        } else {
                            g2d.drawImage(topping.getImage(), topping.getX(), topping.getY(), null);
                        }
                    } else {
                        g2d.drawImage(topping.getImage(), topping.getX(), topping.getY(), null);
                    }
                }
                if(topping instanceof Patty){
                    if(topping.getY()<= (i*(-10)+500)+25){
                        topping.setY(topping.getY()+15);
                    }
                    ArrayList<Image> images = (ArrayList<Image>) (topping).getPatty();
                    g2d.drawImage(images.get(0),(int)(topping.getX()-80), (int)(topping.getY()-40),null);
                    if(images.get(1) != null){
                        g2d.drawImage(images.get(1),(int)(topping.getX()-78), (int)(topping.getY()-37),null);
                    }
                    g2d.drawImage(images.get(2),(int)(topping.getX()-78),  (int)(topping.getY()-15),null);
                } else{
                    if(topping.getY()<= i*(-10)+500){
                        topping.setY(topping.getY()+15);
                    }
                }

            }

            for (int i = 0;i<reset.size();i++) {
                Glide glide = reset.get(i);
                if (glide.getX() >= glide.getDestX()-50 && glide.getX() <= glide.getDestX()+50 && glide.getY() >= glide.getDestY()-50 && glide.getY() <= glide.getDestY()+50 ) {
                    reset.remove(i);
                    i--;

                } else {
                    glide.setX(glide.getX()+glide.getDeltaX());
                    glide.setY(glide.getY()+glide.getDeltaY());

                }

                g2d.drawImage(glide.getImage(),glide.getX(),glide.getY(),null);
            }
        }



        for(int j = 0;j<allTickets.size();j++){
            Ticket tempTicket = allTickets.get(j);
            if(tempTicket != null) {
                g2d.drawImage(tempTicket.getTicket(), tempTicket.getX(), tempTicket.getY(), null);
                int y;
                if (tempTicket.isShrunk()) {
                    y = (int) (tempTicket.getY() + (tempTicket.getRectangle().getHeight()) - 5);
                } else {
                    y = (int) (tempTicket.getY() + (tempTicket.getRectangle().getHeight()) - 25);
                }


                for (int i = 0; i < tempTicket.getOrder().size(); i++) {

                    Image image = tempTicket.getOrder().get(i);
                    if (tempTicket.isShrunk()) {
                        if (tempTicket.getOrderName().get(i).contains(tempTicket.getMeatType())) {
                            g2d.drawImage(image, tempTicket.getX(), y, null);
                        } else {
                            g2d.drawImage(image, (int) (tempTicket.getX() + (tempTicket.getRectangle().getWidth() / 2) - 2.5), y, null);
                        }
                        y -= 7;
                    } else {
                        if (tempTicket.getOrderName().get(i).contains(tempTicket.getMeatType())) {
                            g2d.drawImage(image, tempTicket.getX(), y - 5, null);
                        } else {
                            g2d.drawImage(image, (int) (tempTicket.getX() + (tempTicket.getRectangle().getWidth() / 2) - 25), y, null);
                        }
                        y -= 35;
                    }
                    if (tempTicket.isShrunk()) {
                        g2d.setFont(new Font("Ariel", BOLD, 8));
                        g2d.drawString("" + (j + 1), tempTicket.getX() + 3, tempTicket.getY() + 13);
                    } else {
                        g2d.setFont(new Font("Ariel", BOLD, 25));
                        g2d.drawString("" + (j + 1), tempTicket.getX() + 3, tempTicket.getY() + 60);
                    }
                }
            }

        }



        g2d.setColor(Color.GRAY);
        g2d.fillOval(870,20,15,15);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() instanceof Timer){
            if(e.getSource() == condimentTimer){
                condimentTimerOn = false;
                orangeRect.setLocation(20,20);
            } else if(e.getSource() == customerTimer){
                Customer customer = new Customer();
                allCustomers.add(customer);
                customerTimer.stop();
                customerTimer = new Timer((int)(Math.random()*15000)+30000,this);
                System.out.println(customerTimer.getDelay());
                customerTimer.start();
            }
        }else if(e.getSource() instanceof JButton){
            if(e.getSource() == order){
                ImageIcon backgroundIcon = new ImageIcon("src/orderRoom.png");
                Image backgroundImage = backgroundIcon.getImage();
                backgroundIcon.setImage(backgroundImage.getScaledInstance(1000,600,2));
                backgroundName = "order";
                background = backgroundIcon.getImage();
            }else if(e.getSource() == grill){
                ImageIcon backgroundIcon = new ImageIcon("src/grillRoom.png");
                Image backgroundImage = backgroundIcon.getImage();
                backgroundIcon.setImage(backgroundImage.getScaledInstance(1000,600,2));
                if(!backgroundName.equals("grill")){
                    for(Patty patty : donePatties){
                        if(!allToppings.contains(patty)){
                            patty.setX(875);
                            patty.setY((donePatties.indexOf(patty)+1)*(-25) + 550);
                        }

                    }
                }
                backgroundName = "grill";
                background = backgroundIcon.getImage();
            }else if(e.getSource() == build){
                ImageIcon backgroundIcon = new ImageIcon("src/buildRoom.png");
                Image backgroundImage = backgroundIcon.getImage();
                backgroundIcon.setImage(backgroundImage.getScaledInstance(1000,600,2));
                if(!backgroundName.equals("build")){
                    for(Patty patty : donePatties){
                        if(!allToppings.contains(patty)){
                            patty.setX(290);
                            patty.setY(donePatties.indexOf(patty)*(-25) +550);
                        }

                    }
                }
                backgroundName = "build";
                background = backgroundIcon.getImage();
            }else if(e.getSource() == orderButton){
                Ticket ticket = new Ticket();
                allTickets.add(ticket);
                for(Ticket tempTicket : allTickets){

                    if(tempTicket != null && tempTicket != ticket && tempTicket.isOnHolder()){
                        tempTicket.setLocation((int)(Math.random()*150) + 550,10);
                        tempTicket.shrink();
                        tempTicket.setOnHolder(false);
                    }
                }
                allCustomers.get(0).setOrdered(true);
                allCustomers.get(0).setLeaving(true);
                allCustomers.get(0).setY(allCustomers.get(0).getY()-60);

                orderedCustomers.add(allCustomers.remove(0));
                orderButton.setVisible(false);

            }
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
      ;

        if (!condimentTimerOn && orangeRect.contains(e.getPoint())) {

            int xDiff = e.getX() - orangeRect.x;
            int yDiff = e.getY() - orangeRect.y;
            delta = new Point(xDiff, yDiff);
            isClickingOrangeRect = true;
        }
        if(backgroundName.equals("build") && !end){
            if(e.getX()>=0 && e.getX()<=160){
                if(e.getY() >= 165 && e.getY()<=240){
                    holdingTopping = true;
                    toppingHeld = "topBun";

                }
                if(e.getY() > 240 && e.getY()<=300){
                    holdingTopping = true;
                    toppingHeld = "tomato";

                }
                if(e.getY() > 300 && e.getY()<=365){
                    holdingTopping = true;
                    toppingHeld = "lettuce";

                }
                if(e.getY() > 365 && e.getY()<=420){
                    holdingTopping = true;
                    toppingHeld = "onion";

                }
                if(e.getY() > 420 && e.getY()<=480){
                    holdingTopping = true;
                    toppingHeld = "pickle";
                }
                if(e.getY() > 480 && e.getY()<=535){
                    holdingTopping = true;
                    toppingHeld = "cheese";
                }
                if(e.getY() > 535 && e.getY()<=600){
                    holdingTopping = true;
                    toppingHeld = "bottomBun";
                }
            }

            if(redRect.contains(e.getX(),e.getY())){
                holdingCondiment = true;
                condimentHeld = "ketchupSplatter";
            }
            if(yellowRect.contains(e.getX(),e.getY())){
                holdingCondiment = true;
                condimentHeld = "mustardSplatter";
            }
            if(brownRect.contains(e.getX(),e.getY())){
                holdingCondiment = true;
                condimentHeld = "bbqSplatter";
            }
            if(whiteRect.contains(e.getX(),e.getY())){
                holdingCondiment = true;
                condimentHeld = "mayoSplatter";
            }
            toppingLocation = new Point(e.getX(),e.getY());
            for(Patty patty : donePatties){
                if(patty.getBorder().contains(e.getX(),e.getY()) && !allToppings.contains(patty)){
                    pattyHeld = patty;
                    holdingMeat = true;
                }
            }
        }

    }


    @Override
    public void mouseReleased(MouseEvent e) {


        if(isClickingOrangeRect && !condimentTimerOn){

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

        if(holdingTicket){
            if(e.getY()<=40){
                if(e.getX() <= 700){
                    ticket.shrink();
                    ticket.setLocation(e.getX()-(ticket.getRectangle().width/2),10);

                    ticket.setOnHolder(false);
                } else{
                    if(!ticket.isOnHolder()){
                        for(Ticket tempTicket : allTickets){
                            if(tempTicket != null && tempTicket != ticket && tempTicket.isOnHolder()){
                                tempTicket.setLocation((int)(Math.random()*150) + 550,10);
                                tempTicket.shrink();
                                tempTicket.setOnHolder(false);
                            }
                        }
                    }
                    ticket.setLocation(790,10);
                    ticket.reset();
                    ticket.setOnHolder(true);

                }
            } else if(!ticket.isOnHolder() && e.getX()>700){
                for(Ticket tempTicket : allTickets){
                    if(tempTicket != null && tempTicket != ticket && tempTicket.isOnHolder()){

                        tempTicket.setLocation((int)(Math.random()*150) + 550,10);
                        tempTicket.shrink();
                        tempTicket.setOnHolder(false);
                    }
                }
                ticket.setLocation(790,10);
                ticket.reset();
                ticket.setOnHolder(true);
            } else if(end && backgroundName.equals("build") && e.getX()>=255 & e.getX()<=330 && e.getY()>=450 && e.getY()<=575){
                end = false;
                int right = 0;
                for(int i =0;i<ticket.getOrderName().size();i++){
                    if(i<allToppings.size()){
                        if(ticket.getOrderName().get(i).contains(allToppings.get(i).getName()) && !ticket.getOrderName().get(i).contains("bottomBun") && !ticket.getOrderName().get(i).contains("topBun")){
                            right++;
                        }
                    }
                }
                for(int i = 0;i<allTickets.size();i++){
                    if(allTickets.get(i) != null && allTickets.get(i) == ticket){
                        allTickets.set(i,null);
                    }
                }
                for(Topping topping : allToppings){
                    if(topping instanceof Patty){
                        if(donePatties.contains(topping)){
                            donePatties.remove(topping);
                        }
                    }
                }
                money += (double)right/(ticket.getOrderName().size()-2);
                allToppings.clear();
                orderedCustomers.remove(0);
            } else {
                if(ticket.isOnHolder()){//reset to holder
                    ticket.setLocation(790,10);
                    ticket.reset();
                } else{//reset to line
                    ticket.setLocation(e.getX()-(ticket.getRectangle().width/2),10);
                }
            }
            holdingTicket = false;
            heldTicket = null;
        }

        if(holdingMeat && backgroundName.equals("grill")){
            holdingMeat = false;
            int x = e.getX();
            int y = e.getY();

            Patty patty;
            if(y>=485){
                if(x>=565 && x<=755){
                    if(stove[3][2] == null){
                        if(pattyHeld != null){
                            stove[3][2] = pattyHeld;
                            pattyHeld.setX(670);
                            pattyHeld.setY(540);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(670,540);
                            stove[3][2] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else if(x>=380 && x < 565){
                    if(stove[3][1] == null){
                        if(pattyHeld != null){
                            stove[3][1] = pattyHeld;
                            pattyHeld.setX(475);
                            pattyHeld.setY(540);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(475,540);
                            stove[3][1] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else if(x>=195 && x < 380){
                    if(stove[3][0] == null){
                        if(pattyHeld != null){
                            stove[3][0] = pattyHeld;
                            pattyHeld.setX(280);
                            pattyHeld.setY(540);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(280,540);
                            stove[3][0] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else{
                    if(pattyHeld != null){
                        stove[previousRow][previousColumn] = pattyHeld;
                        pattyHeld.startTimer();
                    }
                }
            } else if(y>=370){
                if(x>=565 && x<=755){
                    if(stove[2][2] == null){
                        if(pattyHeld != null){
                            stove[2][2] = pattyHeld;
                            pattyHeld.setX(670);
                            pattyHeld.setY(425);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(670,425);
                            stove[2][2] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else if(x>=380 && x < 565){
                    if(stove[2][1] == null){
                        if(pattyHeld != null){
                            stove[2][1] = pattyHeld;
                            pattyHeld.setX(475);
                            pattyHeld.setY(425);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(475,425);
                            stove[2][1] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else if(x>=195 && x < 380){
                    if(stove[2][0] == null){
                        if(pattyHeld != null){
                            stove[2][0] = pattyHeld;
                            pattyHeld.setX(280);
                            pattyHeld.setY(425);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(280,425);
                            stove[2][0] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else{
                    if(pattyHeld != null && previousRow>=0 && previousColumn>=0){
                        stove[previousRow][previousColumn] = pattyHeld;
                        pattyHeld.startTimer();
                    }
                }
            } else if(y>=255){
                if(x>=565  && x<=755){
                    if(stove[1][2] == null){
                        if(pattyHeld != null){
                            stove[1][2] = pattyHeld;
                            pattyHeld.setX(670);
                            pattyHeld.setY(310);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(670,310);
                            stove[1][2] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else if(x>=380  && x < 565){
                    if(stove[1][1] == null){
                        if(pattyHeld != null){
                            stove[1][1] = pattyHeld;
                            pattyHeld.setX(475);
                            pattyHeld.setY(310);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(475,310);
                            stove[1][1] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else if(x>=195  && x < 380){
                    if(stove[1][0] == null){
                        if(pattyHeld != null){
                            stove[1][0] = pattyHeld;
                            pattyHeld.setX(280);
                            pattyHeld.setY(310);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(280,310);
                            stove[1][0] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else{
                    if(pattyHeld != null){
                        stove[previousRow][previousColumn] = pattyHeld;
                        pattyHeld.startTimer();
                    }
                }
            } else if(y>=140){
                if(x>=565  && x<=755){
                    if(stove[0][2] == null){
                        if(pattyHeld != null){
                            stove[0][2] = pattyHeld;
                            pattyHeld.setX(670);
                            pattyHeld.setY(195);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(670,195);
                            stove[0][2] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else if(x>=380  && x < 565){
                    if(stove[0][1] == null){
                        if(pattyHeld != null){
                            stove[0][1] = pattyHeld;
                            pattyHeld.setX(475);
                            pattyHeld.setY(195);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(475,195);
                            stove[0][1] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else if(x>=195  && x < 380){
                    if(stove[0][0] == null){
                        if(pattyHeld != null){
                            stove[0][0] = pattyHeld;
                            pattyHeld.setX(280);
                            pattyHeld.setY(195);
                            pattyHeld.startTimer();
                        } else{
                            patty = new Patty(280,195);
                            stove[0][0] = patty;
                            allPatties.add(patty);
                            patty.startTimer();
                        }
                    } else{
                        if(pattyHeld != null){
                            stove[previousRow][previousColumn] = pattyHeld;
                            pattyHeld.startTimer();
                        }
                    }
                } else{
                    if(pattyHeld != null){
                        stove[previousRow][previousColumn] = pattyHeld;
                        pattyHeld.startTimer();
                    }
                }
            } else{
                if(pattyHeld != null){
                    stove[previousRow][previousColumn] = pattyHeld;
                    pattyHeld.startTimer();
                }
            }

            if(pattyHeld != null && e.getX()>=810 && e.getX()<=960 && e.getY()>=donePatties.size()*(-25) + 500 && e.getY()<=575){
                donePatties.add(pattyHeld);
                stove[previousRow][previousColumn] = null;
                pattyHeld.stopTimer();;
                pattyHeld.setX(875);
                pattyHeld.setY(donePatties.size()*(-25) + 550);
            }
            pattyHeld = null;
            previousRow = -1;
            previousY = -1;
        }

        if(holdingTopping && !end){
            if(allToppings.size()!=0 && allToppings.get(0).getName().equals("bottomBun")) {
                if (e.getX() >= allToppings.get(0).getX() && e.getX() <= allToppings.get(0).getX()+140 && e.getY() <= allToppings.size() * (-10) + 500) {
                    ImageIcon c = new ImageIcon("src/" + toppingHeld + ".png");
                    c.setImage(c.getImage().getScaledInstance(140, 70, 1));
                    Topping topping = new Topping(e.getX() - 70, e.getY() - 35, toppingHeld, c.getImage(), false);
                    if (allToppings.size() != 0 && allToppings.get(0).getName().equals("bottomBun") || toppingHeld.equals("bottomBun")) {
                        if (toppingHeld.equals("bottomBun")) {
                            boolean add = true;
                            for (Topping tempTopping : allToppings) {
                                if (tempTopping.getName().equals("bottomBun")) {
                                    add = false;
                                    break;
                                }
                            }
                            if (add) {
                                allToppings.add(topping);
                            }
                        } else if(toppingHeld.equals("topBun")){
                            end = true;
                            allToppings.add(topping);
                        } else {
                            allToppings.add(topping);
                        }
                    }
                } else {
                    ImageIcon c = new ImageIcon("src/" + toppingHeld + ".png");
                    c.setImage(c.getImage().getScaledInstance(140, 70, 1));
                    Glide glide = new Glide(c.getImage(),e.getX(),e.getY(),Glide.findDestX(toppingHeld),Glide.findDestY(toppingHeld));
                    reset.add(glide);
                }
            } else{
                if(toppingHeld.equals("bottomBun")){
                    if (e.getX()>=475 &&e.getX()<=575) {
                        ImageIcon c = new ImageIcon("src/" + toppingHeld + ".png");
                        c.setImage(c.getImage().getScaledInstance(140, 70, 1));
                        Topping topping = new Topping(e.getX() - 70, e.getY() - 35, toppingHeld, c.getImage(), false);
                        allToppings.add(topping);
                    } else {
                        ImageIcon c = new ImageIcon("src/" + toppingHeld + ".png");
                        c.setImage(c.getImage().getScaledInstance(140, 70, 1));
                        Glide glide = new Glide(c.getImage(),e.getX(),e.getY(),Glide.findDestX("bottomBun"),Glide.findDestY("bottomBun"));
                        reset.add(glide);
                    }
                } else {
                    ImageIcon c = new ImageIcon("src/" + toppingHeld + ".png");
                    c.setImage(c.getImage().getScaledInstance(140, 70, 1));
                    Glide glide = new Glide(c.getImage(),e.getX(),e.getY(),Glide.findDestX(toppingHeld),Glide.findDestY(toppingHeld));
                    reset.add(glide);
                }
            }
            toppingHeld = null;
            holdingTopping = false;
        }

        if(holdingCondiment && !end){
            if(allToppings.size()!=0 && allToppings.get(0).getName().equals("bottomBun")) {

                if (e.getX() >= allToppings.get(0).getX() && e.getX() <= allToppings.get(0).getX()+140 && e.getY() <= allToppings.size() * (-10) + 500) {

                    ImageIcon c = new ImageIcon("src/" + condimentHeld + ".png");
                    c.setImage(c.getImage().getScaledInstance(140, 70, 1));
                    Topping topping = new Topping(e.getX() - 70, e.getY() - 35, condimentHeld, c.getImage(), true);
                    if (allToppings.size() != 0 && allToppings.get(0).getName().equals("bottomBun")) {
                        allToppings.add(topping);
                    }
                }
            }
            if (condimentHeld.equals("ketchupSplatter")) {
                redRect.setLocation(650,400);
            } else if (condimentHeld.equals("mustardSplatter")) {
                yellowRect.setLocation(725,400);
            } else if (condimentHeld.equals("bbqSplatter")) {
                brownRect.setLocation(800,400);
            } else if (condimentHeld.equals("mayoSplatter")) {
                whiteRect.setLocation(875,400);
            }

            holdingCondiment = false;
            condimentHeld = null;
        }
        if(holdingMeat && backgroundName.equals("build") && !end){
            if(allToppings.size()!=0 && allToppings.get(0).getName().equals("bottomBun")) {
                if (e.getX() >= allToppings.get(0).getX() && e.getX() <= allToppings.get(0).getX() + 140 && e.getY() <= allToppings.size() * (-10) + 500) {
                    allToppings.add(pattyHeld);
                } else{
                    if(pattyHeld != null){
                        pattyHeld.setX(290);
                        pattyHeld.setY(donePatties.indexOf(pattyHeld)*(-25) +550);
                    }
                }
            } else{
                if(pattyHeld != null){
                    pattyHeld.setX(290);
                    pattyHeld.setY(donePatties.indexOf(pattyHeld)*(-25) +550);
                }
            }
        }else{
            if(pattyHeld != null){
                pattyHeld.setX(290);
                pattyHeld.setY(donePatties.indexOf(pattyHeld)*(-25) +550);
            }

        }
        holdingMeat = false;
        pattyHeld = null;


    }
    @Override
    public void mouseClicked(MouseEvent e) {
        for(Patty patty : allPatties){
            if(patty.getBorder().contains(e.getX(),e.getY()) && !donePatties.contains(patty)){
                patty.flip();
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isClickingOrangeRect || condimentTimerOn) {
            previousX = e.getX();
            previousY = e.getY();
            double newRectX = e.getX() - orangeRect.getWidth()/2;
            double newRectY = e.getY() - orangeRect.getHeight()/2;
            if(e.getY()<=550){
                orangeRect.setLocation((int)newRectX, (int)newRectY);
            }
            repaint();
        } else {
            if(!holdingMeat && !holdingCondiment && !holdingTopping){
                if(!holdingTicket){
                    for(Ticket tempTicket : allTickets){
                        if(tempTicket != null && tempTicket.getRectangle().contains(e.getX(),e.getY())){
                            ticket = tempTicket;
                            heldTicket = tempTicket;
                            tempTicket.setLocation(e.getX()-(tempTicket.getRectangle().width/2),e.getY()-(15));
                            holdingTicket = true;
                            break;
                        }
                    }
                } else{
                    ticket = heldTicket;
                    heldTicket.setLocation(e.getX()-(heldTicket.getRectangle().width/2),e.getY()-(15));
                    holdingTicket = true;
                }
            }
        }

        if(backgroundName.equals("grill")){
            if(e.getX()>=15 && e.getX()<=190 && e.getY()>=425 && e.getY()<=575){
                holdingMeat = true;
            } else{
                for(Patty patty : allPatties){
                    if(!holdingMeat && !holdingTicket && patty.getBorder().contains(e.getX(),e.getY()) && !donePatties.contains(patty)){
                        holdingMeat = true;
                        pattyHeld = patty;
                        patty.stopTimer();
                    }
                }
                if(holdingMeat){
                    for(int i = 0;i<stove.length;i++){
                        for(int j = 0;j<stove[0].length;j++){
                            if(stove[i][j] == pattyHeld){
                                stove[i][j] = null;
                                previousRow = i;
                                previousColumn = j;
                            }
                        }
                    }
                }

            }
        }
        if(holdingMeat){
            meatLocation = new Point(e.getX(),e.getY());
        }

        if(backgroundName.equals("build")){
            if(!holdingTicket) {

                for (int i = donePatties.size() - 1; i >= 0; i--) {
                    Patty patty = donePatties.get(i);
                    if (patty == pattyHeld &&  !allToppings.contains(pattyHeld)) {
                        patty.setX(e.getX());
                        patty.setY(e.getY());
                        break;
                    }
                }

//                if (e.getX() >= 0 && e.getX() <= 160) {
//                    if (e.getY() >= 165 && e.getY() <= 240) {
//                        holdingTopping = true;
//                    }
//                    if (e.getY() > 240 && e.getY() <= 300) {
//                        holdingTopping = true;
//                    }
//                    if (e.getY() > 300 && e.getY() <= 365) {
//                        holdingTopping = true;
//                    }
//                    if (e.getY() > 365 && e.getY() <= 420) {
//                        holdingTopping = true;
//                    }
//                    if (e.getY() > 420 && e.getY() <= 480) {
//                        holdingTopping = true;
//                    }
//                    if (e.getY() > 480 && e.getY() <= 535) {
//                        holdingTopping = true;
//                    }
//                    if (e.getY() > 535 && e.getY() <= 600) {
//                        holdingTopping = true;
//                    }
//                }
                toppingLocation = new Point(e.getX(), e.getY());

            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(condimentTimerOn){
            previousX = e.getX();
            previousY = e.getY();
            double newRectX = e.getX() - orangeRect.getWidth()/2;
            double newRectY = e.getY() - orangeRect.getHeight()/2;
            if(e.getY()<=550){
                orangeRect.setLocation((int)newRectX, (int)newRectY);
            }

            repaint();
        }
    }

}