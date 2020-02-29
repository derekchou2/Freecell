import org.junit.Test;

import java.util.List;

import cs3500.freecell.hw02.Card;
import cs3500.freecell.hw02.Suit;
import cs3500.freecell.hw02.Value;
import cs3500.freecell.hw04.MultiMoveModel;
import cs3500.freecell.hw02.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Test cases for the MultiMoveModel class.
 */
public class MultiMoveModelTest {
  private MultiMoveModel mm1 = new MultiMoveModel();
  private MultiMoveModel mm2 = new MultiMoveModel();
  private MultiMoveModel s1 = new MultiMoveModel(15);
  private MultiMoveModel s2 = new MultiMoveModel(10);
  private MultiMoveModel s3 = new MultiMoveModel(17);
  private MultiMoveModel s4 = new MultiMoveModel(18);
  private Card aceHeart1 = new Card(Suit.Heart, Value.Ace);




  @Test
  public void testDeck() {
    assertEquals(aceHeart1, mm1.getDeck().get(0));
    assertEquals(52, mm1.getDeck().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSuit() {
    Card c = new Card(null, Value.Ace);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidValue() {
    Card c = new Card(Suit.Heart, null);
  }

  @Test
  public void testGetDeck() {
    List<Card> copy = mm1.getDeck();
    copy.clear();
    assertEquals(new Card(Suit.Heart, Value.Ace), mm1.getDeck().get(0));
  }

  @Test
  public void testStartGameAndGetGameState() {
    assertEquals("", mm1.getGameState());
    mm1.startGame(mm1.getDeck(), 8, 4, false);
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
            "C8: 8♥, 3♦, J♦, 6♣, A♠, 9♠", mm1.getGameState());
  }

  @Test
  public void testgetCard() {
    mm1.startGame(mm1.getDeck(), 8, 4, false);
    assertEquals(new Card(Suit.Club, Value.Four),
            mm1.getCard(PileType.CASCADE, 5, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testgetCardNull() {
    mm1.startGame(mm1.getDeck(), 8, 4, false);
    mm1.getCard(PileType.CASCADE, 8, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCardIllegalState() {
    mm1.getCard(PileType.OPEN, 4, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardIllegalArgNoCardInSpot() {
    mm1.startGame(mm1.getDeck(), 8, 4, false);
    mm1.getCard(PileType.CASCADE, 0, 0);
    mm1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    mm1.getCard(PileType.OPEN, 0, 0);
    mm1.getCard(PileType.OPEN, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardIllegalArgOutOfBounds() {
    mm1.startGame(mm1.getDeck(), 8, 4, false);
    mm1.getCard(PileType.CASCADE, 8, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalStartGameNumOpen() {
    mm1.startGame(mm1.getDeck(), 8, 0, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalStartGameNumCascade() {
    mm1.startGame(mm1.getDeck(), 3, 4, false);
  }

  @Test
  // Test valid start game
  public void testStartGame() {
    mm1.startGame(mm1.getDeck(), 8, 4, false);
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
            "C8: 8♥, 3♦, J♦, 6♣, A♠, 9♠", mm1.getGameState());
  }

  @Test
  public void testShuffle() {
    mm1.startGame(mm1.getDeck(), 8, 4, false);
    mm2.startGame(mm1.getDeck(), 8, 4, true);
    assertNotEquals(mm1.getGameState(), mm2.getGameState());
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
  // Trying to move card in foundation pile
  public void testMoveInvalidFoundationCard() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    s1.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 7);

  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card to foundation pile, not matching suit
  public void testMoveInvalidFoundationSuit() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card to foundation pile, matching suit but value is not 1 greater
  public void testMoveInvalidFoundationValue() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    s1.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card to open pile, but spot is occupied
  public void testMoveInvalidOpenOccupied() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 0);
    s1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card to cascade pile, but value is not 1 less than bottom card in pile
  public void testMoveInvalidCascadeValue() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 0);
    s1.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  // Trying to move card to cascade pile, value is 1 less but same color as bottom card in pile
  public void testMoveInvalidCascadeColor() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 2);
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
            s1.getCard(PileType.CASCADE, 3, 6));
    s1.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    assertEquals(new Card(Suit.Diamond, Value.Ace),
            s1.getCard(PileType.FOUNDATION, 0, 0));
  }

  @Test
  // Test legal Open move
  public void testLegalOpenMove() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    assertEquals(new Card(Suit.Diamond, Value.Ace),
            s1.getCard(PileType.CASCADE, 3, 6));
    s1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 0);
    assertEquals(new Card(Suit.Diamond, Value.Ace),
            s1.getCard(PileType.OPEN, 0, 0));
  }

  @Test
  // Test legal Cascade move
  public void testLegalCascadeMove() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    assertEquals(new Card(Suit.Diamond, Value.Ace),
            s1.getCard(PileType.CASCADE, 3, 6));
    s1.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 5);
    assertEquals(new Card(Suit.Diamond, Value.Ace),
            s1.getCard(PileType.CASCADE, 5, 6));
  }

  @Test(expected = IllegalArgumentException.class)
  // Test Foundation pile full
  public void testFoundationFull() {
    s2.startGame(s1.getDeck(), 52, 4, false);
    s2.move(PileType.CASCADE, 15, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 26, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 34, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 38, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 42, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 41, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 37, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 23, 0, PileType.FOUNDATION, 0);
    // Foundation pile full at this point
    s2.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
  }

  @Test
  public void testWinGame() {
    s2.startGame(s1.getDeck(), 52, 4, false);
    s2.move(PileType.CASCADE, 15, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 26, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 34, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 38, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 42, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 41, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 37, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 23, 0, PileType.FOUNDATION, 0);
    s2.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 45, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 29, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 30, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 18, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 31, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 19, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 50, 0, PileType.FOUNDATION, 1);
    s2.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 33, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 17, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 11, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 39, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 43, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 49, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 47, 0, PileType.FOUNDATION, 2);
    s2.move(PileType.CASCADE, 51, 0, PileType.FOUNDATION, 3);
    s2.move(PileType.CASCADE, 13, 0, PileType.FOUNDATION, 3);
    s2.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 3);
    s2.move(PileType.CASCADE, 22, 0, PileType.FOUNDATION, 3);
    s2.move(PileType.CASCADE, 48, 0, PileType.FOUNDATION, 3);
    s2.move(PileType.CASCADE, 21, 0, PileType.FOUNDATION, 3);
    s2.move(PileType.CASCADE, 35, 0, PileType.FOUNDATION, 3);
    s2.move(PileType.CASCADE, 27, 0, PileType.FOUNDATION, 3);
    s2.move(PileType.CASCADE, 25, 0, PileType.FOUNDATION, 3);
    s2.move(PileType.CASCADE, 10, 0, PileType.FOUNDATION, 3);
    s2.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 3);
    s2.move(PileType.CASCADE, 46, 0, PileType.FOUNDATION, 3);

    assertEquals(false, s2.isGameOver());

    s2.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 3);
    assertEquals(true, s2.isGameOver());
  }

