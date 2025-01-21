import java.awt.*;

public class Glide {
    private Image image;
    private int x;
    private int y;
    private int destX;
    private int destY;
    private int deltaX;
    private int deltaY;


    public Glide(Image image, int x, int y, int destX, int destY) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.destX = destX;
        this.destY = destY;
        deltaX = (destX-x)/5;
        deltaY = (destY-y)/5;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDestX() {
        return destX;
    }

    public void setDestX(int destX) {
        this.destX = destX;
    }

    public int getDestY() {
        return destY;
    }

    public void setDestY(int destY) {
        this.destY = destY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public static int findDestX(String toppingHeld) {
        if (toppingHeld.equals("topBun")) {
            return 0;
        } else if (toppingHeld.equals("tomato")) {
            return 0;
        } else if (toppingHeld.equals("lettuce")) {
            return 0;
        } else if (toppingHeld.equals("onion")) {
            return 0;
        } else if (toppingHeld.equals("pickle")) {
            return 0;
        } else if (toppingHeld.equals("cheese")) {
            return 0;
        } else if (toppingHeld.equals("bottomBun")) {
            return 0;
        } else if (toppingHeld.equals("ketchupSplatter")) {
            return 650;
        } else if (toppingHeld.equals("mustardSplatter")) {
            return 725;
        } else if (toppingHeld.equals("bbqSplatter")) {
            return 800;
        } else if (toppingHeld.equals("mayoSplatter")) {
            return 875;
        } else {
            return 0;
        }
    }

    public static int findDestY(String toppingHeld) {
        if (toppingHeld.equals("topBun")) {
            return 165;
        } else if (toppingHeld.equals("tomato")) {
            return 240;
        } else if (toppingHeld.equals("lettuce")) {
            return 300;
        } else if (toppingHeld.equals("onion")) {
            return 365;
        } else if (toppingHeld.equals("pickle")) {
            return 420;
        } else if (toppingHeld.equals("cheese")) {
            return 480;
        } else if (toppingHeld.equals("bottomBun")) {
            return 535;
        } else if (toppingHeld.equals("ketchupSplatter")) {
            return 400;
        } else if (toppingHeld.equals("mustardSplatter")) {
            return 400;
        } else if (toppingHeld.equals("bbqSplatter")) {
            return 400;
        } else if (toppingHeld.equals("mayoSplatter")) {
            return 400;
        } else {
            return 0;
        }
    }
}
