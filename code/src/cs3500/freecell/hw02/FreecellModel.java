package cs3500.freecell.hw02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Represents a game of Freecell.
 */
public class FreecellModel implements FreecellOperations<Card> {
  private static final int deckSize = 52;
  private static final int numFoundation = 4;
  private static final int openCapacity = 1;
  private static final int foundationCapacity = 13;

  private List<Card> deck;
  // Piles have been changed to protected to allow access from subclasses
  protected Card[][] openPiles;
  protected Card[][] cascadePiles;
  protected Card[][] foundationPiles;
  private int numCascade;
  private int numOpen;

  private boolean isPlaying;

  /**
   * default constructor.
   */
  public FreecellModel() {
    Suit[] arrSuit = Suit.values();
    Value[] arrValue = Value.values();
    Card[] d = new Card[FreecellModel.deckSize];
    int count = 0;

    for (int i = 0; i < arrSuit.length; i++) {
      for (int j = 0; j < arrValue.length; j++) {
        d[count] = new Card(arrSuit[i], arrValue[j]);
        count++;
      }
    }
    this.deck = Arrays.asList(d);
    this.isPlaying = false;
  }

  /**
   * Testing constructor that produces deck and game based on seed.
   * @param seed int that determines the random object
   */
  public FreecellModel(int seed) {
    this();
    Random rand = new Random(seed);
    Collections.shuffle(this.deck, rand);
  }

  @Override
  public List<Card> getDeck() {
    List<Card> copy = new ArrayList<>(this.deck);
    return copy;
  }

