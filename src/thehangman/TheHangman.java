/*
 * Melissa Page
 * Section 1B
 * Final Project: Hangman
 */
package thehangman;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;
import java.awt.Panel;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Melissa Page
 */
public class TheHangman {

    private static boolean replay;
    private JFrame mainFrame;
    private JLabel wordToGuessLabel;
    private JLabel yourGuessLabel;
    private MyCanvas hangContainer;
    private Panel guessContainer;
    private Panel previousGuessContainer;
    private JLabel previousGuessArr;
    private JLabel previousGuessLabel;
    private JTextField guess;
    private Words wordObj;
    private String theWord;
    private char[] wordCharArray;
    private char[] blankWord;
    private char[] guessArray;
    private int newLetter;
    private int guessCount;
    private Panel guessContainer2;
    private int yourScore;
    private int compScore;
    private JLabel scores;
    
    /**
     * Call GUI prep and start game
     * @param none
     * @return none
     */
    public TheHangman(){
        yourScore = 0;
        compScore = 0;
        prepareGUI();
        newGame();
    }
    
    /**
     * Main function
     * @param args - Command line arguments
     * @return none
     */
    public static void main(String[] args) {
        // prompt user with instructions, yes: start game, no: exit
        JLabel msgLabel = new JLabel("<html><body><center>Hangman is a game where you guess the letters in a hidden word.<br>In the dialog box, input a letter and hit enter.<br>Continue guessing until you've correctly guessed the word,<br>or until you've had six incorrect guesses and hung the man.<br><br>Do you want to play?</center></html></body>"); 
        int response = JOptionPane.showConfirmDialog(null, msgLabel, "Welcome to Hangman",
        JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
          TheHangman RunHangman = new TheHangman();
        }
        else {
           JOptionPane.showMessageDialog(null, "Goodbye!");
           System.exit(0);
        }
    }
    
