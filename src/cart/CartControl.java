package src.cart;

import src.map_manipulation.Map;

public class CartControl {

    int position_x;
    int position_y;   
    int start_x;
    int start_y;
    int last_position_orig;
    public static Map map = new Map();

    public CartControl(String beginning) {
        this.convertCoords(beginning);
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

    public void gotoPosition(int x, int y) {
        map.setItem(this.position_x, this.position_y, this.last_position_orig);
        this.position_x = x;
        this.position_y = y;
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

    private int getDot(String str) {
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                return i;
            }
        }
        return 0;
    }

    private void convertCoords(String coords) {
        int x = 0;
        int y = 0;
        if (coords.length() == 3) {   // cislo tecka cislo (5.5) == [5][5]
            x = Integer.parseInt(Character.toString(coords.charAt(0)));
            y = Integer.parseInt(Character.toString(coords.charAt(2)));
        }
        else if (coords.length() == 4) {
            int dot = this.getDot(coords);
            if (dot == 2) {
                String dat = Character.toString(coords.charAt(0)) + Character.toString(coords.charAt(1));
                x = Integer.parseInt(dat);
                y = Integer.parseInt(Character.toString(coords.charAt(3)));
            }
            else {
                String dat = Character.toString(coords.charAt(2)) + Character.toString(coords.charAt(3));
                y = Integer.parseInt(dat);
                x = Integer.parseInt(Character.toString(coords.charAt(0)));
            }
        }
        else if (coords.length() == 5) {
            String dat = Character.toString(coords.charAt(0)) + Character.toString(coords.charAt(1));
            String dat2 = Character.toString(coords.charAt(3)) + Character.toString(coords.charAt(4));
            x = Integer.parseInt(dat);
            y = Integer.parseInt(dat2);
        }
        this.position_x = x;
        this.position_y = y;
    }


}