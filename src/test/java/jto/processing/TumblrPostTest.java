package jto.processing;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link TumblrPost}.
 */
public class TumblrPostTest {

    /**
     * Test for {@link TumblrPost#postImage(String, String)}.<br/>
     *
     * This is ignored because it will actually post to the blog.
     *
     * @throws IOException
     */
    @Ignore
    @Test
    public void testPostPhoto() throws IOException {
        TumblrPost tumblrPost = new TumblrPost();
        tumblrPost.postImage("pixelsort-1396836471496.png", "pixelsort");
    }
}
