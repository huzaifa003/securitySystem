import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Runner {
    public static void main(String[] args) throws SQLException {
        Database.connect();

        MainGui gui = new MainGui();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setSize(640,640);

    }
}
