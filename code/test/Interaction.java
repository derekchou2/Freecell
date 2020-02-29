/**
 * An interaction with the user consists of some input to send the program and some output to
 * expect.  We represent it as an object that takes in two StringBuilders and produces the intended
 * effects on them
 */
interface Interaction {

  static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }

  static Interaction inputs(String in) {
    return (input, output) -> {
      input.append(in);
    };
  }

  void apply(StringBuilder in, StringBuilder out);
}
