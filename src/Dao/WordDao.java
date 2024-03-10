package Dao;

import DataSource.ConnectionPool;
import Dto.WordDto;
import DataSource.Injectable;
import org.jetbrains.annotations.NotNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WordDao {
    private final ConnectionPool pool = (ConnectionPool) Injectable.get(ConnectionPool.class.getName());

    public ArrayList<WordDto> getAllWords() {
        ArrayList<WordDto> wordDtos = new ArrayList<>();
        Connection conn = null;
        String sql = "SELECT * FROM words;";
        try {
            conn = pool.get();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                WordDto wordDto = new WordDto();
                wordDto.id = rs.getInt("id");
                wordDto.english = rs.getString("english");
                wordDto.vietnamese = rs.getString("vietnamese");
                wordDtos.add(wordDto);
            }
            statement.close();
            rs.close();
            return wordDtos;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            pool.release(conn);
        }
        return wordDtos;
    }

    public void insertWord(@NotNull WordDto wordDto) {
        Connection conn = null;
        String sql = "INSERT INTO words (english, vietnamese) VALUES (?, ?);";
        try {
            conn = pool.get();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, wordDto.english);
            statement.setString(2, wordDto.vietnamese);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            pool.release(conn);
        }
    }
}
