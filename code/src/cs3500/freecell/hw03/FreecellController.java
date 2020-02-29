package cs3500.freecell.hw03;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;

import cs3500.freecell.hw02.Card;
import cs3500.freecell.hw02.FreecellOperations;
import cs3500.freecell.hw02.PileType;

/**
 * Represents a controller for the Freecell game.
 */
public class FreecellController implements IFreecellController<Card> {
  private final Appendable out;
  private final Scanner scan;

  private FreecellOperations<Card> model;
  private boolean continueGame = true;

  /**
   * Initializes scanner for parsing inputs and outputs.
   *
   * @param rd - Readable object that will be used to read input
   * @param ap - Appendable object to append output to
   * @throws IllegalArgumentException if rd or ap are null
   */
  public FreecellController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }

    this.out = ap;
    scan = new Scanner(rd);
  }

  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model,
                       int numCascades, int numOpens, boolean shuffle) {

    if (model == null || deck == null) {
      throw new IllegalArgumentException("Could not start game.");
    }

    // Start game and output initial game state
    // Check if initializing on already started game
    else {
      try {
        model.startGame(deck, numCascades, numOpens, shuffle);
        this.model = model;
        this.appendGameState();
      } catch (IllegalArgumentException e) {
        this.transmitOutputNoReturn("Could not start game.");
        return;
      }
    }


    List<String> inputList = new ArrayList<>();
    while (this.continueGame && scan.hasNext()) {

      // Looking for pile input when input list has 0 or 2 strings
      while ((inputList.size() == 0 || inputList.size() == 2)
              && this.continueGame && scan.hasNext()) {
        String s = scan.next();
        if (s.equalsIgnoreCase("q")) {
          this.endGameEarly();
          return;
        }

        if (seekPileInput(s)) {
          inputList.add(s);
        }
      }

      // Looking for index input when input list has 1 string already
      while (inputList.size() == 1 && this.continueGame && scan.hasNext()) {
        String s = scan.next();
        if (s.equalsIgnoreCase("q")) {
          this.endGameEarly();
          return;
        }

        if (seekIndexInput(s)) {
          inputList.add(s);
        }
      }

      // if inputlist size is 3, Try to move
      try {
        if (inputList.size() == 3) {
          makeMove(inputList.get(0), inputList.get(1), inputList.get(2));
          inputList.clear();
        }
      } catch (IllegalArgumentException e) {
        this.transmitOutput("invalid move. Try again. " + e.getMessage());
        inputList.clear();
      }
    }

    //  run out of inputs
    if (!this.model.isGameOver()) {
      throw new IllegalStateException("Unexpectedly ran out of inputs");
    }

  }

  /**
   * Attempts to transmit given message to player.
   *
   * @param out message to be transmitted to player
   * @throws IllegalStateException if transmit cannot be completed
   */
  private void transmitOutput(String out) {
    try {
      this.out.append(out + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Append failed.");
    }
  }

  /**
   * Attempts to transmit given message to player, without new line.
   *
   * @param out message to be transmitted to player
   * @throws IllegalStateException if transmit cannot be completed
   */
  private void transmitOutputNoReturn(String out) {
    try {
      this.out.append(out);
    } catch (IOException e) {
      throw new IllegalStateException("Append failed.");
    }
  }


  /**
   * Transmits current game state to appendable object exactly as model provides it.
   *
   * @throws IllegalStateException if game state cannot be transmitted
   */
  private void appendGameState() {
    transmitOutput(this.model.getGameState());
  }


  /**
   * Determines whether given string is an acceptable pile input for freecell.
   *
   * @param s the given string
   * @return whether s can be successfully parsed as a pile input
   */
  private boolean seekPileInput(String s) {
    // If valid pile input, all characters except first should be able to be
    // parsed as Integer
    try {
      Integer i = Integer.parseInt(s.substring(1, s.length()));
    } catch (NumberFormatException e) {
      this.transmitOutput("Not a valid pile input, try again \n");
      return false;
    }

    // Now check first char is good
    if (!(s.charAt(0) == 'O' || s.charAt(0) == 'F' || s.charAt(0) == 'C')) {
      this.transmitOutput("Not a valid pile input, try again \n");
      return false;
    }
    return true;
  }

  /**
   * Determines whether given string is an acceptable index for freecell.
   *
   * @param s the given string
   * @return whether s can be successfully parsed as an index
   */
  private boolean seekIndexInput(String s) {
    try {
      Integer i = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      this.transmitOutput("Not a valid index, try again");
      return false;
    }

    // If no number format exception, then string can be parsed as Integer, is valid index
    return true;
  }


  /**
   * Ends the Freecell game early and informs the user.
   */
  private void endGameEarly() {
    this.continueGame = false;
    this.transmitOutput("Game quit prematurely.");
  }


  /**
   * parses given strings to obtain source pile, destination pile, and index necessary to make a
   * move in the Freecell Model.
   *
   * @param sourceStr the input string representing the source pile
   * @param indexStr  the input string representing the index of the card to be moved
   * @param destStr   the input string representing the destination pile
   */
  private void makeMove(String sourceStr, String indexStr, String destStr) {
    PileType source;
    PileType dest;
    int index;

    switch (sourceStr.charAt(0)) {
      case 'F':
        source = PileType.FOUNDATION;
        break;
      case 'O':
        source = PileType.OPEN;
        break;
      case 'C':
        source = PileType.CASCADE;
        break;
      default:
        throw new IllegalStateException("First character of sourceStr must be: O, F, or C");
    }

    switch (destStr.charAt(0)) {
      case 'F':
        dest = PileType.FOUNDATION;
        break;
      case 'O':
        dest = PileType.OPEN;
        break;
      case 'C':
        dest = PileType.CASCADE;
        break;
      default:
        throw new IllegalStateException("First character of destStr must be: O, F, or C");
    }

    this.model.move(source, adjustIndex(sourceStr.substring(1)), adjustIndex(indexStr),
            dest, adjustIndex(destStr.substring(1)));
    this.checkWin();

    // Prevents extra game state append if game is won
    if (this.continueGame) {
      this.appendGameState();
    }
  }


  /**
   * Parses the given string as an Integer and offsets it by 1 to move from index 0 to more user
   * friendly index 1.
   *
   * @param s the string to be parsed
   * @return an int representing an index or card pile
   */
  private int adjustIndex(String s) {
    return Integer.parseInt(s) - 1;
  }


  /**
   * Checks the Freecell Model to see if user has beaten the game. If game is completed inform
   * player.
   */
  private void checkWin() {
    if (this.model.isGameOver()) {
      this.continueGame = false;
      appendGameState();
      transmitOutput("Game over.");
      return;
    }
  }


}
