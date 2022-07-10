//imports
import java.util.*;

//
public class Queens {
  private int [][] map = new int[8][8];
  private int queenSpot = 0;
}

//intialize the 8x8 map to begin 
public Queens() {
  for(int i = 0; i < 8; i++){
    for(int j = 0; j < 8; j++){
      map[i][j] = 0;
    }
  }
}
  
//use Random from imports to make the map random 
public void randomMap(){
  Random randMap = new Random();
  
  //
  while(queenSpot < 8){
    for(int i = 0; i < 8; i++){
      map[randMap.nextInt(7)][i] = 1;
          queenSpot++;
        }
    }


  //calling heristic  here - create method later 
  heuristic = heuristic(map);
  }  

//herisic: lookinf for all possible solutions and stoping when one is found 
//lets find conlficts in: columns rows and diagnolally same code -
public boolean findRowConf(int[][] test, int x){ //double array of integers called test 
  boolean confExist = false;
  int count = 0;

  //for loop to go through the rows to increment count if conflict is found
  for(int i = 0; i < 8; i++){
    if(test[i][x] == 1){
      count++;
    }
  }

  if(count > 1){
    confExist = true;
  }
  return confExist;
}

public boolean findColConf(int[][] test, int y){ //double array of integers called test 
  boolean confExist = false;
  int count = 0;

  //for loop to go through the columns to increment count if conflict is found
  for(int i = 0; i < 8; i++){
    if(test[i][y] == 1){
      count++;
    }
  }

  if(count > 1){
    confExist = true;
  }
  return confExist;
}

public boolean findDiagConf(int[][] test, int m, int n){ //double array of integers called test now with two new ints 
  boolean diagConfExist = false;

  //for loop to go through diagnolaly increment count if conflict is found
  for(int i = 1; i < 8; i++){
    if(diagConfExist){
      break;
    }
    if((m+ i < 8) &&(n+i <8)){
      if(test[m+i][n+i]==1){
        diagConfExist = true;
      }
    }
    if((m+i < 8) &&(n-i <8)){
      if(test[m+i][n-i]==1){
        diagConfExist = true;
      }
    }
    if((m-i < 8) &&(n+i <8)){
      if(test[m-i][n+i]==1){
        diagConfExist = true;
      }
    }
    if((m-i < 8) &&(n-i <8)){
      if(test[m-i][n-i]==1){
        diagConfExist = true;
      }
    }
  }
  return diagConfExist;
}

//heuristic method to count the number of conflicts
public int heuristic(int [][] test){ 
  int count = 0;
  boolean rowConf;
  boolean colConf;
  boolean diagConf;
  }


}
