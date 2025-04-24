import java.io.*;
import java.util.Scanner;

public class assignmentFIve {
    private static BinaryTree<String> gameTree;
    private static final String FILENAME = "game_tree.ser";

    public static void main(String[] args) {
        gameTree = loadTree(FILENAME);  // Load tree from file

        if (gameTree.isEmpty()) {
            createTree1(gameTree);  // Create a new tree if none exists
        }

        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        while (playing) {
            playGame(scanner);
            System.out.println("Do you want to play again? (yes/no)");
            playing = scanner.nextLine().trim().equalsIgnoreCase("yes");
        }

        saveTree(gameTree, FILENAME);  // Save the updated tree before exiting
        scanner.close();
    }

    public static void playGame(Scanner scanner) {// Method for running down the tree with user inputs accordingly
        BinaryNodeInterface<String> currentNode = gameTree.getRootNode();

        while (!currentNode.isLeaf()) {
            System.out.println(currentNode.getData());
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                currentNode = currentNode.getLeftChild();
            } else if (response.equals("no")) {
                currentNode = currentNode.getRightChild();
            } else {
                System.out.println("Invalid input, please enter 'yes' or 'no'.");
            }
        }

        // Reached a guess (leaf node)
        System.out.println("I think you thinking of " + currentNode.getData() + "? (yes/no)");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("yes")) {
            System.out.println("I guessed it right!");
        } else {
            expandTree(scanner, currentNode); //Calling expand method upon incorrect guess
        }
    }

    public static void expandTree(Scanner scanner, BinaryNodeInterface<String> currentNode) { // Method for expanding tree upon failing to guess 
        System.out.println("I don't know! What were you thinking of?");
        String correctAnswer = scanner.nextLine().trim();

        if (correctAnswer.isEmpty()) {
            System.out.println("Input cannot be empty! Please enter a valid response.");// Handling invalid inputs
            return;
        }

        //Differing question from user
        System.out.println("Give me a question to differentiate " + correctAnswer + " from " + currentNode.getData());
        String newQuestion = scanner.nextLine().trim();

        System.out.println("Is this the right answer for'" + correctAnswer + "'? (yes/no)");
        boolean isYes = scanner.nextLine().trim().equalsIgnoreCase("yes");

        //Creating new node from new information from user
        if (isYes) {
            currentNode.setLeftChild(new BinaryNode<>(correctAnswer));
            currentNode.setRightChild(new BinaryNode<>(currentNode.getData()));
        } else {
            currentNode.setLeftChild(new BinaryNode<>(currentNode.getData()));
            currentNode.setRightChild(new BinaryNode<>(correctAnswer));
        }
        currentNode.setData(newQuestion);//Setting the question got from user as part of the tree
    }

    public static void createTree1(BinaryTree<String> tree) { //Creating a tree of four levels
        System.out.println("\nCreating a tree...");

        BinaryTree<String> hTree = new BinaryTree<>("Tiger");
        BinaryTree<String> iTree = new BinaryTree<>("Wolf");
        BinaryTree<String> jTree = new BinaryTree<>("Canary");
        BinaryTree<String> kTree = new BinaryTree<>("Fish");
        BinaryTree<String> lTree = new BinaryTree<>("Phone");
        BinaryTree<String> mTree = new BinaryTree<>("Tree");
        BinaryTree<String> nTree = new BinaryTree<>("Ireland");
        BinaryTree<String> oTree = new BinaryTree<>("Happiness");

        BinaryTree<String> dTree = new BinaryTree<>("Is it a feline?", hTree, iTree);
        BinaryTree<String> eTree = new BinaryTree<>("Is it a bird?", jTree, kTree);
        BinaryTree<String> fTree = new BinaryTree<>("Is it man-made?", lTree, mTree);
        BinaryTree<String> gTree = new BinaryTree<>("Is it a country?", nTree, oTree);

        BinaryTree<String> bTree = new BinaryTree<>("Is it a mammal?", dTree, eTree);
        BinaryTree<String> cTree = new BinaryTree<>("Is it an object?", fTree, gTree);

        tree.setTree("Are you thinking of an animal?", bTree, cTree);
    }

    public static void saveTree(BinaryTree<String> tree, String filename) {// Method for saving a tree
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(tree);
            System.out.println("Game tree saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving game tree: " + e.getMessage());
        }
    }

    public static BinaryTree<String> loadTree(String filename) {// Method for loading tree
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (BinaryTree<String>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved game tree found. Creating a new one...");
            return new BinaryTree<>();
        }
    }
}


