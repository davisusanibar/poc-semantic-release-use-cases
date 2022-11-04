package io.github.davisusanibar;

/**
 * Class to test artifacts production
 */
public class TestMe {
  /**
   * Main method
   * @param args input
   */
  public static void main(String[] args) {
    System.out.println(hi("David"));
  }

  /**
   * To test Java jar artifact method
   * @param name      The name to say hello
   * @return          Say hello
   */
  public static String hi(String name){
    return String.format("Hi %s", name);
  }
}
