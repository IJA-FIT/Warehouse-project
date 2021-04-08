package src.cart;
import java.util.HashMap;
import src.utils.*;
import java.util.Arrays;

public class PathFinder {

    private int[][] map;
    private int start_x;
    private int start_y;
    private int dest_x;
    private int dest_y;
    private int rows;
    private int cols;
    private int[][] convert_map;
    public HashMap<String, Integer> distances = new HashMap<String, Integer>();
    public String[] closed = new String[1];
    public String[] open = new String[1];
    private Array arr = new Array();
    CoordsConverter coords_cnv = new CoordsConverter();
 
    public PathFinder(int[][] map, int start_x, int start_y, int dest_x, int dest_y) {
        this.map = map;
        this.start_x = start_x;
        this.start_y = start_y;
        this.dest_x = dest_x;
        this.dest_y = dest_y;
    }

    public String Dijkstra() {
        this.convertMap();

        // zpracuj zacatek
        if (this.isValidStart()) {
            String coords = this.coords_cnv.convertCoords(this.start_x, this.start_y);
            this.distances.put(coords, 0);
            this.closed[0] = coords;
            int remove = Arrays.asList(this.open).indexOf(coords);
            this.open = arr.remove(this.open, remove);
            //this.closed = arr.append(closed, coords);
            //Arrays.asList(yourArray).contains(yourValue) ispresent
            for (int i =0; i < this.open.length; i++) {
                System.out.printf("%s\n", this.open[i]);
            }
            while (this.open.length != 0) {

            }
        }


        MapPrinter mpprt = new MapPrinter();
        //mpprt.printMap(this.convert_map);
        return "neco";
    }

    private void convertMap() {
        this.rows = this.map.length;
        boolean flag = true;
        this.cols = this.map[0].length;
        this.convert_map = this.map;
        for (int i = 0; i < this.rows; i++) {
            for (int k = 0; k < this.cols; k++) {
                if (this.map[i][k] == 0 || this.map[i][k] == 1 || this.map[i][k] == 9) {
                    convert_map[i][k] = 0;
                    if (flag) {
                        this.open[0] = this.coords_cnv.convertCoords(i, k);
                    }
                    else {
                        String coords = this.coords_cnv.convertCoords(i, k);
                        this.open = arr.append(open, coords);
                    }
                    flag = false;
                }
                else if (this.map[i][k] == 7) {
                    if (flag) {
                        this.open[0] = this.coords_cnv.convertCoords(i, k);
                    }
                    else {
                        String coords = this.coords_cnv.convertCoords(i, k);
                        this.open = arr.append(open, coords);
                    }
                    flag = false;
                    convert_map[i][k] = 1;
                }
                else {
                    convert_map[i][k] = 9;
                }
            }
        }
    }
    private boolean isValidStart() {
        if (this.start_x < this.rows && this.start_x > 0 && this.start_y < this.cols && this.start_y > 0) {
            return (this.convert_map[this.start_x][this.start_y] == 1);
        }
        return false;
    }

    private boolean isValid(int x, int y) {
        if (x < this.rows && x >= 0 && y < this.cols && y >= 0) {
            return (this.convert_map[x][y] == 0);
        }
        return false;
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