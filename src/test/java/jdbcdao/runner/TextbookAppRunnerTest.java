package jdbcdao.runner;

import jdbcdao.config.TestConfigData;
import jdbcdao.daos.TextbookDao;
import jdbcdao.utilities.ConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

public class TextbookAppRunnerTest {

    @Test
    public void run() {
        // Given
        TextbookDao textbookDao = new TextbookDao(TestConfigData.dataTable, new ConnectionFactory(TestConfigData.url, TestConfigData.user, TestConfigData.pass));
        TextbookAppRunner textbookAppRunner = new TextbookAppRunner(TestConfigData.url, TestConfigData.user, TestConfigData.pass, TestConfigData.dataTable);
        int expectedDataLength = textbookDao.findAll().size();

        // When
        textbookAppRunner.run();
        int actualDataLength = textbookDao.findAll().size();

        // Then
        Assert.assertEquals(expectedDataLength, actualDataLength);
    }
}