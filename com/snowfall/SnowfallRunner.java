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
  * Uses a grid based system to create the objects that the user sees
  * on the screen.
  * It then prints out each row, one by one for the user
  * @param grid A Grid object that constains row and columns data
  */
  private void displayGame(List<List<String>> grid, List<String> player) {
    // Print onstacles
    for (int i = 0; i < grid.size(); i++) {
      String row = String.join("", grid.get(i));
      System.out.printf("%s%n", formatRow(row));
    }
    // Print player
    String playerRow = String.join("", player);
    System.out.printf("%s%n", formatRow(playerRow));
  }
  
  /**
  * Formats a row to have a | on the left and right |
  * @param row The row to be formatted
  * @return The formatted row
  */
  private String formatRow(String row) {
    return "|" + row + "|";
  }
  
  /**
  * The main menu runner for SnowfallRunner, allowing the game to be played
  */
  public void run() {
    mGrid.runGameTick();
    mGrid.runGameTick();
    mGrid.runGameTick();
    
    // Get grid and player locations
    List<List<String>> gridInfo = mGrid.getGridInfo();
    List<String> playerInfo = mGrid.getPlayerInfo();

    displayGame(gridInfo, playerInfo);
  }
  
}