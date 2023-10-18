//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class BaccaratDealer {
    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<Integer> dealedCards = new ArrayList<>();
    String[] suites = new String[]{"HEART", "SPADE", "DIAMOND", "CLUB"};

    public BaccaratDealer() {
    }

    public void generateDeck() {
        this.deck.clear();
        this.dealedCards.clear();

        for(int i = 0; i < 4; ++i) {
            for(int j = 1; j < 14; ++j) {
                Card temp = new Card(this.suites[i], j);
                this.deck.add(temp);
            }
        }

    }

    public ArrayList<Card> dealHand() {
        ArrayList<Card> hand = new ArrayList();
        hand.add(this.drawOne());
        hand.add(this.drawOne());
        return hand;
    }

    public Card drawOne() {
        boolean used = false;
        Random rand = new Random();

        int n;
        do {
            n = rand.nextInt(53);

            for(int i = 0; i < this.dealedCards.size(); ++i) {
                if ((Integer)this.dealedCards.get(i) == n) {
                    used = true;
                }
            }
        } while(used);

        return new Card(((Card)this.deck.get(n)).getSuite(), ((Card)this.deck.get(n)).getValue());
    }

    public void shuffleDeck() {
        generateDeck();
        Collections.shuffle(this.deck);
    }

    public int deckSize() {
        return deck.size();
    }
}
