package pokerhandsorter;

import pokerhandsorter.model.Hand;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class PokerHandSorter {

    public static void main(String[] args) throws FileNotFoundException {

        //Greeting
        System.out.println("Welcome to poker sorter");
        String output = getResultFromInputFile();
        // Write output to a file
        writeOutputToFile(output);
    }

    public static String getResultFromInputFile() throws FileNotFoundException {

        Scanner inFile = new Scanner(new FileReader("src\\main\\resources\\poker-hands-draft.txt"));

        int play1WinCount = 0;
        int play2WinCount = 0;
        int drawCount = 0;
        String[] player1HandInput;
        String[] player2HandInput;
        Hand player1Hand;
        Hand player2Hand;
        while (inFile.hasNext()){
            String play = inFile.nextLine();
            String[] strings = play.split(" ");
            player1HandInput = Arrays.copyOfRange(strings,0,5);
            player2HandInput = Arrays.copyOfRange(strings,5,10);
            player1Hand = new Hand(player1HandInput);
            player2Hand = new Hand(player2HandInput);
            int res = player1Hand.compareHandForWinner(player2Hand);
            if(res ==1) {
                play1WinCount++;
            } else if(res ==-1) {
                play2WinCount++;
            } else {
                drawCount++;
                System.out.println(Arrays.toString(player1HandInput));
                System.out.println(Arrays.toString(player2HandInput));
            }
        }

        String outputString = "Player1:" + play1WinCount + "\n" + "Player2:" + play2WinCount;

        System.out.println(outputString);

        return outputString;
    }

    public static void writeOutputToFile( String output){
        try {
            File myObj = new File("src\\main\\resources\\output\\poker-hands-output.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(myObj);
            myWriter.write(output);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving output.");
            e.printStackTrace();
        }
    }
}
