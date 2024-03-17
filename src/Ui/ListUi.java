package Ui;

import Bus.WordBus;
import DataSource.Injectable;
import Dto.WordDto;

import java.util.ArrayList;
import java.util.Scanner;

public class ListUi {
    private static final WordBus wordBus = (WordBus) Injectable.get(WordBus.class.getName());
    public static void display(String language) {
        Scanner scanner = new Scanner(System.in);
        String choiceList;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("List");
            System.out.println("==========");
            System.out.println("(L)ist all words");
            System.out.println("(B)ack");
            System.out.println("==========");
            System.out.print("Your choice: ");
            choiceList = scanner.next();
            scanner.nextLine();
            if (choiceList.equalsIgnoreCase("l")) {
                if (language.equalsIgnoreCase("japanese")) {
                    ArrayList<WordDto> wordDtos = wordBus.getAllJapaneseWords();
                    for (WordDto wordDto : wordDtos) {
                        System.out.println("- " + wordDto.source + ": " + wordDto.vietnamese);
                    }
                } else {
                    ArrayList<WordDto> wordDtos = wordBus.getAllEnglishWords();
                    for (WordDto wordDto : wordDtos) {
                        System.out.println("- " + wordDto.source + ": " + wordDto.vietnamese);
                    }
                }
                System.out.println("Press any key to continue");
                scanner.nextLine();
            }
        } while (!choiceList.equalsIgnoreCase("b"));
    }
}
