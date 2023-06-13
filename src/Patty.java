import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Patty extends Topping implements ActionListener {
    private int x;
    private int y;
    private Rectangle border;
    private Timer timer;
    private int topTime;
    private int bottomTime;
    private ArrayList<Image> patty;
    private boolean isTop;
    private int totalTime;
    private String name;
    public Patty(int x, int y){
        super(x,y,"Patty",null,false);
        this.x = x;
        this.y = y;
        border = new Rectangle();
        border.setSize(150,60);
        border.setLocation(x-75,y-30);
        name = "raw";
        timer = new Timer(10,this);
        isTop = false;
        patty = new ArrayList<>();
        ImageIcon icon = new ImageIcon("src/rawPatty.png");
        icon.setImage(icon.getImage().getScaledInstance(160,80,1));
        patty.add(icon.getImage());
        patty.add(null);
        //patty.add(null);
        icon = new ImageIcon("src/rawBottom.png");
        icon.setImage(icon.getImage().getScaledInstance(160,60,1));
        patty.add(icon.getImage());

        topTime = 0;
        bottomTime = 0;
    }
    public void startTimer(){
        timer.start();
    }
    public void stopTimer(){
        timer.stop();
    }

    public void setX(int x) {
        this.x = x;
        border.setLocation(x-75,y-30);
    }

    public void setY(int y) {
        this.y = y;
        border.setLocation(x-75,y-30);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBorder() {
        return border;
    }

    public ArrayList<Image> getPatty() {
        return patty;
    }
    public void flip(){
        int temp = bottomTime;
        bottomTime = topTime;
        topTime = temp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(bottomTime);
        if(e.getSource() instanceof Timer){
            bottomTime += 10;
            totalTime+=10;

            if(topTime != 0){
                ImageIcon icon = new ImageIcon("src/marks.png");
                icon.setImage(icon.getImage().getScaledInstance(160,50,1));
                patty.set(1,icon.getImage());
            } else{
                patty.set(1,null);
            }
            if(topTime>=0 && topTime<5000){
                ImageIcon icon = new ImageIcon("src/rawPatty.png");
                icon.setImage(icon.getImage().getScaledInstance(160,80,1));
                patty.set(0,icon.getImage());
            } else if(topTime >= 5000 && topTime<10000){
                ImageIcon icon = new ImageIcon("src/lowPatty.png");
                icon.setImage(icon.getImage().getScaledInstance(160,80,1));
                patty.set(0,icon.getImage());
            } else if(topTime >= 10000 && topTime<15000){
                ImageIcon icon = new ImageIcon("src/mediumPatty.png");
                icon.setImage(icon.getImage().getScaledInstance(160,80,1));
                patty.set(0,icon.getImage());
            } else if(topTime >= 15000 && topTime<20000){
                ImageIcon icon = new ImageIcon("src/highPatty.png");
                icon.setImage(icon.getImage().getScaledInstance(160,80,1));
                patty.set(0,icon.getImage());
            } else{
                ImageIcon icon = new ImageIcon("src/burntPatty.png");
                icon.setImage(icon.getImage().getScaledInstance(160,80,1));
                patty.set(0,icon.getImage());
            }

            if(bottomTime>=0 && bottomTime<5000){
                ImageIcon icon = new ImageIcon("src/rawBottom.png");
                icon.setImage(icon.getImage().getScaledInstance(160,60,1));
                patty.set(2,icon.getImage());
            } else if(bottomTime >= 5000 && bottomTime<10000){
                ImageIcon icon = new ImageIcon("src/lowBottom.png");
                icon.setImage(icon.getImage().getScaledInstance(160,60,1));
                patty.set(2,icon.getImage());
            } else if(bottomTime >= 10000 && bottomTime<15000){
                ImageIcon icon = new ImageIcon("src/mediumBottom.png");
                icon.setImage(icon.getImage().getScaledInstance(160,60,1));
                patty.set(2,icon.getImage());
            } else if(bottomTime >= 15000 && bottomTime<20000){
                ImageIcon icon = new ImageIcon("src/highBottom.png");
                icon.setImage(icon.getImage().getScaledInstance(160,60,1));
                patty.set(2,icon.getImage());
            } else{
                ImageIcon icon = new ImageIcon("src/burntBottom.png");
                icon.setImage(icon.getImage().getScaledInstance(160,60,1));
                patty.set(2,icon.getImage());
            }

            if(bottomTime>=0 && bottomTime<5000 && topTime>=0 && topTime<5000){
                name = "raw";
            }
            if(bottomTime>=5000 && bottomTime<10000 && topTime>=5000 && topTime<10000){
                name = "low";
            }
            if(bottomTime>=10000 && bottomTime<15000 && topTime>=10000 && topTime<15000){
                name = "medium";
            }
            if(bottomTime>=15000 && bottomTime<20000 && topTime>=15000 && topTime<20000){
                name = "high";
            }
            if(bottomTime>=20000 && topTime>=20000){
                name = "burnt";
            }
        }
    }
    public String getName(){
        return name;
    }
}
