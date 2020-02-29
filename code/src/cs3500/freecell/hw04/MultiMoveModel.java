package cs3500.freecell.hw04;

import java.util.Arrays;
import java.util.List;

import cs3500.freecell.hw02.Card;
import cs3500.freecell.hw02.FreecellModel;
import cs3500.freecell.hw02.FreecellOperations;
import cs3500.freecell.hw02.PileType;

/**
 * Represents a game of Freecell in which the player can move multiple cards at once.
 */
public class MultiMoveModel extends FreecellModel implements FreecellOperations<Card> {

  /**
   * default constructor.
   */
  public MultiMoveModel() {
    super();
  }

  public MultiMoveModel(int seed) {
    super(seed);
  }

  @Override
  public List<Card> getDeck() {
    return super.getDeck();
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {
    super.startGame(deck, numCascadePiles, numOpenPiles, shuffle);
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber) {

    switch (source) {
      case FOUNDATION:
      case OPEN:
        super.move(source, pileNumber, cardIndex, destination, destPileNumber);
        break;


      case CASCADE:
        super.checkIsPlaying();
        super.checkSourceIndices(source, pileNumber, cardIndex);

        // If only trying to move 1 card, use move from FreecellModel
        if (this.cascadePiles[pileNumber][cardIndex + 1] == null) {
          super.move(source, pileNumber, cardIndex, destination, destPileNumber);
          break;
        }

        Card[] partCopy = Arrays.copyOfRange(this.cascadePiles[pileNumber],
                cardIndex, this.cascadePiles[pileNumber].length);

        // Check source cards form valid build
        for (int i = 1; i < partCopy.length; i++) {
          if (partCopy[i] == null) {
            break;
          }
          if (!(validBuild(partCopy[i], partCopy[i - 1]))) {
            throw new IllegalArgumentException("Source cards do not form valid build");
          }
        }

        // Check have enough free cells, open cascades
        if (numNotNull(partCopy) > ((numFreePile(PileType.OPEN) + 1)
                * Math.pow(2, numFreePile(PileType.CASCADE)))) {
          throw new IllegalArgumentException("Not enough free open/cascade piles to make move");
        }

        switch (destination) {
          case FOUNDATION:
          case OPEN:
            throw new IllegalArgumentException("Cannot move more than 1 card at " +
                    "a time to open or foundation pile");
          case CASCADE:
            if (!(validBuild(partCopy[0], lastCardInPile(PileType.CASCADE, destPileNumber)))) {
              throw new IllegalArgumentException("Top source card does not form valid build " +
                      "with last card in destination pile");
            }

            // Remove cards from source pile
            for (int i = 0; i < numNotNull(partCopy); i++) {
              removeFromTop(this.cascadePiles, pileNumber);
            }

            // Add to destination pile
            for (int i = 0; i < numNotNull(partCopy); i++) {
              addToEnd(this.cascadePiles, destPileNumber, partCopy[i]);
            }
            break;

          default:
            throw new IllegalArgumentException("dest pile must be open, cascade, or foundation");
        }
        break;

      default:
        throw new IllegalArgumentException("source pile must be open, cascade, or foundation");


    }

  }

  @Override
  public boolean isGameOver() {
    return super.isGameOver();
  }

  @Override
  public Card getCard(PileType pile, int pileNumber, int cardIndex) {
    return super.getCard(pile, pileNumber, cardIndex);
  }

  @Override
  public String getGameState() {
    return super.getGameState();
  }


  /**
   * For counting the number of free piles of the given type in a game.
   *
   * @return the number of free piles currently of the given type
   */
  private int numFreePile(PileType pileType) {
    int count = 0;

    switch (pileType) {
      case OPEN:
        for (Card[] pile : this.openPiles) {
          if (numNotNull(pile) == 0) {
            count += 1;
          }
        }
        break;

      case CASCADE:
        for (Card[] pile : this.cascadePiles) {
          if (numNotNull(pile) == 0) {
            count += 1;
          }
        }
        break;

      case FOUNDATION:
        for (Card[] pile : this.foundationPiles) {
          if (numNotNull(pile) == 0) {
            count += 1;
          }
        }
        break;

      default:
        throw new IllegalArgumentException("Piletype must be open, cascade, or foundation");
    }
    return count;
  }





}
