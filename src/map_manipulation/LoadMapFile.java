/**
* <h1>Load Map File</h1>
* Load Map File načte mapu skladu ze souboru Warehouse.txt 
* ve složce /data
*
* @author Vojtěch Fiala <xfiala61>
*/

package src.map_manipulation;
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner;

/**
 * <h2>LoadMapFile načte mapu skladu ze souboru Warehouse.txt 
 * ve složce /data</h2>
 */
public class LoadMapFile {
    private int rows = 0;
    private int cols = -5;
    private int map[][];

    /**
    * <h2>LoadMapFile Inicializace</h2>
    * Získej počet řádků a sloupců mapy a je-li validní, načti ji.
    */
    public LoadMapFile() {
        this.getMapData();
        this.loadMap();
    }

    /**
    * <h2>getRows</h2>
    * Vrací počet řádků v mapě.
    * @return Počet řádků v mapě.
    */
    public int getRows() {
        return this.rows;
    }

    /**
    * <h2>getCols</h2>
    * Vrací počet sloupců v mapě.
    * @return Počet sloupců v mapě.
    */
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

     /**
    * <h2>getMap</h2>
    * Vrací načtenou mapu.
    * @return Načtená mapa jako 2D array prvků typu integer.
    */
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