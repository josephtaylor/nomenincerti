package jto.processing;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.IOException;

/**
 * The main processing sketch.
 */
public class PixelSorter extends PApplet {

    private static final Integer NUM_SWAPS = 100000000;

    private NounDictionary nounDictionary;
    private ImageSearch imageSearch;
    private PImage img;
    private String noun;
    private TumblrPost tumblrPost;

    /**
     * The processing setup method.
     */
    @Override
    public void setup() {
        nounDictionary = new NounDictionary();
        noun = nounDictionary.getRandomNoun();
        imageSearch = new ImageSearch();
        String imageUrl = "";
        try {
            imageUrl = imageSearch.getImageUrl(noun);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            System.exit(-1);
        }

        tumblrPost = new TumblrPost();

        img = loadImage(imageUrl);
        size(img.width, img.height);
        background(255);
    }

    /**
     * The processing draw method.
     */
    @Override
    public void draw() {
        img.loadPixels();
        for(int i = 0; i < NUM_SWAPS; i++) {
            int index = (int) random(img.pixels.length);
            int pixel = img.pixels[index];
            float rand = random(1);
            if (rand < 0.5f) {
                swapRight(index, pixel, img.pixels);
            } else {
                swapTop(index, pixel, img.pixels);
            }
        }
        updatePixels();
        image(img,0,0);
        filter(BLUR);
        String filename = noun + System.currentTimeMillis() + ".png";
        saveFrame(filename);

        try {
            tumblrPost.postImage(filename, noun);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            System.exit(-1);
        }

        System.exit(0);
    }

    /**
     * Swaps the pixel to the right if the hue is lesser
     * @param index
     *      index of the current pixel.
     * @param pixel
     *      the color of the current pixel.
     * @param pixels
     *      the array of pixels of the image.
     */
    private void swapRight(int index, int pixel, int[] pixels) {
        if((index + 1) >= pixels.length) return;

        int right = pixels[index + 1];

        if(hue(right) < hue(pixel)) {
            pixels[index] = right;
            pixels[index + 1] = pixel;
        }
    }

    /**
     * Swaps the pixel with the pixel above it
     * if the value of red is greater than the one above it.
     * @param index
     *      the index of the current pixel.
     * @param pixel
     *      the color of the current pixel.
     * @param pixels
     *      the array of pixels of the image.
     */
    private void swapTop(int index, int pixel, int[] pixels) {
        if((index - width) < width) return;

        int top = pixels[index - width];
        if(red(top) < red(pixel)) {
            pixels[index] = top;
            pixels[index - width] = pixel;
        }
    }

    /**
     * The java main method.
     * Starts up the processing sketch.
     *
     * @param args
     *      arguments passed from the command line.
     *      None are needed in this case.
     */
    public static void main(String args[]) {
        PApplet.main("jto.processing.PixelSorter");
    }
}
