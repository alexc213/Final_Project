import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        super("Mini Game");
        init();
    }

    private void init() {
        GamePanel game = new GamePanel();
        add(game);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocation(300, 50);
        setVisible(true);
    }
}
