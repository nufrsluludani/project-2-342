import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaccaratDealer {
    ArrayList<Card> deck;
    ArrayList<Integer> dealedCards = new ArrayList<>();
    String[] suites = {"HEART","SPADE","DIAMOND","CLUB"};

    public void generateDeck() {
        deck = new ArrayList<Card>();
        dealedCards.clear();
        for(int i = 0; i < 4; i++) {
            for (int j = 1; j < 14; j++) {
                Card temp = new Card(suites[i], j);
                deck.add(temp);
            }
        }
    }
    public ArrayList<Card> dealHand(){

        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(drawOne());
        hand.add(drawOne());

        return hand;
    }
    public Card drawOne(){
        boolean used = false;
        Random rand = new Random();
        int n;
        while(true) {
            n = rand.nextInt(53);
            for (int i = 0; i < dealedCards.size(); i++) {
                if (dealedCards.get(i) == n) {
                    used = true;
                }
            }
            if (!used) {
                break;
            }
        }
        return(new Card(deck.get(n).getSuite(),deck.get(n).getValue()));
    }
    public void shuffleDeck(){

    }
    public int deckSize(){
        return 0;
    }
}
