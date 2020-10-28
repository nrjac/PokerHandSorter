package pokerhandsorter.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void initValueFrom() {
        String cardString1 = "5D";
        Card card = new Card(cardString1);
        Assert.assertEquals( 5, card.getValue());
        Assert.assertEquals( 'D' , card.getSuit());

        String cardString = "KS";
        card.initValueFrom(cardString);
        card.setSuit('S');
        Assert.assertEquals( 13, card.getValue());
        Assert.assertEquals( 'S', card.getSuit());
    }
}