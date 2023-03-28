package util;

import entities.AlienChild;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ChildScanner {
    public AlienChild inputChild() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*---------Operation Begins.");
        System.out.println("Enter ID:");

        int a = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter year of study 1-6");
        int b = -1;
        while (true) {
            try {
                b = scanner.nextInt();
                if (b >= 1 && b <= 6) {
                    break; // exit the loop if input is valid
                }
                System.out.println("Unavailable YoS!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.nextLine(); // consume the invalid input to prevent infinite loop
            }
        }


        String c;
        List<String> grades = new ArrayList<String>();
        while (grades.size() < 5) {
            while (!(c = scanner.nextLine()).matches("[A-D]")) {
                System.out.println("Enter grades between A-D");
            }
            grades.add(c);
        }
        System.out.println("Enter school ID");
        int schoolId = scanner.nextInt();

        return new AlienChild(a, name, b, grades.get(0).charAt(0), grades.get(1).charAt(0), grades.get(2).charAt(0), grades.get(3).charAt(0), grades.get(4).charAt(0), schoolId);
    }
}