  @Test
  public void testRestartGame() {
    s1.startGame(s1.getDeck(), 8, 4, false);
    s1.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    assertEquals("F1: A♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: 9♥, J♦, A♣, Q♣, A♠, Q♥, 5♦\n" +
            "C2: 3♣, J♠, 4♠, 9♦, 2♠, 8♥, Q♠\n" +
            "C3: 9♠, 10♦, 9♣, 2♥, 3♥, 7♥, K♣\n" +
            "C4: 3♠, 5♠, J♣, 8♦, 7♦, 8♠\n" +
            "C5: 10♥, 5♣, 7♠, 4♥, 5♥, K♦\n" +
            "C6: 3♦, 2♦, 6♦, 6♣, J♥, 2♣\n" +
            "C7: 8♣, 10♠, 4♦, 7♣, 6♥, Q♦\n" +
            "C8: 4♣, A♥, K♥, 10♣, 6♠, K♠", s1.getGameState());

    s1.startGame(s1.getDeck(), 8, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: 9♥, J♦, A♣, Q♣, A♠, Q♥, 5♦\n" +
            "C2: 3♣, J♠, 4♠, 9♦, 2♠, 8♥, Q♠\n" +
            "C3: 9♠, 10♦, 9♣, 2♥, 3♥, 7♥, K♣\n" +
            "C4: 3♠, 5♠, J♣, 8♦, 7♦, 8♠, A♦\n" +
            "C5: 10♥, 5♣, 7♠, 4♥, 5♥, K♦\n" +
            "C6: 3♦, 2♦, 6♦, 6♣, J♥, 2♣\n" +
            "C7: 8♣, 10♠, 4♦, 7♣, 6♥, Q♦\n" +
            "C8: 4♣, A♥, K♥, 10♣, 6♠, K♠", s1.getGameState());
  }

