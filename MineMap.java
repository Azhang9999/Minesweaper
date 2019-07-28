public interface MineMap {
    boolean move(int x, int y);
    int[][] getDisplayedMap();
    boolean isGameWon();
    int getX();
    int getY();
}
