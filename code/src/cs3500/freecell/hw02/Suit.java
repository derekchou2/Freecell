package cs3500.freecell.hw02;

/**
 * Represents the 4 suits in a deck of cards.
 */
public enum Suit {
  Heart("Heart"), Diamond("Diamond"), Club("Club"), Spade("Spade");

  private final String shape;

  Suit(String shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Suit cannot be null");
    }
    this.shape = shape;
  }

  @Override
  public String toString() {
    return this.shape;
  }
}
