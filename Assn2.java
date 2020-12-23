// Author: Roman Smith
// Class/Assignment: CSE 205/Assignment 2
// Due Date: June 12, 2020
// Description: calls deck methods and displays the decks based on user input

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Assn2 {
    public static void main(String[] args) {
        int deckSelection;
        int menuSelection;

        // creating references for the two decks that will be displayed
        Deck mainDeck = null;
        Deck drawTo = null;

        System.out.println("Would you like to build a custom deck from a file or use a standard deck of 52 cards?");
        System.out.println("1 - Custom Deck");
        System.out.println("2 - Standard Deck");

        deckSelection = deckInputChoice(); // either 1 or 2

        if (deckSelection == 1) { // if the user wants to make a custom deck
            String fileName;
            File inputFile = null;
            Scanner fileScanner = null; // for scanning the file
            Scanner lineScanner = null; // for scanning within each line of the file
            Scanner in = new Scanner(System.in); // for scanning from system input


            System.out.println("Enter the name of the file to create the deck from:");
            fileName = in.nextLine();

            try {
                inputFile = new File(fileName);
                fileScanner = new Scanner(inputFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // FILE I/O for file types 1 and 5
            if (fileScanner.hasNextInt()) { // file is either type 1 or 5
                int deckSize = fileScanner.nextInt(); // reads in the initial integer
                mainDeck = new Deck(deckSize); // creates main deck with "max size" of deck size
                drawTo = new Deck(deckSize); // creates draw to deck with "max size" of deck size
                String trash = fileScanner.nextLine(); // throws out the rest of the first line

                while (fileScanner.hasNextLine()) { // while there are more lines of input
                    String line = fileScanner.nextLine(); // reads in one line at a time
                    lineScanner = new Scanner(line); // creates scanner for that line

                    char cardSuit = lineScanner.next().charAt(0); // gets the listed suit from the line
                    int cardVal = lineScanner.nextInt(); // gets the listed int value from the line

                    if (lineScanner.hasNext()) { // if there is anything left in the line, it is the name and we use the 3 parameter card constructor
                        String cardName = lineScanner.next(); // gets listed name
                        Card cardToAdd = new Card(cardVal, cardSuit, cardName); // creates new card to be added to deck
                        mainDeck.add(cardToAdd); // adds card to deck
                    } else { // if there is nothing else in the line, we use the 2 parameter card constructor
                        Card cardToAdd = new Card(cardVal, cardSuit); // creates new card to be added to deck
                        mainDeck.add(cardToAdd); // adds card to deck
                    }

                    lineScanner.close(); // closes line scanner
                }
            // FILE I/O for types 2 and 3
            } else { // file is either type 2 or 3
                // creates both decks, sets the initial max size to 0 since no max size is indicated, will be increased as more cards are added
                mainDeck = new Deck(0);
                drawTo = new Deck(0);

                // ArrayLists for holding the suits and values of cards since they are read in at separate times
                ArrayList<Character> suits = new ArrayList<>();
                ArrayList<Integer> values = new ArrayList<>();

                // for keeping track of the file type, since the number of times the values list is added depends on which file type
                int fileType = 2;

                // for keeping track of the number of values associated with each suit (for file type 3)
                ArrayList<Integer> valuesForSuit = new ArrayList<>();

                while (fileScanner.hasNextLine()) { // while there is still stuff in the file
                    String line = fileScanner.nextLine(); // reads in one line at a time
                    lineScanner = new Scanner(line); // creates scanner for that line

                    // if the line doesn't start with an integer, there is at least one suit to add
                    if (!lineScanner.hasNextInt()) {

                        while (lineScanner.hasNext()) { // while there is a next token in the line

                            if (lineScanner.hasNextInt()) { // if the next token is an int (like file type 3)
                                valuesForSuit.add(lineScanner.nextInt()); // records how many values the user wants for that suit
                                fileType = 3;
                            } else { // if the next token is not an int, it's a suit that we want to add
                                suits.add(lineScanner.next().charAt(0)); // suit char is added to suits array list
                            }
                        } // all suits in the suit line have been added

                        // if the line starts with integer, it is full of values to add
                    } else {

                        while (lineScanner.hasNextInt()) { // while there are still values in the line
                            values.add(lineScanner.nextInt()); //
                        } // all the values on the line have been entered
                    }
                }

                // using the suits and values array lists to fill the deck
                if (fileType == 2) { // we want the value array list to add cards for each suit

                    for (int sindex = 0; sindex < suits.size(); sindex++) { // iterates through each suit
                        char cardSuit = suits.get(sindex); // gets the suit of the card to be added

                        for (int vindex = 0; vindex < values.size(); vindex++) { // iterates through each value
                            int cardVal = values.get(vindex); // gets the value of the card to be added
                            Card cardToAdd = new Card(cardVal, cardSuit); // makes the card to be added
                            mainDeck.setMaxSize(mainDeck.getMaxSize() + 1); // increases max size by one
                            drawTo.setMaxSize(drawTo.getMaxSize() + 1); // increases max size by one
                            mainDeck.add(cardToAdd); // adds the card
                        }
                    }
                } else { // if the type is three, we only want the value array list to add cards once through
                    int vindexTrack = 0; // to track the position in the value index since we need to stop and start back where we left off

                    for (int sindex = 0; sindex < suits.size(); sindex++) { // iterates through each suit
                        char cardSuit = suits.get(sindex); // gets the suit of the card to be added

                        // iterates through the range of the values in values array list that correspond to the current suit
                        for (int vindex = vindexTrack; vindex < vindexTrack + valuesForSuit.get(sindex); vindex++) {
                            int cardVal = values.get(vindex); // gets the value of the card to be added
                            Card cardToAdd = new Card(cardVal, cardSuit); // makes the card to be added
                            mainDeck.setMaxSize(mainDeck.getMaxSize() + 1); // increases max size by one
                            drawTo.setMaxSize(drawTo.getMaxSize() + 1); // increases max size by one
                            mainDeck.add(cardToAdd); // adds the card
                        }

                        vindexTrack = vindexTrack + valuesForSuit.get(sindex); // increases the vindextrack to the starting point of the next suit
                    }
                }
            } // routine for file types 2 and 3

            fileScanner.close(); // close the file scanner

        } else { // if 2 is selected (deckInputChoice makes sure either 1 or 2 is selected), a standard deck and drawTo deck of the same size are created
            mainDeck = new Deck();
            drawTo = new Deck(52);
        }

        // loops the menu options and user actions until user enters -1 to quit
        do {
            // print current decks
            System.out.println("Deck Contains:");
            mainDeck.printDeck();
            System.out.println("Drawn so far:");
            drawTo.printDeck();

            // print menu
            System.out.println("1 - Draw");
            System.out.println("2 - Shuffle");
            System.out.println("3 - Put Back");
            System.out.println("-1 - Exit");

            menuSelection = actionChoice(); // either 1,2,3 or -1

            if (menuSelection == 1) {
                if (mainDeck.getCardCount() > 0) { // if there are cards in the deck
                    drawTo.add(mainDeck.draw()); // draws a card from main deck and adds it to drawTo deck
                } else {
                    System.out.println("There are no cards to draw.");
                }
            }

            if (menuSelection == 2) {
                mainDeck.shuffle(); // shuffles deck
            }

            if (menuSelection == 3) {
                if (drawTo.getCardCount() > 0) { // if there are cards in the drawTo deck
                    mainDeck.add(drawTo.draw()); // does the opposite of 1, draws a card from drawTo deck and adds it to main deck
                } else {
                    System.out.println("There are no cards to put back.");
                }
            }

        } while (menuSelection != -1);
    } // main

    // checks to make sure user int is valid for the deck input options, custom or standard
    public static int deckInputChoice() {
        int userInt;
        boolean isMenuInput = false;

        do { // loops until a menu choice is entered
            userInt = integer(); // integer() makes sure user input is an int
            if (userInt == 1 || userInt == 2) { // checks if int was on the menu
                isMenuInput = true;
            } else { // if int was not 1 or 2
                System.out.println("Invalid input. Please enter a number from the menu.");
            }
        } while (!isMenuInput);

        return userInt; // returns an integer that is a menu selection
    } // deckInputChoice()

    // checks to make sure user int is valid for the deck action menu options, then either prints an error message or returns the int
    public static int actionChoice() {
        int userInt;
        boolean isMenuInput = false;

        do { // loops until a menu choice is entered
            userInt = integer(); // integer() makes sure user input is an int
            if ((userInt >= 1 && userInt <= 3) || userInt == -1) { // checks if int was on the menu
                isMenuInput = true;
            } else { // if user entered an int, but not 1,2,3 or -1
                System.out.println("Invalid input. Please enter a number from the menu.");
            }
        } while (!isMenuInput);

        return userInt; // returns an integer that is a menu selection
    } // actionChoice()

    // takes a string and returns the integer if it has one or prompts the user to enter an integer until a valid int is entered
    public static int integer() {
        Scanner in = new Scanner(System.in);
        int toReturn = 0;
        boolean isInt = false;
        String trash;

        do { // loops until an integer is entered
            if (in.hasNextInt()) { // checks to see if the user entered an int
                isInt = true;
                toReturn = in.nextInt(); // returns the integer
            } else { // if user didn't enter an int
                System.out.println("Invalid input. Please enter a number from the menu.");
                trash = in.nextLine(); // gets rid of the input from the input stream
            }
        } while (!isInt);

        return toReturn; // returns an integer
    } // integer()

} // Assn 2 class
