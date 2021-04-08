package src.utils;

public class CoordsConverter {

    public String convertCoords(int x, int y) {
        String coords = Integer.toString(x) + "." + Integer.toString(y);
        return coords;
    }

    public int[] coordsInt(String coords) {
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
        int[] ret = {x, y};
        return ret;
    }

    private int getDot(String str) {
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                return i;
            }
        }
        return 0;
    }

}