import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ClueDatabase extends Database{
    public ClueDatabase() throws SQLException {
    }

    public void showCluesOnScreen(String tableName, TextArea textArea) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int noOfColumns = resultSetMetaData.getColumnCount();

        textArea.setText("_______________________________________________________\n");
        textArea.append("[ "+resultSetMetaData.getTableName(1) + " ]\n");
        for (int i = 1; i <= noOfColumns; i++) {
            textArea.append("| " + resultSetMetaData.getColumnName(i) + " |");
        }
        textArea.append("\n----------------------------------------------------\n");
        while (resultSet.next())
        {
            for (int i = 1; i <= noOfColumns; i++) {
                textArea.append("| " + resultSet.getString(i) + " |");
            }
            textArea.append("\n");
        }
        textArea.append("_____________________________________________________");
    }

    public void addReport(int year, int month, int day,String street, String description) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT id FROM crime_scene_reports ORDER BY id DESC LIMIT 1");
        int id = resultSet.getInt(1) + 1;
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO crime_scene_reports (id,day,month,year,street,description) VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1,id);
        preparedStatement.setInt(2,day);
        preparedStatement.setInt(3,month);
        preparedStatement.setInt(4,year);
        preparedStatement.setString(5,street);
        preparedStatement.setString(6,description);
        preparedStatement.executeUpdate();

    }

}
