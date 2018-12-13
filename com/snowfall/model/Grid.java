package com.snowfall.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Grid {
  // Grid information
  private int mColumns;
  private List<List<String>> mGridInfo = new ArrayList<List<String>>();
  private int mRows;
  
  // Grid shape info
  private String mSnowflake = "*";

  /**
  * Grid's constructor method
  * @param rows The number of rows the grid contains
  * @param columns The number of rows the grid contains
  */
  public Grid(int rows, int columns) {
    mRows = rows;
    mColumns = columns;
    
    // Creates a grid system based on the given rows and columns
    for(int i = 0; i < mRows; i++) {
      mGridInfo.add(
        new ArrayList<String>(Collections.nCopies(mColumns, null))
      );
    }
    
  }
  
  /**
  * Method that gets and returns all of the grid data in string/null format
  * @return A list of rows, with the status of each pixel in the column
  */
  public List<List<String>> getGridInfo() {
    return mGridInfo;
  }
  
  /**
  * Runs the game for one tick, cascading down the rows and deleting
  * the one closest to the ground
  * @return A list of rows, with the status of each pixel in the column
  */
  public void runGameTick() {
    // Initalize an empty ArrayList to replace the first mGridInfo element
    ArrayList<String> newList = new ArrayList<String>(
      Collections.nCopies(mColumns, null)
    );
    
    // Get random number to fill the grid
    Random rand = new Random(); 
    int value = rand.nextInt(mColumns);
    
    // Add snowflake to Grid
    newList.add(value, mSnowflake);
    newList.remove(value + 1);
    
    mGridInfo.add(0, newList);
    mGridInfo.remove( mGridInfo.size() -1 );
  }
   
  
}