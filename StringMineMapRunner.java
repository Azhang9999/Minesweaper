import java.util.Scanner;

/**
 * A class that can be called to run the map to make it a game
 * @author Andrew Zhang
 */
public class StringMineMapRunner implements MineMapRunner {
    private MineMap map;

    public StringMineMapRunner(int x, int y, int iX, int iY) {
        map = new Minefield(x, y, iX, iY);
    }

    @Override
    public void display() {
        display(map);
    }

    /**
     * A static method that prints out the layout of the map for the
     * player as strings.
     * @param map The map that is used to display the current status
     */
    public static void display(MineMap map) {
        int[][] toBeDisplayed = map.getDisplayedMap();
        System.out.print("  ");
        for (int i = 0; i < toBeDisplayed[0].length; i++) {
            System.out.print(i + " ");
        }
        for (int x = 0; x < toBeDisplayed.length; x++) {
            System.out.print("\n" + x);
            for (int y = 0; y < toBeDisplayed[x].length; y++) {
                System.out.print("|");
                if (toBeDisplayed[x][y] == -10) {
                    System.out.print('?');
                } else {
                    System.out.print(toBeDisplayed[x][y]);
                }
            }
        }
        System.out.println();
        System.out.println();
    }

    /**
     * automatically takes care of moving the next step in by parsing
     * User input and placing the move on the map
     * @return whether the user has lost the game by moving there
     */
    @Override
    public boolean nextStep() {
        Scanner keyboard = new Scanner(System.in);
        int row, col;
        do {
            System.out.println("Enter the Row number: ");
            row = keyboard.nextInt();
        } while (!(row >= 0 && row < map.getX()));
        do {
            System.out.println("Enter the Column number: ");
            col = keyboard.nextInt();
        } while (!(col >= 0 && col < map.getY()));
        boolean notLost = map.move(row, col);
        return notLost;
    }

    public static void main(String[] args) {
        MineMapRunner runner = new StringMineMapRunner(10,10, 4, 4);
        runner.display();
        boolean notLost = true;
        while (!((StringMineMapRunner) runner).map.isGameWon() && notLost) {
            notLost = runner.nextStep();
            runner.display();
        }
        if (notLost) {
            System.out.println("YOU WON");
        } else {
            System.out.println("YOU LOST");
        }
    }
}
