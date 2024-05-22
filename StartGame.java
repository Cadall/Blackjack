/* Cody Asher
May 2024, 
Blackjack game for my portfolio*/

import java.util.*; // Using ArrayList, Scanner, and HashMap

/**
 * The class that runs main, housing all the primary logic of this program
 */
public class StartGame{
    
    // Scanner object to be used in blackjack() and replay()
    private static Scanner scan = new Scanner(System.in);
    
    /**
     * Starts the program, introducing players to the game
     */
    public static void main(String[] args){
        System.out.println("Welcome to Blackjack, standard rules apply here.\nAces will automatically switch their value according to what benefits the hand.");
        System.out.println("Would you like to read the rules? (Input \"Y\" or \"N\")");
        String viewRules = scan.nextLine();
        
        while(!viewRules.equalsIgnoreCase("Y") && !viewRules.equalsIgnoreCase("N")){
            System.out.println("Invalid input. Please input \"Y\" or \"N\"");
            viewRules = scan.nextLine();
        }
        if(viewRules.equalsIgnoreCase("Y")){
            System.out.println("\nThe game starts with the Player(s) and Dealer each recieving 2 cards.\nOne of the dealer's cards will be face-down, so the players won't know the value. The dealer knows what this card is.\n\nThe player is then presented with the option to \"Hit\" or \"Stand\". If you want to draw another card, you hit. If you're alright with your current cards, you stand. Standing will lock your cards for the rest of the hand. You may hit as many times as you want unless you reach/go over 21.\n\nThe goal is to get your cards as close to a total value of 21 as you can without going over. Each card is valued at the displayed number, and face cards are worth 10 each.\n\nAn Ace can be worth either 1 or 11, depending on circumstances. For example, an Ace and a 7 can be valued at 8 or 18, since 18 is better, it would be worth 18 if you stand, but you can still hit to try getting 21.\n\nIf you go over 21, you bust, which means you automatically lose. Getting exactly 21, means you automatically win. If nobody gets 21, the person with the closest to 21 without busting is the winner.");
        }
        System.out.println("Starting the game, enjoy!");
        blackjack();
    }

