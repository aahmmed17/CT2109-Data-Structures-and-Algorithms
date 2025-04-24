import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


public class Phonetic_Learner {
    public static void main(String[] args) {

        String[] phonetics = {"alpha", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel", //creation of string array of codewords in alphabetical order to match index of char array
                "india", "juliett", "kilo", "lima", "mike", "november", "oscar", "papa", "quebec", "romeo", "sierra",
                "tango", "uniform", "victor", "whiskey", "xray", "yankee", "zulu"};

        char[] alphabet = new char[26];//populating alphabet array with char a-z
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = (char) ('a' + i);
        }


        Scanner s = new Scanner(System.in);
        
        // Main loop to keep program running
        while (true) {
            System.out.println("\n [MAIN MENU] - (Type 'exit' to quit)");
            System.out.println("Please select a mode: (Enter 'learn' or 'test')");

            String modeSelection = s.nextLine().toLowerCase();

            // Exit condition
            if (modeSelection.equals("exit")) {
                System.out.println("Exiting program...");
                break;
            }

            // Learn mode
            if (modeSelection.equals("learn")) {
                while (true) {//continues until exit is typed
                    System.out.println("Enter a letter to learn its code word (or type 'exit' to return to menu):");
                    String entryInput = s.nextLine().toLowerCase();
                    
                    if (entryInput.equals("exit")) {//condition to exit program
                        break;
                    }
                    
                    if (entryInput.length() != 1 || !Character.isLetter(entryInput.charAt(0))) {// catching unintended inputs like multiple letters or empty input
                        System.out.println("Invalid input. Please enter a single letter.");
                        continue;
                    }
                    
                    char entry = entryInput.charAt(0);
                    
                    for (int i = 0; i < alphabet.length; i++) { //scanning through the char array to find a match for the input
                        if (entry == alphabet[i]) { //when a match is found
                            String codeWord = phonetics[i]; //stores the corresponding codeword for the inputted char
            
                            int hintLength = Math.min(2, codeWord.length());
                            String hint = codeWord.substring(0, hintLength) + "_".repeat(codeWord.length() - hintLength);
            
                            // asking the user to guess based on the hint
                            System.out.println("Hint: " + hint);
                            System.out.println("Try to guess the code word:");
            
                            String userGuess = s.nextLine().toLowerCase();
            
                            // checking if the guess is correct
                            if (userGuess.equals(codeWord)) {
                                System.out.println("Correct! The Code Word for '" + entry + "' is: " + codeWord);
                            } else {
                                System.out.println("Incorrect. The correct Code Word for '" + entry + "' is: " + codeWord);
                            }
                            break; // brings user back to enter a char
                        }
                    }
                }
            }
            
            // Test Mode
            else if (modeSelection.equals("test")) {
                Stack<String> codeWords = new Stack<>(); //creating a new stack for codewords
                Random rand = new Random();
                
                // Randomize stack
                for (int i = 0; i < alphabet.length; ++i) { // pushing a phonetic string of random index for length of char array
                    int idx = rand.nextInt(alphabet.length);
                    codeWords.push(phonetics[idx]);
                }

                int correct = 0;
                System.out.println("Testing knowledge. Type 'exit' to return to menu.");

                for (int i = 0; i < 26; i++) {
                    if (codeWords.isEmpty()) {
                        break; // loop is over if stack is empty
                    }

                    String correctCodeWord = codeWords.peek();// the correct word is the top of the stack at given interval
                    System.out.println("Enter the code word for the letter: " + correctCodeWord.charAt(0));
                    String testInput = s.nextLine().toLowerCase();

                    if (testInput.equals("exit")) {// allow the user to return to main menu
                        break;
                    }

                    if (testInput.equals(correctCodeWord)) {
                        System.out.println("Correct!");
                        correct++; //adds to correct score integer for right answer
                    } else {
                        System.out.println("Incorrect! The Code Word was: " + correctCodeWord);
                    }
                    codeWords.pop();// regardless of correct or incorrect result the stack is popped to continue to next question
                }
                System.out.println("Test Complete, Result: " + correct + "/26");
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
        s.close();
    }
}
