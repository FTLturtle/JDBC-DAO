package jdbcdao.utilities;

import jdbcdao.config.ConfigData;
import jdbcdao.config.TestConfigData;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryTest {

    @Test
    public void getTestConnectionTest() {
        // Given
        ConnectionFactory connectionFactory = new ConnectionFactory(TestConfigData.url, TestConfigData.user, TestConfigData.pass);

        // When
        Connection connection = connectionFactory.getConnection();

        // Then
        Assert.assertNotNull(connection);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getRealConnectionTest() {
        // Given
        ConnectionFactory connectionFactory = new ConnectionFactory(ConfigData.url, ConfigData.user, ConfigData.pass);

        // When
        Connection connection = connectionFactory.getConnection();

        // Then
        Assert.assertNotNull(connection);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBadConnectionTest() {
        // Given
        ConnectionFactory connectionFactory = new ConnectionFactory("garbage", "garbage", "garbage");

        // When
        Connection connection = connectionFactory.getConnection();
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Then
        Assert.assertNull(connection);
    }
}