    /**
     * Prepare the window
     * @param none
     * @return none
     */
    public void prepareGUI() {
        // set up the main frame
        mainFrame = new JFrame("Hangman - by Melissa Page");
        mainFrame.setSize(650,450);
        mainFrame.getContentPane().setBackground(Color.WHITE);
        mainFrame.setLayout(new BorderLayout(10, 20));
        
        // add listener for exit
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }        
        });
    }
    
    /**
     * Create/Display all components, listener logic
     * @param none
     * @return none
     */
    public void createComponents() {
        // create the different components and set their initial values
        Font labelFont = new Font("Courier New", Font.PLAIN, 14);
        Font lilLabelFont = new Font("Courier New", Font.PLAIN, 12);

        Font font = new Font("Courier New", Font.PLAIN, 25);
        Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
        attributes.put(TextAttribute.TRACKING, 0.4);
        Font font2 = font.deriveFont(attributes);
        wordToGuessLabel = new JLabel();
        wordToGuessLabel.setFont(font2);
        wordToGuessLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordToGuessLabel.setText(String.valueOf(blankWord));
        
        hangContainer = new MyCanvas();
        
        previousGuessContainer = new Panel();
        previousGuessContainer.setLayout(new BorderLayout(0, 0));
        previousGuessLabel = new JLabel();
        previousGuessLabel.setFont(labelFont);
        previousGuessLabel.setText("Previous Guesses: ");
        previousGuessArr = new JLabel();
        previousGuessArr.setFont(lilLabelFont);
        
        guessContainer = new Panel();
        guessContainer.setLayout(new BorderLayout(0, 0));
        guessContainer2 = new Panel();
        yourGuessLabel = new JLabel();
        yourGuessLabel.setFont(labelFont);
        yourGuessLabel.setText("Guess a letter: ");
        guess = new JTextField("", 4);
        guess.setFont(lilLabelFont);
        guess.setHorizontalAlignment(SwingConstants.CENTER);
        
        scores = new JLabel();
        scores.setFont(labelFont);
        scores.setHorizontalAlignment(SwingConstants.CENTER);
        scores.setText("Your Score: " + yourScore + "   " + "Computer Score: " + compScore);
        
        
        // logic for guess textfield
        guess.addActionListener(new ActionListener() {
            /**
            * Interact with textfield
            * @param ActionEvent e
            * @return none
            */
            public void actionPerformed(ActionEvent e) {
                // get the guess
                String theGuess = guess.getText().toLowerCase();
                
                // handle nulls, mutiple characters
                if (theGuess.length() < 1 || !Character.isLetter(theGuess.charAt(0)) || theGuess.length() > 1) {
                    guess.setText("");
                    return;
                }
                
                // if char is already in array, return
                for (int i = 0; i < guessArray.length; i++) {
                    if (theGuess.charAt(0) == guessArray[i]) {
                        guess.setText("");
                        return;
                    }
                }
                
                // create array of guesses
                guessArray[newLetter] = theGuess.charAt(0);
                newLetter++;
                
                // make sure funky chars aren't printed to screen because array is initialized large
                String guessedChar = String.valueOf(guessArray).replace("\0","");
                
                // add the guessed char to the label
                previousGuessArr.setText(guessedChar);
                
                // guess as a char
                char guessChar = guess.getText().toLowerCase().charAt(0);
                
                guess.setText("");
                
                // check if the word has the guessed char
                boolean wordIsIn = theWord.contains("" + guessChar);
                if (wordIsIn == true) {
                    for (int i = 0; i < wordCharArray.length; i++) {
                        // if the guessed char is in the word, then set char in proper blank spot
                        if (wordCharArray[i] == guessChar) {
                            blankWord[i] = guessChar;
                        }
                    }
                    // if you guess everything proper, you've won, ask to play again
                    wordToGuessLabel.setText(String.valueOf(blankWord));
                    if (Arrays.equals(blankWord, wordCharArray)) {
                        previousGuessArr.setText("You win!");
                        yourScore++;
                        playAgain();
                    }
                }
                else {
                    // increase wrong guess counter, and send that to canvas to draw
                    guessCount++;
                    hangContainer.setGuesses(guessCount);
                    
                    // after 6 guesses you've lost, ask to play again
                    if (guessCount == 6) {
                        previousGuessArr.setText("You lose!");
                        wordToGuessLabel.setText("The word was: " + theWord);
                        compScore++;
                        playAgain();
                    }
                }
            }
        });
        
        // Add components to their proper place
        mainFrame.add(wordToGuessLabel,BorderLayout.NORTH);
        mainFrame.add(previousGuessContainer, BorderLayout.WEST);
        mainFrame.add(guessContainer,BorderLayout.EAST);
        
        Dimension minSize = new Dimension(50, 150);
        Dimension prefSize = new Dimension(50, 150);
        Dimension maxSize = new Dimension(50, 150);
        
        previousGuessContainer.add(new Box.Filler(minSize, prefSize, maxSize), BorderLayout.WEST); 
        previousGuessLabel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
        previousGuessContainer.add(previousGuessLabel, BorderLayout.NORTH);
        previousGuessArr.setBorder(BorderFactory.createEmptyBorder(-190, 50, 0, 50));
        previousGuessContainer.add(previousGuessArr, BorderLayout.CENTER);
        
        guessContainer.add(new Box.Filler(minSize, prefSize, maxSize), BorderLayout.EAST);
        yourGuessLabel.setBorder(BorderFactory.createEmptyBorder(50, 50, 20, 50));
        guessContainer.add(yourGuessLabel, BorderLayout.NORTH);
        guessContainer.add(guessContainer2, BorderLayout.CENTER);
        guessContainer2.add(guess, BorderLayout.NORTH);
        Dimension guessDimensions = new Dimension(10, 30);
        guess.setPreferredSize(guessDimensions);
        guess.setMaximumSize(guessDimensions);
        
        mainFrame.add(hangContainer,BorderLayout.CENTER); 
        
        mainFrame.add(scores,BorderLayout.SOUTH); 
    }
    
    /**
     * set the visibility
     * @param none
     * @return none
     */
    public void showHangman() {
        mainFrame.setVisible(true);
    }
    
    /**
     * Prompt window to play again
     * @param none
     * @return none
     */
    public void playAgain() {
        // play again pop-up
        JLabel playLabel = new JLabel("<html><body><center>Play again?</center></html></body>"); 
        int response = JOptionPane.showConfirmDialog(null, playLabel, " ",
        JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            newGame();
        }
        else {
           JOptionPane.showMessageDialog(null, "Goodbye!");
           System.exit(0);
        }
    }
    
    /**
     * Construct a new game
     * @param none
     * @return none
     */
    public void newGame() {
        wordObj = new Words();
        theWord = wordObj.getWord();
        blankWord = new char[this.theWord.length()];
        blankWord = wordObj.getBlanks();
        wordCharArray = new char[this.theWord.length()];
        wordCharArray = wordObj.getWordCharArray();
        guessArray = new char[26];
       
        newLetter = 0;
        guessCount = 0;
        
        // remove old components
        mainFrame.getContentPane().removeAll();
        
        // add new components
        createComponents();
        mainFrame.setVisible(true);
    }
}
