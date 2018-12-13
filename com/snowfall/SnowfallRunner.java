package com.snowfall;

import com.snowfall.model.Grid;

import java.util.ArrayList;
import java.util.List;

public class SnowfallRunner {
  private Grid mGrid;
  
  /**
  * SnowfallRunner's constructor method
  * @param grid A Grid object that allows us to interact with the game's logic
  */
  public SnowfallRunner(Grid grid) {
    mGrid = grid;
  }
  
  /**
  * The main menu runner for SnowfallRunner, allowing the game to be played
  */
  public void run() {
    mGrid.runGameTick();
    mGrid.runGameTick();
    mGrid.runGameTick();
    
    List<List<String>> gridInfo = mGrid.getGridInfo();
    gridInfo.forEach(System.out::println);
  }
  
}