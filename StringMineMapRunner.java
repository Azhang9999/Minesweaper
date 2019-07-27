public class StringMineMapRunner implements MineMapRunner {
    private MineMap map;

    public StringMineMapRunner(int x, int y, int iX, int iY) {
        map = new Minefield(x, y, iX, iY);
    }

    @Override
    public void display() {
        display(map);
    }

    public static void display(MineMap map) {
        int[][] toBeDisplayed = map.getDisplayedMap();
        System.out.print(" ");
        for (int i = 0; i < toBeDisplayed[0].length; i++) {
            System.out.print(i);
        }
        for (int x = 0; x < toBeDisplayed.length; x++) {
            System.out.print("\n" + x);
            for (int y = 0; y < toBeDisplayed[x].length; y++) {
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

    @Override
    public void nextStep() {
    }

    public static void main(String[] args) {
        MineMapRunner runner = new StringMineMapRunner(10,10, 4, 4);
        runner.display();
    }
}
