package DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;

public class ConnectionPool {
    static final private String url = "jdbc:mysql://localhost:3306/English";
    static final private String username = "root";
    static final private String password = "reallyStrongPwd123";
    static final private String driver = "com.mysql.cj.jdbc.Driver";
    static final private int size = 5;

    private final Queue<Connection> connections = new ArrayDeque<>(size);

    public ConnectionPool() {
        try {
            for (int i = 0; i < size; i++) {
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(url, username, password);
                connections.add(connection);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized Connection get() throws SQLException {
        if (connections.isEmpty()) {
            throw new SQLException("Connection pool is full, try again later.");
        }

        return connections.remove();
    }

    public synchronized void release(Connection connection) {
        if (connection != null) {
            connections.add(connection);
        }
    }

    public synchronized void closeAll() {
        try {
            for (Connection connection : connections) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Can not close connections!");
        }

        connections.clear();
    }
}
