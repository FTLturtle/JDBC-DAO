package jdbcdao.daos;

import jdbcdao.interfaces.DaoInterface;
import jdbcdao.models.Textbook;
import jdbcdao.utilities.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TextbookDao implements DaoInterface<Textbook> {
    private final String dataTable;
    private final ConnectionFactory connectionFactory;

    public TextbookDao(String dataTable, ConnectionFactory connectionFactory) {
        this.dataTable = dataTable;
        this.connectionFactory = connectionFactory;
    }

    public Textbook findById(Long id) {
        Textbook result = null;
        try (Connection connection = connectionFactory.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + dataTable + " WHERE id=" + id);
            if (resultSet.next()) {
                result = extractTextbookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Textbook> findAll() {
        List<Textbook> result = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + dataTable);
            while (resultSet.next()) {
                result.add(extractTextbookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Textbook extractTextbookFromResultSet(ResultSet resultSet) throws SQLException {
        return new Textbook(resultSet.getLong("id"),
                resultSet.getString("isbn"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getShort("publication_year"));
    }

    public Boolean update(Textbook dto) {
        boolean result = false;
        try (Connection connection = connectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + dataTable + " SET isbn = ?, title = ?, author = ?, publication_year = ? WHERE id = ?;");
            preparedStatement.setString(1, dto.getIsbn());
            preparedStatement.setString(2, dto.getTitle());
            preparedStatement.setString(3, dto.getAuthor());
            preparedStatement.setShort(4, dto.getYear());
            preparedStatement.setLong(5, dto.getId());
            int i = preparedStatement.executeUpdate();
            if(i != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean create(Textbook dto) {
        boolean result = false;
        try (Connection connection = connectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + dataTable + " (id, isbn, title, author, publication_year) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setLong(1, dto.getId());
            preparedStatement.setString(2, dto.getIsbn());
            preparedStatement.setString(3, dto.getTitle());
            preparedStatement.setString(4, dto.getAuthor());
            preparedStatement.setShort(5, dto.getYear());
            int i = preparedStatement.executeUpdate();
            if(i == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean delete(Long id) {
        boolean result = false;
        try (Connection connection = connectionFactory.getConnection()){
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM " + dataTable + " WHERE id=" + id);
            if(i == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
