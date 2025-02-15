package gov.iti.jets.database.dao;

import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.model.FileType;
import gov.iti.jets.model.MyFile;

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
    public int addFile(MyFile file) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "INSERT INTO FILES (file_name, file_path , file_type) VALUES (?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, file.getFileName());
            preparedStatement.setString(2, file.getFilePath());
            preparedStatement.setString(3, file.getFileType().toString());
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
    public MyFile getFileById(int fileId) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "SELECT * FROM FILES WHERE file_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, fileId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new MyFile(
                        resultSet.getInt("file_id"),
                        resultSet.getString("file_name"),
                        resultSet.getString("file_path"),
                       FileType.valueOf( resultSet.getString("file_type"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MyFile> getAllFiles() {
        Connection connection = dataBaseConnection.getConnection();
        List<MyFile> files = new ArrayList<>();
        String query = "SELECT * FROM MyFile";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                files.add(new MyFile(
                   resultSet.getInt("file_id"),
                   resultSet.getString("file_name"),
                   resultSet.getString("file_path"),
                   FileType.valueOf( resultSet.getString("file_type"))

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return files;
    }

    @Override
    public void updateFile(MyFile file) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "UPDATE MyFile SET file_name = ? WHERE file_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, file.getFileName());
            preparedStatement.setInt(2, file.getFileId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFile(int fileId) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "DELETE FROM MyFile WHERE file_Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, fileId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
