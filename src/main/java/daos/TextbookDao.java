package daos;

import models.Textbook;
import ConnectionFactory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TextbookDao implements DaoInterface<Textbook> {
    private final String dataTable = "JDBC_DAO.textbook";

    public Textbook findById(Long id) {
        Textbook result = null;
        try (Connection connection = ConnectionFactory.getConnection()){
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

    private Textbook extractTextbookFromResultSet(ResultSet resultSet) throws SQLException {
        return new Textbook(resultSet.getLong("id"),
                resultSet.getString("isbn"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getShort("publication_year"));
    }

    public List<Textbook> findAll() {
        List<Textbook> result = new ArrayList<Textbook>();
        try (Connection connection = ConnectionFactory.getConnection()){
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

    public Boolean update(Textbook dto) {
        return null;
    }

    public Boolean create(Textbook dto) {
        boolean result = false;
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + dataTable + " (isbn, title, author, publication_year) VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, dto.getIsbn());
            preparedStatement.setString(2, dto.getTitle());
            preparedStatement.setString(3, dto.getAuthor());
            preparedStatement.setShort(4, dto.getYear());
            int i = preparedStatement.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean delete(Long id) {
        boolean result = false;
        try (Connection connection = ConnectionFactory.getConnection()){
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

    public static void main(String[] args) {
        TextbookDao textbookDao = new TextbookDao();
        List<Textbook> textbookList = textbookDao.findAll();

        for (Textbook textbook : textbookList) {
            System.out.println(textbook.toString());
        }
    }
}
