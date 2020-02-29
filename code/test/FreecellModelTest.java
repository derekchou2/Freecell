import org.junit.Test;

import java.util.List;

import cs3500.freecell.hw02.Card;
import cs3500.freecell.hw02.Suit;
import cs3500.freecell.hw02.Value;
import cs3500.freecell.hw02.FreecellModel;
import cs3500.freecell.hw02.PileType;
import cs3500.freecell.hw04.FreecellModelCreator;
import cs3500.freecell.hw04.MultiMoveModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test cases for the FreecellModel class.
 */
public class FreecellModelTest {
  private FreecellModel fc1 = new FreecellModel();
  private FreecellModel fc2 = new FreecellModel();
  private FreecellModel s1 = new FreecellModel(10);
  private Card aceHeart1 = new Card(Suit.Heart, Value.Ace);
  private Card aceHeart2 = new Card(Suit.Heart, Value.Ace);
  private Card twoHeart = new Card(Suit.Heart, Value.Two);
  private Card aceSpade = new Card(Suit.Spade, Value.Ace);



  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSuit() {
    Card c = new Card(null, Value.Ace);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidValue() {
    Card c = new Card(Suit.Heart, null);
  }

  @Test
  public void testDeck() {
    assertEquals(aceHeart1, fc1.getDeck().get(0));
    assertEquals(52, fc1.getDeck().size());
  }

  @Test
  public void testGetDeck() {
    List<Card> copy = fc1.getDeck();
    copy.clear();
    assertEquals(new Card(Suit.Heart, Value.Ace), fc1.getDeck().get(0));
  }

  @Test
  public void testEquals() {
    assertTrue(aceHeart1.equals(aceHeart1));
    assertTrue(aceHeart1.equals(aceHeart2));
    assertFalse(aceHeart1.equals(aceSpade));
    assertFalse(aceHeart1.equals(twoHeart));
  }

  @Test
  public void testHashCode() {
    assertEquals(aceHeart1.hashCode(), aceHeart2.hashCode());
    assertNotEquals(aceHeart1.hashCode(), twoHeart.hashCode());
    assertNotEquals(aceHeart1.hashCode(), aceSpade.hashCode());
  }

  @Test
  public void testStartGameAndGetGameState() {
    assertEquals("", fc1.getGameState());
    fc1.startGame(fc1.getDeck(), 8, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♥, 9♥, 4♦, Q♦, 7♣, 2♠, 10♠\n" +
            "C2: 2♥, 10♥, 5♦, K♦, 8♣, 3♠, J♠\n" +
            "C3: 3♥, J♥, 6♦, A♣, 9♣, 4♠, Q♠\n" +
            "C4: 4♥, Q♥, 7♦, 2♣, 10♣, 5♠, K♠\n" +
            "C5: 5♥, K♥, 8♦, 3♣, J♣, 6♠\n" +
            "C6: 6♥, A♦, 9♦, 4♣, Q♣, 7♠\n" +
            "C7: 7♥, 2♦, 10♦, 5♣, K♣, 8♠\n" +
            "C8: 8♥, 3♦, J♦, 6♣, A♠, 9♠", fc1.getGameState());
  }

  @Test
  public void testgetCard() {
    fc1.startGame(fc1.getDeck(), 8, 4, false);
    assertEquals(new Card(Suit.Club, Value.Four),
            fc1.getCard(PileType.CASCADE, 5, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testgetCardNull() {
    fc1.startGame(fc1.getDeck(), 8, 4, false);
    fc1.getCard(PileType.CASCADE, 8, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCardIllegalState() {
    fc1.getCard(PileType.OPEN, 4, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardIllegalArgNoCardInSpot() {
    fc1.startGame(fc1.getDeck(), 8, 4, false);
    fc1.getCard(PileType.CASCADE, 0, 0);
    fc1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    fc1.getCard(PileType.OPEN, 0, 0);
    fc1.getCard(PileType.OPEN, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardIllegalArgOutOfBounds() {
    fc1.startGame(fc1.getDeck(), 8, 4, false);
    fc1.getCard(PileType.CASCADE, 8, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalStartGameNumOpen() {
    fc1.startGame(fc1.getDeck(), 8, 0, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalStartGameNumCascade() {
    fc1.startGame(fc1.getDeck(), 3, 4, false);
  }

  @Test
  // Test valid start game
  public void testStartGame() {
    fc1.startGame(fc1.getDeck(), 8, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♥, 9♥, 4♦, Q♦, 7♣, 2♠, 10♠\n" +
            "C2: 2♥, 10♥, 5♦, K♦, 8♣, 3♠, J♠\n" +
            "C3: 3♥, J♥, 6♦, A♣, 9♣, 4♠, Q♠\n" +
            "C4: 4♥, Q♥, 7♦, 2♣, 10♣, 5♠, K♠\n" +
            "C5: 5♥, K♥, 8♦, 3♣, J♣, 6♠\n" +
            "C6: 6♥, A♦, 9♦, 4♣, Q♣, 7♠\n" +
            "C7: 7♥, 2♦, 10♦, 5♣, K♣, 8♠\n" +
            "C8: 8♥, 3♦, J♦, 6♣, A♠, 9♠", fc1.getGameState());
  }

  @Test
  public void testShuffle() {
    fc1.startGame(fc1.getDeck(), 8, 4, false);
    fc2.startGame(fc1.getDeck(), 8, 4, true);
    assertNotEquals(fc1.getGameState(), fc2.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidFoundationSourceIndex() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.FOUNDATION, 4, 0, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidCascadeSourceIndex() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 8, 0, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidOpenSourceIndex() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.OPEN, 2, -1, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidFoundationDestIndex() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidOpenDestIndex() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 5, 5, PileType.OPEN, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidCascadeDestIndex() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card in cascade pile that isn't on the bottom
  public void testMoveInvalidCascadeCard() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 5, 3, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card in foundation pile
  public void testMoveInvalidFoundationCard() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 4, 4, PileType.OPEN, 0);
    s1.move(PileType.CASCADE, 4, 3, PileType.FOUNDATION, 0);
    s1.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card to foundation pile, not matching suit
  public void testMoveInvalidFoundationSuit() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 4, 4, PileType.OPEN, 0);
    s1.move(PileType.CASCADE, 4, 3, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 3, 5, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card to foundation pile, matching suit but value is not 1 greater
  public void testMoveInvalidFoundationValue() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 4, 4, PileType.OPEN, 0);
    s1.move(PileType.CASCADE, 4, 3, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 1, 6, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card to open pile, but spot is occupied
  public void testMoveInvalidOpenOccupied() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 4, 4, PileType.OPEN, 0);
    s1.move(PileType.CASCADE, 4, 3, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card to cascade pile, but value is not 1 less than bottom card in pile
  public void testMoveInvalidCascadeValue() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 6, 5, PileType.CASCADE, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card to cascade pile, value is 1 less but same color as bottom card in pile
  public void testMoveInvalidCascadeColor() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 0);
    s1.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 1);
  }

  @Test(expected = IllegalStateException.class)
  // Trying to move when game hasn't started
  public void testMoveInvalidState() {
    s1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 0);
  }

  @Test
  // Test legal foundation move
  public void testLegalFoundationMove() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    assertEquals(new Card(Suit.Diamond, Value.Ace),
            s1.getCard(PileType.CASCADE, 4, 5));
    s1.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 0);
    assertEquals(new Card(Suit.Diamond, Value.Ace),
            s1.getCard(PileType.FOUNDATION, 0, 0));
  }

  @Test
  // Test legal Open move
  public void testLegalOpenMove() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    assertEquals(new Card(Suit.Diamond, Value.Ace),
            s1.getCard(PileType.CASCADE, 4, 5));
    s1.move(PileType.CASCADE, 4, 5, PileType.OPEN, 0);
    assertEquals(new Card(Suit.Diamond, Value.Ace),
            s1.getCard(PileType.OPEN, 0, 0));
  }

  @Test
  // Test legal Cascade move
  public void testLegalCascadeMove() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    assertEquals(new Card(Suit.Heart, Value.Six),
            s1.getCard(PileType.CASCADE, 6, 5));
    s1.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 6);
    assertEquals(new Card(Suit.Spade, Value.Five),
            s1.getCard(PileType.CASCADE, 6, 6));
  }

  @Test(expected = IllegalArgumentException.class)
  // Test Foundation pile full
  public void testFoundationFull() {
    s1.startGame(s1.getDeck(), 52, 4, false);
    s1.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 30, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 29, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 33, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 25, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 19, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 50, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 0);
    // Foundation pile full at this point
    s1.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
  }

  @Test
  // Test game won
  public void testWinGame() {
    s1.startGame(s1.getDeck(), 52, 4, false);
    s1.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 30, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 29, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 33, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 25, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 19, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 50, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 13, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 34, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 43, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 39, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 11, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 48, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 42, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 38, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 15, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 1);
    s1.move(PileType.CASCADE, 31, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 26, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 37, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 21, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 46, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 17, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 35, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 27, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 22, 0, PileType.FOUNDATION, 2);
    s1.move(PileType.CASCADE, 45, 0, PileType.FOUNDATION, 3);
    s1.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 3);
    s1.move(PileType.CASCADE, 51, 0, PileType.FOUNDATION, 3);
    s1.move(PileType.CASCADE, 18, 0, PileType.FOUNDATION, 3);
    s1.move(PileType.CASCADE, 49, 0, PileType.FOUNDATION, 3);
    s1.move(PileType.CASCADE, 41, 0, PileType.FOUNDATION, 3);
    s1.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 3);
    s1.move(PileType.CASCADE, 47, 0, PileType.FOUNDATION, 3);
    s1.move(PileType.CASCADE, 10, 0, PileType.FOUNDATION, 3);
    s1.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 3);
    s1.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 3);
    s1.move(PileType.CASCADE, 23, 0, PileType.FOUNDATION, 3);

    assertEquals(false, s1.isGameOver());

    s1.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 3);

