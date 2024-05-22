import java.util.Random;

/**
 * Class for a 52 card deck, 4 houses 13 ranks
 * Allows only for creation of a deck, and drawing cards
 */
public class Deck{
    private Random rand = new Random();
    private int[][] deck = new int[4][13];
        
    /**
     * Constructor for a new deck without any parameters
     * Assigns values 1-13 for Ace-King in each house
     */
    public Deck(){
        // Sets the value of each card one row at a time
        for(int count = 0; count < deck.length; count++){
            for(int count2 = 1; count2 <= deck[count].length; count2++){
                deck[count][count2-1] = count2;
            }
        }
    }
    
    /**
     * Method for drawing a random card
     * Each card can only be drawn once
     * @return the value of the drawn card 1-13
     */
    public int draw(){
        int suit = rand.nextInt(4);
        int number = rand.nextInt(13);
        
        // Checks if the card was already drawn
        while(deck[suit][number] == 0){
            suit = rand.nextInt(4);
            number = rand.nextInt(13);
        }
        
        // Marks the card as drawn if it wasn't already
        int drawnCard = deck[suit][number];
        deck[suit][number] = 0;
        
        return drawnCard;
    }
    
    // More methods could be implemented as needed, most notably like shuffle()
    // As the game is now, it's impossible to draw all 52 cards from the deck, so there's no need to shuffle or return cards to a deck
}