    /**
     * The main game functions, contains all the logic for blackjack
     * Draws cards, checks values, takes player input, and determines the winner
     */
    public static void blackjack(){
        Deck deck = new Deck();
        
        // Utilizing a HashMap to intertwine a card's index in the Deck as an int with it's name as a String
        Map<Integer, String> cardMap = new HashMap<>();
        cardMap.put(1, "Ace");
        cardMap.put(2, "Two");
        cardMap.put(3, "Three");
        cardMap.put(4, "Four");
        cardMap.put(5, "Five");
        cardMap.put(6, "Six");
        cardMap.put(7, "Seven");
        cardMap.put(8, "Eight");
        cardMap.put(9, "Nine");
        cardMap.put(10, "Ten");
        cardMap.put(11, "Jack");
        cardMap.put(12, "Queen");
        cardMap.put(13, "King");
        
        // Using an ArrayList for each of these to allow easy addition and access of values
        ArrayList<Integer> pCards = new ArrayList<>();
        ArrayList<Integer> dCards = new ArrayList<>();
        ArrayList<String> pCardName = new ArrayList<>();
        ArrayList<String> dCardName = new ArrayList<>();
        
        /* Drawing 2 cards each from the deck for the player and dealer,
        adding the appropriate name to the respective CardName ArrayList*/
        pCards.add(deck.draw());
        pCardName.add(cardMap.get(pCards.get(0)));
        pCards.add(deck.draw());
        pCardName.add(cardMap.get(pCards.get(1)));
        dCards.add(deck.draw());
        dCardName.add(cardMap.get(dCards.get(0)));
        dCards.add(deck.draw());
        dCardName.add(cardMap.get(dCards.get(1)));
        
        int pSum = 0;
        int dSum = 0;
        
        // Assigning the total sum of the player and dealer hands, making sure face cards (index value > 10) are worth 10
        for(Integer card : pCards){
            if(card > 10){
                pSum += 10;
            }
            else{
                pSum += card;
            }
        }
        for(Integer card : dCards){
            if(card > 10){
                dSum += 10;
            }
            else{
                dSum += card;
            }
        }
        
        // Showcasing what cards the dealer and player were dealt, showing one of the dealer's cards as a "?", representing it's face-down.
        System.out.println("Dealer:\n+ "+dCardName.get(0)+"\n+ ?\n");
        System.out.println("Player:\n+ "+pCardName.get(0)+"\n+ "+pCardName.get(1)+"\n");
        
        // Checks whether or not the dealer got a natural 21, or blackjack
        if(dSum == 11 && (dCards.get(0) == 1 || dCards.get(1) == 1)){
            dSum = 21;
            System.out.println("Dealer Blackjack!");
            
            // Checks if the player got one as well. If they both have a blackjack, it leads to a push, which is a tie.
            if(pSum == 11 && (pCards.get(0) == 1 || pCards.get(1) == 1)){
                pSum = 21;
                System.out.println("Player Blackjack!");
                push(dSum);
            }
            // If the player didn't get blackjack as well, the dealer wins.
            else{
                dWin(dSum);
            }
        }
        
        // If the dealer didn't get blackjack, and the player does, this triggers their immediate win.
        else if(pSum == 11 && (pCards.get(0) == 1 || pCards.get(1) == 1)){
            System.out.println("Player Blackjack!");
            pWin(dSum);
        }
        
        // Player's turn
        
        // While loop for the player drawing as many cards as they like, without getting or going over 21.
        String hit = "Filler";
        while(!hit.equals("s")){
            
            // Scans the player's next input, telling them to try again if it's not "H" or "S" for hit/stand
            System.out.println("Hit or Stand? (Input H/S)");
            hit = scan.next();
            hit = hit.toLowerCase();
            while(!hit.equalsIgnoreCase("h") && !hit.equalsIgnoreCase("s")){
                System.out.println("Invalid input, try again.");
                hit = scan.next();
            }
            
            /* As long as the player input says they want to hit, this adds a card to their hand,
            adjusts the value of their hand, and calculates any immediate victory or loss */
            if(hit.equals("h")){
                pCards.add(deck.draw());
                pCardName.add(cardMap.get(pCards.get(pCards.size()-1)));
                // Prints a message telling the player what card they got.
                System.out.println("+ "+pCardName.get(pCards.size()-1)+"\n");
                
                /* If they draw a face card, add 10 to the sum, otherwise add the cards index value 
                (which is equal to it's actual value) */
                if(pCards.get(pCards.size()-1) > 10){
                    pSum += 10;
                }
                else{
                    pSum += pCards.get(pCards.size()-1);
                }
                
                // Checks if the player made 21 with a card, or can make 21 by converting an Ace's value to 11
                if(pSum == 11 && pCards.indexOf(1) != -1 || pSum == 21){
                    System.out.println("The Player made 21!");
                    pWin(dSum);
                }
                // Checks if the player busted
                else if(pSum > 21){
                    System.out.println("The Player busted.");
                    dWin(dSum);
                }
            }
            
            /* Once the player stands, this checks if they have an ace in their hand,
            which can be valued at 11 instead of 1 without busting, and increases the value if so */
            else if (pSum < 11 && pCards.indexOf(1) != -1){
                pSum += 10;
            }
        }
        
        // Dealer's turn
        
        // If the dealer's sum is less than the player's, do one addition check before drawing
        if(dSum < pSum){
            
            // Checks if the dealer has an Ace, which counted at 11, would boost them above the player without busting
            if(dSum + 10 > pSum && dSum + 10 <= 21 && dCards.indexOf(1) != -1){
                dSum += 10;
                System.out.println("The Player didn't draw higher than the Dealer's hand.");
                dWin(dSum);
            }
            
            System.out.println("The Dealer is drawing...");
            /* If the dealer has less than the player, including Aces, begin drawing more cards for the dealer,
            until the dealer gets equal/more than the player, or busts trying. Very similar to the player drawing code */
            while(dSum < pSum){
            
                dCards.add(deck.draw());
                dCardName.add(cardMap.get(dCards.get(dCards.size()-1)));
                // Prints a message telling the player what card the dealer got
                System.out.println("+ "+dCardName.get(dCards.size()-1)+"\n");
                
                // Adds the appropriate card value to the dealer's hand
                if(dCards.get(dCards.size()-1) > 10){
                    dSum += 10;
                }
                else{
                    dSum += dCards.get(dCards.size()-1);
                }
                
                // Checks if the player has made 21 with their card, or can by changing their Ace's value
                if((dSum == 11 && dCards.indexOf(1) != -1) || dSum == 21){
                    System.out.println("The Dealer made 21!");
                    dWin(dSum);
                }
                // If the dealer can surpass the player by changing an Ace, it does so
                else if (dSum + 10 > pSum && dCards.indexOf(1) != -1 && dSum + 10 < 21){
                    dSum += 10;
                }
                
                else if(dSum > 21){
                    System.out.println("The Dealer busted.");
                    pWin(dSum);
                }
                
                // Informs the player how the dealer won/tied
                if(dSum > pSum){
                    System.out.println("The Dealer was able to draw higher than the Player's hand.");
                    dWin(dSum);
                }
                else if(dSum == pSum){
                    System.out.println("The Dealer equaled the Player's hand.");
                    push(dSum);
                }
            }
        }
        
        // Informs the player how they lost/tied
        if(dSum > pSum){
            System.out.println("The Player didn't draw higher than the Dealer.");
            dWin(dSum);
        }
        else if(dSum == pSum){
            System.out.println("The Player equaled the Dealer's hand.");
            push(dSum);
        }
        
        System.out.println("There has been a logic error, sorry for the inconvenience.");
        replay();
    }
    
    
    /**
     * Method for a push/tie.
     * @param dealerSum the sum of the dealer's hand
     */
    public static void push(int dealerSum){
        System.out.println("It's a draw at "+dealerSum+", hand over.");
        replay();
    }
    
    /**
     * Method for a dealer victory.
     * @param dealerSum the sum of the dealer's hand
     */
    public static void dWin(int dealerSum){
        System.out.println("The Dealer wins with "+dealerSum+", hand over.");
        replay();
    }
    
    /**
     * Method for a player victory.
     * @param dealerSum the sum of the dealer's hand
     */
    public static void pWin(int dealerSum){
        System.out.println("The Player wins against the Dealer's "+dealerSum+", hand over.");
        replay();
    }
    
    /**
     * Method which asks the player to replay, repeating the question on an invalid input
     */
    public static void replay(){
        System.out.println("Would you like to play another round? (Input Y/N)");
        String input = scan.next();
        if(input.equalsIgnoreCase("y")){
            blackjack();
        }
        else if(input.equalsIgnoreCase("n")){
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
        else{
            System.out.println("Invalid Input");
            replay();
        }
    }
}