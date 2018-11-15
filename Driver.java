public class Driver{

  public static void main(String[] args) {
    WordSearch one = new WordSearch(20, 20);
    one.addWord("hello", 10, 10, -1, -1);
    one.addWord("soooo",12 ,12, 1, 1);
    System.out.println(one.toString());
  }
}
