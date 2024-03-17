package Bus;

import Dao.WordDao;
import Dto.WordDto;
import DataSource.Injectable;
import java.util.ArrayList;
import java.util.Collections;

public class WordBus {
    private final WordDao wordDao = (WordDao) Injectable.get(WordDao.class.getName());
    public ArrayList<WordDto> getAllEnglishWords() {
        return wordDao.getAllEnglishWords();
    }
    public ArrayList<WordDto> getAllJapaneseWords() {
        return wordDao.getAllJapaneseWords();
    }

    public void insertEnglishWord(WordDto wordDto) {
        wordDao.insertEnglishWord(wordDto);
    }
    public void insertJapaneseWord(WordDto wordDto) {
        wordDao.insertJapaneseWord(wordDto);
    }

    public ArrayList<WordDto> practice(String language) {
        ArrayList<WordDto> wordDtos;
        if (language.equalsIgnoreCase("japanese")) {
            wordDtos = wordDao.getAllJapaneseWords();
        } else if (language.equalsIgnoreCase("english")) {
            wordDtos = wordDao.getAllEnglishWords();
        } else {
            wordDtos = new ArrayList<>();
        }
        Collections.shuffle(wordDtos);
        return wordDtos;
    }
}
