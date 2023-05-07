

import entities.dao.AlienSchoolDao;
import repository.AlienChildCommandsRepository;
import entities.dao.AlienChildDao;
import repository.AlienSchoolCommandsRepository;
import ui.Menu;
import util.Users;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


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

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Alien Education Database!");
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        Users users = new Users();
        if (users.validUser(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
            System.exit(0);
        }
        while (true){
            menu.displayMenu();
            menu.handleUserInput();
        }
    }
}