  /**
   * Checks inputs in startGame method to determine if it is a valid game of Freecell.
   *
   * @param deck The deck to be used for play
   * @param numCascadePiles The number of cascade piles in the game
   * @param numOpenPiles The number of open piles in the game
   */
  private void checkGameSettings(List<Card> deck, int numCascadePiles, int numOpenPiles) {
    if (this.duplicatesPresent(deck)) {
      throw new IllegalArgumentException("Deck must not have duplicate cards");
    }
    if (deck.size() != FreecellModel.deckSize) {
      throw new IllegalArgumentException("Deck must have 52 cards");
    }
    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("Game must have at least 4 cascade piles");
    }
    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("Game must have at least 1 open pile");
    }
  }

  /**
   * Returns true if given deck has duplicates (at least 2 cards have same suit and value).
   *
   * @param deck The deck to be used for play
   * @return whether the deck to be used has any duplicates
   */
  private boolean duplicatesPresent(List<Card> deck) {
    for (int i = 0; i < deck.size(); i++) {
      for (int j = i + 1; j < deck.size(); j++) {
        if (deck.get(i).equals(deck.get(j))) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {

    this.checkGameSettings(deck, numCascadePiles, numOpenPiles);

    this.isPlaying = true;

    if (shuffle) {
      Collections.shuffle(deck);
    }

    this.foundationPiles = new Card[FreecellModel.numFoundation][FreecellModel.foundationCapacity];
    this.openPiles = new Card[numOpenPiles][FreecellModel.openCapacity];
    this.cascadePiles = new Card[numCascadePiles][FreecellModel.deckSize];

    this.numCascade = numCascadePiles;
    this.numOpen = numOpenPiles;

    // Deals cards out to cascade piles
    for (int i = 0; i < deck.size(); i++) {
      cascadePiles[i % numCascadePiles][i / numCascadePiles] = deck.get(i);
    }

  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber) {
    checkIsPlaying();
    checkSourceIndices(source, pileNumber, cardIndex);

    Card copy;

    switch (source) {
      case FOUNDATION:
        throw new IllegalArgumentException("Cannot move cards from foundation pile");

      case OPEN:
        copy = this.openPiles[pileNumber][cardIndex];
        // Remove source card from original array
        this.openPiles[pileNumber][0] = null;
        break;
      case CASCADE:
        if (this.cascadePiles[pileNumber][cardIndex + 1] != null) {
          throw new IllegalArgumentException("Can only move bottom card in a cascade pile");
        }
        copy = this.cascadePiles[pileNumber][cardIndex];
        this.removeFromTop(this.cascadePiles, pileNumber);
        break;
      default:
        throw new IllegalArgumentException("pile must be foundation, open, or cascade");
    }

    checkDestIndices(destination, destPileNumber, copy);

    switch (destination) {
      case FOUNDATION:
        addToEnd(this.foundationPiles, destPileNumber, copy);
        break;
      case OPEN:
        addToEnd(this.openPiles, destPileNumber, copy);
        break;
      case CASCADE:
        addToEnd(this.cascadePiles, destPileNumber, copy);
        break;
      default:
        throw new IllegalArgumentException("destination must be foundation, open, or cascade");
    }
  }

  /**
   * Checks indices of source card provided by player and throws
   * exception if they are out of bounds or if there is no card there.
   * Also used to check indices provided by player in getCard method.
   *
   * @param pile The piletype
   * @param pileNumber The index of the pile
   * @param cardIndex The index of the card
   *
   * @throws IllegalArgumentException if card at pile or index doesn't exist, or invalid piletype
   */
  // Changed access to protected to allow MultiMove model to use same logic in source card checks
  protected void checkSourceIndices(PileType pile, int pileNumber, int cardIndex) {
    switch (pile) {
      case FOUNDATION:
        if (pileNumber < 0 || pileNumber > (numFoundation - 1) || cardIndex < 0 ||
                cardIndex > (foundationCapacity - 1)
                || foundationPiles[pileNumber][cardIndex] == null) {
          throw new IllegalArgumentException("No source card " +
                  "exists at this foundation pile and index");
        }
        break;
      case OPEN:
        if (pileNumber < 0 || pileNumber > (numOpen - 1) || cardIndex < 0 ||
                cardIndex > (openCapacity - 1)
                || openPiles[pileNumber][cardIndex] == null) {
          throw new IllegalArgumentException("No source card exists at this open pile and index");
        }
        break;
      case CASCADE:
        if (pileNumber < 0 || pileNumber > (numCascade - 1) || cardIndex < 0 ||
                cardIndex > (deckSize - 1)
                || cascadePiles[pileNumber][cardIndex] == null) {
          throw new IllegalArgumentException("No source card " +
                  "exists at this cascade pile and index");
        }
        break;
      default:
        throw new IllegalArgumentException("pile must be foundation, open, or cascade");
    }
  }

  /**
   * Checks that destination pile exists and if it does, enforces
   * rules of freecell based on which type of pile it is.
   *
   * @param pile The piletype of the destination
   * @param pileNumber The index of the destination pile
   * @param sourceCard The card that the player wishes to move to destination pile
   *
   * @throws IllegalArgumentException if card at pile or index doesn't exist, or invalid piletype
   */
  // Changed to protected to allow MultiMoveModel to use same logic for destination card checks
  // Turned previous logic into new protected method lastCardInPile()
  protected void checkDestIndices(PileType pile, int pileNumber, Card sourceCard) {
    Value[] arrValue = Value.values();

    switch (pile) {
      case FOUNDATION:

        // check indices
        if (pileNumber < 0 || pileNumber > (numFoundation - 1)) {
          throw new IllegalArgumentException("Given destination pile does not exist");
        }
        if (this.foundationPiles[pileNumber][0] == null && sourceCard.getValue() != Value.Ace) {
          throw new IllegalArgumentException("First card in a foundation pile must be an Ace");
        }

        // Check if foundations array is full
        if (this.foundationFull(pileNumber)) {
          throw new IllegalArgumentException("Foundation pile is full already.");
        }

        // If array is empty, and source card is ace, we can skip the next 2 checks, move is valid
        if (this.foundationPiles[pileNumber][0] == null && sourceCard.getValue() == Value.Ace) {
          break;
        }

        // Finds last card in destination pile otherwise
        Card lastCardF = lastCardInPile(PileType.FOUNDATION, pileNumber);

        // Check that a move to foundation pile maintains suit rule
        if (lastCardF.getSuit() != sourceCard.getSuit()) {
          throw new IllegalArgumentException("Foundation pile cards must have same suit");
        }

        // Check that source card is one higher than card on top of foundation pile
        if (lastCardF.getValue().ordinal() + 1 != sourceCard.getValue().ordinal()) {
          throw new IllegalArgumentException("Source card must have value 1 greater than" +
                  " last card in current foundation pile");
        }
        break;

      case OPEN:
        // Check indices
        if (pileNumber < 0 || pileNumber > (numOpen - 1)) {
          throw new IllegalArgumentException("Given destination pile does not exist");
        }

        // Check if space is occupied, if not then move is valid
        if (openPiles[pileNumber][0] != null) {
          throw new IllegalArgumentException("Open pile is full already");
        }
        break;

      case CASCADE:

        // Check indices
        if (pileNumber < 0 || pileNumber > (numCascade - 1)) {
          throw new IllegalArgumentException("Given destination pile does not exist");
        }

        // If array is empty, move is valid
        if (this.cascadePiles[pileNumber][0] == null) {
          break;
        }

        // Don't have to check if cascade pile is full, as its max size is the full deck

        // Find last card in destination pile
        Card lastCardC = lastCardInPile(PileType.CASCADE, pileNumber);

        // Check that source card's rank is 1 less than last card in destination pile
        if (sourceCard.getValue().ordinal() + 1 != lastCardC.getValue().ordinal()) {
          throw new IllegalArgumentException("Source card must be 1 less than last card " +
                  "in destination cascade pile");
        }

        // Check that source card and last card in destination pile are different colors
        if (sameColor(sourceCard, lastCardC)) {
          throw new IllegalArgumentException("Source card must be different color" +
                  " than last card in destination cascade pile");
        }
        break;

      default:
        throw new IllegalArgumentException("Destination pile must be foundation, open, or cascade");
    }
  }

  /**
   * Helper for @code{move()}, checks whether the game has started.
   *
   * @throws IllegalStateException if game has not started
   */
  // Changed to protected to allow MultiMoveModel to have same check
  protected void checkIsPlaying() {
    if (!isPlaying) {
      throw new IllegalStateException("Game has not started yet");
    }
  }


  @Override
  public boolean isGameOver() {
    if (!isPlaying) {
      return false;
    }
    else {
      for (int i = 0; i < numFoundation; i++) {
        // If any foundation pile is not full, game is not over
        if (!foundationFull(i)) {
          return false;
        }
      }
      return true;
    }
  }

  @Override
  public Card getCard(PileType pile, int pileNumber, int cardIndex) {

    if (!this.isPlaying) {
      throw new IllegalStateException("Game has not started yet.");
    }

    this.checkCardIndices(pile, pileNumber, cardIndex);

    switch (pile) {
      case FOUNDATION:
        checkSourceIndices(PileType.FOUNDATION, pileNumber, cardIndex);
        return this.foundationPiles[pileNumber][cardIndex];

      case OPEN:
        checkSourceIndices(PileType.OPEN, pileNumber, cardIndex);
        return this.openPiles[pileNumber][cardIndex];

      case CASCADE:
        checkSourceIndices(PileType.CASCADE, pileNumber, cardIndex);
        return this.cascadePiles[pileNumber][cardIndex];

      default:
        throw new IllegalArgumentException("pile must be foundation, open, or cascade");
    }
  }


  /**
   * Checks to see if card exists at given indices.
   * @param pile The piletype of the card to be checked
   * @param pileNumber The pile number of the card to be checked
   * @param cardIndex The card index of the card to be checked
   */
  private void checkCardIndices(PileType pile, int pileNumber, int cardIndex) {
    try {
      switch (pile) {
        case FOUNDATION:
          if (pileNumber < 0 || pileNumber >= numFoundation
                  || cardIndex < 0 || cardIndex >= foundationCapacity) {
            throw new IllegalArgumentException("Card does not exist in this foundation pile");
          }
          break;
        case OPEN:
          if (pileNumber < 0 || pileNumber >= numOpen
                  || cardIndex < 0 || cardIndex >= openCapacity) {
            throw new IllegalArgumentException("Card does not exist in this open pile");
          }
          break;
        case CASCADE:
          if (pileNumber < 0 || pileNumber >= numCascade
                  || cardIndex < 0 || cardIndex >= deckSize) {
            throw new IllegalArgumentException("Card does not exist in this cascade pile");
          }
          break;
        default:
          throw new IllegalArgumentException("Piletype must be foundation, cascade, or open");
      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Card does not exist at specified indices");
    }
  }

  @Override
  public String getGameState() {
    String foundationString = "";
    String openString = "";
    String cascadeString = "";
    if (!isPlaying) {
      return "";
    }
    else {

      for (int i = 0; i < FreecellModel.numFoundation; i++) {
        if (this.foundationPiles[i][0] == null) {
          foundationString = foundationString + "F" + Integer.toString(i + 1) + ":  ";
        }
        else {
          foundationString = foundationString + "F" + Integer.toString(i + 1) + ": ";
        }
        for (Card c: this.foundationPiles[i]) {
          if (c == null) {
            break;
          }
          foundationString = foundationString + c.toString() + ", ";
        }
        //Remove comma and open space and add newline
        foundationString = foundationString.substring(0, foundationString.length() - 2) + "\n";
      }

      for (int j = 0; j < this.numOpen; j++) {
        if (this.openPiles[j][0] == null) {
          openString = openString + "O" + Integer.toString(j + 1) + ":  ";
        }
        else {
          openString = openString + "O" + Integer.toString(j + 1) + ": ";
        }
        for (Card c: this.openPiles[j]) {
          if (c == null) {
            break;
          }
          openString = openString + c.toString() + ", ";
        }
        //Remove comma and extra space and add newline
        openString = openString.substring(0, openString.length() - 2) + "\n";
      }

      for (int k = 0; k < this.numCascade; k++) {
        if (this.cascadePiles[k][0] == null) {
          cascadeString = cascadeString + "C" + Integer.toString(k + 1) + ":  ";
        }
        else {
          cascadeString = cascadeString + "C" + Integer.toString(k + 1) + ": ";
        }
        for (Card c: this.cascadePiles[k]) {
          if (c == null) {
            break;
          }
          cascadeString = cascadeString + c.toString() + ", ";
        }
        // Last row in cascade string should not add new line
        if (k == numCascade - 1) {
          cascadeString = cascadeString.substring(0, cascadeString.length() - 2);
        }
        //Remove comma and extra space and add newline
        else {
          cascadeString = cascadeString.substring(0, cascadeString.length() - 2) + "\n";
        }
      }
    }
    return foundationString + openString + cascadeString;
  }


  /**
   * Finds the top card on the given pile and removes it.
   *
   * @param toRemove The pile from which we are removing the top card
   * @param pileNumber The index of the pile
   */
  // Changed to protected, as MultiMoveModel needs to remove cards from top of a pile in a for loop
  // if trying to move multiple cards
  protected void removeFromTop(Card[][] toRemove, int pileNumber) {
    for (int i = 0; i < deckSize; i++) {
      if (toRemove[pileNumber][i] == null) {
        toRemove[pileNumber][i - 1] = null;
        break;
      }
    }
  }

  /**
   * Adds the given card to the end of the given pile.
   *
   * @param toAdd The pile from which we are adding the card
   * @param pileNumber The index of the pile
   * @param c The card to be added
   */
  // Changed to protected, as MultiMoveModel needs to add cards to end of a pile in a for loop
  // if trying to move multiple cards
  protected void addToEnd(Card[][] toAdd, int pileNumber, Card c) {
    for (int i = 0; i < deckSize; i++) {
      if (toAdd[pileNumber][i] == null) {
        toAdd[pileNumber][i] = c;
        break;
      }
    }
  }


  /**
   * Determines whether a foundation pile is full.
   *
   * @param pileNumber The pile index that we are checking
   * @return Whether the foundation pile at the given number is full
   */
  private boolean foundationFull(int pileNumber) {
    for (int i = 0; i < foundationCapacity; i++) {
      if (this.foundationPiles[pileNumber][i] == null) {
        return false;
      }
    }
    // If no spot in array is null, then it is full
    return true;
  }

  /**
   * Determines whether the two given cards are the same color.
   *
   * @param one card 1
   * @param two card 2
   * @return whether the two cards are the same color
   */
  private boolean sameColor(Card one, Card two) {
    switch (one.getSuit()) {
      // merge red case
      case Heart:
      case Diamond:
        return (two.getSuit() == Suit.Heart || two.getSuit() == Suit.Diamond);

      // merge black case
      case Spade:
      case Club:
        return (two.getSuit() == Suit.Club || two.getSuit() == Suit.Spade) ;

      default:
        throw new IllegalArgumentException("Card's suit must be Heart, diamond, spade, or club");
    }
  }

  /**
   * Returns whether 2 cards form a valid build, values differ by 1 and different colors.
   *
   * @param bottom card on bottom, or to be moved below top card
   * @param top card on top
   * @return whether the two given cards form a valid build
   */
  // New method designed that combined logic from previous submission
  // Having a new method for determining whether two cards form a valid build is useful
  // for the MultiMoveModel for determining whether a multimove is valid
  protected boolean validBuild(Card bottom, Card top) {
    return !sameColor(bottom, top) &&
            (bottom.getValue().ordinal() + 1 == top.getValue().ordinal());
  }

  /**
   * Counts the number of elements in given array, not including nulls.
   * @param arr the given array
   * @return the number of non-null elements in given array
   */
  // New method that is useful for finding last card in pile and for MultiMoveModel
  // for determining if a pile is empty (free) or not
  protected int numNotNull(Card[] arr) {
    int count = 0;
    for (Card c : arr) {
      if (c != null) {
        count++;
      }
    }
    return count;
  }

  /**
   * Returns last card in given pile.
   *
   * @param pileType The given piletype
   * @param pileNumber The given pile number
   * @return The last card in the pile
   *
   * @throws IllegalArgumentException If pile is empty, or invalid pile type
   */
  // New method reusing previous model's logic in move() method
  // Useful for MultiMoveModel in determining if a move provides valid build
  protected Card lastCardInPile(PileType pileType, int pileNumber) {
    Card copy;
    switch (pileType) {
      case FOUNDATION:
        if (this.foundationPiles[pileNumber][0] == null) {
          throw new IllegalArgumentException("Given foundation pile is empty");
        }
        copy = foundationPiles[pileNumber][numNotNull(foundationPiles[pileNumber]) - 1];
        break;

      case CASCADE:
        if (this.cascadePiles[pileNumber][0] == null) {
          throw new IllegalArgumentException("Given cascade pile is empty");
        }
        copy = cascadePiles[pileNumber][numNotNull(cascadePiles[pileNumber]) - 1];
        break;

      case OPEN:
        if (this.openPiles[pileNumber][0] == null) {
          throw new IllegalArgumentException("Given open pile is empty");
        }
        copy = foundationPiles[pileNumber][0];
        break;

      default:
        throw new IllegalArgumentException("Piletype must be Foundation, Cascade, or Open");
    }
    return copy;
  }




}
