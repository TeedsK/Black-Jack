import java.util.*;
/**
 * Class for checking players hands
*/
public class CheckHand  {
    /**
     * gets the sum of the players hand
     * @return the sum
    */
    static public int getSum(PlayerHand player) {
        int sum = 0;
        for(int x = 0; x < player.viewHand().size(); x++) {
            sum += player.viewHand().get(x).getValue();
        }
        return sum;
    }
    /**
     * Gets wether the players hand is less than 21
    */
    public boolean getAlive(PlayerHand player) {
        int sum = getSum(player);
        if(sum > 21) {
            player.id.setText("Out");
            return false;
        }
        return true;
    }
    
}