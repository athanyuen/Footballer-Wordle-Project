import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        List<String[]> arrayOfPlayers = readFileIntoArray("Big6.csv");
        String[] chosenPlayer = randomPlayer(arrayOfPlayers, "Big6.csv");
        String[] everyPlayerName = everyName(arrayOfPlayers, "Big6.csv");
        //System.out.println(chosenPlayer[0]);
        int numberOfLines = countLinesInFile("Big6.csv");
        for (int i = 1; i <= 8; i++) {
            System.out.print(ANSI_RESET + "Enter your guess: ");
            String inputGuess = scan.nextLine();
            int count = 0;
            // Checks if the entered guess is invalid
            for (String s : everyPlayerName) {
                if (!inputGuess.equalsIgnoreCase(s)) {
                    count++;
                }
            }
            // Prints invalid guess and moves on to the next guess
            // Player loses a guess
            if (count == numberOfLines) {
                System.out.println(ANSI_YELLOW + "Invalid Guess!");

            }
            // Checks if the entered guess is the correct guess
            // Game ends
            if (inputGuess.equalsIgnoreCase(chosenPlayer[0])) {
                for (int m = 1; m <= 5; m++) {
                    if(m == 3){
                        System.out.println(ANSI_GREEN + "Number: " + chosenPlayer[m]);
                    }
                    if(m == 4){
                        System.out.println(ANSI_GREEN + "Age: " + chosenPlayer[m]);
                    }
                    else if(m == 1 || m == 2 || m == 5){
                        System.out.println(ANSI_GREEN + chosenPlayer[m]);
                    }
                }
                System.out.println(ANSI_BLUE + "Good Job! You Won!");
                System.exit(0);
            }
            //
            else {
                for (String[] guessArray : arrayOfPlayers) { // Loops through arraylist of arrays of players
                    if (inputGuess.equalsIgnoreCase(guessArray[0])) {// Finds out the array of the player the user guessed
                        for (int m = 1; m <= 5; m++) {
                            if (guessArray[m].equalsIgnoreCase(chosenPlayer[m])) {
                                if (m == 3) {
                                    System.out.println(ANSI_GREEN + "Number: " + guessArray[m]);
                                }
                                if (m == 4) {
                                    System.out.println(ANSI_GREEN + "Age: " + guessArray[m]);
                                } else if (m == 1 || m == 2 | m == 5) {
                                    System.out.println(ANSI_GREEN + guessArray[m]);
                                }
                            } else {
                                if (m == 3) {
                                    int number1 = Integer.parseInt(guessArray[m]);
                                    int number2 = Integer.parseInt(chosenPlayer[m]);
                                    if (number1 < number2) {
                                        System.out.println(ANSI_WHITE + "Number: " + guessArray[m] + " higher");
                                    } else {
                                        System.out.println(ANSI_WHITE + "Number: " + guessArray[m] + " lower");
                                    }
                                } else if (m == 4) {
                                    int number1 = Integer.parseInt(guessArray[m]);
                                    int number2 = Integer.parseInt(chosenPlayer[m]);
                                    if (number1 < number2) {
                                        System.out.println(ANSI_WHITE + "Age: " + guessArray[m] + " higher");
                                    } else {
                                        System.out.println(ANSI_WHITE + "Age: " + guessArray[m] + " lower");
                                    }
                                } else {
                                    System.out.println(ANSI_WHITE + guessArray[m]);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(ANSI_RED + "You lost! You used up 8 guesses. The correct answer was:" );
        for (int m = 0; m <= 5; m++) {
            if(m == 3){
                System.out.println(ANSI_RED + "Number: " + chosenPlayer[m]);
            }
            if(m == 4){
                System.out.println(ANSI_RED + "Age: " + chosenPlayer[m]);
            }
            else if(m == 0 || m == 1 || m == 2 || m == 5){
                System.out.println(ANSI_RED + chosenPlayer[m]);
            }
        }
        System.exit(0);
    }



    /*  Creates an array of every player name from the text file
     *  Takes the first index (index 0) of every array
     *  Takes the array list and text file as parameters
     *  Returns an array with every player name in it
     */
    public static String [] everyName(List<String[]> arrayListName, String inputFileName) throws FileNotFoundException {
        int numberOfLines = countLinesInFile(inputFileName);
        String[] allPlayerNames;
        allPlayerNames = new String[numberOfLines];
        for (int i = 0; i < arrayListName.size(); i++) {
            String [] array = arrayListName.get(i);
            allPlayerNames[i] = array[0];
        }
        return allPlayerNames;
    }


    /*  Reads the text file and turns each line into an array
     *  Takes the test file name as a parameter
     *  Each array is stored as an element in an array list
     *  Returns an array list
     */
    public static List<String[]> readFileIntoArray(String fileName) throws FileNotFoundException {
        List<String[]> data = new ArrayList<>();
        String testRow;
        try {
            // Open and read the file
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            // Read data as long as it's not empty
            // Parse the data by comma using .split() method
            // Place into a temporary array, then add to List
            while ((testRow = br.readLine()) != null) {
                String[] line = testRow.split(",");
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found " + fileName);
        } catch (IOException e) {
            System.out.println("ERROR: Could not read " + fileName);
        }
        int numberOfLines = countLinesInFile(fileName);
        for(int i = 0; i < numberOfLines; i++){
            String [] array = data.get(i);
            array[5] = "Nationality: " + array[5];
        }
        return data;
    }


    /*  Selects a random player from the text file
     *  Takes the array list and text file as parameters
     *  Returns the array of the randomly chosen player
     */
    public static String[] randomPlayer(List<String[]> arrayListName, String inputFileName) throws FileNotFoundException {
        int numberOfLines = countLinesInFile(inputFileName);
        double random = Math.floor(Math.random() * numberOfLines + 1) ;
        int value = (int) random;
        return arrayListName.get(value);
    }


    /*  Counts the number of lines in a text file
     *  Takes the text file as a parameter
     *  Returns an integer with the amount of lines
     */
    public static int countLinesInFile(String inputFileName) throws FileNotFoundException{
        File file = new File(inputFileName);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        return lineCount;
    }

    /*  Colour for output text
     */
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_WHITE = "\u001b[37m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

}