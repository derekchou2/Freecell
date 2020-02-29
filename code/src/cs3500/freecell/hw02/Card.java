package cs3500.freecell.hw02;

import java.util.Objects;

/**
 * Represents a standard playing card.
 */
public class Card {
  private final Suit suit;
  private final Value value;

  /**
   * default constructor.
   * @param suit the suit of the card
   * @param value the value of the card
   */
  public Card(Suit suit, Value value) {
    if (suit == null || value == null) {
      throw new IllegalArgumentException("Suit and value cannot be null");
    }
    this.suit = suit;
    this.value = value;
  }

  @Override
  public String toString() {
    switch (this.suit) {
      case Spade:
        return this.value.toString() + "♠";
      case Club:
        return this.value.toString() + "♣";
      case Heart:
        return this.value.toString() + "♥";
      case Diamond:
        return this.value.toString() + "♦";
      default:
        throw new RuntimeException("Unrecognized Suit");
    }
  }

  /**
   * Determines if the other object is the same as this card or has the same suit and value.
   *
   * @param that the other object
   * @return whether that object is the same as this or has the same suit and value
   */
  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Card)) {
      return false;
    }
    return this.suit == ((Card) that).suit && this.value == ((Card) that).value;
  }

  /**
   * Hashes cards by suit and value.
   *
   * @return hashcode of this card
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.suit, this.value);
  }

  /**
   * Return this card's suit.
   *
   * @return this card's suit
   */
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * Return this card's value.
   *
   * @return this card's value
   */
  public Value getValue() {
    return this.value;
  }
}
