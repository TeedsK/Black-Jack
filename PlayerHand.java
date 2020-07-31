import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.IIOImage;
import javax.swing.*;
import javax.swing.border.Border;
/**
 * The container for the hand of each player
*/
public class PlayerHand {
    String getShownValue() {return null;}
    boolean alive = true;
    String name;
    JLabel id;
    ArrayList<Cards> hand = new ArrayList<Cards>();

    /**
     * Sets the player name and id
    */
    public PlayerHand() {
        this.name = "Player ";
        this.id = new JLabel();
    }
    /**
     * Shows its this players turn by highliting cards
    */
    public void active() {
        for(int x = 0; x < panels.size(); x++) {
                panels.get(x).setBackground(new Color(255,255,255,255));
                TableViewer.gameBackground.repaint();
        }
    }
    /**
     * While waiting for turn the cards are faded
    */
    public void waiting() {
        for(int x = 0; x < panels.size(); x++) {
            panels.get(x).setBackground(new Color(255,255,255,150));
        }
    }
    /**
     * Sets the distance from the other far left side
     * @param x the distance
    */
    public void setX(int x) {
        this.xDistance = x;
    }
    /**
     * Sets the players name
     * @param name players name
    */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Adds the titles to the home page
    */
    public String getName() {
        return this.name;
    }
    /**
     * Gives the player a card
    */
    public void getCard() {
        if(this.alive == true) {
            hand.add(Cards.randomCard());
        }
    }
    /**
     * @return wether the player is still in the game
    */
    public boolean getAlive() {
        if (new CheckHand().getAlive(this)) {
            return true;
        } else {
            this.alive = false;
            return false;
        }
    }
    /**
     * sets the initial two cards the player gets
    */
    public void setCards() {
        hand.add(Cards.randomCard());
        hand.add(Cards.randomCard());
    }

    /**
     * @return the players hand as an arraylist
    */
    public ArrayList<Cards> viewHand() {
        return hand;
    }
    ArrayList<JPanel> panels = new ArrayList<JPanel>();
    
    /**
     * @return returns the visual of the card and adds it to the panels arraylist
    */
    public JPanel setVisualPanel() {
        JPanel b = visualCard(xDistance + 10, 30);
        panels.add(b);
        cardAmount++;
        return b;
    }
    int xDistance;
    int cardAmount = 0;
    /**
     * @return creates the visual of the card
     * @param x the X location
     * @param y the Y location
    */
    private JPanel visualCard(int x, int y) {
        Border outline = BorderFactory.createLineBorder(Color.black);
            JPanel background = new JPanel();
            background.setLayout(null);
            int[] size = {11,20,11};
            int[] positionX = {7, 20, 43}; 
            int[] positionY = {18, 30, 50}; 
            ArrayList<JLabel> lables = new ArrayList<JLabel>();
            for(int p = 0; p < 3; p++) {
                JLabel suite = new JLabel(icon(hand.get(cardAmount).toString(), size[p]));
                suite.setSize(size[p], size[p]);
                suite.setLocation(positionX[p], positionY[p]);
                lables.add(suite);
                background.add(suite);
            }
            for(int p = 0; p < 2; p++) {
                JLabel value = new JLabel(hand.get(cardAmount).getShownValue());
                if(hand.get(cardAmount).toString() == "D" || hand.get(cardAmount).toString() == "H") {
                    value.setForeground(new Color(238, 57, 57));
                }
                value.setSize(16,11);
                //https://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size
                value.setFont(new Font(value.getName(), Font.PLAIN, 12));

                value.setHorizontalAlignment(SwingConstants.CENTER);
                if(p == 0) {
                    value.setLocation(lables.get(0).getX() - 2, lables.get(0).getY() - 13);
                } else {
                    value.setLocation(lables.get(2).getX() - 2, lables.get(2).getY() + 13);
                }
                background.add(value);
            }
            background.setBorder(outline);
            background.setSize(60, 80);
            background.setLocation(x, y + (cardAmount * 81));
            return background;
    }
    /**
     * calls the function to create the card for every card in the players hand
    */
    public ArrayList<JPanel> playerHand() {
        for (int j = 0; j < hand.size(); j++) {
            setVisualPanel();
        }
        return panels;
    }
    /**
     * @returns the correct icon for the type of card
    */
    private ImageIcon icon(String type, int size) {
        ImageIcon icon;
        if (type == "D") {
             icon = new ImageIcon("diamondsIcon.png");
        } else if(type == "H") {
            icon =  new ImageIcon("heartsIcon.png");
        } else if(type == "C") {
            icon =  new ImageIcon("clubsIcon.jpg");
        } else {
            icon =  new ImageIcon("spadesIcon.png");
        }
        // http://www.nullpointer.at/2011/08/21/java-code-snippets-howto-resize-an-imageicon/#comment-11870
          
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);
    }
    /**
     * Creates the label for the players name
    */
    public JLabel getTitle(int x, int y) {
        id.setText(this.name);
        id.setSize(60, 20);
        id.setLocation(x, y - 10);
        id.setForeground(new Color(255,255,255));
        return id;
    }
}