package src.cart;

public class PathFinder {

    private int[][] map;
    private int start_x;
    private int start_y;
    private int dest_x;
    private int dest_y;
    private int[][] convert_map;
 
    public PathFinder(int[][] map, int start_x, int start_y, int dest_x, int dest_y) {
        this.map = map;
        this.start_x = start_x;
        this.start_y = start_y;
        this.dest_x = dest_x;
        this.dest_y = dest_y;
    }

    public String Dijkstra() {
        this.convertMap();
        this.printMap(this.convert_map);
        return "neco";
    }

    private void convertMap() {
        int rows = this.map.length;
        int cols = this.map[0].length;
        this.convert_map = this.map;
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                if (this.map[i][k] == 0 || this.map[i][k] == 1 || this.map[i][k] == 9) {
                    convert_map[i][k] = 0;
                }
                else {
                    convert_map[i][k] = 9;
                }
            }
        }
    }

    private void printMap(int[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                System.out.printf("%d", map[i][k]);
            }
            System.out.printf("\n");
        }
    }


}

/*
51155115599
51155115511
51155115511
51155115511
51155115511
11111111111
11111111111
51155115507
51155115570
*/