import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class WriteFile {
    static int countFile = 1;

    public static void writeData(ResultSet resultSet ) {

        try {


            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int noOfColumns = resultSetMetaData.getColumnCount();
            String tableName = resultSetMetaData.getTableName(1);
            String filename = tableName + String.valueOf(countFile);

            File file = new File("src/txtFiles/" + filename + ".txt");
            FileWriter fileWriter = new FileWriter(file, false);

            fileWriter.write("_____________________________________________________\n");
            fileWriter.write("[ " + resultSetMetaData.getTableName(1) + " ]\n");
            for (int i = 1; i <= noOfColumns; i++) {
                fileWriter.write("| " + resultSetMetaData.getColumnName(i) + " |");
            }
            fileWriter.write("\n----------------------------------------------------\n");

            while (resultSet.next()) {
                for (int i = 1; i <= noOfColumns; i++) {
                    fileWriter.write("| " + resultSet.getString(i) + " |");
                }
                fileWriter.write("\n");
            }

            fileWriter.write("_____________________________________________________");


            fileWriter.flush();
            fileWriter.close();

            countFile++;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
