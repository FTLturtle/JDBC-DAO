package jdbcdao.daos;

import jdbcdao.config.TestConfigData;
import jdbcdao.models.Textbook;
import org.junit.Assert;
import org.junit.Test;
import jdbcdao.utilities.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;

public class TextbookDaoTest {
    private ConnectionFactory connectionFactory = new ConnectionFactory(TestConfigData.url, TestConfigData.user, TestConfigData.pass);
    private TextbookDao textbookDao = new TextbookDao(TestConfigData.dataTable, connectionFactory);

    @Test
    public void findByIdTest() {
        // Given
        long id = 100L;
        Textbook expectedTextbook = new Textbook(id, "02938475029", "title", "author", (short) 1900);
        textbookDao.create(expectedTextbook);

        // When
        Textbook actualTextbook = textbookDao.findById(id);
        textbookDao.delete(id);

        // Then
        Assert.assertEquals(expectedTextbook, actualTextbook);
    }

    @Test
    public void findAllTest() {
        // Given
        List<Textbook> expectedList = new ArrayList<>();
        Textbook textbook1 = new Textbook(100L, "02938475029", "title1", "author1", (short) 1900);
        Textbook textbook2 = new Textbook(-100L, "029475029", "title2", "author2", (short) 2000);
        Textbook textbook3 = new Textbook(240L, "34250292345", "title3", "author3", (short) 2100);
        expectedList.add(textbook2);
        expectedList.add(textbook1);
        expectedList.add(textbook3);
        for (Textbook text : expectedList) {
            textbookDao.create(text);
        }

        // When
        List<Textbook> actualList = textbookDao.findAll();
        for (Textbook text : actualList) {
            textbookDao.delete(text.getId());
        }

        // Then
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void updateTest() {
        long id = 200L;
        Textbook expectedTextbook = new Textbook(id, "0928347509238", "title", "author", (short) 1900);
        textbookDao.create(expectedTextbook);

        // When
        expectedTextbook.setTitle("updatedTitle");
        expectedTextbook.setAuthor("updatedAuthor");
        expectedTextbook.setIsbn("9023847509");
        expectedTextbook.setYear((short) 3000);
        textbookDao.update(expectedTextbook);
        Textbook actualTextbook = textbookDao.findById(id);
        textbookDao.delete(id);

        // Then
        Assert.assertEquals(expectedTextbook, actualTextbook);
    }

    @Test
    public void createTest() {
        // Given
        long id = 200L;
        Textbook expectedTextbook = new Textbook(id, "0928347509238", "title", "author", (short) 1900);
        textbookDao.create(expectedTextbook);

        // When
        Textbook actualTextbook = textbookDao.findById(id);
        textbookDao.delete(id);

        // Then
        Assert.assertEquals(expectedTextbook, actualTextbook);
    }

    @Test
    public void deleteTest1() {
        // Given
        long id = 300L;
        Textbook expectedTextbook = new Textbook(id, "0928347509", "title", "author", (short) 1900);
        textbookDao.create(expectedTextbook);

        // When
        Boolean result = textbookDao.delete(id);

        // Then
        Assert.assertTrue(result);
    }

    @Test
    public void deleteTest2() {
        // Given
        long id = 300L;
        Textbook expectedTextbook = new Textbook(id, "0928347509", "title", "author", (short) 1900);
        textbookDao.create(expectedTextbook);

        // When
        textbookDao.delete(id);
        Textbook result = textbookDao.findById(id);

        // Then
        Assert.assertNull(result);
    }
}