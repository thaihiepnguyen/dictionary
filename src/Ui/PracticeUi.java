package Ui;

import Bus.WordBus;
import DataSource.Injectable;
import Dto.WordDto;

import java.util.*;

public class PracticeUi {
    private static final WordBus wordBus = (WordBus) Injectable.get(WordBus.class.getName());
    private static final int MAX_CHOICES = 4;

    private static final HashMap<Integer, String> answerIndexMapping = new HashMap<>();
    private static final HashMap<String, Integer> answerMapping = new HashMap<>();

    static {
        answerIndexMapping.put(0, "A");
        answerIndexMapping.put(1, "B");
        answerIndexMapping.put(2, "C");
        answerIndexMapping.put(3, "D");

        answerMapping.put("a", 0);
        answerMapping.put("b", 1);
        answerMapping.put("c", 2);
        answerMapping.put("d", 3);
    }
    public static void display(String language) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<WordDto> wordDtos = wordBus.practice(language);
        String choicePractice;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Practice");
            System.out.println("==========");
            System.out.println("(M)ultiple-choice");
            System.out.println("(I)nput-choice");
            System.out.println("(B)ack");
            System.out.println("==========");
            System.out.print("Your choice: ");
            choicePractice = scanner.next();
            scanner.nextLine();
            if (choicePractice.equalsIgnoreCase("m")) {
                multipleChoicePractice(wordDtos);
            } else if (choicePractice.equalsIgnoreCase("i")) {
                inputChoicePractice(wordDtos, language);
            }
        } while (!choicePractice.equalsIgnoreCase("b"));
    }

    public static void multipleChoicePractice(ArrayList<WordDto> wordDtos) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        for (int i = 0; i < wordDtos.size(); i++) {
            int answerIndex = rand.nextInt(MAX_CHOICES);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Practice");
            System.out.println("==========");
            System.out.println((i + 1) + ". What is the translation of '" + wordDtos.get(i).source + "'?");
            System.out.println("==========");
            for (int j = 0; j < MAX_CHOICES; j++) {
                if (j == answerIndex) {
                    System.out.println("(" + answerIndexMapping.get(j) + ") " + wordDtos.get(i).vietnamese);
                } else {
                    while (true) {
                        int randomIndex = rand.nextInt(wordDtos.size());
                        if (randomIndex != i) {
                            System.out.println("(" + answerIndexMapping.get(j) + ") " + wordDtos.get(randomIndex).vietnamese);
                            break;
                        }
                    }
                }
            }
            System.out.println("==========");
            System.out.print("Your choice: ");
            String choicePractice = scanner.next();
            if (answerMapping.get(choicePractice.toLowerCase()) == answerIndex) {
                System.out.println("Correct");
            } else {
                System.out.println("Incorrect");
            }
            System.out.println("Press any key to continue");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public static void inputChoicePractice(ArrayList<WordDto> wordDtos, String language) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < wordDtos.size(); i++) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Practice");
            System.out.println("==========");
            if (language.equalsIgnoreCase("japanese")) {
                System.out.println((i + 1) + ". What is the translation of '" + wordDtos.get(i).source + "'?");
            } else {
                System.out.println((i + 1) + ". What is the translation of '" + wordDtos.get(i).vietnamese + "'?");
            }
            System.out.println("==========");
            System.out.print("Your choice: ");
            String choicePractice = scanner.nextLine();
            if ((language.equalsIgnoreCase("japanese") && choicePractice.equalsIgnoreCase(wordDtos.get(i).vietnamese))
            || (language.equalsIgnoreCase("english") && choicePractice.equalsIgnoreCase(wordDtos.get(i).source))) {
                System.out.println("Correct");
            } else {
                System.out.println("Incorrect");
            }
            System.out.println("Press any key to continue");
            scanner.nextLine();
        }
    }
}
