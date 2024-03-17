import DataSource.ConnectionPool;
import DataSource.Injectable;
import Ui.LanguageUi;

public class Application {
    private final LanguageUi ui = (LanguageUi) Injectable.get(LanguageUi.class.getName());
    private final ConnectionPool pool = (ConnectionPool) Injectable.get(ConnectionPool.class.getName());
    public void run() {
        ui.display();
        pool.closeAll();
    }
}
