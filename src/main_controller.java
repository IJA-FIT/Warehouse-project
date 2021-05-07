/**
* main_controller
* main_controller spravuje akce co se stanou v gui
* @author Vojtěch Bubela <xbubel08>
*/


import javafx.fxml.FXML;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import src.shelf_manipulation.store.*;
import src.shelf_manipulation.goods.*;
import src.cart.*;
import src.utils.*;
import src.map_manipulation.MapControl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;


public class main_controller {

    @FXML
    private AnchorPane main_content;


    @FXML
    private GridPane main_grid;

    private Timer timer;
    private LocalTime time = LocalTime.now();

    // start of the main
    private WaitList wait_list = new WaitList();
    private CoordsConverter cnv = new CoordsConverter();
    private HashIndexFinder fnd = new HashIndexFinder();
    private MapControl map = new MapControl();
    private RandomNumberGenerator rng = new RandomNumberGenerator();

    private CartControl cart;
    private CartControl cart2;

    private String[] end_points = {"0.23", "0.22"};
    private int rand_min = 0;
    private int rand_max = end_points.length;   // Tady by logicky melo byt -1, ale tohle funguje lip

    private boolean cart1_flag = false;
    private boolean cart2_flag = false;

    private int[] pos;

    private ShelfManipulator regal;
    

    @FXML
    private void on_zoom(ScrollEvent event) {
        event.consume();
        double zoom_level = event.getDeltaY();

        if(zoom_level == 0) {
            zoom_level = 1;
        } else if(zoom_level < 0){
            zoom_level = 0.9;
        } else {
            zoom_level = 1.1;
        }

        main_content.setScaleX(zoom_level *main_content.getScaleX());
        main_content.setScaleY(zoom_level *main_content.getScaleY());
    }

    public void count_time() {
        timer = new Timer(false);
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
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

                                int res = rng.getRandomNumber(rand_min, rand_max);
                                int[] exit_point = cnv.coordsInt(end_points[res]);

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

                                int res = rng.getRandomNumber(rand_min, rand_max);
                                int[] exit_point = cnv.coordsInt(end_points[res]);

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
                TimeUnit.MILLISECONDS.sleep(150);
            }
            // Nevim proc tu ten catch je, ale bez nej to nejde
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            // Vytiskni updatnutou mapu
            // System.out.printf("************\n");
            // mpp.printMap(map.getMap());

            Platform.runLater(() -> {
                main_grid.getChildren().clear();
                init_gui(map.getMap());
            });

            }
        }, 0, 500);
    }

    public void init_gui(int[][] map) {
    
        for(int i = 0; i < map.length; i++) {
            for (int k = 0; k < map[0].length; k++) {
                if(map[i][k] == 5) {
                    Rectangle rec = new Rectangle(30,50);
                    main_grid.add(rec ,k,i);
                    GridPane.setHalignment(rec, HPos.CENTER);
                    GridPane.setValignment(rec, VPos.CENTER);
                } else if(map[i][k] == 9) {
                    // obrazek je prevzaty z:
                    // https://www.pinterest.com/pin/342203271663134343/
                    Image rec = new Image("/vydejni_misto.png");
                    ImageView new_image = new ImageView(rec);
                    new_image.setFitHeight(50);
                    new_image.setFitWidth(50);
                    main_grid.add(new_image ,k,i);
                } else if (map[i][k] == 0) {
                    // Rectangle rec = new Rectangle(30,50);
                    Image rec = new Image("/parking_slot.png");
                    ImageView new_image = new ImageView(rec);
                    new_image.setFitHeight(50);
                    new_image.setFitWidth(50);
                    main_grid.add(new_image ,k,i);
                } else if (map[i][k] == 7) {
                    // Rectangle rec = new Rectangle(30,50);
                    Image rec = new Image("/forklift.png");
                    ImageView new_image = new ImageView(rec);
                    new_image.setFitHeight(70);
                    new_image.setFitWidth(70);
                    main_grid.add(new_image ,k,i);
                }
            }
        }
    }

    public void main_loop_prepare() {
        

        int[][] map_orig = map.getOriginalMap();
        int[][] nm = new int[map_orig.length][map_orig[0].length];

        for (int i = 0; i < map_orig.length; i++) {
            for (int k = 0; k < map_orig[0].length; k++) {
                nm[i][k] = map_orig[i][k];
            }
        }

        map.mapSet(nm);
        
        // Vytvoreni voziku
        cart = new CartControl("11.23");
        cart2 = new CartControl("11.22");

        init_gui(nm);

        MapPrinter mpp = new MapPrinter();
        WarehouseLoader loader = new WarehouseLoader(); // naplneni skladu

        // Kontrolni regal, ktery je pouzit k pristupu k ostatnim
        regal = loader.controller;

        // Ziskej mapu skladu pro zobrazeni


        // Vytisk originalni mapy
        mpp.printMap(map.getMap());

        // ukoncuju jenom kdyz vozik "dojede", coz je jen kvuli testum


        


    }

}   