package pokerhandsorter.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HandTest {
    @Test
    public void testCalcRank() {
        String play = "9C 9D 8D 7C 3C 2S KD TH 9H 8H";
        String[] strings = play.split(" ");
        String[] player1Hand = Arrays.copyOfRange(strings, 0, 5);
        String[] player2Hand = Arrays.copyOfRange(strings, 5, 10);

        Hand hand = new Hand(player1Hand);
        Assert.assertEquals(2, hand.calcRank());

        Hand hand2 = new Hand(player2Hand);
        Assert.assertEquals(1, hand2.calcRank());
    }
}