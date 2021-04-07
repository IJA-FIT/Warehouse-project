package src.map_manipulation;

public class Map {

    private LoadMapFile load_map = new LoadMapFile();
    private static int map[][];

    public Map() {
        this.map = load_map.getMap();
    }

    public void printLayout() {
        int rows = this.load_map.getRows();
        int cols = this.load_map.getCols();
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                System.out.printf("%d", this.map[i][k]);
            }
            System.out.printf("\n");
        }
    }

    public void setItem(int i, int k, int x) {
        this.map[i][k] = x;
    }

    public int getItem(int i, int k) {
        return this.map[i][k];
    }

    public void insertObstacle(int i, int k) {
        if (this.map[i][k] == 1) {
            this.map[i][k] = 3;
        }
        else {
            throw new ArithmeticException("Zde nelze prekazku umistit!");
        }
    }

    public void removeObstacle(int i, int k) {
        this.map[i][k] = 1;
    }

}