import java.util.Random;
/**
 * a class that contains everything about a minefield, including a constructor and a reveal/movement method
 */

 //TODO: create a movement method
public class Minefield{
    int [][] map;
    boolean [][] displayed;

    public Minefield(int sizeX, int sizeY, int initialX, int initialY) {
        map = new int[sizeX][sizeY];
        map[initialX][initialY] = 0;
        Random rng = new Random();
        int numberOfMines  = rng.nextInt(sizeX * sizeY);
        // Generate the position of the mines
        for (int i = 0; i < numberOfMines; i++) {
            int randomX = rng.nextInt(sizeX);
            int randomY = rng.nextInt(sizeY);
            while (randomX == initialX && randomY == initialY) {
                randomX = rng.nextInt(sizeX);
                randomY = rng.nextInt(sizeY);
            }
            map[randomX][randomY] = -1;
        }
        // Generate the number displayed
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (map[i][j] >= 0) {
                    int mineCounter = 0;
                    for (int a = -1; a < 2; a++) {
                        for (int b = -1; b < 2; b++) {
                            try{
                                if (map[i + a][j + b] < 0) {
                                    mineCounter++;
                                }
                            } catch (ArrayIndexOutOfBoundsException e){}
                        }
                    }
                    map[i][j] = mineCounter;
                }
            }
        }
        //figure out what to display
        figureOutDisplayed(initialX, initialY);
    }

    /**
     * a recursive helper method that displays bordering areas that are not covered by mines
     * should be called every time a coordinate is revealed
     * changes displayed value to true if the value is not a mine
     * @param x x coordinate of the point to be checked
     * @param y y coordinate of the point to be checked
     */
    private void figureOutDisplayed(int x, int y) {
        if (map[x][y] < 0) {
            return;
        }
        displayed[x][y] = true;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!displayed[i + x][j + y] && map[i + x][j + y] >= 0) {
                    try {
                        figureOutDisplayed(i + x, j + y);
                    } catch (ArrayIndexOutOfBoundsException e) {}
                }
            }
        }
    }
}