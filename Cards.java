import java.util.*;

public abstract class Cards {
    static ArrayList<Cards> cards = new ArrayList<Cards>();
    abstract int getValue();
    abstract String getShownValue();
    public Cards() {}
    /**
     * @return a random card
    */
    static public Cards randomCard() {
        int random = getRandom(0, cards.size() - 1);
        Cards card = cards.get(random);
        cards.remove(random);
        return card;
    }
    /**
     * @return a random number
    */
    static public int getRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    /**
     * @return the card size
    */
    static public int getCards() {
        return cards.size();
    }
}