import java.util.ArrayList;
import java.util.*;

public class Blackjack
{
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        Deck deck = new Deck();

        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();

        playerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());

        System.out.println("Your cards: " + playerHand);
        System.out.println("Dealer's face-up card: " + dealerHand.getFirst());

        while (true) {
            System.out.print("Hit or Stand? (h/s): ");
            String action = console.nextLine();

            if (action.equals("h")) {
                playerHand.add(deck.drawCard());
                System.out.println("Your cards: " + playerHand);

                if (calculateHandValue(playerHand) > 21) {
                    System.out.println("You busted! Dealer wins.");
                    return;
                }
            } else {
                break;
            }
        }

        System.out.println("Dealer's cards: " + dealerHand);
        while (calculateHandValue(dealerHand) < 17) {
            dealerHand.add(deck.drawCard());
            System.out.println("Dealer hits. Dealer's cards: " + dealerHand);
        }

        int playerTotal = calculateHandValue(playerHand);
        int dealerTotal = calculateHandValue(dealerHand);

        System.out.println("Your total: " + playerTotal);
        System.out.println("Dealer's total: " + dealerTotal);

        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("You win!");
        } else if (playerTotal == dealerTotal) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer wins!");
        }

        console.close();
    }

    public static int calculateHandValue (ArrayList<Card> hand)
    {
        int total = 0;
        int aces = 0;

        for (Card card : hand) {
            total += card.getValue();
            if (card.getValue() == 1) {
                aces++;
            }
        }

        while (aces > 0 && total + 10 <= 21) {
            total += 10;
            aces--;
        }

        return total;
    }
}
