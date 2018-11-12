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
    private ArrayList<String> wordsAdded;
    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      for(int i = 0; i < data.length; i++){
        for(int j = 0; j < data[i].length; j++){
          data[i][j] = '_';
        }
      }
    }
    public WordSearch( int rows, int cols, String fileName) throws FileNotFoundException{
      data = new char[rows][cols];
      for(int i = 0; i < data.length; i++){
        for(int j = 0; j < data[i].length; j++){
          data[i][j] = '_';
        }
      }
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      /*wordsToAdd = in.toUpperCase().split("\n");*/
    }
    public WordSearch( int rows, int cols, String fileName, int randSeed)throws FileNotFoundException{
      data = new char[rows][cols];
      for(int i = 0; i < data.length; i++){
        for(int j = 0; j < data[i].length; j++){
          data[i][j] = '_';
        }
      }
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      /*wordsToAdd = in.toUpperCase().split("\n");*/
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
      for(int i = 0; i < this.wordsAdded.size(); i++){
        if(i ==  this.wordsAdded.size() - 1){
          words = words + this.wordsAdded.get(i);
        }
        else{
          words = words + this.wordsAdded.get(i) + ",";
        }
      }
      template = template + "\n Words: " + words;
      return template;
    }

    private boolean addWord( String word, int r, int c, int rowIncrement, int colIncrement){
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



    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
     public boolean addWordHorizontal(String word,int row, int col){
       boolean overlapswell = true;
       if(this.data[row].length - col >= word.length()){
         for(int j = 0; j < word.length(); j++){
           if(this.data[row][col + j] != word.charAt(j) && this.data[row][col + j] != '_'){
             overlapswell = false;
           }
         }
         if(overlapswell){
           for(int i = 0; i < word.length(); i++){
             this.data[row][col + i] = word.charAt(i);
           }
         }
         return overlapswell;
       }
       return false;
     }

/**Attempts to add a given word to the specified position of the WordGrid.
  *The word is added from top to bottom, must fit on the WordGrid, and must
  *have a corresponding letter to match any letters that it overlaps.
  *
  *@param word is any text to be added to the word grid.
  *@param row is the vertical locaiton of where you want the word to start.
  *@param col is the horizontal location of where you want the word to start.
  *@return true when the word is added successfully. When the word doesn't fit,
  *or there are overlapping letters that do not match, then false is returned.
  *and the board is NOT modified.
  */
  public boolean addWordVertical(String word,int row, int col){
   boolean overlapswell = true;
   if(this.data.length - row >= word.length()){
     for(int j = 0; j < word.length(); j++){
       if(this.data[row + j][col] != word.charAt(j) && this.data[row + j][col] != '_'){
         overlapswell = false;
       }
     }
     if(overlapswell){
       for(int i = 0; i < word.length(); i++){
         if(this.data[row + i][col] == '_'){
           this.data[row + i][col] = word.charAt(i);
         }
         if(this.data[row + i][col] != word.charAt(i)){
           overlapswell = false;
         }
       }
       return overlapswell;
     }
   }
   return false;
 }
 public boolean addWordDiagonal(String word, int row, int col){
   boolean overlapswell = true;
   if(this.data[row].length - col >= word.length() && this.data.length - row >= word.length()){
     for(int i = 0; i < word.length(); i++){
       if(this.data[row + i][col + i] != word.charAt(i) && this.data[row + i][col + i] != '_'){
         overlapswell = false;
       }
     }
     if(overlapswell){
       for(int j = 0; j < word.length(); j++){
         this.data[row + j][col + j] = word.charAt(j);
       }
     }
   }
   return overlapswell;
 }
}
