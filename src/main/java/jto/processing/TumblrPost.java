package jto.processing;

import com.google.common.collect.Lists;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.PhotoPost;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This class creates and uploads tumblr photo posts.
 */
public class TumblrPost {

    private JumblrClient client;

    private static final String CONSUMER_KEY = "your_api_consumer_key";

    private static final String CONSUMER_SECRET = "your_api_secret_key";

    private static final String OAUTH_TOKEN = "your_oauth_token";

    private static final String OAUTH_SECRET = "your_oauth_secret_token";

    /**
     * Sets up the Jumblr client for authentication.
     */
    private void connect() {
        client = new JumblrClient(CONSUMER_KEY, CONSUMER_SECRET);
        client.setToken(OAUTH_TOKEN, OAUTH_SECRET);
    }

    /**
     * Creates a post for the given photo and caption (noun).
     *
     * @param filename
     *          the image file to be uploaded
     * @param noun
     *          the caption for the post
     * @return
     *          the generated PhotoPost
     */
    private PhotoPost generatePost(String filename, String noun) {
        PhotoPost post = new PhotoPost();
        post.setData(new File(filename));
        post.setCaption(noun);
        post.setBlogName("nomenincerti");
        post.setClient(client);
        post.setTags(setupTags());

        return post;
    }

    /**
     * Creates an image post to tumblr with the given file and caption.
     *
     * @param filename
     *      the filename of the image to be uploaded.
     * @param caption
     *      the caption of the post.
     */
    public void postImage(String filename, String caption) throws IOException {
        connect();
        PhotoPost post = generatePost(filename, caption);
        post.save();
    }

    /**
     * Generates a list of Strings of the tags for posts.
     * @return
     */
    private List<String> setupTags() {
        return Lists.newArrayList(
                "nomen incerti",
                "jtoprocessing",
                "pixel sorting",
                "Art",
                "Artists on Tumblr",
                "J. Taylor O'Connor"
        );
    }
}
