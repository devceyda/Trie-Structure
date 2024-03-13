import java.util.Scanner;

//-----------------------------------------------------
// Title: Main class
// Author: Ceyda Kuşçuoğlu

// Description: This class runs the main method 
//-----------------------------------------------------
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TrieST<Integer> trie = new TrieST<>(); // creating new trie

        // System.out.println("Enter the number of inputs:");
        // int numInputs = Integer.parseInt(scanner.nextLine()); // taking the number of string

        // for (int i = 0; i < numInputs; i++) {
        //     String word = scanner.nextLine(); // taking words from user
        //     trie.put(word, i + 1); // give i + 1 to the value for simplicity and insert strings to trie
        // }
        trie.put("at", 1);
        trie.put("atomy", 2);
        trie.put("ate", 3);
        trie.put("ato", 4);
        trie.put("borned", 5);
        trie.put("born", 6);
        trie.put("curve", 7);

        while (true) {
            System.out.println("Enter Operation Code:");

            int operationCode = Integer.parseInt(scanner.nextLine());

            switch (operationCode) {

                case 1:
                    String searchWord = scanner.nextLine();
                    trie.Search(searchWord);
                    break;

                case 2:
                    trie.countPrefix();
                    break;

                case 3:
                    String suffix = scanner.nextLine();
                    trie.reverseFind(suffix);
                    break;

                case 4:
                    trie.ShortestUniquePrefix();
                    break;

                case 5:
                    trie.LongestCommonPrefix();
                    break;

                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid operation code. Try again.");
            }
        }
    }
}
