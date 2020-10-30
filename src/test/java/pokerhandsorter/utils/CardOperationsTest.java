package pokerhandsorter.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardOperationsTest {
    @Test
    public void testCompareArrays() {
        int value1[] = {1, 2, 3, 5, 7};
        int value2[] = {1, 2, 4, 5, 7};
        int compareValue = CardOperations.compareTwoArrays(value1, value2);
        Assert.assertEquals(-1, compareValue);
    }
}