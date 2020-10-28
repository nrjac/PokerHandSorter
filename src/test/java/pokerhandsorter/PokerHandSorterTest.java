package pokerhandsorter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PokerHandSorterTest {
    @Test
    public void testGetResultFromInputFile() {
        try {
            String expectedOutput = "Player1:275\n" + "Player2:225";
            String output = PokerHandSorter.getResultFromInputFile();
            Assert.assertEquals(expectedOutput, output);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteToFile() throws FileNotFoundException {
        String output = "Player1" +
                ":275\n" + "Player2:225";
        String output2 = PokerHandSorter.getResultFromInputFile();
        PokerHandSorter.writeOutputToFile(output2);
        Scanner inFile = new Scanner(new FileReader("src\\main\\resources\\output\\poker-hands-output.txt"));
        String[] expectedOutputLines = output.split("\n");
        int lineNumber = 0;
        while (inFile.hasNext()) {
            Assert.assertEquals(expectedOutputLines[lineNumber++], inFile.next());
        }
    }

}