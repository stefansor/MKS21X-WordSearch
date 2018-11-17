import java.util.Random;
public class Driver{

  public static void main(String[] args) {
    WordSearch one = new WordSearch(20, 20);
    System.out.println(one.toString());
    System.out.println(""+(int)(Math.random() * 1000));
  }
}
