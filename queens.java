//imports to include random
import java.util.*;

public class Queens {
  private int[][] map = new int[8][8];
  private int[][] testMap = new int[8][8]; //used in move queen method
  private int queenSpot = 0;
  private int heuristic = 0;
  private int restarts = 0; //used in move queen method
  private int moves = 0; //used in move queen method
  private boolean newMap = true; //used in move queen method
  private int neighbors = 8;

  // intialize the 8x8 map to begin
  public Queens() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        map[i][j] = 0;
      }
    }
  }

  // use Random from imports to make the map random
  public void randomMap() {
    Random randMap = new Random();

    //
    while (queenSpot < 8) {
      for (int i = 0; i < 8; i++) {
        map[randMap.nextInt(7)][i] = 1;
        queenSpot++;
      }
    }

    // calling heristic here - create method later
    heuristic = heuristic(map);
  }

  // herisic: looking for all possible solutions and stoping when one is found
  // lets find conlficts in: columns rows and diagnolally same code - chekcing if
  // it is goal state
  // if not, evaluate all of the possible neighbor states by moving each column’s
  // queen through the rows of its column
  // and generating a heuristic value for each of those states.
  public boolean findRowConf(int[][] test, int x) { // double array of integers called test
    boolean confExist = false;
    int count = 0;

    // for loop to go through the rows to increment count if conflict is found
    for (int i = 0; i < 8; i++) {
      if (test[i][x] == 1) {
        count++;
      }
    }

    if (count > 1) {
      confExist = true;
    }
    return confExist;
  }

  public boolean findColConf(int[][] test, int y) { // double array of integers called test
    boolean confExist = false;
    int count = 0;

    // for loop to go through the columns to increment count if conflict is found
    for (int i = 0; i < 8; i++) {
      if (test[i][y] == 1) {
        count++;
      }
    }

    if (count > 1) {
      confExist = true;
    }
    return confExist;
  }

  public boolean findDiagConf(int[][] test, int m, int n) { // double array of integers called test now with two new
                                                            // ints
    boolean diagConfExist = false;

    // for loop to go through diagnolaly increment count if conflict is found
    for (int i = 1; i < 8; i++) {
      if (diagConfExist) {
        break;
      }
      if ((m + i < 8) && (n + i < 8)) {
        if (test[m + i][n + i] == 1) {
          diagConfExist = true;
        }
      }
      if ((m + i < 8) && (n - i >= 0)) {
        if (test[m + i][n - i] == 1) {
          diagConfExist = true;
        }
      }
      if ((m - i >= 0) && (n + i < 8)) {
        if (test[m - i][n + i] == 1) {
          diagConfExist = true;
        }
      }
      if ((m - i >= 0) && (n - i >=0 )) {
        if (test[m - i][n - i] == 1) {
          diagConfExist = true;
        }
      }
    }
    return diagConfExist;
  }

  // heuristic method to count the number of conflicts
  public int heuristic(int[][] test) {
    int count = 0;
    boolean rowConf;
    boolean colConf;
    boolean diagConf;
    
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        if(test[i][j] == 1){
          rowConf = findRowConf(test, j);
          colConf = findColConf(test, i);
          diagConf = findDiagConf(test, i, j);
          
          if(rowConf || colConf || diagConf){ //if rows columns or diagonally have conflicts increment the count
            count++;
          }
        }
      }
    }

    return count;
  }
  
  //move a queen to know wehter to continue to new state/restart/or it is the solution
  public void moveQueen( ){ 
    int[][] hArr = new int[8][8];
    int colCount;
    int minCol;
    int minRow;
    int prevColQueen = 0;
    newMap = false;
    
    while(true){
    colCount = 0;
    
    for(int i = 0; i < 8; i++){
    System.arraycopy(map[i], 0, testMap[i], 0, 8);
    }

    while(colCount < 8){
      for(int i = 0; i < 8;i++){
        testMap[i][colCount] = 0;
    }

    for(int i = 0; i < 8; i++){
      if(map[i][colCount] == 1){
        prevColQueen = i;
    }
    
    testMap[i][colCount] = 1;
    hArr[i][colCount] = heuristic(testMap);
    testMap[i][colCount] = 0;
    
  }

    testMap[prevColQueen][colCount] = 1;
    colCount++;
    
  }

//determine if restart  is necessary
  if(necRestart(hArr)){ 
    queenSpot = 0;
   
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        map[i][j] = 0;
      }
    }

    randomMap( );
    System.out.println("RESTART");
    restarts++;
    }
    
    minCol = findMinCol(hArr);
    minRow = findMinRow(hArr);
    
    for(int i = 0; i < 8; i++){
      map[i][minCol] = 0;
    }
    
    map[minRow][minCol] = 1;
    moves++;
    heuristic = heuristic(map);
    
    if(heuristic(map) == 0){
    System.out.println("\nCurrent State");
    
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        System.out.print(map[i][j] + " ");
      }
      System.out.print("\n");
    }
    System.out.println("Solution Found!");
    System.out.println("State changes: " + moves);
    System.out.println("Restarts: " + restarts);
    break;
  }
    
    System.out.println("\n");
    System.out.println("Current h: " + heuristic);
    System.out.println("Current State");
    
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        System.out.print(map[i][j] + " ");
      }
      System.out.print("\n");
    }
    
    System.out.println("Neighbors found with lower h: " + neighbors);
    System.out.println("Setting new current State");
    }
  }

//If a better state was not found, then we have 
//reached the local minima and must perform a random restart.  If a better (lower heuristic) state was found, then that 
//state becomes the current state and the above process is repeated on that state. 


//method that finds local minima of nerhbords for columns
public int findMinCol(int[][] test){ 
  int minCol = 8;
  int minVal = 8;
  int count = 0;
  
  for(int i = 0; i < 8; i++){
    for(int j = 0; j < 8; j++){
      if(test[i][j] < minVal){
        minVal = test[i][j];
        minCol = j;
        }
        if(test[i][j] < heuristic){
          count++;
        }
      }   
    }
    
    neighbors = count;
    return minCol;
  }

//method that finds local minima of nerhbords for rows
public int findMinRow(int[][] test){ 
  int minRow = 8;
  int minVal = 8;
  
  for(int i = 0; i < 8; i++){
    for(int j = 0; j < 8; j++){
      if(test[i][j] < minVal){
        minVal = test[i][j];
        minRow = i;
      }
    }
  }
  return minRow;
}

//create method in order to detmerine if restart is needed 
public boolean necRestart(int [][] test){
  int minVal = 8;
  boolean restart = false;
  
  for(int i = 0; i < 8; i++){
    for(int j = 0; j < 8; j++){
      if(test[i][j] < minVal){
        minVal = test[i][j];
      }
    }
  }
  
  if(neighbors == 0){
    restart = true;
  }
  return restart;
}

  
//added main here
//main method to run the program
public static void main(String[] args) {
  Queens test = new Queens();
  test.randomMap();
  test.moveQueen();
  }
}
