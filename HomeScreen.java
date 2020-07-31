
import javax.swing.*;
import java.awt.*;
/**
 * The display for the home screen
*/
public class HomeScreen {
    JPanel background = TableViewer.homeBackground;
    JLabel title = new JLabel("Welcome to");
    JLabel titleV2 = new JLabel("Black Jack");
    JButton start = new JButton("New Game");
    JLabel playersTxt = new JLabel("Players: 0");
    
    /**
     * Creates the home screen
     */
    public HomeScreen() {
        background.setBackground(new Color(18, 67, 56));
        background.setLayout(null);
        setTitle();
        setStart();
        background.repaint();
    }
    /**
     * Adds the titles to the home page
    */
    private void setTitle() {
        background.add(title);
        background.add(titleV2);
        background.add(playersTxt);
        title.setForeground(new Color(255,255,255, 140));
        titleV2.setForeground(new Color(255,255,250));
        playersTxt.setForeground(new Color(255,255,240));
        title.setSize(TableViewer.frame.getWidth(), 40);
        playersTxt.setSize(TableViewer.frame.getWidth(), 40);
        titleV2.setSize(TableViewer.frame.getWidth(), 40);
        title.setFont(new Font(title.getName(), Font.PLAIN, 30));
        titleV2.setFont(new Font(titleV2.getName(), Font.PLAIN, 35));
        playersTxt.setFont(new Font(titleV2.getName(), Font.PLAIN, 25));
        title.setLocation(0, (int) (TableViewer.frame.getHeight() / 25.0) * 4);
        titleV2.setLocation(0, (int) (TableViewer.frame.getHeight() / 25.0) * 6);
        playersTxt.setLocation(0, (int) (TableViewer.frame.getHeight() / 25.0) * 9);
        playersTxt.setHorizontalAlignment(SwingConstants.CENTER);
        titleV2.setHorizontalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
    }
    /**
     * Sets the player count on the home page
    */
    public void setPlayers() {
        playersTxt.setText("Players: " + Integer.toString(Table.playerNumber));
    }
    /**
     * Sets the start button
    */
    private void setStart() {
        background.add(start);
        start.setLocation(TableViewer.frame.getWidth() / 2 - (start.getWidth() / 2), (TableViewer.frame.getHeight() / 3) * 2);
    }
    /**
     * @return the background panel
    */
    public JPanel getPanel() {
        return background;
    }
}