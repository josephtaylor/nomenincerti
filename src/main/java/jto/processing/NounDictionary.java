package jto.processing;

import com.google.api.client.util.Lists;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * This class wraps a text file containing a long list of nouns.
 */
public class NounDictionary {
    private static final Double DICTIONARY_LENGTH = 104237.0d;
    private static final String DICTIONARY_FILE = "pos/nouns.txt";
    private static List<String> words = Lists.newArrayList();

    /**
     * Static initializer loads the words from the file.
     */
    static {
        loadWords();
    }

    /**
     * Retrieves a random noun from the list of words.
     * @return
     */
    public String getRandomNoun() {
        int index = (int) (Math.random() * DICTIONARY_LENGTH);
        return words.get(index);
    }

    /**
     * Reads a text file containing a long list of nouns and stores the words in a list.
     * The file is formatted like:<br/><br/>
     *      <code>word\n</code>   <br/><br/>
     * on each line.
     */
    private static void loadWords() {
        InputStream inputStream = NounDictionary.class.getClassLoader().getResourceAsStream(DICTIONARY_FILE);
        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("\n");

        while(scanner.hasNext()) {
            words.add(scanner.next());
        }
    }
}
