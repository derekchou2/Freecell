package cs3500.freecell.hw02;

/**
 * Represents the 13 values in a deck of cards.
 */
public enum Value {
  Ace("A"), Two("2"), Three("3"), Four("4"), Five("5"), Six("6"), Seven("7"),
  Eight("8"), Nine("9"), Ten("10"), Jack("J"), Queen("Q"), King("K");

  private final String value;

  Value(String v) {
    if (v == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }
    this.value = v;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
