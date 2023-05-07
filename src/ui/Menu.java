package ui;

import entities.AlienChild;
import entities.AlienSchool;
import repository.AlienChildCommandsRepository;
import repository.AlienSchoolCommandsRepository;
import util.ChildScanner;
import util.SchoolScanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Logo logo;
    private AlienChildCommandsRepository alienChildCommandsRepository;
    private AlienSchoolCommandsRepository alienSchoolCommandsRepository;
    private Scanner scanner;
    private File auditFile;

    public Menu(
            AlienChildCommandsRepository alienChildCommandsRepository,
            AlienSchoolCommandsRepository alienSchoolCommandsRepository
    ) {
        this.auditFile = new File("src/db/audit.csv");
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
        System.out.println("9. Get children who are in final year");
    }
    public void handleUserInput() throws IOException {
        int choice = scanner.nextInt();
        // get timestamp

        FileWriter fw = new FileWriter(auditFile, true);
        scanner.nextLine();
        int id;
        switch (choice){
            case 1:
                alienChildCommandsRepository.getAll();
                try {
                    fw.write("get_all_child," + System.currentTimeMillis() + "\n"); fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case 2:
                alienSchoolCommandsRepository.getAll();
                try {
                    fw.write("get_all_school," + System.currentTimeMillis() + "\n"); fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Enter ID:");
                id = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                System.out.println(alienChildCommandsRepository.getById(id));
                try {
                    fw.write("get_child_by_id," + System.currentTimeMillis() + "\n"); fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                System.out.println("Enter ID:");
                id = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                System.out.println(alienSchoolCommandsRepository.getById(id));
                try {
                    fw.write("get_school_by_id," + System.currentTimeMillis() + "\n"); fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                ChildScanner childscanner = new ChildScanner();
                AlienChild x = childscanner.inputChild();
                alienChildCommandsRepository.upsert(x.getId(), x);
                System.out.println("*---------Operation Complete!");
                try {
                    fw.write("upsert_child," + System.currentTimeMillis() + "\n"); fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                SchoolScanner schoolscanner = new SchoolScanner();
                AlienSchool y = schoolscanner.inputSchool();
                alienSchoolCommandsRepository.upsert(y.getId(), y);
                System.out.println("*---------Operation Complete!");
                try {
                    fw.write("upsert_school," + System.currentTimeMillis() + "\n"); fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                try {
                    fw.write("delete_child," + System.currentTimeMillis() + "\n"); fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
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
                try {
                    fw.write("delete_school," + System.currentTimeMillis() + "\n"); fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 9:

                alienChildCommandsRepository.getFinalYear();
                try{
                    fw.write("get_children_in_terminal_year," + System.currentTimeMillis() + "\n"); fw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

                break;
            default:
                System.out.println("Invalid Command!");
                break;


        }
    }
}
