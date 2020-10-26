package pokerhandsorter.model;

public class Card implements Comparable<Card> {

    private int value;
    private char suit;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    Card(String cardString) {
        if (cardString.length() == 2) {
            initValueFrom(cardString);
            this.suit = cardString.charAt(1);
        }

      /*else
          determine how to handle if wrong input
          1. may skip that line */

    }

    public void initValueFrom(String cardString) {
        switch (cardString.charAt(0)) {
            // Check cases greater than 9 .... cause they are character
            case 'T':
                this.value = 10;
                break;

            case 'J':
                this.value = 11;
                break;

            case 'Q':
                this.value = 12;
                break;

            case 'K':
                this.value = 13;
                break;

            case 'L':
                this.value = 14;
                break;

            default:
                this.value = (int) cardString.charAt(0);
                break;
        }
    }

    public boolean compareSuite(Card card2) {
        if (this.suit == card2.suit) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Card card2) {
        if( this.getValue() > card2.getValue()) return 1;
        return 0;
    }
}
