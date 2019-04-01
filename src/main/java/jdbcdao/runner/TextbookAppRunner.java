package jdbcdao.runner;

import jdbcdao.daos.TextbookDao;
import jdbcdao.models.Textbook;
import jdbcdao.utilities.ConnectionFactory;

public class TextbookAppRunner {
    private final TextbookDao textbookDao;

    public TextbookAppRunner(String url, String user, String pass, String dataTable) {
        textbookDao = new TextbookDao(dataTable, new ConnectionFactory(url, user, pass));
    }

    public void run() {
        long id = 100L;
        Textbook textbook = new Textbook(id, "2309485798", "title", "author", (short) 2000);

        textbookDao.create(textbook);
        System.out.println(textbookDao.findById(id));
        textbook.setTitle("newTitle");
        textbookDao.update(textbook);
        System.out.println(textbookDao.findById(id));

        for (Textbook text : textbookDao.findAll()) {
            System.out.println(text);
        }

        textbookDao.delete(id);

        for (Textbook text : textbookDao.findAll()) {
            System.out.println(text);
        }
    }
}
