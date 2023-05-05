package util;

import entities.AlienSchool;

import java.util.Scanner;

public class SchoolScanner {
    public AlienSchool inputSchool(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("*---------Operation Begins");
        System.out.println("Enter ID:");
        int a = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter name:");
        String name = scanner.nextLine();

        return new AlienSchool(a, name);
    }
}
