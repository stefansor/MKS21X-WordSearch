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
    private ArrayList<String> wordsAdded;
    private boolean answers;
    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch( int rows, int cols, String fileName, int randseed, String g){
      seed = randseed;
      data = new char[rows][cols];
      ans = new char[rows][cols];
      answers = false;
      if(g.equals("key") || g.equals("answers")){
          answers = true;
      }
      clear();
      for(int i = 0; i < ans.length; i++){
        for(int j = 0; j < ans[i].length; j++){
          ans[i][j] = '_';
        }
      }
      randgen = new Random(seed);
      try{
        File f = new File(fileName);
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
        System.out.println("Must input a file that exists, your file was not found");
        System.exit(1);
      }
    }



    public static void main(String[] args){
      String directions = "Please input an integer number of rows, an integer number of columns, and at least a valid file. You can also input a a seed between 0 and 10000 inclusive and ask only for the answers by typing key or answers at the end. Make sure the file exists and that the dimensions of the puzzle are not negative.";
      if(args.length < 3){
        System.out.println(directions);
        System.exit(1);
      }
      if(args.length == 3){
        try{
          int a = Integer.parseInt(args[0]);
          int b = Integer.parseInt(args[1]);
          String c = args[2];
          int d = (int)(Math.random() * 10000);
          WordSearch yes = new WordSearch(a,b,c,d,"yr");
          System.out.println(yes);
        }
        catch(NumberFormatException e){
          System.out.println(directions);
          System.exit(1);
        }
        catch(NegativeArraySizeException e){
          System.out.println(directions);
          System.exit(1);
        }
      }
      if(args.length == 4){
        try{
          int a = Integer.parseInt(args[0]);
          int b = Integer.parseInt(args[1]);
          String c = args[2];
          int d = Integer.parseInt(args[3]);
          WordSearch yeah = new WordSearch(a,b,c,d, "yrrr");
          System.out.println(yeah);
        }
        catch(NumberFormatException e){
          System.out.println(directions);
          System.exit(1);
        }
        catch(NegativeArraySizeException e){
          System.out.println(directions);
          System.exit(1);
        }
      }
      if(args.length == 5){
        try{
          int a = Integer.parseInt(args[0]);
          int b = Integer.parseInt(args[1]);
          String c = args[2];
          int d = Integer.parseInt(args[3]);
          String e = args[4];
          WordSearch ohyeah = new WordSearch(a,b,c,d,e);
          System.out.println(ohyeah);
        }
        catch(NumberFormatException e){
          System.out.println(directions);
          System.exit(1);
        }
        catch(NegativeArraySizeException e){
          System.out.println(directions);
          System.exit(1);
        }
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
        base = base + "| \n" + words + "\n" + "Seed: "+  this.seed + "\n";
      }
      if(this.answers){
        return base;
      }
      return template + "\n" + "Seed: " + this.seed +"\n";
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
