

import entities.dao.AlienSchoolDao;
import repository.AlienChildCommandsRepository;
import entities.dao.AlienChildDao;
import repository.AlienSchoolCommandsRepository;
import ui.Menu;

import java.io.FileNotFoundException;


public class Main {
    static String CHILDTXT = "C:\\University\\PAO\\PROIECT LABORATOR\\Proiect lab 2\\src\\db\\AlienChildDB.txt";
    static String SCHOOLTXT = "C:\\University\\PAO\\PROIECT LABORATOR\\Proiect lab 2\\src\\db\\AlienSchoolDB.txt";

    public static void main(String[] args) {
        AlienChildDao alienChildDao = null;
        AlienSchoolDao alienSchoolDao = null;
        try {
            alienSchoolDao = new AlienSchoolDao(SCHOOLTXT);
            alienChildDao = new AlienChildDao(CHILDTXT);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        AlienChildCommandsRepository childRepository = new AlienChildCommandsRepository(alienChildDao);
        AlienSchoolCommandsRepository schoolRepository = new AlienSchoolCommandsRepository(alienSchoolDao);
        Menu menu = new Menu(childRepository, schoolRepository);

        while (true){
            menu.displayMenu();
            menu.handleUserInput();
        }
    }
}