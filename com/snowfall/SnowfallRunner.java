package com.snowfall;

import com.snowfall.model.Grid;

import java.lang.Math;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SnowfallRunner {
  private Grid mGrid;
  private BufferedReader mReader;

  private int mMaxSnowflakes;
  
  /**
  * SnowfallRunner's constructor method
  * @param grid A Grid object that allows us to interact with the game's logic
  */
  public SnowfallRunner(Grid grid) {
    mReader = new BufferedReader(new InputStreamReader(System.in));
    mGrid = grid;
    mMaxSnowflakes = 1;
  }

  /**
  * Determines if the user was hit. If so, display hit message
  * @return boolean True is the user was hit
  */
  private boolean collisionHandler() {
    boolean hit = mGrid.playerHit();
    if (hit) {
      System.out.println("Player down!");
      System.out.printf("Your high score %d!%n", mGrid.getHighScore());
      stopExecution(2000);
      return true;
    }
    return false;
  }
  
  /**
  * Uses a grid based system to create the objects that the user sees
  * on the screen.
  * It then prints out each row, one by one for the user
  * @param grid A Grid object that constains row and columns data
  */
  private void displayGame(List<List<String>> grid, List<String> player) {
    // Print obstacles
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
  * Determines how many obsticles should run in a gametick and apply it
  */
  private void gameTickHandler() {
    // Get a random number of snowflakes to fall
    int randomNum = (int) Math.floor(Math.random() * (mMaxSnowflakes + 1));
    mGrid.runGameTick(randomNum);
  }

  /**
  * Gets the user's desired movements and moves accordingly if applicable
  */
  private void movementHandler() {
    try {
      String direction = usersDirection();
      switch(direction) {
        case "a":
          mGrid.movePlayerLeft();
          break;
        case "d":
          mGrid.movePlayerRight();
          break;
        default:
          // Don't move
      }
    } catch(IOException ioe) {
      System.out.println("Problem with input");
      ioe.printStackTrace();
    }
  }
  
  /**
  * The main menu runner for SnowfallRunner, allowing the game to be played
  */
  public void run() {
    while(true) {
      gameTickHandler();
      
      // Get grid and player locations
      List<List<String>> gridInfo = mGrid.getGridInfo();
      List<String> playerInfo = mGrid.getPlayerInfo();
      displayGame(gridInfo, playerInfo);

      // If applicable, move player
      movementHandler();
      if (collisionHandler()) {
        break;
      };
    
      // Increase the HighScore with each succesful move
      mGrid.incrementHighScore();

      // Temporary Diffuculty scalling
      if (mGrid.getHighScore() > 45) {
        mMaxSnowflakes = 9;
      } else if (mGrid.getHighScore() > 35) {
        mMaxSnowflakes = 7;
      } else if (mGrid.getHighScore() > 30) {
        mMaxSnowflakes = 4;
      } else if (mGrid.getHighScore() > 25) {
        mMaxSnowflakes = 3;
      } else if (mGrid.getHighScore() > 10) {
        mMaxSnowflakes = 2;
      }

      // Clears the screen on non windows
      System.out.print("\033[H\033[2J");
      
    }
  }
  
  /**
  * Stops the program's execution for a specific period of time
  * @param miliseconds The number of miliseconds to pause
  */
  private void stopExecution(int miliseconds) {
    // Temporary, wait a little while to cascade the next Snowflake
    try
    {
        Thread.sleep(miliseconds);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
  }

  /**
  * Asks the user which direction they want to go
  * @return A String stating the direction or anything the user inputed
  */
  private String usersDirection() throws IOException {
    System.out.print("Enter the direction you want to go:  ");
    String direction = mReader.readLine();
    return direction;
  }
  
}