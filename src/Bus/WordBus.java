package Bus;

import Dao.WordDao;
import Dto.WordDto;
import DataSource.Injectable;
import java.util.ArrayList;
import java.util.Collections;

public class WordBus {
    private final WordDao wordDao = (WordDao) Injectable.get(WordDao.class.getName());
    public ArrayList<WordDto> getAllWords() {
        return wordDao.getAllWords();
    }

    public void insertWord(WordDto wordDto) {
        wordDao.insertWord(wordDto);
    }

    public ArrayList<WordDto> practice() {
        ArrayList<WordDto> wordDtos = wordDao.getAllWords();
        Collections.shuffle(wordDtos);
        return wordDtos;
    }
}
