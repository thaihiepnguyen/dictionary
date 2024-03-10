package Ui;

import java.util.Scanner;

public class MainUi {
    public void display() {
        String choice;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Dictionary");
            System.out.println("==========");
            System.out.println("(L)ist all words");
            System.out.println("(S)earch a word");
            System.out.println("(I)nsert a new word");
            System.out.println("(U)pdate a word");
            System.out.println("(D)elete a word");
            System.out.println("(P)ractice");
            System.out.println("(E)xit");
            System.out.println("==========");
            System.out.print("Your choice: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.next();

            switch (choice.toLowerCase()) {
                case "l":
                    ListUi.display();
                    break;
                case "s":
                    System.out.println("Search a word");
                    break;
                case "i":
                    InsertUi.display();
                    break;
                case "u":
                    System.out.println("Update a word");
                    break;
                case "d":
                    System.out.println("Delete a word");
                    break;
                case "p":
                    PracticeUi.display();
                    break;
                case "e":
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (!choice.equals("e"));
    }
}
