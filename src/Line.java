import java.awt.Graphics;

public class Line extends Shape {
    public Line() {
        super();
    }

    @Override
    public void draw(Graphics g) {
        // set the color
        g.setColor(getColor());

        // draw the Line between (x1, y1) and (x2, y2)
        // NOTE: requires us to add getter methods for both p1 and p2 in the Shape class
        // since they are private instance variables and we can't access them in a subclass
        g.drawLine(getP1().x, getP1().y, getP2().x, getP2().y);
    }
}


