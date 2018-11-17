import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class WordSearch{
    private char[][]data;
    private char[][]ans;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd;
    public ArrayList<String> wordsAdded;
    private boolean answers;
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
        while(in.hasNextLine()){
          String wor = in.nextLine();
          wordsToAdd.add(wor);
        }
        addAllWords();
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
      data = new char[r][c];
      ans = new char[r][c];
      answers = true;
      clear();
      for(int i = 0; i < ans.length; i++){
        for(int j = 0; j < ans[i].length; j++){
          ans[i][j] = '_';
        }
      }
      randgen = new Random();
      try{
        File f = new File("words.txt");
        Scanner in = new Scanner(f);
        wordsToAdd = new ArrayList<String>();
        wordsAdded = new ArrayList<String>();
        while(in.hasNext()){
          String wor = in.next();
          wordsToAdd.add(wor);
        }
        addAllWords();
        this.fillRandom();
      }
      catch(FileNotFoundException e){
        System.out.println("nope");
        System.exit(1);
      }
    }

    /**Set all values in the WordSearch to underscores'_'*/
    public void clear(){
      for(int i = 0; i < this.data.length; i++){
        for(int j = 0; j < this.data[i].length; j++){
          this.data[i][j] = '_';
        }
      }
    }
    private viod addAns(){
      if(answer){
        for()
      }
    }

    private void fillRandom(){
      for(int i = 0; i < this.data.length; i++){
        for(int j = 0; j < this.data[i].length; j++){
          if(this.data[i][j] == '_'){
            int a = 65;
            char ad = (char)(a + Math.abs(randgen.nextInt() % 26));
            this.data[i][j] = ad ;
          }
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String template = "\n";
      String base = "\n\n\nKey: \n";
      for(int i = 0; i < this.data.length; i++){
        template = template + "|";
        for(int j = 0; j < this.data[i].length; j++){
          template = template + this.data[i][j] + " ";
        }
        template = template + "| \n";
      }
      String words = "";
      for(int i = 0; i < wordsAdded.size(); i++){
        if(i == wordsAdded.size() - 1){
          words = words + wordsAdded.get(i);
        }
        else{
          words = words + wordsAdded.get(i) + ", ";
        }
      }
      template = template + "\n Words: " + words;
      for(int k = 0; k < this.ans.length; k++){
        base = base + "|";
        for(int l = 0; l < this.ans[k].length; l++){
          base = base + this.ans[k][l] + " ";
        }
        base = base + "| \n";
      }
      if(this.answers){
        template = template + base;
      }
      return template + "\n";
    }

    public boolean addWord( String word, int r, int c, int rowIncrement, int colIncrement){
      if(rowIncrement == 0 && colIncrement == 0){
        return false;
      }
      try{
        word = word.toUpperCase();
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
        this.ans[r + rowIncrement * j][c + colIncrement * j] = word.charAt(j);
      }
      return true;
    }


    public void addAllWords(){
      for(int x = 0; x < this.wordsToAdd.size(); x++){
        String word = this.wordsToAdd.get(Math.abs(randgen.nextInt() % this.wordsToAdd.size()));
        int rowI = (randgen.nextInt() % 2);
        int colI = (randgen.nextInt() % 2);
        boolean added = false;
        for(int y = 0; y < 1000000; y++){
          if(!added && addWord(word, Math.abs(randgen.nextInt() % this.data.length),
          Math.abs(randgen.nextInt() % this.data[0].length), rowI, colI)){
            added = true;
            this.wordsToAdd.remove(word);
            this.wordsAdded.add(word);
          }
        }
      }
    }


}
