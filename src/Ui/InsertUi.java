package Ui;

import Bus.WordBus;
import DataSource.Injectable;
import Dto.WordDto;

import java.util.Scanner;

public class InsertUi {
    private static final WordBus wordBus = (WordBus) Injectable.get(WordBus.class.getName());
    public static void display(String language) {
        Scanner scanner = new Scanner(System.in);
        String choiceInsert;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Insert");
            System.out.println("==========");
            System.out.println("(I)nsert a new word");
            System.out.println("(B)ack");
            System.out.println("==========");
            System.out.print("Your choice: ");
            choiceInsert = scanner.next();
            scanner.nextLine();
            if (choiceInsert.equalsIgnoreCase("i")) {
                WordDto wordDto = new WordDto();
                System.out.print("Input a new word: ");
                wordDto.source = scanner.nextLine();
                System.out.print("Input translation: ");
                wordDto.vietnamese = scanner.nextLine();
                if (language.equalsIgnoreCase("japanese")) {
                    wordBus.insertJapaneseWord(wordDto);
                } else {
                    wordBus.insertEnglishWord(wordDto);
                }

            }
        } while (!choiceInsert.equalsIgnoreCase("b"));
    }
}
