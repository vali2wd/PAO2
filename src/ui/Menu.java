package ui;

import entities.AlienChild;
import repository.AlienChildCommandsRepository;
import repository.AlienSchoolCommandsRepository;
import util.ChildScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Logo logo;
    private AlienChildCommandsRepository alienChildCommandsRepository;
    private AlienSchoolCommandsRepository alienSchoolCommandsRepository;
    private Scanner scanner;

    public Menu(
            AlienChildCommandsRepository alienChildCommandsRepository,
            AlienSchoolCommandsRepository alienSchoolCommandsRepository
    ) {
        this.logo = new Logo();
        logo.showLogo();
        this.alienChildCommandsRepository = alienChildCommandsRepository;
        this.alienSchoolCommandsRepository = alienSchoolCommandsRepository;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println();
        System.out.println("1. Get all children");
        System.out.println("2. Get all schools");
        System.out.println("3. Get children by id");
        System.out.println("4. Get school by id");
        System.out.println("5. Insert or update a child.");
        System.out.println("6. Insert or update a school.");
        System.out.println("7. (WARNING) DELETE child by ID");
        System.out.println("8. (WARNING) DELETE school by ID");
    }
    public void handleUserInput() throws IOException {
        int choice = scanner.nextInt();
        scanner.nextLine();
        int id;
        switch (choice){
            case 1:
                alienChildCommandsRepository.getAll();
                break;
            case 2:
                alienSchoolCommandsRepository.getAll();
                break;
            case 3:
                System.out.println("Enter ID:");
                id = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                System.out.println(alienChildCommandsRepository.getById(id));
                break;
            case 4:
                System.out.println("Enter ID:");
                id = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                alienChildCommandsRepository.getById(id);
                break;
            case 5:
                ChildScanner childscanner = new ChildScanner();
                AlienChild x = childscanner.inputChild();
                alienChildCommandsRepository.upsert(x.getId(), x);
                System.out.println("*---------Operation Complete!");
            case 6:
                //TODO
                break;

            case 7:
                System.out.println("Enter ID:");
                id = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                try {
                    alienChildCommandsRepository.delete(id);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 8:
                System.out.println("Enter ID:");
                id = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                try {
                    alienSchoolCommandsRepository.delete(id);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Invalid Command!");
                break;


        }
    }
}
