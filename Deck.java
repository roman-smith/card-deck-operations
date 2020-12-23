// Author: Roman Smith
// Class/Assignment: CSE 205/Assignment 2
// Due Date: June 12, 2020
// Description: class for a Deck, holds variables and methods for Deck class

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardCount;
    private int maxSize;

    // private method to build a standard deck of cards (ordered), called in the unparam constructor
    private ArrayList<Card> standardDeck() {
        ArrayList<Card> returnDeck = new ArrayList<>();
        int index = 0;

        // adds D suited cards for the first 13 cards, if the card has a name, it is added
        while (index < 13){
            if (index == 0) {
                returnDeck.add(new Card(index + 1, 'D', "Ace"));
            } else if (index < 10) {
                returnDeck.add(new Card(index + 1, 'D'));
            } else if (index == 10){
                returnDeck.add(new Card(index + 1, 'D', "Jack"));
            } else if (index == 11) {
                returnDeck.add(new Card(index + 1, 'D', "Queen"));
            } else {
                returnDeck.add(new Card(index + 1, 'D', "King"));
            }
            index++;
        }

        // adds H suited cards for the first 13 cards, if the card has a name, it is added
        while (index < 26){
            if (index == 13) {
                returnDeck.add(new Card(index - 12, 'H', "Ace"));
            } else if (index < 23) {
                returnDeck.add(new Card(index - 12, 'H'));
            } else if (index == 23){
                returnDeck.add(new Card(index - 12, 'H', "Jack"));
            } else if (index == 24) {
                returnDeck.add(new Card(index - 12, 'H', "Queen"));
            } else {
                returnDeck.add(new Card(index - 12, 'H', "King"));
            }
            index++;
        }

        // adds C suited cards for the first 13 cards, if the card has a name, it is added
        while (index < 39){
            if (index == 26) {
                returnDeck.add(new Card(index - 25, 'C', "Ace"));
            } else if (index < 36) {
                returnDeck.add(new Card(index - 25, 'C'));
            } else if (index == 36){
                returnDeck.add(new Card(index - 25, 'C', "Jack"));
            } else if (index == 37) {
                returnDeck.add(new Card(index - 25, 'C', "Queen"));
            } else {
                returnDeck.add(new Card(index - 25, 'C', "King"));
            }
            index++;
        }

        // adds D suited cards for the first 13 cards, if the card has a name, it is added
        while (index < 52){
            if (index == 39) {
                returnDeck.add(new Card(index - 38, 'S', "Ace"));
            } else if (index < 49) {
                returnDeck.add(new Card(index - 38, 'S'));
            } else if (index == 49){
                returnDeck.add(new Card(index - 38, 'S', "Jack"));
            } else if (index == 50) {
                returnDeck.add(new Card(index - 38, 'S', "Queen"));
            } else {
                returnDeck.add(new Card(index - 38, 'S', "King"));
            }
            index++;
        }

        return returnDeck;
    } // standardDeck() private method

    // constructs a standard deck of 52 cards
    public Deck() {
        this.cards = standardDeck();
        this.cardCount = 52;
        this.maxSize = 52;
    } // Deck (unparam)

    // constructs an empty deck of a specified size
    public Deck(int size) {
        this.cards = new ArrayList<>();
        this.cardCount = 0;
        this.maxSize = size;
    } // Deck (param)

    // getters for card count and max size, setter for max size
    public int getCardCount() { return this.cardCount; }
    public int getMaxSize() { return this.maxSize; }
    public void setMaxSize(int maxSize) { this.maxSize = maxSize; }

    // shuffles cards in deck
    public void shuffle() {
        int ranIndex;
        Card temp;

        // iterates through cards array, swapping random remaining card with card in current index
        for (int index = 0; index < this.cardCount; index++) {
            // chooses a random number of index in the range between the index to be replaced and the last card in the array
            ranIndex = (int)(Math.random()*(this.cardCount - index) + index);

            // swaps the card in the random index with the card in the current index
            temp = this.cards.get(index);
            this.cards.set(index, this.cards.get(ranIndex));
            this.cards.set(ranIndex, temp);
        }
    } //shuffle()

   // returns the last card in the deck
    public Card draw() {
        Card lastCard;

        lastCard = this.cards.get(cardCount - 1);// copies last card in the array
        this.cards.set(cardCount - 1, null); // sets actual index to null
        cardCount--; // reduces card count by 1
        return lastCard; // returns copied Card
    } // draw()

    // adds a card to the end of the deck
    public void add(Card newCard) {
        if (this.cardCount != this.maxSize) { // checks that deck is not full
            if (this.cardCount < this.cards.size()) { // checks if there are empty slots in the arraylist, meaning we have to use set for an already created index
                this.cards.set(cardCount, newCard); // adds new card to next open spot in array
            } else { // if there are no empty spots in the arraylist, add one to the end
                this.cards.add(newCard);
            }
            cardCount++;
        } else {
            System.out.println("This deck is already full.");
        }
    } // Add()

    // prints the deck in a formatted grid
    public void printDeck() {
        int index = 0;
        // prints first up to 13 cards on one line, spaced apart
        while (index < this.cardCount && index < 13) {
            System.out.printf("%12s", this.cards.get(index).toString());
            index++;
        }
        if (index < this.cardCount)
            System.out.print("\n"); // goes down a line

        // prints next up to 13 cards on the second line, spaced apart
        while (index < this.cardCount && index < 26) {
            System.out.printf("%12s", this.cards.get(index).toString());
            index++;
        }

        if (index < this.cardCount)
            System.out.print("\n"); // goes down a line

        // prints next up to 13 cards on the third line, spaced apart
        while (index < this.cardCount && index < 39) {
            System.out.printf("%12s", this.cards.get(index).toString());
            index++;
        }

        if (index < this.cardCount)
            System.out.print("\n"); // goes down a line

        // prints remaining cards on fourth line, spaced apart
        while (index < this.cardCount && index < 52) {
            System.out.printf("%12s", this.cards.get(index).toString());
            index++;
        }

        if (index < this.cardCount)
            System.out.print("\n"); // goes down a line

        while (index < this.cardCount && index < 65) {
            System.out.printf("%12s", this.cards.get(index).toString());
            index++;
        }

        if (index < this.cardCount)
            System.out.print("\n"); // goes down a line

        while (index < this.cardCount && index < 78) {
            System.out.printf("%12s", this.cards.get(index).toString());
            index++;
        }

        if (index < this.cardCount)
            System.out.print("\n"); // goes down a line

        while (index < this.cardCount) {
            System.out.printf("%12s", this.cards.get(index).toString());
            index++;
        }

        System.out.print("\n"); // goes down a line
    } // PrintDeck()
} // Deck class
