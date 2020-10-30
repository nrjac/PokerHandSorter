package pokerhandsorter.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class HandTest {
    @Test
    public void testCalcRank() {
//        String play = "9C 9D 8D 7C 3C 2S KD TH 9H 8H";
//        String play = "4H 4C 6S 7S KD 2C 3S 9S 9D TD";
        String play = "5D 8C 9S JS AC 2C 5C 7D 8S QH";
        String[] strings = play.split(" ");
        String[] player1Hand = Arrays.copyOfRange(strings, 0, 5);
        String[] player2Hand = Arrays.copyOfRange(strings, 5, 10);

        Hand hand = new Hand(player1Hand);
        Assert.assertEquals(1, hand.calcRank());

        Hand hand2 = new Hand(player2Hand);
        Assert.assertEquals(1, hand2.calcRank());
    }
}