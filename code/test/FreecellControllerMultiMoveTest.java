import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;


import cs3500.freecell.hw02.FreecellModel;
import cs3500.freecell.hw03.FreecellController;
import cs3500.freecell.hw03.IFreecellController;
import cs3500.freecell.hw04.MultiMoveModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Test cases for the FreecellController Class playing a game that allows Multi card moves.
 */
public class FreecellControllerMultiMoveTest {
  private MultiMoveModel m1 = new MultiMoveModel();
  private MultiMoveModel m2 = new MultiMoveModel(17);
  private MultiMoveModel m3 = new MultiMoveModel(18);



  void testRun(FreecellModel model, Interaction... interactions) throws IOException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    IFreecellController controller = new FreecellController(input, actualOutput);
    controller.playGame(m1.getDeck(), m1, 52, 4, false);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    StringReader input = new StringReader("C1 q k O1");
    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m1.getDeck(), null, 8, 4, false);
  }

  @Test
  public void testSingleMove() throws IOException {
    assertNotEquals(null, m1);
    testRun(m1, Interaction.inputs("C1 1 O1 "),
            Interaction.prints("F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: A♥\n" +
                    "C2: 2♥\n" +
                    "C3: 3♥\n" +
                    "C4: 4♥\n" +
                    "C5: 5♥\n" +
                    "C6: 6♥\n" +
                    "C7: 7♥\n" +
                    "C8: 8♥\n" +
                    "C9: 9♥\n" +
                    "C10: 10♥\n" +
                    "C11: J♥\n" +
                    "C12: Q♥\n" +
                    "C13: K♥\n" +
                    "C14: A♦\n" +
                    "C15: 2♦\n" +
                    "C16: 3♦\n" +
                    "C17: 4♦\n" +
                    "C18: 5♦\n" +
                    "C19: 6♦\n" +
                    "C20: 7♦\n" +
                    "C21: 8♦\n" +
                    "C22: 9♦\n" +
                    "C23: 10♦\n" +
                    "C24: J♦\n" +
                    "C25: Q♦\n" +
                    "C26: K♦\n" +
                    "C27: A♣\n" +
                    "C28: 2♣\n" +
                    "C29: 3♣\n" +
                    "C30: 4♣\n" +
                    "C31: 5♣\n" +
                    "C32: 6♣\n" +
                    "C33: 7♣\n" +
                    "C34: 8♣\n" +
                    "C35: 9♣\n" +
                    "C36: 10♣\n" +
                    "C37: J♣\n" +
                    "C38: Q♣\n" +
                    "C39: K♣\n" +
                    "C40: A♠\n" +
                    "C41: 2♠\n" +
                    "C42: 3♠\n" +
                    "C43: 4♠\n" +
                    "C44: 5♠\n" +
                    "C45: 6♠\n" +
                    "C46: 7♠\n" +
                    "C47: 8♠\n" +
                    "C48: 9♠\n" +
                    "C49: 10♠\n" +
                    "C50: J♠\n" +
                    "C51: Q♠\n" +
                    "C52: K♠\n" +
                    "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1: A♥\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1:\n" +
                    "C2: 2♥\n" +
                    "C3: 3♥\n" +
                    "C4: 4♥\n" +
                    "C5: 5♥\n" +
                    "C6: 6♥\n" +
                    "C7: 7♥\n" +
                    "C8: 8♥\n" +
                    "C9: 9♥\n" +
                    "C10: 10♥\n" +
                    "C11: J♥\n" +
                    "C12: Q♥\n" +
                    "C13: K♥\n" +
                    "C14: A♦\n" +
                    "C15: 2♦\n" +
                    "C16: 3♦\n" +
                    "C17: 4♦\n" +
                    "C18: 5♦\n" +
                    "C19: 6♦\n" +
                    "C20: 7♦\n" +
                    "C21: 8♦\n" +
                    "C22: 9♦\n" +
                    "C23: 10♦\n" +
                    "C24: J♦\n" +
                    "C25: Q♦\n" +
                    "C26: K♦\n" +
                    "C27: A♣\n" +
                    "C28: 2♣\n" +
                    "C29: 3♣\n" +
                    "C30: 4♣\n" +
                    "C31: 5♣\n" +
                    "C32: 6♣\n" +
                    "C33: 7♣\n" +
                    "C34: 8♣\n" +
                    "C35: 9♣\n" +
                    "C36: 10♣\n" +
                    "C37: J♣\n" +
                    "C38: Q♣\n" +
                    "C39: K♣\n" +
                    "C40: A♠\n" +
                    "C41: 2♠\n" +
                    "C42: 3♠\n" +
                    "C43: 4♠\n" +
                    "C44: 5♠\n" +
                    "C45: 6♠\n" +
                    "C46: 7♠\n" +
                    "C47: 8♠\n" +
                    "C48: 9♠\n" +
                    "C49: 10♠\n" +
                    "C50: J♠\n" +
                    "C51: Q♠\n" +
                    "C52: K♠"),
            Interaction.inputs("q"),
            Interaction.prints("Game quit prematurely."));
  }

  @Test
  public void testBadInputSource() throws IOException {
    assertNotEquals(null, m1);
    testRun(m1, Interaction.inputs("CC C1 1 O1 q"),
            Interaction.prints("F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: A♥\n" +
                    "C2: 2♥\n" +
                    "C3: 3♥\n" +
                    "C4: 4♥\n" +
                    "C5: 5♥\n" +
                    "C6: 6♥\n" +
                    "C7: 7♥\n" +
                    "C8: 8♥\n" +
                    "C9: 9♥\n" +
                    "C10: 10♥\n" +
                    "C11: J♥\n" +
                    "C12: Q♥\n" +
                    "C13: K♥\n" +
                    "C14: A♦\n" +
                    "C15: 2♦\n" +
                    "C16: 3♦\n" +
                    "C17: 4♦\n" +
                    "C18: 5♦\n" +
                    "C19: 6♦\n" +
                    "C20: 7♦\n" +
                    "C21: 8♦\n" +
                    "C22: 9♦\n" +
                    "C23: 10♦\n" +
                    "C24: J♦\n" +
                    "C25: Q♦\n" +
                    "C26: K♦\n" +
                    "C27: A♣\n" +
                    "C28: 2♣\n" +
                    "C29: 3♣\n" +
                    "C30: 4♣\n" +
                    "C31: 5♣\n" +
                    "C32: 6♣\n" +
                    "C33: 7♣\n" +
                    "C34: 8♣\n" +
                    "C35: 9♣\n" +
                    "C36: 10♣\n" +
                    "C37: J♣\n" +
                    "C38: Q♣\n" +
                    "C39: K♣\n" +
                    "C40: A♠\n" +
                    "C41: 2♠\n" +
                    "C42: 3♠\n" +
                    "C43: 4♠\n" +
                    "C44: 5♠\n" +
                    "C45: 6♠\n" +
                    "C46: 7♠\n" +
                    "C47: 8♠\n" +
                    "C48: 9♠\n" +
                    "C49: 10♠\n" +
                    "C50: J♠\n" +
                    "C51: Q♠\n" +
                    "C52: K♠\n" +
                    "Not a valid pile input, try again \n" +
                    "\n" +
                    "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1: A♥\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1:\n" +
                    "C2: 2♥\n" +
                    "C3: 3♥\n" +
                    "C4: 4♥\n" +
                    "C5: 5♥\n" +
                    "C6: 6♥\n" +
                    "C7: 7♥\n" +
                    "C8: 8♥\n" +
                    "C9: 9♥\n" +
                    "C10: 10♥\n" +
                    "C11: J♥\n" +
                    "C12: Q♥\n" +
                    "C13: K♥\n" +
                    "C14: A♦\n" +
                    "C15: 2♦\n" +
                    "C16: 3♦\n" +
                    "C17: 4♦\n" +
                    "C18: 5♦\n" +
                    "C19: 6♦\n" +
                    "C20: 7♦\n" +
                    "C21: 8♦\n" +
                    "C22: 9♦\n" +
                    "C23: 10♦\n" +
                    "C24: J♦\n" +
                    "C25: Q♦\n" +
                    "C26: K♦\n" +
                    "C27: A♣\n" +
                    "C28: 2♣\n" +
                    "C29: 3♣\n" +
                    "C30: 4♣\n" +
                    "C31: 5♣\n" +
                    "C32: 6♣\n" +
                    "C33: 7♣\n" +
                    "C34: 8♣\n" +
                    "C35: 9♣\n" +
                    "C36: 10♣\n" +
                    "C37: J♣\n" +
                    "C38: Q♣\n" +
                    "C39: K♣\n" +
                    "C40: A♠\n" +
                    "C41: 2♠\n" +
                    "C42: 3♠\n" +
                    "C43: 4♠\n" +
                    "C44: 5♠\n" +
                    "C45: 6♠\n" +
                    "C46: 7♠\n" +
                    "C47: 8♠\n" +
                    "C48: 9♠\n" +
                    "C49: 10♠\n" +
                    "C50: J♠\n" +
                    "C51: Q♠\n" +
                    "C52: K♠\n" +
                    "Game quit prematurely."));
  }

  @Test
  public void testBadInputIndex() throws IOException {
    assertNotEquals(null, m1);
    testRun(m1, Interaction.inputs("C1 j 1 F1 q"),
            Interaction.prints("F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: A♥\n" +
                    "C2: 2♥\n" +
                    "C3: 3♥\n" +
                    "C4: 4♥\n" +
                    "C5: 5♥\n" +
                    "C6: 6♥\n" +
                    "C7: 7♥\n" +
                    "C8: 8♥\n" +
                    "C9: 9♥\n" +
                    "C10: 10♥\n" +
                    "C11: J♥\n" +
                    "C12: Q♥\n" +
                    "C13: K♥\n" +
                    "C14: A♦\n" +
                    "C15: 2♦\n" +
                    "C16: 3♦\n" +
                    "C17: 4♦\n" +
                    "C18: 5♦\n" +
                    "C19: 6♦\n" +
                    "C20: 7♦\n" +
                    "C21: 8♦\n" +
                    "C22: 9♦\n" +
                    "C23: 10♦\n" +
                    "C24: J♦\n" +
                    "C25: Q♦\n" +
                    "C26: K♦\n" +
                    "C27: A♣\n" +
                    "C28: 2♣\n" +
                    "C29: 3♣\n" +
                    "C30: 4♣\n" +
                    "C31: 5♣\n" +
                    "C32: 6♣\n" +
                    "C33: 7♣\n" +
                    "C34: 8♣\n" +
                    "C35: 9♣\n" +
                    "C36: 10♣\n" +
                    "C37: J♣\n" +
                    "C38: Q♣\n" +
                    "C39: K♣\n" +
                    "C40: A♠\n" +
                    "C41: 2♠\n" +
                    "C42: 3♠\n" +
                    "C43: 4♠\n" +
                    "C44: 5♠\n" +
                    "C45: 6♠\n" +
                    "C46: 7♠\n" +
                    "C47: 8♠\n" +
                    "C48: 9♠\n" +
                    "C49: 10♠\n" +
                    "C50: J♠\n" +
                    "C51: Q♠\n" +
                    "C52: K♠\n" +
                    "Not a valid index, try again\n" +
                    "F1: A♥\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1:\n" +
                    "C2: 2♥\n" +
                    "C3: 3♥\n" +
                    "C4: 4♥\n" +
                    "C5: 5♥\n" +
                    "C6: 6♥\n" +
                    "C7: 7♥\n" +
                    "C8: 8♥\n" +
                    "C9: 9♥\n" +
                    "C10: 10♥\n" +
                    "C11: J♥\n" +
                    "C12: Q♥\n" +
                    "C13: K♥\n" +
                    "C14: A♦\n" +
                    "C15: 2♦\n" +
                    "C16: 3♦\n" +
                    "C17: 4♦\n" +
                    "C18: 5♦\n" +
                    "C19: 6♦\n" +
                    "C20: 7♦\n" +
                    "C21: 8♦\n" +
                    "C22: 9♦\n" +
                    "C23: 10♦\n" +
                    "C24: J♦\n" +
                    "C25: Q♦\n" +
                    "C26: K♦\n" +
                    "C27: A♣\n" +
                    "C28: 2♣\n" +
                    "C29: 3♣\n" +
                    "C30: 4♣\n" +
                    "C31: 5♣\n" +
                    "C32: 6♣\n" +
                    "C33: 7♣\n" +
                    "C34: 8♣\n" +
                    "C35: 9♣\n" +
                    "C36: 10♣\n" +
                    "C37: J♣\n" +
                    "C38: Q♣\n" +
                    "C39: K♣\n" +
                    "C40: A♠\n" +
                    "C41: 2♠\n" +
                    "C42: 3♠\n" +
                    "C43: 4♠\n" +
                    "C44: 5♠\n" +
                    "C45: 6♠\n" +
                    "C46: 7♠\n" +
                    "C47: 8♠\n" +
                    "C48: 9♠\n" +
                    "C49: 10♠\n" +
                    "C50: J♠\n" +
                    "C51: Q♠\n" +
                    "C52: K♠\n" +
                    "Game quit prematurely."));
  }

  @Test
  public void testBadInputDest() throws IOException {
    assertNotEquals(null, m1);
    testRun(m1, Interaction.inputs("C1 1 K2 F1 q"),
            Interaction.prints("F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: A♥\n" +
                    "C2: 2♥\n" +
                    "C3: 3♥\n" +
                    "C4: 4♥\n" +
                    "C5: 5♥\n" +
                    "C6: 6♥\n" +
                    "C7: 7♥\n" +
                    "C8: 8♥\n" +
                    "C9: 9♥\n" +
                    "C10: 10♥\n" +
                    "C11: J♥\n" +
                    "C12: Q♥\n" +
                    "C13: K♥\n" +
                    "C14: A♦\n" +
                    "C15: 2♦\n" +
                    "C16: 3♦\n" +
                    "C17: 4♦\n" +
                    "C18: 5♦\n" +
                    "C19: 6♦\n" +
                    "C20: 7♦\n" +
                    "C21: 8♦\n" +
                    "C22: 9♦\n" +
                    "C23: 10♦\n" +
                    "C24: J♦\n" +
                    "C25: Q♦\n" +
                    "C26: K♦\n" +
                    "C27: A♣\n" +
                    "C28: 2♣\n" +
                    "C29: 3♣\n" +
                    "C30: 4♣\n" +
                    "C31: 5♣\n" +
                    "C32: 6♣\n" +
                    "C33: 7♣\n" +
                    "C34: 8♣\n" +
                    "C35: 9♣\n" +
                    "C36: 10♣\n" +
                    "C37: J♣\n" +
                    "C38: Q♣\n" +
                    "C39: K♣\n" +
                    "C40: A♠\n" +
                    "C41: 2♠\n" +
                    "C42: 3♠\n" +
                    "C43: 4♠\n" +
                    "C44: 5♠\n" +
                    "C45: 6♠\n" +
                    "C46: 7♠\n" +
                    "C47: 8♠\n" +
                    "C48: 9♠\n" +
                    "C49: 10♠\n" +
                    "C50: J♠\n" +
                    "C51: Q♠\n" +
                    "C52: K♠\n" +
                    "Not a valid pile input, try again \n" +
                    "\n" +
                    "F1: A♥\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1:\n" +
                    "C2: 2♥\n" +
                    "C3: 3♥\n" +
                    "C4: 4♥\n" +
                    "C5: 5♥\n" +
                    "C6: 6♥\n" +
                    "C7: 7♥\n" +
                    "C8: 8♥\n" +
                    "C9: 9♥\n" +
                    "C10: 10♥\n" +
                    "C11: J♥\n" +
                    "C12: Q♥\n" +
                    "C13: K♥\n" +
                    "C14: A♦\n" +
                    "C15: 2♦\n" +
                    "C16: 3♦\n" +
                    "C17: 4♦\n" +
                    "C18: 5♦\n" +
                    "C19: 6♦\n" +
                    "C20: 7♦\n" +
                    "C21: 8♦\n" +
                    "C22: 9♦\n" +
                    "C23: 10♦\n" +
                    "C24: J♦\n" +
                    "C25: Q♦\n" +
                    "C26: K♦\n" +
                    "C27: A♣\n" +
                    "C28: 2♣\n" +
                    "C29: 3♣\n" +
                    "C30: 4♣\n" +
                    "C31: 5♣\n" +
                    "C32: 6♣\n" +
                    "C33: 7♣\n" +
                    "C34: 8♣\n" +
                    "C35: 9♣\n" +
                    "C36: 10♣\n" +
                    "C37: J♣\n" +
                    "C38: Q♣\n" +
                    "C39: K♣\n" +
                    "C40: A♠\n" +
                    "C41: 2♠\n" +
                    "C42: 3♠\n" +
                    "C43: 4♠\n" +
                    "C44: 5♠\n" +
                    "C45: 6♠\n" +
                    "C46: 7♠\n" +
                    "C47: 8♠\n" +
                    "C48: 9♠\n" +
                    "C49: 10♠\n" +
                    "C50: J♠\n" +
                    "C51: Q♠\n" +
                    "C52: K♠\n" +
                    "Game quit prematurely."));
  }

  @Test
  public void testIllegalNumOpen() {
    StringReader input = new StringReader("C1 q k O1");
    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m1.getDeck(), m1, 8, -6, false);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Could not start game.", lines[lines.length - 1]);
  }

  @Test
  public void testIllegalNumCascade() {
    StringReader input = new StringReader("C1 q k O1");
    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m1.getDeck(), m1, 0, 4, false);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Could not start game.", lines[lines.length - 1]);
  }

  @Test(expected = IllegalStateException.class)
  public void testRunOutInput() {
    StringReader input = new StringReader("C1 7 O1");
    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m1.getDeck(), m1, 8, 4, false);
  }

  @Test
  public void testGameWin() {
    StringReader input = new StringReader(
            "C1 1 F1 " +
                    "C2 1 F1 " +
                    "C3 1 F1 " +
                    "C4 1 F1 " +
                    "C5 1 F1 " +
                    "C6 1 F1 " +
                    "C7 1 F1 " +
                    "C8 1 F1 " +
                    "C9 1 F1 " +
                    "C10 1 F1 " +
                    "C11 1 F1 " +
                    "C12 1 F1 " +
                    "C13 1 F1 " +
                    "C14 1 F2 " +
                    "C15 1 F2 " +
                    "C16 1 F2 " +
                    "C17 1 F2 " +
                    "C18 1 F2 " +
                    "C19 1 F2 " +
                    "C20 1 F2 " +
                    "C21 1 F2 " +
                    "C22 1 F2 " +
                    "C23 1 F2 " +
                    "C24 1 F2 " +
                    "C25 1 F2 " +
                    "C26 1 F2 " +
                    "C27 1 F3 " +
                    "C28 1 F3 " +
                    "C29 1 F3 " +
                    "C30 1 F3 " +
                    "C31 1 F3 " +
                    "C32 1 F3 " +
                    "C33 1 F3 " +
                    "C34 1 F3 " +
                    "C35 1 F3 " +
                    "C36 1 F3 " +
                    "C37 1 F3 " +
                    "C38 1 F3 " +
                    "C39 1 F3 " +
                    "C40 1 F4 " +
                    "C41 1 F4 " +
                    "C42 1 F4 " +
                    "C43 1 F4 " +
                    "C44 1 F4 " +
                    "C45 1 F4 " +
                    "C46 1 F4 " +
                    "C47 1 F4 " +
                    "C48 1 F4 " +
                    "C49 1 F4 " +
                    "C50 1 F4 " +
                    "C51 1 F4 " +
                    "C52 1 F4");

    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m1.getDeck(), m1, 52, 4, false);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game over.", lines[lines.length - 1]);

  }

  @Test
  public void testGameWinBadInputs() {
    StringReader input = new StringReader(
            "k " +
                    "C1 1 F1 " +
                    "C2 1 F1 " +
                    "C3 1 F1 " +
                    "C4 1 F1 " +
                    "C5 1 F1 " +
                    "C123 34543 F1 " +
                    "C6 1 F1 " +
                    "C7 1 F1 " +
                    "C8 1 F1 " +
                    "C9 1 F1 " +
                    "C10 1 F1 " +
                    "C11 1 F1 " +
                    "C143 12 F1 " +
                    "C12 1 F1 " +
                    "C13 1 F1 " +
                    "C14 1 F2 " +
                    "C15 1 F2 " +
                    "C16 1 F2 " +
                    "C17 1 F2 " +
                    "C18 1 F2 " +
                    "fewefesreses " +
                    "C19 1 F2 " +
                    "C20 1 F2 " +
                    "C21 1 F2 " +
                    "C22 1 F2 " +
                    "C23 1 F2 " +
                    "C24 1 F2 " +
                    "C25 1 F2 " +
                    "C26 1 F2 " +
                    "C27 1 F3 " +
                    "C28 1 F3 " +
                    "C29 1 F3 " +
                    "C30 1 F3 " +
                    "C31 1 F3 " +
                    "C32 1 F3 " +
                    "C33 1 F3 " +
                    "C34 1 F3 " +
                    "C35 1 F3 " +
                    "C36 1 F3 " +
                    "C37 1 F3 " +
                    "C38 1 F3 " +
                    "C39 1 F3 " +
                    "C40 1 F4 " +
                    "C41 1 F4 " +
                    "C42 1 F4 " +
                    "C43 1 F4 " +
                    "C44 1 F4 " +
                    "C45 1 F4 " +
                    "C46 1 F4 " +
                    "C47 1 F4 " +
                    "C48 1 F4 " +
                    "C49 1 F4 " +
                    "C50 1 F4 " +
                    "C51 1 F4 " +
                    "C52 1 F4");

    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m1.getDeck(), m1, 52, 4, false);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game over.", lines[lines.length - 1]);

  }

  @Test
  public void testQuitSourcePile() {
    StringReader input = new StringReader(
            "C1 1 F1 " +
                    "q 1 F1 ");

    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m1.getDeck(), m1, 52, 4, false);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game quit prematurely.", lines[lines.length - 1]);
  }

  @Test
  public void testQuitDestPile() {
    StringReader input = new StringReader(
            "C1 1 F1 " +
                    "C2 1 q " +
                    "F1");

    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m1.getDeck(), m1, 52, 4, false);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game quit prematurely.", lines[lines.length - 1]);
  }

  @Test
  public void testQuitIndex() {
    StringReader input = new StringReader(
            "C1 1 F1 " +
                    "C2 q F1 ");

    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m1.getDeck(), m1, 52, 4, false);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game quit prematurely.", lines[lines.length - 1]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    FreecellController c = new FreecellController(null, new StringBuilder());
    c.playGame(m1.getDeck(), m1, 52, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    FreecellController c = new FreecellController(new StringReader(""), null);
    c.playGame(m1.getDeck(), m1, 52, 4, false);
  }

  @Test
  public void testInvalidMultiMoveSourceBuild() {
    StringReader input = new StringReader(
            "C1 7 C8 C8 5 C1 q");

    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m2.getDeck(), m2, 8, 4, false);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("invalid move. Try again. " +
            "Source cards do not form valid build", lines[lines.length - 2]);
    assertEquals("Game quit prematurely.", lines[lines.length - 1]);
  }

  @Test
  public void testInvalidMultiMoveDestBuild() {
    StringReader input = new StringReader(
            "C1 7 C8 C8 6 C1 q");

    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m2.getDeck(), m2, 8, 4, false);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("invalid move. Try again. " +
            "Top source card does not form valid build with last card" +
            " in destination pile", lines[lines.length - 2]);
    assertEquals("Game quit prematurely.", lines[lines.length - 1]);
  }

  @Test
  public void testInvalidMultiMoveFreecells() {
    StringReader input = new StringReader(
            "C4 7 F1 C7 6 O1 C7 5 F2 C7 4 C4 C1 7 O2 C1 6 O3 C4 5 C7 q");

    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(input, gameLog);
    c.playGame(m3.getDeck(), m3, 8, 4, false);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("invalid move. Try again. " +
            "Not enough free open/cascade piles to make move", lines[lines.length - 2]);
    assertEquals("Game quit prematurely.", lines[lines.length - 1]);
  }

}
