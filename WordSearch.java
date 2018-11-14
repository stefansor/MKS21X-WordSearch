import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class WordSearch{
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd;
    public ArrayList<String> wordsAdded;
    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch( int rows, int cols, String fileName, int seed, boolean answers){
      try{
        data = new char[rows][cols];
        for(int i = 0; i < data.length; i++){
          for(int j = 0; j < data[i].length; j++){
            data[i][j] = '_';
          }
        }
        File f = new File(fileName);
        Scanner in = new Scanner(f);
        while(in.hasNext()){
          wordsToAdd.add(in.next());
        }
        this.addAllWords();
        /*this.fillRandom(); -- fills in random letters need to write helper function*/
        /*if(answers){
          this.showAns(); -- also prints wordsearch with no random letters afterwards
        }*/
        randgen = new Random(seed);
      }
      catch(FileNotFoundException e){
        System.out.println("Must input an existing text file");
        System.exit(1);
      }
    }
    /* for testing addALLWords()*/
    public WordSearch(int r, int c){
      try{
        data = new char[r][c];
        for(int i = 0; i < data.length; i++){
          for(int j = 0; j < data[i].length; j++){
            data[i][j] = '_';
          }
        }
        File f = new File("words.txt");
        Scanner in = new Scanner(f);
        while(in.hasNext()){
          wordsToAdd.add(in.next());
        }
        this.addAllWords();
      }
      catch(FileNotFoundException e){
        System.out.println("nope");
        System.exit(1);
      }
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for(int i = 0; i < this.data.length; i++){
        for(int j = 0; j < this.data[i].length; j++){
          this.data[i][j] = '_';
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String template = "";
      for(int i = 0; i < this.data.length; i++){
        template = template + "|";
        for(int j = 0; j < this.data[i].length; j++){
          template = template + this.data[i][j] + " ";
        }
        template = template + "| \n";
      }
      String words = "";
      /*for(int i = 0; i < this.wordsAdded.size(); i++){
        if(i ==  this.wordsAdded.size() - 1){
          words = words + this.wordsAdded.get(i);
        }
        else{
          words = words + this.wordsAdded.get(i) + ",";
        }
      }*/
      template = template + "\n Words: " + words;
      return template;
    }

    public boolean addWord( String word, int r, int c, int rowIncrement, int colIncrement){
      if(rowIncrement == 0 && colIncrement == 0){
        return false;
      }
      try{
        for(int i = 0; i < word.length(); i++){
          if(this.data[r + rowIncrement * i][c + colIncrement * i] != word.charAt(i)
          && this.data[r + rowIncrement * i][c + colIncrement * i] != '_'){
            return false;
          }
        }
      }
      catch(IndexOutOfBoundsException e){
        return false;
      }
      for(int j = 0; j < word.length(); j++){
        this.data[r + rowIncrement * j][c + colIncrement * j] = word.charAt(j);
      }
      return true;
    }


/*
    public void addAllWords(){
      String word = "";
      for(int i = 0; i < this.wordsToAdd.size(); i++){
        word = this.wordsToAdd.get(randgen.nextInt() % this.wordsToAdd.size());
        int rowI = (randgen.nextInt() % 3) - 1;
        int colI = (randgen.nextInt() % 3) - 1;
        boolean added = false;
        for(int j = 0; j < 1000; j++){
          if(!added && this.addWord(word, randgen.nextInt() % this.data.length,
          randgen.nextInt() % this.data[0].length, rowI, colI)){
            added = true;
            this.wordsToAdd.remove(word);
            this.wordsAdded.add(word);
          }
        }
      }
    }
*/
public void addAllWords(){
  String word = "";
  Random ran = new Random();
  for(int i = 0; i < this.wordsToAdd.size(); i++){
    word = this.wordsToAdd.get(ran.nextInt() % this.wordsToAdd.size());
    int rowI = (ran.nextInt() % 3) - 1;
    int colI = (ran.nextInt() % 3) - 1;
    boolean added = false;
    for(int j = 0; j < 1000; j++){
      if(!added && this.addWord(word, ran.nextInt() % this.data.length,
      ran.nextInt() % this.data[0].length, rowI, colI)){
        added = true;
        this.wordsToAdd.remove(word);
        this.wordsAdded.add(word);
      }
    }
  }
}


}
