package com.snowfall.model;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Grid {
  // Grid information
  private int mColumns;
  private List<List<String>> mGrid = new ArrayList<List<String>>();
  private List<String> mPlayer = new ArrayList<String>();
  private int mRows;
  
  // Grid shape info
  private String mEmptyChar = " ";
  private String mSnowflake = "*";
  private String mPlayerChar = "X";
  
  // Other info
  private int mHighScore = 0;

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
      mGrid.add(
        new ArrayList<String>(Collections.nCopies(mColumns, mEmptyChar))
      );
    }
    
    // Initializes the player at as close to the halfway mark as possible
    int midpoint = (int) Math.floor(mColumns / 2);
    for(int i =0; i < mColumns; i++) {
      if (i == midpoint) {
        mPlayer.add(mPlayerChar);
      } else {
        mPlayer.add(mEmptyChar);
      } 
    }
  }
  
  /**
  * Method that gets and returns all of the grid data in string/null format
  * @return A list of rows, with the status of each pixel in the column
  */
  public List<List<String>> getGridInfo() {
    return mGrid;
  }
  
  /**
  * Method that gets the HighScore
  * @return The HighScore
  */
  public int getHighScore() {
    return mHighScore;
  }
  
  /**
  * Method that gets and returns all of the player data in string/null format
  * @return Returns a list columns length wide
  * that contains the player's location
  */
  public List<String> getPlayerInfo() {
    return mPlayer;
  }
  
  /**
  * Increases the High Score by one
  */
  public void incrementHighScore() {
    mHighScore += 1;
  }

  /**
  * Moves the player one space to the left if not on the left bounds.
  * Otherwise the player does not move
  * @return True if the player moved
  */
  public boolean movePlayerLeft () {
    // If player at index 0 moving left would delete player
    int index = mPlayer.indexOf(mPlayerChar);
    if (index == 0) {
      return false;
    }
    
    mPlayer.add(mEmptyChar);
    mPlayer.remove(0);
    return true;
  }

  /**
  * Moves the player one space to the right if not on the left bounds.
  * Otherwise the player does not move
  * @return True if the player moved
  */
  public boolean movePlayerRight() {
    // If player at the last index moving right would delete player
    int index = mPlayer.indexOf(mPlayerChar);
    if (index == (mColumns - 1)) {
      return false;
    }
    mPlayer.add(0, mEmptyChar);
    mPlayer.remove(mPlayer.size() - 1);
    return true;
  }
  
  /**
  * Runs the game for one tick, cascading down the rows and deleting
  * the one closest to the ground
  * @return True if the user has been hit, else false
  */
  public boolean playerHit() {
    // The last row in mGrid is the one that hits the player
    List<String> grid = mGrid.get(mGrid.size() - 1);
    
    int index = mPlayer.indexOf(mPlayerChar);
    
    if (grid.get(index) != mEmptyChar) {
      return true;
    }
    return false;
  }
  
  /**
  * Runs the game for one tick, cascading down the rows and deleting
  * the one closest to the ground
  */
  public void runGameTick() {
    runGameTick(1);
  }

  /**
  * Runs the game for one tick, cascading down the rows and deleting
  * the one closest to the ground

  * Temporary, does not add all of the number. Just up to it
  * Some snowflakes can be overwritten

  * @param number The number of snowflake obstacles
  */
  public void runGameTick(int number) {
    // Initalize an empty ArrayList to replace the first mGrid element
    ArrayList<String> newList = new ArrayList<String>(
      Collections.nCopies(mColumns, mEmptyChar)
    );
    
    // If no snowflakes are added, ignore
    if (number > 0) {
      for (int i = 0; i < number; i++) {
        // Get random number to fill the grid
        Random rand = new Random(); 
        int value = rand.nextInt(mColumns);
        
        // Add snowflake to Grid
        newList.add(value, mSnowflake);
        newList.remove(value + 1);
      }
    }

    mGrid.add(0, newList);
    mGrid.remove( mGrid.size() -1 );
  }
   
  
}