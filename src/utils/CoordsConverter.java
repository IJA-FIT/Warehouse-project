package src.utils;

public class CoordsConverter {

    public String convertCoords(int x, int y) {
        String coords = Integer.toString(x) + "." + Integer.toString(y);
        return coords;
    }

}