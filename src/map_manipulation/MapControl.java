/**
* <h1>Map Control</h1>
* Map Control zajišťuje operace nad načtenou mapou skladu.
*
* @author Vojtěch Fiala <xfiala61>
*/

package src.map_manipulation;

/**
 * <h2>MapControl zajišťuje operace nad načtenou mapou skladu.</h2>
 */
public class MapControl {

    private static LoadMapFile load_map = new LoadMapFile();
    private static int map[][];

    /**
    * <h2>MapControl Inicializace</h2>
    * Získej načtenou mapu pro další zpracovávání.
    */
    public MapControl() {
        this.map = load_map.getMap();
    }

    /**
    * <h2>setItem</h2>
    * Nastaví daný prvek v mapě na danou hodnotu.
    * @param x Souřadnice X prvku, který se má nastavit. 
    * @param y Souřadnice Y prvku, který se má nastavit.
    * @param new_val Hodnota, na jakou se má prvek nastavit.
    */
    public void setItem(int x, int y, int new_val) {
        this.map[x][y] = new_val;
    }


    /**
    * <h2>getItem</h2>
    * Získá hodnotu daného prvku.
    * @param x Souřadnice X prvku, jehož hodnota se má získat. 
    * @param y Souřadnice Y prvku, jehož hodnota se má získat.
    * @return Hodnota daného prvku.
    */
    public int getItem(int x, int y) {
        return this.map[x][y];
    }

    /**
    * <h2>insertObstacles</h2>
    * Vloží překážku na dané místo v mapě.
    * @param x Souřadnice X prvku, kam se má překážka vložit. 
    * @param y Souřadnice Y prvku, kam se má překážka vložit.
    */
    public void insertObstacle(int x, int y) {
        if (this.map[x][y] == 1) {
            this.map[x][y] = 3;
        }
        else {
            throw new ArithmeticException("Zde nelze prekazku umistit!");
        }
    }

    /**
    * <h2>removeObstacles</h2>
    * Odstraní překážku z daného místa v mapě.
    * @param x Souřadnice X prvku, odkud se má překážka odstranit. 
    * @param y Souřadnice Y prvku, odkud se má překážka odstranit.
    */
    public void removeObstacle(int x, int y) {
        this.map[x][y] = 1;
    }

    /**
    * <h2>getMap</h2>
    * Vrátí načtenou mapu skladu.
    * @return Mapa skladu reprezentovaná jako 2D array prvků typu integer.
    */
    public int[][] getMap() {
        return this.map;
    }

    /**
    * <h2>exists</h2>
    * Zjistí, jestli dané souřadnice náleží do mapy.
    * @param x Souřadnice X místa, které chceme vědět, jestli se v mapě nachází.
    * @param y Souřadnice Y místa, které chceme vědět, jestli se v mapě nachází.
    * @return True/False v závislosti na validitě souřadnic.
    */
    public boolean exists(int x, int y) {
        if (x < this.load_map.getRows() && y < this.load_map.getCols()) {
            return true;
        }
        return false;
    }

}