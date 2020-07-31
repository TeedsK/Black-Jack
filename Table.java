import javax.swing.text.TableView;
/**
 * Class for the game board, displays the table and cards of the players
*/
public class Table  {
    static int playerNumber;
    PlayerHand turn;

    /**
     * adds another player to the game
    */
    public void addPlayer() {
        playerNumber++;
    }

    /**
     * Creates the object for the player and adds cards
     * @param x the player number
     */
    public void setPlayer(int x) {
        int xL = 90;
        PlayerHand p = new PlayerHand();
        p.setCards();
        p.setX(x * xL);
        TableViewer.players.add(p);
        p.setName("Player " + Integer.toString(x));
        TableViewer.gameBackground.add(p.getTitle((xL * x) +  10, 20));
        TableViewer.gameBackground.add(p.setVisualPanel());
        TableViewer.gameBackground.add(p.setVisualPanel());
        p.waiting();
        TableViewer.gameBackground.repaint();
    }
    /**
     * Starts the poker game
     */
    public void startGame() {
        setCards();
        for(int x = 0; x < playerNumber; x++) {
            setPlayer(x);
        }
        turn = TableViewer.players.get(0);
        TableViewer.players.get(0).active();
        TableViewer.gameBackground.repaint();
    }
    /**
     * Creates 52 cards for poker deck
     */
    public void setCards() {
        for(int x = 1; x < 14; x++) {
            new Hearts(x);
        }
        for(int x = 1; x < 14; x++) {
            new Clubs(x);
        }
        for(int x = 1; x < 14; x++) {
            new Diamonds(x);
        }
        for(int x = 1; x < 14; x++) {
            new Spades(x);
        }
        
    }
    /**
     * @return winner of the game
     */
    public PlayerHand getWinner() {
        PlayerHand winner = null;
        for(int x = 0; x < TableViewer.players.size(); x++) {
            if(TableViewer.players.get(x).getAlive()) {
                if(winner == null) {
                    winner = TableViewer.players.get(x);
                }
                if(CheckHand.getSum(TableViewer.players.get(x)) > CheckHand.getSum(winner)) {
                    winner = TableViewer.players.get(x);
                } 
            }
        }
        return winner;
    }
}