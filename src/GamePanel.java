import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.awt.event.KeyEvent.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private JLabel sprite;
    private int spriteX;
    private int spriteY;
    private Rectangle redEnemy;
    private int enemyX;
    private int enemyY;

    public GamePanel() {
        spriteX = 0;
        spriteY = 0;
        enemyX = 0;
        enemyY = 0;
        timer = new Timer(10, this);
        timer.start();
        redEnemy = new Rectangle(20, 20);
        redEnemy.setLocation(300, 0);

        setBackground(Color.black);
        sprite = new JLabel();
        ImageIcon spriteImage = new ImageIcon("src/star1a.png");
        sprite.setIcon(spriteImage);
        add(sprite);

        // add a keylistener to this JPanel, then set it to be focusable and take focus from frame
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void paint(Graphics gp) {
        super.paint(gp);
        Graphics2D g2d = (Graphics2D) gp;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.red);
        g2d.draw(redEnemy);
    }

    private void moveEnemy() {
        enemyX += 1;
        enemyY += 2;
        if (enemyX > getWidth()) {
            enemyX = 0;
        }
        if (enemyY > getHeight()) {
            enemyY = 0;
        }
        redEnemy.setLocation(enemyX, enemyY);
    }

    // -------- ACTIONLISTENER INTERFACE METHOD ---------
    // called when Timer pulses (every 10ms)
    @Override
    public void actionPerformed(ActionEvent e) {
        moveEnemy();
        repaint();
    }

    // -------- KEYLISTENER INTERFACE METHODS ---------
    @Override
    public void keyTyped(KeyEvent e) {  } // unimplemented but required by interface

    @Override
    public void keyPressed(KeyEvent e) {
        int x = sprite.getX();
        int y = sprite.getY();
        int key = e.getKeyCode();
        int delta = 5;
        if (key == VK_W) {
            sprite.setLocation(x, y - delta);
        } else if (key == VK_A) {
            sprite.setLocation(x - delta, y);
        } else if (key == VK_S) {
            sprite.setLocation(x, y + delta);
        } else if (key == VK_D) {
            sprite.setLocation(x + delta, y);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {  } // unimplemented but required by interface
}
