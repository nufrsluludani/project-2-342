import java.util.ArrayList;
public class BaccaratGameLogic {
    public String whoWon(ArrayList<Card> Banker, ArrayList<Card> Player){

        int BankerTotal = this.handTotal(Banker);
        int PlayerTotal = this.handTotal(Player);

        if((BankerTotal == PlayerTotal)){
            return "Draw";
        }

        if(9 - BankerTotal > 9 - PlayerTotal){
            return "Player";
        }
        else{
            return "Banker";
        }
    }
    public int handTotal(ArrayList<Card> hand){

        int total = 0;

        // get total
        for(int i = 0; i < hand.size(); i++){
            if(hand.get(i).getValue() <= 9) {
                total += hand.get(i).getValue();
            }
        }

        if(total > 9){ // get rids of first digit
            total = total % 10;
        }

        return total;
    }
    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card
    playerCard){
        int sum = this.handTotal(hand);
        if (sum <= 6){
            return true;
        }
        return false;
    }
    public boolean evaluatePlayerDraw(ArrayList<Card> hand){
        int sum = this.handTotal(hand);  // get sum of card values
        if(sum <= 5){ // 5 or less
            return true;
        }
        return false; // 6 and up
    }
}
