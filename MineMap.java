public interface MineMap {
    public boolean move(int x, int y);
    public int[][] getDisplayedMap();
    public boolean isGameWon();
}
