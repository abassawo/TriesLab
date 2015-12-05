package nyc.c4q.abassawo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    /** Path to the system dictionary file.  */
    public static final String DICTIONARY_FILE = "/usr/share/dict/words";
    public static final String TEST_WORDS = "/Users/c4q-Abass/Projects/TriesLab/src/nyc/c4q/abassawo/testwords.txt";

    /**
     * Returns true if 'string' consists entirely of the letters A through Z.
     */
    public static boolean isAlphabetic(String string) {
        for (int i = 0; i < string.length(); ++i) {
            final char c = string.charAt(i);
            if (!('A' <= c && c <= 'Z'))
                return false;
        }
        return true;
    }

    /**
     * Loads words in the system dictionary.
     *
     * Words are converted to upper case.  Only words that are entirely alphabetic are included.
     *
     * @return
     *   A set containing all words in the dictionary.
     */



    public static WordTrie loadDictionary(String src) {
        //Set<String> dictionary = new HashSet<String>();
        WordTrie trie = new WordTrie();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(src));
            for (String word = reader.readLine(); word != null; word = reader.readLine()) {
                word = word.toUpperCase();
                if (isAlphabetic(word)){
                    trie.add(word);
                }

            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
        return trie;
    }

    public static void main(String[] args) {
        // Load the words in the dictionary.
       final  WordTrie trie = loadDictionary(DICTIONARY_FILE);

        // Print the dictionary.
        for (String word : trie.toList())
            System.out.println(word);
        System.out.println();

        // Loop for user input.
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            // Ask for a word.
            System.out.print("word? ");
            final String word = scanner.nextLine().toUpperCase();

            if (word.equals(""))
                // Empty input: all done.
                break;
            else if (!isAlphabetic(word))
                System.out.println("'" + word + "' is not alphabetic");
            else {
                final boolean inDictionary = trie.contains(word);
                System.out.println("'" + word + "' " + (inDictionary ? "is" : "is not") +
                        " in the dictionary");
            }
        }
    }

}