  // test moving multiple cards to open pile
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMultiMoveOpen() {
    s3.startGame(s3.getDeck(), 8, 4, false);
    s3.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 7);
    s3.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
  }

  // test moving multiple cards to foundation pile
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMultiMoveFoundation() {
    s3.startGame(s3.getDeck(), 8, 4, false);
    s3.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 7);
    s3.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 0);
  }

  // Test multi move bad source build
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMultiMoveSourceBuild() {
    s3.startGame(s3.getDeck(), 8, 4, false);
    s3.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 7);
    s3.move(PileType.CASCADE, 7, 4, PileType.CASCADE, 0);
  }

  // Test multi move bad destination build
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMultiMoveDestBuild() {
    s3.startGame(s3.getDeck(), 8, 4, false);
    s3.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 7);
    s3.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 0);
  }

  // Test valid multi move
  @Test
  public void testValidMultiMove() {
    s4.startGame(s4.getDeck(), 8, 4, false);
    s4.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    s4.move(PileType.CASCADE, 6, 5, PileType.OPEN, 0);
    s4.move(PileType.CASCADE, 6, 4, PileType.FOUNDATION, 1);
    s4.move(PileType.CASCADE, 6, 3, PileType.CASCADE, 3);
    assertEquals("F1: A♥\n" +
            "F2: A♠\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: K♠\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: 6♥, 10♥, K♦, 6♠, A♣, 5♥, K♥\n" +
            "C2: 2♠, J♥, 5♠, 4♣, 4♥, J♣, K♣\n" +
            "C3: 7♥, 8♣, 8♥, 9♠, Q♥, 3♣, 2♦\n" +
            "C4: 10♦, 9♦, Q♣, 6♣, 6♦, 5♣, 4♦\n" +
            "C5: A♦, 2♣, J♦, 9♥, 3♠, J♠\n" +
            "C6: Q♠, 4♠, 10♠, 7♦, 8♠, 8♦\n" +
            "C7: 9♣, 2♥, 7♠\n" +
            "C8: 5♦, Q♦, 7♣, 3♥, 3♦, 10♣", s4.getGameState());
    s4.move(PileType.CASCADE, 3, 4, PileType.CASCADE, 6);
    assertEquals("F1: A♥\n" +
            "F2: A♠\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: K♠\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: 6♥, 10♥, K♦, 6♠, A♣, 5♥, K♥\n" +
            "C2: 2♠, J♥, 5♠, 4♣, 4♥, J♣, K♣\n" +
            "C3: 7♥, 8♣, 8♥, 9♠, Q♥, 3♣, 2♦\n" +
            "C4: 10♦, 9♦, Q♣, 6♣\n" +
            "C5: A♦, 2♣, J♦, 9♥, 3♠, J♠\n" +
            "C6: Q♠, 4♠, 10♠, 7♦, 8♠, 8♦\n" +
            "C7: 9♣, 2♥, 7♠, 6♦, 5♣, 4♦\n" +
            "C8: 5♦, Q♦, 7♣, 3♥, 3♦, 10♣", s4.getGameState());
  }

  // Test invalid multi move, not enough freecells
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMultiMoveFreecells() {
    s4.startGame(s4.getDeck(), 8, 4, false);
    s4.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    s4.move(PileType.CASCADE, 6, 5, PileType.OPEN, 0);
    s4.move(PileType.CASCADE, 6, 4, PileType.FOUNDATION, 1);
    s4.move(PileType.CASCADE, 6, 3, PileType.CASCADE, 3);
    s4.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    s4.move(PileType.CASCADE, 0, 5, PileType.OPEN, 2);
    assertEquals("F1: A♥\n" +
            "F2: A♠\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: K♠\n" +
            "O2: K♥\n" +
            "O3: 5♥\n" +
            "O4:\n" +
            "C1: 6♥, 10♥, K♦, 6♠, A♣\n" +
            "C2: 2♠, J♥, 5♠, 4♣, 4♥, J♣, K♣\n" +
            "C3: 7♥, 8♣, 8♥, 9♠, Q♥, 3♣, 2♦\n" +
            "C4: 10♦, 9♦, Q♣, 6♣, 6♦, 5♣, 4♦\n" +
            "C5: A♦, 2♣, J♦, 9♥, 3♠, J♠\n" +
            "C6: Q♠, 4♠, 10♠, 7♦, 8♠, 8♦\n" +
            "C7: 9♣, 2♥, 7♠\n" +
            "C8: 5♦, Q♦, 7♣, 3♥, 3♦, 10♣", s4.getGameState());

    s4.move(PileType.CASCADE, 3, 4, PileType.CASCADE, 6);
  }


}