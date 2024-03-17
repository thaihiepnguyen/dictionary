package Ui;

import DataSource.Injectable;

import java.util.Scanner;

public class LanguageUi {
    private final MainUi ui = (MainUi) Injectable.get(MainUi.class.getName());
    public void display() {
        String choice;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Language");
            System.out.println("==========");
            System.out.println("(E)nglish");
            System.out.println("(J)apanese");
            System.out.println("(Q)uit");
            System.out.println("==========");
            System.out.print("Your choice: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.next();
            switch (choice.toLowerCase()) {
                case "e":
                    ui.display("english");
                    break;
                case "j":
                    ui.display("japanese");
                    break;
                case "q":
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (!choice.equals("q"));
    }
}
