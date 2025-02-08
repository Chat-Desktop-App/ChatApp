package gov.iti.jets.database.dao;

import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.model.Files;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilesDaoImpl implements FilesDao{

    static DataBaseConnection dataBaseConnection;

    static {
        try {
            dataBaseConnection = DataBaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int addFile(Files file) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "INSERT INTO FILES (file_name, file_type, file_path) VALUES (?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, file.getFileName());
            preparedStatement.setString(2, file.getFileType());
            preparedStatement.setString(3, file.getFilePath());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // return fileId
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public Files getFileById(int fileId) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "SELECT * FROM FILES WHERE file_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, fileId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Files(
                        resultSet.getInt("file_id"),
                        resultSet.getString("file_name"),
                        resultSet.getString("file_type"),
                        resultSet.getString("file_path")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Files> getAllFiles() {
        Connection connection = dataBaseConnection.getConnection();
        List<Files> files = new ArrayList<>();
        String query = "SELECT * FROM Files";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                files.add(new Files(
                   resultSet.getInt("file_id"),
                   resultSet.getString("file_name"),
                   resultSet.getString("file_type"),
                   resultSet.getString("file_path")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return files;
    }

    @Override
    public void updateFile(Files file) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "UPDATE Files SET file_name = ?, file_type = ? WHERE file_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, file.getFileName());
            preparedStatement.setString(2, file.getFileType());
            preparedStatement.setInt(3, file.getFileId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFile(int fileId) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "DELETE FROM Files WHERE file_Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, fileId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
