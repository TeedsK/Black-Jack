import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * This program simulates a poker game of black jack
 * @author Theo kremer
 * @version 1.0
*/
public class TableViewer {
    static JFrame frame;
    static JPanel homeBackground;
    static JPanel container;
    static CardLayout cl;
    static JPanel gameBackground;
    static JLabel winnerText = new JLabel("");
    static JButton hit = new JButton("Hit");
    static JButton Skip = new JButton("Skip");
    static ArrayList<PlayerHand> players = new ArrayList<PlayerHand>();
    /**
     * Resets the game board
    */
    static public void reset() {
        gameBackground.removeAll();
        players.clear();
        Cards.cards.clear();
        gameBackground.add(hit);
        gameBackground.add(Skip);
        gameBackground.revalidate();
        gameBackground.repaint();
    }
    
    public static void main(String[] args) {
        frame = new JFrame();
        frame.setSize(500, 600);
        frame.setTitle("Black Jack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Table game = new Table();
        hit = new JButton("Hit");
        Skip = new JButton("Skip");
        JButton AddPlayer = new JButton("Add Player");
        JButton newGame = new JButton("New Game");
        hit.setSize(70,30);
        Skip.setSize(70,30);
        AddPlayer.setSize(100,30);
        newGame.setSize(100,30);
        winnerText.setSize(200,30);
        winnerText.setForeground(new Color(255,255,255));
        winnerText.setFont(new Font(winnerText.getName(), Font.PLAIN, 17));
        Skip.setLocation(frame.getWidth() - hit.getWidth() - 140, frame.getHeight() - 100);
        AddPlayer.setLocation(135, (int) (frame.getHeight() / 25.0) * 13);
        hit.setLocation(frame.getWidth() - 120, frame.getHeight() - 100);
        winnerText.setLocation(62 + AddPlayer.getWidth() + 20, (int) (frame.getHeight() / 25.0) * 15);
        newGame.setLocation(135 + AddPlayer.getWidth() + 20, (int) (frame.getHeight() / 25.0) * 13);
        container = new JPanel(new CardLayout());
        
        gameBackground = new JPanel();
        homeBackground = new JPanel();
        gameBackground.setLayout(null);
        
        gameBackground.add(hit);
        gameBackground.add(Skip);
        homeBackground.add(winnerText);
        homeBackground.add(newGame);
        homeBackground.add(AddPlayer);
        gameBackground.setBackground(new Color(18, 67, 39)); 

        HomeScreen home = new HomeScreen();
        container.add(gameBackground, "GameBoard");
        container.add(home.getPanel(), "Home");
        cl = (CardLayout) (container.getLayout());
        cl.show(container, "Home");
        frame.add(container);
        
       
        
       

        class MousePressListener implements MouseListener {  
            public void mousePressed(MouseEvent event) {}
            public void mouseReleased(MouseEvent event) {}
            public void mouseClicked(MouseEvent event) {
                Object obj = event.getSource();
                if(obj == hit) {
                        game.turn.getCard();
                        gameBackground.add(game.turn.setVisualPanel());
                        gameBackground.repaint();
                    if(!game.turn.getAlive()) {
                        if(players.indexOf(game.turn) != players.size() - 1) {
                            game.turn.waiting();
                            game.turn = players.get(players.indexOf(game.turn) + 1);
                            game.turn.active();
                        } else {
                            String txt;
                            if(game.getWinner() != null) {
                                txt = "Winner: " + game.getWinner().getName();
                            } else {
                                txt = "No Winner";
                            }
                            winnerText.setText(txt);
                            cl.show(container, "Home");
                            reset();
                        }
                    }
                }
                else if(obj == newGame) {
                    if(Table.playerNumber > 1) {
                        CardLayout cl = (CardLayout) (container.getLayout());
                        cl.show(container, "GameBoard");
                        game.startGame();
                    } else {
                        winnerText.setText("2 player minimum");
                    }
                }
                else if(obj == Skip) {
                    if(players.indexOf(game.turn) != players.size() - 1) {
                        game.turn.waiting();
                        game.turn = players.get(players.indexOf(game.turn) + 1);
                        game.turn.active();
                    } else {
                        String txt;
                            if(game.getWinner() != null) {
                                txt = "Winner: " + game.getWinner().getName();
                            } else {
                                txt = "No Winner";
                            }
                            winnerText.setText(txt);
                            cl.show(container, "Home");
                            reset();
                    }
                }
                if(obj == AddPlayer) {
                    game.addPlayer();
                    home.setPlayers();
                }
            }
            public void mouseEntered(MouseEvent event) {}
            public void mouseExited(MouseEvent event) {}
         }

        MouseListener a = new MousePressListener();
        hit.addMouseListener(a);
        AddPlayer.addMouseListener(a);
        Skip.addMouseListener(a);
        newGame.addMouseListener(a);
        frame.setVisible(true);
    }
}