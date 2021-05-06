/**
 * Demonstrační třída sloužící k ukázce současné funkcionality programu.
 *
 * @author Vojtěch Fiala <xfiala61>
 */


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import src.shelf_manipulation.store.*;
import src.shelf_manipulation.goods.*;
import src.cart.*;
import src.utils.*;
import src.map_manipulation.MapControl;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

/**
 * Demonstrační třída sloužící k ukázce současné funkcionality programu.
 */
public class Main {

    public static void main(String[] args) {
        WaitList wait_list = new WaitList();
        CoordsConverter cnv = new CoordsConverter();
        HashIndexFinder fnd = new HashIndexFinder();
        MapControl map = new MapControl();
        int[][] map_orig = map.getOriginalMap();
        int[][] nm = new int[map_orig.length][map_orig[0].length];
        
        for (int i = 0; i < map_orig.length; i++) {
                for (int k = 0; k < map_orig[0].length; k++) {
                    nm[i][k] = map_orig[i][k];
                }
        }

        map.mapSet(nm);
        MapPrinter mpp = new MapPrinter();


        // Vytvoreni typu zoozi
        Goods goods1 = new StoreGoods("Kuchynsky stul");
        Goods goods2 = new StoreGoods("Zidle");
        Goods goods3 = new StoreGoods("Skrin");
        Goods goods4 = new StoreGoods("Pohovka");
        Goods goods5 = new StoreGoods("Kreslo");
        Goods goods6 = new StoreGoods("Konferencni stul");
        Goods goods7 = new StoreGoods("Komoda");
        Goods goods8 = new StoreGoods("Postel");

        // Vytvoreni regalu s danym typem zbozi
        GoodsShelf shelf = new StoreShelf(goods1);
        GoodsShelf shelf2 = new StoreShelf(goods2);
        GoodsShelf shelf3 = new StoreShelf(goods3);
        GoodsShelf shelf4 = new StoreShelf(goods4);
        GoodsShelf shelf5 = new StoreShelf(goods5);
        GoodsShelf shelf6 = new StoreShelf(goods6);
        GoodsShelf shelf7 = new StoreShelf(goods7);
        GoodsShelf shelf8 = new StoreShelf(goods8);

        // Vytvoreni noveho kusu zbozi
        GoodsItem itm11 = new StoreGoodsItem(goods1, LocalDate.of(2021, 8, 5));
        GoodsItem itm12 = new StoreGoodsItem(goods1, LocalDate.of(2021, 6, 6));
        GoodsItem itm21 = new StoreGoodsItem(goods2, LocalDate.of(2021, 12, 8));
        GoodsItem itm31 = new StoreGoodsItem(goods3, LocalDate.of(2021, 12, 6));
        GoodsItem itm41 = new StoreGoodsItem(goods4, LocalDate.of(2021, 11, 6));
        GoodsItem itm51 = new StoreGoodsItem(goods5, LocalDate.of(2021, 10, 6));
        GoodsItem itm61 = new StoreGoodsItem(goods6, LocalDate.of(2021, 9, 6));
        GoodsItem itm71 = new StoreGoodsItem(goods7, LocalDate.of(2021, 8, 6));
        GoodsItem itm81 = new StoreGoodsItem(goods8, LocalDate.of(2021, 7, 6));

        // Pridani kusu zbozi do typu
        goods1.addItem(itm11);
        goods1.addItem(itm12);
        goods2.addItem(itm21);
        goods3.addItem(itm31);
        goods4.addItem(itm41);
        goods5.addItem(itm51);
        goods6.addItem(itm61);
        goods7.addItem(itm71);
        goods8.addItem(itm81);

        // Pridani zbozi do regalu
        shelf.put(itm11);
        shelf.put(itm12);
        shelf2.put(itm21);
        shelf3.put(itm31);
        shelf4.put(itm41);
        shelf5.put(itm51);
        shelf6.put(itm61);
        shelf7.put(itm71);
        shelf8.put(itm81);

        // Vytvoreni voziku
        CartControl cart = new CartControl("8.30");
        CartControl cart2 = new CartControl("8.31");


        // Seznam regalu vcetne jejich pozice
        ShelfManipulator regal = new ShelfManipulator("0.0", shelf);
        ShelfManipulator regal2 = new ShelfManipulator("1.0", shelf2);
        ShelfManipulator regal3 = new ShelfManipulator("2.0", shelf3);
        ShelfManipulator regal4 = new ShelfManipulator("3.0", shelf4);
        ShelfManipulator regal5 = new ShelfManipulator("4.0", shelf5);
        ShelfManipulator regal6 = new ShelfManipulator("7.0", shelf6);
        ShelfManipulator regal7 = new ShelfManipulator("8.0", shelf7);
        ShelfManipulator regal8 = new ShelfManipulator("0.4", shelf8);

        // Najdi v seznamu regalu typ zbozi a vrat jeho pozici
        String key = fnd.getIndex(regal.shelf_map, "Kuchynsky stul");
        String key2 = fnd.getIndex(regal.shelf_map, "Zidle");
        String key3 = fnd.getIndex(regal.shelf_map, "Skrin");
        String key4 = fnd.getIndex(regal.shelf_map, "Pohovka");
        String key5 = fnd.getIndex(regal.shelf_map, "Kreslo");
        String key6 = fnd.getIndex(regal.shelf_map, "Konferencni stul");
        String key7 = fnd.getIndex(regal.shelf_map, "Komoda");
        String key8 = fnd.getIndex(regal.shelf_map, "Postel");

        // Ziskej mapu skladu pro zobrazeni
        int[] pos;

        // Pridani polozek do wait_listu (listu, co maji voziky dovest)
        wait_list.WaitList_Add("Pohovka");
        wait_list.WaitList_Add("Postel");
        wait_list.WaitList_Add("Kreslo");
        // Vytisk originalni mapy
        mpp.printMap(map.getMap());

        // ukoncuju jenom kdyz vozik "dojede", coz je jen kvuli testum
        boolean cart1_flag = false;
        boolean cart2_flag = false;
        while (true) {
            
            // Pokud bylo zadano, ze se ma nejake zbozi dovest
            if (wait_list.WaitList_Len() > 0) {
                // A zaroven je volny vozik
                if (cart.free_carts.size() > 0) {
                    // Tak vyber prvni ze seznamu volnych voziku, ktery tohle zbozi odveze
                    CartControl active = cart.free_carts.get(0);
                    cart.free_carts.remove(active); // odstran ho ze seznamu volnych
                    String dest = fnd.getDestination(regal.shelf_map, wait_list.WaitList[0]); // Ziskej souradnice, kam ma vozik dojet
                    int[] destination = cnv.coordsInt(dest);    // zkonvertuj je
                    active.current_path = active.findPath(destination[0], destination[1]);  // Spocitej pocatecni cestu
                    wait_list.WaitList_Remove_First();  // Smaz z wait_listu zbozi, co vozik zpracovava
                    active.cart_go4ware = true;
                }
            }

            // Tady bude nejspis funkce, ktera tohle zahrne a nebo 5x tentyz kod :)
            // Zkontroluj, jestli ma vozik vubec nekam jet a nebo je volny
            if (cart.current_path != null) {
                // Zkontroluj, ze cesta je delsi nez 0
                if (cart.current_path.length > 0) {

                    if (cart.cart_go4ware == true) {
                        if (cart1_flag == true) {
                            cart1_flag = false; // Jedno kolo cekani (nakladani)
                            cart.cart_loaded = true; // Je nalozeno, cesta do odberu
                            cart.cart_go4ware = false; // Uz nejede pro zbozi

                            int[] exit_point = cnv.coordsInt("0.30"); // Docasny hardcode cile (mozna random vybrat jeden z moznych?) (maybe permanent hardcode)
                            cart.current_path = cart.findPath(exit_point[0], exit_point[1]);    // cesta do cile
                        }
                        else {
                            pos = cnv.coordsInt(cart.current_path[cart.current_path.length-2]); // nova pozice je predposledni souradnice ze soucastne cesty
                            cart.gotoPosition(pos[0], pos[1]);  // Jdi na tu pozici (tzn. mam treba [0,1  1,1  2,1], kde 2,1 je aktualni -> Jdi na 1,1)
                            int[] target_refresh = cnv.coordsInt(cart.current_path[0]); // Nacti cil z puvodni cesty
                            cart.current_path = cart.findPath(target_refresh[0], target_refresh[1]);    // Spocitej novou cestu z nove polohy do puvodniho cile


                            System.out.println(Arrays.toString(cart.current_path)); // Testovaci vypis zbyvajici cesty
                            // Takhle se to chova, kdyz to dojede do cile -> Zustavaji v ceste 2 body
                            if (cart.current_path.length > 1) {
                                // Takze je porovnej, jestli jsou totozne a jestli jo, je vozik v cili a ukonci cyklus
                                if (cart.current_path[0].equals(cart.current_path[1])) {
                                    if (cart1_flag != true) {
                                        cart1_flag = true;
                                    }
                                }
                            }
                        }
                    }
                    else if (cart.cart_loaded == true) {
                        if (cart1_flag == true) {
                            cart1_flag = false;
                            cart.cart_loaded = false;   // vylozeno do vydeje
                            cart.cart_start = true;

                            int[] start = cart.getOriginalStart(); // Docasny hardcode cile (mozna random vybrat jeden z moznych?) (maybe permanent hardcode)
                            cart.current_path = cart.findPath(start[0], start[1]);    // cesta na start
                        }
                        else {
                            pos = cnv.coordsInt(cart.current_path[cart.current_path.length-2]); // nova pozice je predposledni souradnice ze soucastne cesty
                            cart.gotoPosition(pos[0], pos[1]);  // Jdi na tu pozici (tzn. mam treba [0,1  1,1  2,1], kde 2,1 je aktualni -> Jdi na 1,1)
                            int[] target_refresh = cnv.coordsInt(cart.current_path[0]); // Nacti cil z puvodni cesty
                            cart.current_path = cart.findPath(target_refresh[0], target_refresh[1]);    // Spocitej novou cestu z nove polohy do puvodniho cile


                            System.out.println(Arrays.toString(cart.current_path)); // Testovaci vypis zbyvajici cesty
                            // Takhle se to chova, kdyz to dojede do cile -> Zustavaji v ceste 2 body
                            if (cart.current_path.length > 1) {
                                // Takze je porovnej, jestli jsou totozne a jestli jo, je vozik v cili a ukonci cyklus
                                if (cart.current_path[0].equals(cart.current_path[1])) {
                                    if (cart1_flag != true) {
                                        cart1_flag = true;
                                    }
                                }
                            }
                        }
                    }
                    else if (cart.cart_start == true) {
                        pos = cnv.coordsInt(cart.current_path[cart.current_path.length-2]); // nova pozice je predposledni souradnice ze soucastne cesty
                        cart.gotoPosition(pos[0], pos[1]);  // Jdi na tu pozici (tzn. mam treba [0,1  1,1  2,1], kde 2,1 je aktualni -> Jdi na 1,1)
                        int[] target_refresh = cnv.coordsInt(cart.current_path[0]); // Nacti cil z puvodni cesty
                        cart.current_path = cart.findPath(target_refresh[0], target_refresh[1]);    // Spocitej novou cestu z nove polohy do puvodniho cile

                        System.out.println(Arrays.toString(cart.current_path)); // Testovaci vypis zbyvajici cesty
                        // Takhle se to chova, kdyz to dojede do cile -> Zustavaji v ceste 2 body
                        if (cart.current_path.length > 1) {
                            // Takze je porovnej, jestli jsou totozne a jestli jo, je vozik v cili a ukonci cyklus
                            if (cart.current_path[0].equals(cart.current_path[1])) {
                                cart.cart_start = false;
                                cart.free_carts.add(cart);
                                cart.current_path = null;
                            }
                        }
                    }
                }
            }

            if (cart2.current_path != null) {
                // Zkontroluj, ze cesta je delsi nez 0
                if (cart2.current_path.length > 0) {

                    if (cart2.cart_go4ware == true) {
                        if (cart2_flag == true) {
                            cart2_flag = false; // Jedno kolo cekani (nakladani)
                            cart2.cart_loaded = true; // Je nalozeno, cesta do odberu
                            cart2.cart_go4ware = false; // Uz nejede pro zbozi

                            int[] exit_point = cnv.coordsInt("0.31"); // Docasny hardcode cile (mozna random vybrat jeden z moznych?) (maybe permanent hardcode)
                            cart2.current_path = cart2.findPath(exit_point[0], exit_point[1]);    // cesta do cile
                        }
                        else {
                            pos = cnv.coordsInt(cart2.current_path[cart2.current_path.length-2]); // nova pozice je predposledni souradnice ze soucastne cesty
                            cart2.gotoPosition(pos[0], pos[1]);  // Jdi na tu pozici (tzn. mam treba [0,1  1,1  2,1], kde 2,1 je aktualni -> Jdi na 1,1)
                            int[] target_refresh = cnv.coordsInt(cart2.current_path[0]); // Nacti cil z puvodni cesty
                            cart2.current_path = cart2.findPath(target_refresh[0], target_refresh[1]);    // Spocitej novou cestu z nove polohy do puvodniho cile


                            System.out.println(Arrays.toString(cart2.current_path)); // Testovaci vypis zbyvajici cesty
                            // Takhle se to chova, kdyz to dojede do cile -> Zustavaji v ceste 2 body
                            if (cart2.current_path.length > 1) {
                                // Takze je porovnej, jestli jsou totozne a jestli jo, je vozik v cili a ukonci cyklus
                                if (cart2.current_path[0].equals(cart2.current_path[1])) {
                                    if (cart2_flag != true) {
                                        cart2_flag = true;
                                    }
                                }
                            }
                        }
                    }
                    else if (cart2.cart_loaded == true) {
                        if (cart2_flag == true) {
                            cart2_flag = false;
                            cart2.cart_loaded = false;   // vylozeno do vydeje
                            cart2.cart_start = true;

                            int[] start = cart2.getOriginalStart(); // Docasny hardcode cile (mozna random vybrat jeden z moznych?) (maybe permanent hardcode)
                            cart2.current_path = cart2.findPath(start[0], start[1]);    // cesta na start
                        }
                        else {
                            pos = cnv.coordsInt(cart2.current_path[cart2.current_path.length-2]); // nova pozice je predposledni souradnice ze soucastne cesty
                            cart2.gotoPosition(pos[0], pos[1]);  // Jdi na tu pozici (tzn. mam treba [0,1  1,1  2,1], kde 2,1 je aktualni -> Jdi na 1,1)
                            int[] target_refresh = cnv.coordsInt(cart2.current_path[0]); // Nacti cil z puvodni cesty
                            cart2.current_path = cart2.findPath(target_refresh[0], target_refresh[1]);    // Spocitej novou cestu z nove polohy do puvodniho cile


                            System.out.println(Arrays.toString(cart2.current_path)); // Testovaci vypis zbyvajici cesty
                            // Takhle se to chova, kdyz to dojede do cile -> Zustavaji v ceste 2 body
                            if (cart2.current_path.length > 1) {
                                // Takze je porovnej, jestli jsou totozne a jestli jo, je vozik v cili a ukonci cyklus
                                if (cart2.current_path[0].equals(cart2.current_path[1])) {
                                    if (cart2_flag != true) {
                                        cart2_flag = true;
                                    }
                                }
                            }
                        }
                    }
                    else if (cart2.cart_start == true) {
                        pos = cnv.coordsInt(cart2.current_path[cart2.current_path.length-2]); // nova pozice je predposledni souradnice ze soucastne cesty
                        cart2.gotoPosition(pos[0], pos[1]);  // Jdi na tu pozici (tzn. mam treba [0,1  1,1  2,1], kde 2,1 je aktualni -> Jdi na 1,1)
                        int[] target_refresh = cnv.coordsInt(cart2.current_path[0]); // Nacti cil z puvodni cesty
                        cart2.current_path = cart2.findPath(target_refresh[0], target_refresh[1]);    // Spocitej novou cestu z nove polohy do puvodniho cile

                        System.out.println(Arrays.toString(cart2.current_path)); // Testovaci vypis zbyvajici cesty
                        // Takhle se to chova, kdyz to dojede do cile -> Zustavaji v ceste 2 body
                        if (cart2.current_path.length > 1) {
                            // Takze je porovnej, jestli jsou totozne a jestli jo, je vozik v cili a ukonci cyklus
                            if (cart2.current_path[0].equals(cart2.current_path[1])) {
                                cart2.cart_start = false;
                                cart2.free_carts.add(cart2);
                                cart2.current_path = null;
                            }
                        }
                    }
                }
            }

            int[][] orig_map = map.getOriginalMap();
            int[][] new_map = new int[orig_map.length][orig_map[0].length];
            int[] cart1_pos = cart.getPosition();
            int[] cart2_pos = cart2.getPosition();

            for (int i = 0; i < orig_map.length; i++) {
                for (int k = 0; k < orig_map[0].length; k++) {
                    if ((cart1_pos[0] == i && cart1_pos[1] == k) || (cart2_pos[0] == i && cart2_pos[1] == k))
                        new_map[i][k] = 7;
                    else 
                        new_map[i][k] = orig_map[i][k];
                }
            }

            map.mapSet(new_map);


            // Provizorni ghetto verze zpozdeni v milisekundach
            try { 
                TimeUnit.MILLISECONDS.sleep(750);
            }
            // Nevim proc tu ten catch je, ale bez nej to nejde
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            // Vytiskni updatnutou mapu
            System.out.printf("************\n");
            mpp.printMap(map.getMap());

        }
    }
}
