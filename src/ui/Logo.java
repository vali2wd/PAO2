package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Logo {
    void showLogo(){
        System.out.println("Nafornita Adrian Valentin 243");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\University\\PAO\\PROIECT LABORATOR\\Proiect lab 2\\src\\resources\\LOGO"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(500); // wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
