import com.snowfall.SnowfallRunner;
import com.snowfall.model.Grid;

public class Snowfall {
  
  public static void main(String[] args) {
    Grid grid = new Grid(10, 50);
    SnowfallRunner runner = new SnowfallRunner(grid);
  }
}