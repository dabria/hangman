/*
 * Melissa Page
 * Section 1B
 * Final Project: Hangman
 */

package thehangman;

import java.util.Random;

/**
 *
 * @author Melissa Page
 */

public class Words {
    private String word;
    private char[] blanks;
    private String[] words;
    
    /**
     * Construct word object
     * @param none
     * @return none
     */
    public Words() {
        this.words = new String[] {"jive", "chives", "pickles", "rabbit", "computer", "beef", "pies", "zipper", "feather", "metal"};
        Random random = new Random();
        int randomInt = random.nextInt(words.length);
        this.word = this.words[randomInt];
        this.blanks = new char[this.word.length()];
    }
    
    /**
     * Get the current word
     * @param none
     * @return this.word
     */
    public String getWord() {
        return this.word;
    }
    
    /**
     * Get the current word as an array of chars
     * @param none
     * @return this.word.toCharArray()
     */
    public char[] getWordCharArray() {
        return this.word.toCharArray();
    }
 
    /**
     * Get the blanks of a word
     * @param none
     * @return this.blanks
     */
    public char[] getBlanks() {
        for (int i = 0; i < this.word.length(); i++) {
            this.blanks[i] = '_';
        }
        return this.blanks;
    }
 
}
