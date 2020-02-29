package cs3500.freecell.hw04;


import cs3500.freecell.hw02.Card;
import cs3500.freecell.hw02.FreecellModel;
import cs3500.freecell.hw02.FreecellOperations;


/**
 * A factory class for creating FreecellModels and MultiMoveModels.
 */
public class FreecellModelCreator {

  /**
   * Represents different gametypes of Freecell.
   */
  public enum GameType {
    SINGLEMOVE("Single Move"), MULTIMOVE("Multi Move");

    private final String game;

    GameType(String game) {
      if (game == null) {
        throw new IllegalArgumentException("gametype cannot be null");
      }
      this.game = game;
    }

    @Override
    public String toString() {
      return this.game;
    }
  }

  /**
   * Returns a freecell game of the given type.
   * @param type The type of freecell game
   * @return a new freecell game
   * @throws IllegalArgumentException if gametype is not singlemove or multimove
   */
  public static FreecellOperations<Card> create(GameType type) {
    switch (type) {
      case SINGLEMOVE:
        return new FreecellModel();
      case MULTIMOVE:
        return new MultiMoveModel();
      default:
        throw new IllegalArgumentException("Gametype must be either singlemove or multimove");
    }
  }
}
