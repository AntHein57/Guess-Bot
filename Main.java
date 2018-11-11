import java.awt.*;
import java.util.Calendar;

public class Main {


    public static void background(){
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.05);
        // Sets up screen background
        StdDraw.setXscale(-10.0,10.0);
        StdDraw.setYscale(-10.0,10.0);
        StdDraw.rectangle(-5.0,0.0,3,3);
        StdDraw.rectangle(5.0,0.0,3,3);

        // Designs choices 1,2
        Font font = new Font("Arial", Font.BOLD, 60);
        StdDraw.setFont(font);
        StdDraw.text(-5.0,0.0,"1");
        StdDraw.text(5.0,0.0,"2");
    }



    public static void main(String[] args) {

        String sentinel = "Y";
        int numGuess = 0;
        int numRight = 0;

        final int startSize = 20;

        // Display some basic text explaining the program to the user.
        StdOut.println("=============================================================================================");
        StdOut.println();
        StdOut.println("Welcome to ACTION GUESSER!");
        StdOut.println();
        StdOut.println("This program will prompt you to give the computer a sample of entries, then based on these" +
                "\nentries, will try to predict what you will do next. The nature of these entries is extremely \nsimple" +
                " (1 or 2) but the back end of the program is performing massive computations.");
        StdOut.println();
        StdOut.println("The goal is to predict your action with an accuracy of > 50 %.");
        StdOut.println();
        StdOut.println("=============================================================================================");
        StdOut.println();

        while (sentinel.equalsIgnoreCase("Y")) {

        // Create collective object from 10 blank brains. 10 is hardcoded for the moment.
        Brain[] brains = new Brain[startSize];
        for (int i = 0; i < brains.length; i++)
            brains[i] = new Brain();

        int[] inputs = new int[startSize + 1];
        for (int i = 0; i < inputs.length; i++) {
            do { // can implement try-catch block for inputting a double by accident
                StdOut.print("Please enter entry number " + (i + 1) + ": ");
                inputs[i] = StdIn.readInt();
            } while (inputs[i] != 1 && inputs[i] != 2);
        }

        // Create 10 brains from 11 items. These values are hardcoded for the moment, but items must always
        // be one greater than brains.
        for (int b = 0; b < brains.length; b++) {

            // Each brain will have sequences of this many numbers/ array of this size.
            int seqLength = b + 2;
            int[] temp = new int[seqLength];

            for (int start = b; start + seqLength < brains.length; start+= seqLength) {
                for (int run = 0; run < seqLength; run++) {
                    temp[run] = inputs[start + run];
                }

                brains[b].add(new Seq(temp));
            }
        }

        Collective collective = new Collective(brains);

        background(); // sets up background

        // waits for enter to be pressed
        StdOut.println("---------------------------------------------------------------------------------\n");
        StdOut.println("I will now start guessing...");

        numGuess++;
            background();

            StdOut.println("Show this to your partner as I predict...partner press enter.");

            while (true) {
                if (StdDraw.hasNextKeyTyped()) {
                    if (StdDraw.nextKeyTyped() == '\n') {
                        break;
                    }
                }
            }

            StdOut.println();

            // Calculate prediction.
            int p = collective.predict(inputs);

            if (p == 0)
                p = (int) Math.round(Math.random() + 1);

            // Computer reveals guess by coloring a square.
            StdDraw.setPenColor(StdDraw.BLUE);
            if (p == 1)
                StdDraw.rectangle(-5.0, 0.0, 3, 3);
            else
                StdDraw.rectangle(5.0, 0.0, 3, 3);

            StdOut.println("Partner, please press enter again and turn computer to user.\n");

            while (true) {
                if (StdDraw.hasNextKeyTyped()) {
                    if (StdDraw.nextKeyTyped() == '\n') {
                        break;
                    }
                }
            }

            background();

            // Prompt to enter 12th number.
            int nextNum;
            do { // can implement try-catch block for inputting a double by accident
                StdOut.print("Please input your next number: ");
                nextNum = StdIn.readInt();
                StdOut.println();
            } while (nextNum != 1 && nextNum != 2);


            // if computer guesses correctly, shade guessed box green
            // otherwise, shade guessed box red

            // computer guessed 1 correctly
            if (p == 1 && nextNum == 1) {
                StdDraw.setPenColor(StdDraw.GREEN);
                StdDraw.filledRectangle(-5.0, 0.0, 3, 3);
                StdDraw.text(-5.0, 0.0, "1");
                StdOut.println("I guessed correctly!");
                numRight++;
            }

            // computer guessed 1 but is wrong
            else if (p == 1) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledRectangle(-5.0, 0.0, 3, 3);
                StdDraw.text(-5.0, 0.0, "1");
                StdOut.println("I guessed incorrectly...");
            }

            // computer guessed 2 but is wrong
            else if (p == 2 && nextNum == 1) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledRectangle(5.0, 0.0, 3, 3);
                StdDraw.text(5.0, 0.0, "2");
                StdOut.println("I guessed incorrectly...");
            }

            // computer guessed 2 correctly
            else {
                StdDraw.setPenColor(StdDraw.GREEN);
                StdDraw.filledRectangle(5.0, 0.0, 3, 3);
                StdDraw.text(5.0, 0.0, "2");
                StdOut.println("I guessed correctly!");
                numRight++;
            }

            System.out.print("Guess again? (Y/N): ");
            sentinel = StdIn.readString();
            StdOut.println("\n=======================================================================================");

        }

        StdOut.println("\nI predicted " + (double) numRight/numGuess * 100 + "% right.");

    }
}



