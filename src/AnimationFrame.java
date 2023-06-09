import javax.swing.*;

public class AnimationFrame extends JFrame {

    private JPanel mainPanel;

    public AnimationFrame() {
        createUIComponents();
    }

    private void createUIComponents() {
        // set mainPanel (JPanel) to be a new AnimationPanel, which is allowed
        // since AnimationPanel is a subclass of JPanel; since AnimationPanel is designed
        // entirely in code, we must CHECK the "Custom Create" box for mainPanel in the Swing UI designer
        mainPanel = new AnimationPanel();
        //add(mainPanel);
        setContentPane(mainPanel);
//        JButton button = new JButton();
//        mainPanel.add(button);
//        button.setLocation(125,450);
        //add(mainPanel);
        setResizable(false);
        setTitle("Animation App");
        setSize(1000, 750);
        setLocation(250, 50);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
