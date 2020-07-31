/**
Class for the heart cards
*/
public class Hearts extends Cards {
    int shownValue;
    int value;
    String type;
    public Hearts(int value) {
        this.shownValue = value;
        type = "H";
        cards.add(this);
    }
    /**
     * @return the type of the card
     */
    public String toString() {
        return type;
    }
    String[] faces = {"J", "Q", "K", "A"};
    /**
     * @return the value shown on the card
     */
    String getShownValue() {
        if(shownValue == 1 || shownValue == 13) {
            value = 11;
            return faces[3];
        }
        if(shownValue > 10) {
            value = 10;
            return faces[shownValue - 10];
        }
        value = shownValue;
        return Integer.toString(shownValue);
    }
    /**
     * @return the worth of the card
     */
    int getValue() {
        return value;
    }
}