package src.utils;

public class MapPrinter {
    
    public void printMap(int[][] map) {
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