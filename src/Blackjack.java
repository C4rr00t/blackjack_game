import java.util.ArrayList;
import java.util.*;

public class Blackjack
{
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        double playerFunds = 100.0;
        boolean playAgain = true;

        while (playAgain && playerFunds > 0) {
            System.out.println("\nYour current funds: $" + playerFunds);
            System.out.print("Place your bet: $");

            double bet = getValidBet(console, playerFunds);
            playerFunds -= bet;

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
                String action = console.nextLine().toLowerCase();

                if (action.equals("h")) {
                    playerHand.add(deck.riggedDrawForPlayer());
                    System.out.println("Your cards: " + playerHand);

                    if (calculateHandValue(playerHand) > 21) {
                        System.out.println("You busted! Dealer wins.");
                        break;
                    }
                } else if (action.equals("s")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'h' or 's'.");
                }
            }

            if (calculateHandValue(playerHand) <= 21) {
                System.out.println("Dealer's cards: " + dealerHand);
                while (calculateHandValue(dealerHand) < 17) {
                    dealerHand.add(deck.riggedDrawForDealer(calculateHandValue(playerHand)));
                    System.out.println("Dealer hits. Dealer's cards: " + dealerHand);
                }

                int playerTotal = calculateHandValue(playerHand);
                int dealerTotal = calculateHandValue(dealerHand);

                System.out.println("Your total: " + playerTotal);
                System.out.println("Dealer's total: " + dealerTotal);

                if (dealerTotal > 21 || playerTotal > dealerTotal) {
                    System.out.println("You win!");
                    playerFunds += bet * 2;
                } else if (playerTotal == dealerTotal) {
                    System.out.println("It's a tie!");
                    playerFunds += bet;
                } else {
                    System.out.println("Dealer wins!");
                }
            }

            System.out.print("Play again? (y/n): ");
            playAgain = console.nextLine().equalsIgnoreCase("y");
            System.out.print("Enter developer command or press Enter to continue: ");
            String command = console.nextLine();

            if (command.equals("reset")) {
                playerFunds = 100.0;
                System.out.println("Game reset: Funds set to $100.");
            } else if (command.equals("add50")) {
                playerFunds += 50;
                System.out.println("Added $50 to funds.");
            }
        }

        System.out.println("Thanks for playing! You cashed out with $" + playerFunds);
        console.close();
    }

    public static double getValidBet(Scanner console, double playerFunds) {
        while (true) {
            try {
                double bet = Double.parseDouble(console.nextLine());
                if (bet <= 0 || bet > playerFunds) {
                    throw new IllegalArgumentException("Bet must be between $1 and your current funds.");
                }
                return bet;
            } catch (Exception e) {
                System.out.print("Invalid input. Enter a valid bet amount: $");
            }
        }
    }

    public static int calculateHandValue(ArrayList<Card> hand) {
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
