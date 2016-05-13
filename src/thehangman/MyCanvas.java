/*
 * Melissa Page
 * Section 1B
 * Final Project: Hangman
 */

package thehangman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Melissa Page
 */
class MyCanvas extends Canvas {
    private int guesses;
    
    /**
     * MyCanvas
     * @param none
     * @return none
     */
    public MyCanvas() {
        setBackground (Color.WHITE);   
    }

    /**
     * Set the number of guesses made, so you know what to draw, and repaint
     * @param guessCount
     * @return none
     */
    public void setGuesses(int guessCount) {
        this.guesses = guessCount;
        this.repaint();
    }

    /**
     * Paint initial canvas
     * @param g
     * @return none
     */
    public void paint (Graphics g) {
        BufferedImage img = null;

        // switch statement for what part of the body to draw
        switch (guesses) {
            // gallows
            case 0:
                try {
                    img=ImageIO.read(getClass().getResourceAsStream("gallows.jpg"));
                    } catch (IOException ex) {
                        Logger.getLogger(MyCanvas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                g.drawImage(img, 0, 10, this);
                break;
                
            // head
            case 1:
                try {
                img=ImageIO.read(getClass().getResourceAsStream("hangmanhead.jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(MyCanvas.class.getName()).log(Level.SEVERE, null, ex);
                }
                g.drawImage(img, 0, 10, this);
                break;
            
            // body
            case 2:
                try {
                img=ImageIO.read(getClass().getResourceAsStream("hangmanbody.jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(MyCanvas.class.getName()).log(Level.SEVERE, null, ex);
                }
                g.drawImage(img, 0, 10, this);
                break;
            
            // arm
            case 3:
                try {
                img=ImageIO.read(getClass().getResourceAsStream("hangmanarm.jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(MyCanvas.class.getName()).log(Level.SEVERE, null, ex);
                }
                g.drawImage(img, 0, 10, this);
                break;
            
            // arm 2
            case 4:
                try {
                img=ImageIO.read(getClass().getResourceAsStream("hangmanarm2.jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(MyCanvas.class.getName()).log(Level.SEVERE, null, ex);
                }
                g.drawImage(img, 0, 10, this);
                break;
            
            // leg
            case 5:
                try {
                img=ImageIO.read(getClass().getResourceAsStream("hangmanleg.jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(MyCanvas.class.getName()).log(Level.SEVERE, null, ex);
                }
                g.drawImage(img, 0, 10, this);
                break;
            
            // leg 2
            case 6:
                try {
                img=ImageIO.read(getClass().getResourceAsStream("hangmanleg2.jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(MyCanvas.class.getName()).log(Level.SEVERE, null, ex);
                }
                g.drawImage(img, 0, 10, this);
                break;
            
            // quit the program if you somehow end up outside that range
            default:
                System.exit(0);
        }
      }
   }
