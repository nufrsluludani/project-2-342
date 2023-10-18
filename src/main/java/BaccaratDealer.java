import java.util.ArrayList;
import java.util.List;

public class BaccaratDealer {
    ArrayList<Card> deck;
    String[] suites = {"HEART","SPADE","DIAMOND","CLUB"};

    public void generateDeck() {
        deck = new ArrayList<Card>();
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

    }
    public void shuffleDeck(){

    }
    public int deckSize(){
        return 0;
    }
}
