/**
* load_from_file
* load_from_file nacita uvodni obsah skladu ze souboru
* 
* @author Vojtěch Bůbela <xbubel08>
*/

package utils;

import java.io.File;  
import java.io.FileNotFoundException;
import java.util.Scanner;

public class load_from_file {
    
    public load_from_file() {

        Scanner s = get_file();

        while(s.hasNextLine()) {
            String new_line = s.nextLine();
            parse_line(new_line);
        }
    }

    private Scanner get_file() {
        Scanner reader = null;
        File file = new File("./data/Warehouse_goods.txt");

        try {
            reader = new Scanner(file);

        } catch (FileNotFoundException e){
            System.out.println("Couldn't load file with goods.");
        }

        return reader;
    }

    private void parse_line(String line) {
        
    }
}
