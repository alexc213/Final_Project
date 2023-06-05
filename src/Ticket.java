import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ticket extends Rectangle{
    private static int ticketNumber = 0;
    private ArrayList<JPanel> order;

    public Ticket(){
        setSize(100, 100);
        //setLocation(250, 250);
        //setLayout(new GridLayout());
        ticketNumber ++;
        order = new ArrayList<>();
        for(int i = 0;i<10;i++){
            JPanel panel = new JPanel();
            if(i%2 == 1){
                panel.setBackground(Color.pink);
            } else{
                panel.setBackground(Color.white);
            }
            order.add(panel);
            //add(panel);
        }

    }
//    public void paint(Graphics gp){
//        super.paint(gp); // must do this!
//        Graphics2D g2d = (Graphics2D) gp;
//
//
//    }
}
