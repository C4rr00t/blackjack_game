import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
    ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        for (String suit : suits) {
            for (int i = 1; i <= 13; i++) {
                int cardValue = Math.min(i, 10);
                cards.add(new Card(cardValue, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public Card riggedDrawForPlayer() {
        for (Card card : cards) {
            if (card.getValue() >= 8) {
                cards.remove(card);
                return card;
            }
        }
        return drawCard();
    }

    public Card riggedDrawForDealer(int playerTotal) {
        for (Card card : cards) {
            if (Blackjack.calculateHandValue(new ArrayList<>(Collections.singletonList(card))) <= playerTotal && card.getValue() >= 7) {
                cards.remove(card);
                return card;
            }
        }
        return drawCard();
    }

    public Card drawCard() {
        return cards.removeFirst();
    }
}
