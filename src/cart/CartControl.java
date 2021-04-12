/**
* <h1>Cart Control</h1>
* Cart Content pokrývá funkce sloužící pro pohyb
* s vozíkem a hledání ideální cesty.
*
* @author Vojtěch Fiala <xfiala61>
*/

package src.cart;

import src.map_manipulation.MapControl;
import src.utils.CoordsConverter;

/**
 * <h2>CartControl je třída pracující nad operacemi s pohybem vozíku</h2>
 */
public class CartControl {

    private int position_x;
    private int position_y;   
    private int start_x;
    private int start_y;
    private int last_position_orig;
    private static MapControl map = new MapControl();
    private CoordsConverter cnv = new CoordsConverter();

    /**
    * <h2>CartControl Inicializace</h2>
    * Nastaví atributy, umístí na mapu vozík a zazálohuje
    * originální hodnotu v mapě na místě vozíku.
    * @param beginning Počáteční poloha v "zakódované" String formě
    */
    public CartControl(String beginning) {
        int[] pair = cnv.coordsInt(beginning);
        this.position_x = pair[0];
        this.position_y = pair[1];
        this.start_x = this.position_x;
        this.start_y = this.position_y;
        this.last_position_orig = map.getItem(this.position_x, this.position_y);
        if (this.last_position_orig == 0 || this.last_position_orig == 1) {
            map.setItem(this.position_x, this.position_y, 7);
        }
        else {
            throw new ArithmeticException("Na tuto pozici nelze vozik umistit!");
        }
    }

    /**
    * <h2>gotoPosition</h2>
    * Metoda přesune vozík na určenou pozici a zazálohuje původní hodnotu místa.
    * @param x Poloha X
    * @param y Poloha Y
    */
    public void gotoPosition(int x, int y) {
        map.setItem(this.position_x, this.position_y, this.last_position_orig);
        this.position_x = x;
        this.position_y = y;
        this.start_x = this.position_x;
        this.start_y = this.position_y;
        this.cartMoveCheck();
    }

    private void cartMoveCheck() {
        if (this.last_position_orig == 0 || this.last_position_orig == 1) {
            this.last_position_orig = map.getItem(this.position_x, this.position_y);
            map.setItem(this.position_x, this.position_y, 7);
        }
        else {
            throw new ArithmeticException("Na tuto pozici nelze vozik umistit!");
        }
    }

    /**
    * <h2>findPath</h2>
    * Metoda najde nejkratší cestu mezi začátkem a destinací.
    * @param dst_x Souřadnice X cíle, k němuž nejkratší cestu chceme.
    * @param dst_y Souřadnice Y cíle, k němuž nejkratší cestu chceme.
    */
    public void findPath(int dst_x, int dst_y) {
        PathFinder dk = new PathFinder(this.map.getMap(), this.start_x, this.start_y, dst_x, dst_y);
        dk.Dijkstra();
    }
}
