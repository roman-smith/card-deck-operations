// Author: Roman Smith
// Class/Assignment: CSE 205/Assignment 2
// Due Date: June 12, 2020
// Description: class for a playing card, holds variables for value, suit, and whether or not the card is face up

public class Card {
    private int value;
    private char suit;
    private String name;
    private boolean faceUp;

    // no unparameterized constructor because there can't be a card without a value and suit
    // parameterized constructor
    public Card (int value, char suit) {
        this.value = value;
        this.suit = suit;
        this.name = null;
    } // constructor w/o name

    public Card(int value, char suit, String name) {
        this.value = value;
        this.suit = suit;
        this.name = name;
    } // constructor w/ name

    // getters for value and suit
    public int getValue() { return this.value; }
    public char getSuit() { return this.suit; }
    public String getName() { return this.name; }

    //getter and setter for faceUp boolean
    public boolean getFaceUp() { return this.faceUp; }
    public void setFaceUp(boolean faceUp) { this.faceUp = faceUp; }

    // toString() override
    @Override
    public String toString() {
        if (this.name == null) {
            return String.format("[%d:%C]", this.value, this.suit);
        } else {
            return String.format("[%s:%C]", this.name, this.suit);
        }
    }
} // Card class
