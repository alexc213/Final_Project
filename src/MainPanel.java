import javax.swing.*;

public class MainPanel extends JPanel {
    private JButton button;
    public MainPanel(){
        button = new JButton();
        add(button);
        button.setLocation(250,250);
    }
}
