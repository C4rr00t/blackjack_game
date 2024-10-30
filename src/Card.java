public class Card
{
    private final String suit;
    private final int value;

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
