import java.util.Random;

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
                            if (map[a][b] < 0) {
                                mineCounter++;
                            }
                        }
                    }
                    map[i][j] = mineCounter;
                }
            }
        }
        //figure out what to display
        figureOutDisplayed(initialX, initialY);
    }

    private void figureOutDisplayed(int x, int y) {
        displayed[x][y] = true;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!displayed[i + x][j + y] && map[i + x][j + y] >= 0) {
                    figureOutDisplayed(i + x, j + y);
                }
            }
        }
    }

}