package pokerhandsorter.model;


import pokerhandsorter.utils.CardOperations;

import java.util.*;

public class Hand {
    private Card[] cards = new Card[5];
    private int rank;

    public Hand(Card[] cards) {
        this.cards = cards;
    }

    public Hand(String[] cardStrings) {
        Card[] cards = new Card[5];
        int index = 0;
        for (String str : cardStrings) {
            Card card = new Card(str);
            cards[index++] = card;
        }
        this.cards = cards;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void sortCardsInHand() {
        Arrays.sort(this.cards);
    }

    // Return Rank value of the Hand
    public int calcRank() {
        sortCardsInHand();

        // check Royal flush
        if (this.areCardsInStraight() && this.cards[0].getValue() == 10 && areCardsInSameSuite()) return 10;

        // Straight flush: All five cards in consecutive value order, with the same suit
        if (areCardsInSameSuite() && areCardsInStraight()) return 9;

        int pairCheck = CardOperations.numberOfPairs(this.getHashMapOfValues());

        // Four of a kind: Four cards of the same value
        if (pairCheck == 4) return 8;

        // Full house: Three of a kind and a Pair
        if (pairCheck == 5) return 7;

        // Flush: All five cards having the same suit
        if (areCardsInSameSuite()) return 6;

        // Straight: All five cards in consecutive value order
        if (areCardsInStraight()) return 5;

        // Three of a kind: Three cards of the same value
        if (pairCheck == 3) return 4;

        // Two pairs: Two different pairs
        if (pairCheck == 2) return 3;

        // A pair: Two cards of same value
        if (pairCheck == 1) return 2;

        // High card: Highest value card
        return 1;
    }


    /*Compares ramks of two hands
     * 1: greter
     * 2: smaller
     * 3: same rank
     *
     * */
    public int compareHandForWinner(Hand hand) {
        int thisRank = this.calcRank();
        int handRank = hand.calcRank();
        if (thisRank > handRank) return 1;
        if (thisRank < handRank) return -1;

        if( thisRank == 4 || thisRank == 7){
            if( getTripleIndex() > hand.getTripleIndex()) return 1;
            if( getTripleIndex() < hand.getTripleIndex()) return -1;
        }
        if( thisRank == 8){
            if( getSameValueCardOf4Index() > hand.getSameValueCardOf4Index()) return 1;
            if( getSameValueCardOf4Index() < hand.getSameValueCardOf4Index()) return -1;
        }
        if( thisRank == 7 || thisRank == 3 || thisRank == 2){
            int[] indexOfPair = getPairsIndexArray();
            int[] indexOfPair2 = hand.getPairsIndexArray();
            int pairOfIndexValue = indexOfPair[0] * 100 + indexOfPair[1];
            int pairOfIndexValue2 = indexOfPair2[0] * 100 + indexOfPair2[1];
            if( pairOfIndexValue > pairOfIndexValue2) return 1;
            if( pairOfIndexValue < pairOfIndexValue2) return -1;
        }
        return CardOperations.compareTwoArrays(getCardValuesInHand(), hand.getCardValuesInHand());
    }
    // utils methods
    public boolean areCardsInSameSuite() {
        Card initialCard = this.cards[0];
        for (int i = 1; i < 5; i++) {
            if (!this.cards[i].compareSuite(initialCard)) {
                return false;
            }
        }
        return true;
    }

    public boolean areCardsInStraight() {
        int previousSuit = this.cards[0].getValue();
        for (int i = 1; i < 5; i++) {
            int thisValue = this.cards[i].getValue();
            if (!(previousSuit == thisValue + 1)) {
                return false;
            } else {
                previousSuit = thisValue;
            }
        }
        return true;
    }

    public int[] getCardValuesInHand() {
        int[] values = new int[5];
        for (int i = 0; i < 5; i++) {
            int thisValue = this.cards[i].getValue();
            values[i] = thisValue;
        }
        return values;
    }

    public Map<Integer, Integer> getHashMapOfValues() {

        Map<Integer, Integer> map = new HashMap<>();

        // Traverse through card array values and
        // count frequencies for each values

        map.put(this.cards[0].getValue(), 1);

        for (int i = 1; i < 5; i++) {
            int myValue = this.cards[i].getValue();
            if (map.containsKey(myValue)) {
                map.put(myValue, map.get(myValue) + 1);
            } else {
                map.put(myValue, 1);
            }
        }
        return map;
    }

    public int getTripleIndex(){
        int key = 0;
        Map<Integer, Integer> map = getHashMapOfValues();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 3) {
                key = entry.getKey();
            }
        }
        return key;
    }

    public int getSameValueCardOf4Index(){
        int key = 0;
        Map<Integer, Integer> map = getHashMapOfValues();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 4) {
                key = entry.getKey();
            }
        }
        return key;
    }

    public int[] getPairsIndexArray(){
        int keys[] = { 0, 0 };
        int indexOfKeys = 0;
        Map<Integer, Integer> map = getHashMapOfValues();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 2) {
                keys[indexOfKeys] = entry.getKey();
                indexOfKeys++;
            }
        }
        return keys;
    }
}