    assertEquals(true, s1.isGameOver());


  }

  @Test
  public void testRestartGame() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION, 1);
    assertEquals("F1: A♦\n" +
            "F2: A♠\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: 5♣, 7♠, J♠, K♣, K♠, 8♦, 8♣\n" +
            "C2: J♦, 3♥, 8♥, 6♦, 5♦, 6♠, 5♠\n" +
            "C3: 4♦, 9♠, 4♠, 2♥, 3♣, 10♣, Q♦\n" +
            "C4: 7♥, 7♣, 10♦, Q♥, J♥, 4♣, 3♠\n" +
            "C5: 9♦, 9♥, 10♠, 2♠, 9♣\n" +
            "C6: 7♦, 2♣, 5♥, 3♦, 4♥\n" +
            "C7: A♣, K♦, K♥, 2♦, J♣, 6♥\n" +
            "C8: 10♥, Q♣, Q♠, A♥, 6♣, 8♠", s1.getGameState());


    s1.startGame(s1.getDeck(), 8, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: 5♣, 7♠, J♠, K♣, K♠, 8♦, 8♣\n" +
            "C2: J♦, 3♥, 8♥, 6♦, 5♦, 6♠, 5♠\n" +
            "C3: 4♦, 9♠, 4♠, 2♥, 3♣, 10♣, Q♦\n" +
            "C4: 7♥, 7♣, 10♦, Q♥, J♥, 4♣, 3♠\n" +
            "C5: 9♦, 9♥, 10♠, 2♠, 9♣, A♦\n" +
            "C6: 7♦, 2♣, 5♥, 3♦, 4♥, A♠\n" +
            "C7: A♣, K♦, K♥, 2♦, J♣, 6♥\n" +
            "C8: 10♥, Q♣, Q♠, A♥, 6♣, 8♠", s1.getGameState());


  }

  @Test
  public void testFactorySingleMove() {
    assertTrue(FreecellModelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE)
            instanceof FreecellModel);
    assertFalse(FreecellModelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE)
            instanceof MultiMoveModel);
  }

  @Test
  public void testFactoryMultiMove() {
    assertTrue(FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE)
            instanceof FreecellModel);
    assertTrue(FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE)
            instanceof MultiMoveModel);
  }

}
