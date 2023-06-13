import java.awt.*;
public class Topping {
    private int x;
    private int y;
    private Rectangle border;
    private Image image;
    private String name;
    private boolean isCondiment;

    public Topping(int x,int y, String name, Image image, boolean isCondiment) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.isCondiment = isCondiment;
        this.name = name;
        border = new Rectangle(x,y);
        border.setSize(140,70);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public boolean isCondiment() {
        return isCondiment;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public Object getPatty() {
        return null;
    }
}
