import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;


public class Database {
    static Connection connection = null;
    Statement statement = Database.connection.createStatement();

    public Database() throws SQLException {
    }

    public static Connection connect(){


        try
        {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:fiftyville.db";
            connection = DriverManager.getConnection(url);

            System.out.println("The connection is successfully established");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }




    public void printData(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int noOfColumns = resultSetMetaData.getColumnCount();

        System.out.println("_______________________________________________________");
        System.out.println("[ "+resultSetMetaData.getTableName(1) + " ]");
        for (int i = 1; i <= noOfColumns; i++) {
            System.out.print("| " + resultSetMetaData.getColumnName(i) + " |");
        }
        System.out.println("\n----------------------------------------------------");
        while (resultSet.next())
        {
            for (int i = 1; i <= noOfColumns; i++) {
                System.out.print("| " + resultSet.getString(i) + " |");
            }
            System.out.println();
        }
        System.out.println("_____________________________________________________");
    }




}
