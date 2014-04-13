package jto.processing;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertNotNull;

/**
 * Test case for {@link ImageSearch}.
 */
public class ImageSearchTest {

    /**
     * Test for {@link ImageSearch#getImageUrl(String)}.
     * @throws IOException
     */
    @Test
    public void testGetImageUrl() throws IOException {
        ImageSearch imageSearch = new ImageSearch();
        String url = imageSearch.getImageUrl("dad");
        assertNotNull(url);
        System.out.println("The ImageSearch returned: " + url);
    }
}
