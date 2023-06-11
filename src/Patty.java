import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Patty  implements ActionListener {
    private int x;
    private int y;
    private Oval border;
    private Timer timer;
    private int topTime;
    private int bottomTime;
    private boolean isTop;
    public Patty(int x, int y){
        this.x = x;
        this.y = y;
        border = new Oval();
        border.setPoint1Values(x-75,y-30);
        border.setPoint2Values(x+75,y+30);
        timer = new Timer(10,this);
        isTop = false;
    }
    public void startTimer(){
        timer.start();
    }
    public void stopTimer(){
        timer.stop();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Oval getBorder() {
        return border;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof Timer){
            if(isTop){
                topTime += 10;
            } else{
                bottomTime += 10;
            }
        }
    }
}
