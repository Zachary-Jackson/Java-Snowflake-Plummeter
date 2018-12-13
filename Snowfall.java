import com.snowfall.SnowfallRunner;
import com.snowfall.model.Grid;

public class Snowfall {
  
  /**
  * This is the main function, which makes use of the SnowfallRunner's main
  * run method in order to start the game
  */
  public static void main(String[] args) {
    Grid grid = new Grid(4, 10);
    SnowfallRunner runner = new SnowfallRunner(grid);
    
    // Start game
    runner.run();
  }
}