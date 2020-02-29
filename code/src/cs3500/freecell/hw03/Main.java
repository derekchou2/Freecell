package cs3500.freecell.hw03;

import java.io.InputStreamReader;
// import cs3500.freecell.hw02.FreecellModel;
import cs3500.freecell.hw04.MultiMoveModel;


/**
 * Run a Freecell game interactively on the console.
 */
public class Main {
  /**
   * Run a Freecell game interactively on the console.
   */
  public static void main(String[] args) {
    MultiMoveModel m = new MultiMoveModel(18);
    new FreecellController(new InputStreamReader(System.in),
            System.out).playGame(m.getDeck(), m, 8, 4, false);
  }
}