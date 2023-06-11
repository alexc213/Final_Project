import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class Customer {
    private Image customer;
    private int x;
    private int y;
    private boolean ordered;
    private boolean leaving;
    private boolean gotFood;

    public Customer(){
        ImageIcon icon = new ImageIcon("src/customer.png");
        icon.setImage(icon.getImage().getScaledInstance(200,300,4));
        customer = icon.getImage();
        x=1000;
        y=300;
        ordered = false;
        leaving = false;
        gotFood = false;
    }

    public Image getCustomer() {
        return customer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public boolean isOrdered() {
        return ordered;
    }

    public boolean isGotFood() {
        return gotFood;
    }

    public boolean isLeaving() {
        return leaving;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public void setLeaving(boolean leaving) {
        this.leaving = leaving;
    }

    public void setGotFood(boolean gotFood) {
        this.gotFood = gotFood;
    }
}
