public class Card {
    String suite;
    int value;

    Card(String theSuite, int theValue){
        this.suite = theSuite;
        this.value = theValue;
    }

    void setValue(int newValue){
        this.value = newValue;
    }

    void setSuite(String newSuite){
        this.suite = newSuite;
    }


    int getValue(){
        return this.value;
    }

    String getSuite(){
        return this.suite;
    }

}
