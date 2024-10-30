import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
    ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        for (String suit : suits) {
            for (int i = 0; i <= 13; i++) {
                int cardValue = Math.min(i, 10);
                cards.add(new Card(cardValue, suit));
            }
        }

        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.removeFirst();
    }
}
