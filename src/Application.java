import DataSource.ConnectionPool;
import DataSource.Injectable;
import Ui.MainUi;

public class Application {
    private final MainUi ui = (MainUi) Injectable.get(MainUi.class.getName());
    private final ConnectionPool pool = (ConnectionPool) Injectable.get(ConnectionPool.class.getName());
    public void run() {
        ui.display();
        pool.closeAll();
    }
}
