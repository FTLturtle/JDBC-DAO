package jdbcdao.daos;

import jdbcdao.config.TestConfigData;
import jdbcdao.models.TextbookDto;
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
        TextbookDto expectedTextbookDto = new TextbookDto(id, "02938475029", "title", "author", (short) 1900);
        textbookDao.create(expectedTextbookDto);

        // When
        TextbookDto actualTextbookDto = textbookDao.findById(id);
        textbookDao.delete(id);

        // Then
        Assert.assertEquals(expectedTextbookDto, actualTextbookDto);
    }

    @Test
    public void findAllTest() {
        // Given
        List<TextbookDto> expectedList = new ArrayList<>();
        TextbookDto textbookDto1 = new TextbookDto(100L, "02938475029", "title1", "author1", (short) 1900);
        TextbookDto textbookDto2 = new TextbookDto(-100L, "029475029", "title2", "author2", (short) 2000);
        TextbookDto textbookDto3 = new TextbookDto(240L, "34250292345", "title3", "author3", (short) 2100);
        expectedList.add(textbookDto2);
        expectedList.add(textbookDto1);
        expectedList.add(textbookDto3);
        for (TextbookDto text : expectedList) {
            textbookDao.create(text);
        }

        // When
        List<TextbookDto> actualList = textbookDao.findAll();
        for (TextbookDto text : actualList) {
            textbookDao.delete(text.getId());
        }

        // Then
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void updateTest() {
        long id = 200L;
        TextbookDto expectedTextbookDto = new TextbookDto(id, "0928347509238", "title", "author", (short) 1900);
        textbookDao.create(expectedTextbookDto);

        // When
        expectedTextbookDto.setTitle("updatedTitle");
        expectedTextbookDto.setAuthor("updatedAuthor");
        expectedTextbookDto.setIsbn("9023847509");
        expectedTextbookDto.setYear((short) 3000);
        textbookDao.update(expectedTextbookDto);
        TextbookDto actualTextbookDto = textbookDao.findById(id);
        textbookDao.delete(id);

        // Then
        Assert.assertEquals(expectedTextbookDto, actualTextbookDto);
    }

    @Test
    public void createTest() {
        // Given
        long id = 200L;
        TextbookDto expectedTextbookDto = new TextbookDto(id, "0928347509238", "title", "author", (short) 1900);
        textbookDao.create(expectedTextbookDto);

        // When
        TextbookDto actualTextbookDto = textbookDao.findById(id);
        textbookDao.delete(id);

        // Then
        Assert.assertEquals(expectedTextbookDto, actualTextbookDto);
    }

    @Test
    public void deleteTest1() {
        // Given
        long id = 300L;
        TextbookDto expectedTextbookDto = new TextbookDto(id, "0928347509", "title", "author", (short) 1900);
        textbookDao.create(expectedTextbookDto);

        // When
        Boolean result = textbookDao.delete(id);

        // Then
        Assert.assertTrue(result);
    }

    @Test
    public void deleteTest2() {
        // Given
        long id = 300L;
        TextbookDto expectedTextbookDto = new TextbookDto(id, "0928347509", "title", "author", (short) 1900);
        textbookDao.create(expectedTextbookDto);

        // When
        textbookDao.delete(id);
        TextbookDto result = textbookDao.findById(id);

        // Then
        Assert.assertNull(result);
    }
}