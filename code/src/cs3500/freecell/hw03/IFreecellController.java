package cs3500.freecell.hw03;

import cs3500.freecell.hw02.FreecellOperations;
import java.util.List;

/**
 * This is the interface of the Freecell controller, parameterized over the
 * card type.
 */
public interface IFreecellController<K> {


  /**
   * Plays a game of Freecell with the given settings.
   *
   * @param deck        The deck to be used for starting the game
   * @param model       The model for the type of Freecell to be played
   * @param numCascades Number of cascade piles in the game
   * @param numOpens    Number of open piles in the game
   * @param shuffle     Whether the deck is to be shuffled before being dealt
   * @throws IllegalStateException if the controller has not been initialized properly to receive
   *                               input and transmit output
   * @throws IllegalArgumentException if player attempts invalid move
   */
  void playGame(List<K> deck, FreecellOperations<K> model, int numCascades,
                int numOpens, boolean shuffle);


}
