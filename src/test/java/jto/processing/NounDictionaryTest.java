package jto.processing;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * Test case for {@link NounDictionary}.
 */
public class NounDictionaryTest {

    /**
     * Test for {@link jto.processing.NounDictionary#getRandomNoun()}.
     */
    @Test
    public void testGetRandomNoun() {
        NounDictionary nounDictionary = new NounDictionary();
        String noun = nounDictionary.getRandomNoun();
        assertNotNull(noun);
        System.out.println("The dictionary returned: " + noun);
    }
}
