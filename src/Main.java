

import entities.dao.AlienSchoolDao;
import repository.AlienChildCommandsRepository;
import entities.dao.AlienChildDao;
import repository.AlienSchoolCommandsRepository;
import ui.Menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws IOException {
        AlienChildDao alienChildDao = null;
        AlienSchoolDao alienSchoolDao = null;
        try {
            alienSchoolDao = new AlienSchoolDao("jdbc:sqlite:src/db/AlienEducation.db");
            alienChildDao = new AlienChildDao("jdbc:sqlite:src/db/AlienEducation.db");
        } catch (SQLException e) {
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