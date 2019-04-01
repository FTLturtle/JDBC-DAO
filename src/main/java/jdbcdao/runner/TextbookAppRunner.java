package jdbcdao.runner;

import jdbcdao.daos.TextbookDao;
import jdbcdao.utilities.ConnectionFactory;

public class TextbookAppRunner {
    private final String dataTable;
    private final ConnectionFactory connectionFactory;

    public TextbookAppRunner(String url, String user, String pass, String dataTable) {
        this.dataTable = dataTable;
        this.connectionFactory = new ConnectionFactory(url, user, pass);
    }

    public void run() {
        TextbookDao textbookDao = new TextbookDao(dataTable, connectionFactory);
    }
}
