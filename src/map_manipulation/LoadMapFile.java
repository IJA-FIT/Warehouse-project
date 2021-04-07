package src.map_manipulation;
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner;

public class LoadMapFile {
    private int rows = 0;
    private int cols = -5;
    private int map[][];


    public LoadMapFile() {
        this.getMapData();
        this.loadMap();
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    private void rowValid(String data) {
        if (this.cols == -5) {
            this.cols = data.length();
        }
        else {
            if (data.length() != this.cols) {
                throw new ArithmeticException("Spatne zadana mapa skladu!");
            }
        }
    }

    private void getMapData() {
        try {
            File file = new File("./data/Warehouse.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this.rowValid(data);
                this.rows++;
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Couldn't load Warehouse map.");
        }
    }

    private void setMapSize() { 
        this.map = new int[this.rows][this.cols];
    }

    public int[][] getMap() {
        return this.map;
    }

    private void loadMap() {
        this.setMapSize();
        int current_row = 0;
        try {
            File file = new File("./data/Warehouse.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for (int i = 0; i < data.length(); i++) {
                    this.map[current_row][i] = Integer.parseInt(Character.toString(data.charAt(i)));
                }
                current_row++;
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Couldn't load Warehouse map.");
        }
    }
}