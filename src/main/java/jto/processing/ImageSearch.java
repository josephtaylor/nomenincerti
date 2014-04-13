package jto.processing;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * This is responsible for searching the google search API for a random image
 * given a search string.
 */
public class ImageSearch {

    private static final String API_KEY = "your_google_search_api_key";

    /**
     * Searches google for a random image given the search string.
     * @param search
     *          the search string.
     * @return
     *          the URL for a random image from google.
     * @throws IOException
     *          if anything happens in the http request/response.
     */
    public String getImageUrl(String search) throws IOException {
        URLConnection connection = setupConnection(search);
        JSONObject response = getSearchResults(connection);
        return extractRandomUrl(response);
    }

    /**
     * This extracts the URL of a random image from the response.
     * @param response
     *      the <code>JSONObject</code> response.
     * @return
     *      the URL.
     */
    private String extractRandomUrl(JSONObject response) {
        JSONObject responseData = (JSONObject) response.get("responseData");
        JSONArray results = (JSONArray) responseData.get("results");
        int index = (int) Math.random() * results.length();

        JSONObject image = (JSONObject) results.get(index);

        return (String) image.get("url");
    }

    /**
     * This sets up the URL and establishes the connection.
     * @param search
     *          the string to search.
     * @return
     *          a connection object.
     * @throws IOException
     *          if it fails to establish a connection.
     */
    private URLConnection setupConnection(String search) throws IOException {
        String searchString = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&"
                + "q=" + search + "&userip=USERS-IP-ADDRESS"
                + "&searchType=image"
                + "&key=" + API_KEY
                + "&imgSize=large";

        URL url = new URL(searchString);
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("Referer", "http://jtoprocessing.tumblr.com");

        return connection;
    }

    /**
     * This gets the result from the serach query.
     * @param connection
     *          the URLConnection
     * @return
     *          a JSON object of the response.
     * @throws IOException
     *         if reading from the connection fails.
     */
    private JSONObject getSearchResults(URLConnection connection) throws IOException {
        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return new JSONObject(builder.toString());
    }
}
