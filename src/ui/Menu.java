package ui;

import repository.AlienChildCommandsRepository;
import repository.AlienSchoolCommandsRepository;

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
        System.out.println("4.Get school by id");
        System.out.println("TODO");
        System.out.println("Waiting for key press");
    }
    public void handleUserInput() {
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
                System.out.println("Enter ID:");
                id = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                alienChildCommandsRepository.getById(id);
                break;
            default:
                System.out.println("Invalid Command!");
                break;


        }
    }
}
