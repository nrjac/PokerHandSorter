package pokerhandsorter.utils;

import java.util.Map;

public class CardOperations {
    /* if 4 cards same returns 4
     *  if 3 cards of same value and a pair ... return 5
     *  if only 3 cards of same value ------- return 3
     *  else return number of pairs
     * */
    public static int numberOfPairs(Map<Integer, Integer> map) {

        int pairCount = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 4) {
                return 4;
            }

            if (entry.getValue() == 3) {
                if (pairCount == 1) {
                    return 5;
                } else {
                    return 3;
                }
            }


            if (entry.getValue() == 2) {
                pairCount++;
            }
        }

        if (pairCount == 2) {
            return 2;
        } else if (pairCount == 1) {
            return 1;
        } else return 0;
    }

    public static int compareTwoArrays( int[] values1, int[] values2) {
        for (int i = 4; i >= 0; i--) {
            if ( values1[i] > values2[i]) return 1;
            if ( values1[i] < values2[i]) return 2;
        }
        return 0;
    }
}
