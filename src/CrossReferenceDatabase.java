import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CrossReferenceDatabase extends Database{
    TextArea textArea;
    public CrossReferenceDatabase(TextArea textArea) throws SQLException {
        this.textArea = textArea;
    }

    public void printOnScreen(ResultSet resultSet, TextArea textArea) throws SQLException {
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

    public void getCrimeSceneReport(int month, int day, int year, String street) {

        try {
            ResultSet set = statement.executeQuery("SELECT id,description FROM crime_scene_reports WHERE month = 7 AND day = 28 AND year  = 2021 AND street = 'Humphrey Street'");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT id,description FROM crime_scene_reports WHERE month = 7 AND day = 28 AND year  = 2021 AND street = 'Humphrey Street'");
            textArea.setText("HELLO");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void getTranscripts(int month, int day, int year) throws SQLException {
        try {
            ResultSet set = statement.executeQuery("SELECT name,transcript from interviews WHERE month = 7 AND day = 28 AND year  = 2021");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT name,transcript from interviews WHERE month = 7 AND day = 28 AND year  = 2021");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//
    }

    public void getLicensePlate(){
        try {
            ResultSet set = statement.executeQuery("SELECT license_plate FROM bakery_security_logs\n" +
                    "WHERE year  = 2021 AND month  = 7 AND day = 28 AND hour = 10 AND minute >= 15 AND minute <=25  AND activity = \"exit\"");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT license_plate FROM bakery_security_logs\n" +
                    "WHERE year  = 2021 AND month  = 7 AND day = 28 AND hour = 10 AND minute >= 15 AND minute <=25  AND activity = \"exit\"");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void getAtmLocation(){
        try {
            ResultSet set = statement.executeQuery("SELECT account_number, atm_location FROM atm_transactions\n" +
                    "WHERE month  = 7 AND day  = 28 AND atm_location = \"Leggett Street\"");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT account_number, atm_location FROM atm_transactions\n" +
                    "WHERE month  = 7 AND day  = 28 AND atm_location = \"Leggett Street\"");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAccountNumbers(){
        try {
            ResultSet set = statement.executeQuery("SELECT bank_accounts.account_number,person_id, creation_year FROM bank_accounts\n" +
                    "WHERE  account_number in (\n" +
                    "SELECT account_number FROM atm_transactions\n" +
                    "WHERE month  = 7 AND day  = 28 AND atm_location = \"Leggett Street\"\n" +
                    ")\n");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT bank_accounts.account_number,person_id, creation_year FROM bank_accounts\n" +
                    "WHERE  account_number in (\n" +
                    "SELECT account_number FROM atm_transactions\n" +
                    "WHERE month  = 7 AND day  = 28 AND atm_location = \"Leggett Street\"\n" +
                    ")\n");
            textArea.setText("");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAirport(){
        try {
            ResultSet set = statement.executeQuery("SELECT id, full_name, city FROM airports\n" +
                    "WHERE city = \"Fiftyville\"");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT id, full_name, city name FROM airports\n" +
                    "WHERE city = \"Fiftyville\"");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getEarliestFlight(){
        try {
            ResultSet set = statement.executeQuery("SELECT id,destination_airport_id, hour, minute\n" +
                    "FROM flights\n" +
                    "WHERE origin_airport_id = (\n" +
                    "SELECT id FROM airports\n" +
                    "WHERE city = \"Fiftyville\")\n" +
                    "AND year = 2021 AND day = 29 AND month = 7\n" +
                    "ORDER BY hour,minute\n" +
                    "LIMIT 1");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT id,destination_airport_id, hour, minute\n" +
                    "FROM flights\n" +
                    "WHERE origin_airport_id = (\n" +
                    "SELECT id FROM airports\n" +
                    "WHERE city = \"Fiftyville\")\n" +
                    "AND year = 2021 AND day = 29 AND month = 7\n" +
                    "ORDER BY hour,minute\n" +
                    "LIMIT 1");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void getPassportNo(){
        try {
            ResultSet set = statement.executeQuery("SELECT passport_number FROM passengers\n" +
                    "WHERE flight_id =\n" +
                    "(\n" +
                    "    SELECT flights.id\n" +
                    "    FROM flights\n" +
                    "    WHERE flights.origin_airport_id = (\n" +
                    "    SELECT airports.id FROM airports\n" +
                    "    WHERE airports.city = \"Fiftyville\")\n" +
                    "    AND flights.year = 2021 AND flights.day = 29 AND flights.month = 7\n" +
                    "    ORDER BY flights.hour,flights.minute\n" +
                    "    LIMIT 1\n" +
                    ")");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT passport_number FROM passengers\n" +
                    "WHERE flight_id =\n" +
                    "(\n" +
                    "    SELECT flights.id\n" +
                    "    FROM flights\n" +
                    "    WHERE flights.origin_airport_id = (\n" +
                    "    SELECT airports.id FROM airports\n" +
                    "    WHERE airports.city = \"Fiftyville\")\n" +
                    "    AND flights.year = 2021 AND flights.day = 29 AND flights.month = 7\n" +
                    "    ORDER BY flights.hour,flights.minute\n" +
                    "    LIMIT 1\n" +
                    ")");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getPersonId(){
        try {
            ResultSet set = statement.executeQuery("SELECT person_id FROM bank_accounts\n" +
                    "WHERE  bank_accounts.account_number in\n" +
                    "(\n" +
                    "    SELECT atm_transactions.account_number FROM atm_transactions\n" +
                    "    WHERE month  = 7 AND day  = 28 AND atm_location = \"Leggett Street\"\n" +
                    ")\n");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT person_id FROM bank_accounts\n" +
                    "WHERE  bank_accounts.account_number in\n" +
                    "(\n" +
                    "    SELECT atm_transactions.account_number FROM atm_transactions\n" +
                    "    WHERE month  = 7 AND day  = 28 AND atm_location = \"Leggett Street\"\n" +
                    ")\n");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getCallerId(){
        try {
            ResultSet set = statement.executeQuery("SELECT caller FROM phone_calls\n" +
                    "WHERE month  = 7 AND day  = 28 AND duration < 60");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT caller FROM phone_calls\n" +
                    "WHERE month  = 7 AND day  = 28 AND duration < 60");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getCulprit(){
        try {
            ResultSet set = statement.executeQuery("SELECT * FROM people\n" +
                    "WHERE\n" +
                    "id IN\n" +
                    "(\n" +
                    "    SELECT person_id FROM bank_accounts\n" +
                    "        WHERE  bank_accounts.account_number in\n" +
                    "        (\n" +
                    "            SELECT atm_transactions.account_number FROM atm_transactions\n" +
                    "            WHERE month  = 7 AND day  = 28 AND atm_location = \"Leggett Street\"\n" +
                    "        )\n" +
                    ")\n" +
                    "\n" +
                    "INTERSECT\n" +
                    "\n" +
                    "SELECT * FROM people\n" +
                    "WHERE\n" +
                    "phone_number IN\n" +
                    "(\n" +
                    "    SELECT caller FROM phone_calls\n" +
                    "    WHERE month  = 7 AND day  = 28 AND duration < 60\n" +
                    ")\n" +
                    "\n" +
                    "INTERSECT\n" +
                    "\n" +
                    "SELECT * FROM people\n" +
                    "WHERE\n" +
                    "passport_number IN\n" +
                    "(\n" +
                    "    SELECT passport_number FROM passengers\n" +
                    "    WHERE flight_id =\n" +
                    "        (\n" +
                    "            SELECT flights.id\n" +
                    "            FROM flights\n" +
                    "            WHERE flights.origin_airport_id = (\n" +
                    "            SELECT airports.id FROM airports\n" +
                    "            WHERE airports.city = \"Fiftyville\")\n" +
                    "            AND flights.year = 2021 AND flights.day = 29 AND flights.month = 7\n" +
                    "            ORDER BY flights.hour,flights.minute\n" +
                    "            LIMIT 1\n" +
                    "        )\n" +
                    ")\n" +
                    "\n" +
                    "INTERSECT\n" +
                    "\n" +
                    "SELECT * FROM people\n" +
                    "WHERE\n" +
                    "license_plate IN\n" +
                    "(\n" +
                    "    SELECT license_plate FROM bakery_security_logs\n" +
                    "    WHERE year  = 2021 AND month  = 7 AND day = 28 AND hour = 10 AND minute >= 15 AND minute <=25  AND activity = \"exit\"\n" +
                    ")");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT * FROM people\n" +
                    "WHERE\n" +
                    "id IN\n" +
                    "(\n" +
                    "    SELECT person_id FROM bank_accounts\n" +
                    "        WHERE  bank_accounts.account_number in\n" +
                    "        (\n" +
                    "            SELECT atm_transactions.account_number FROM atm_transactions\n" +
                    "            WHERE month  = 7 AND day  = 28 AND atm_location = \"Leggett Street\"\n" +
                    "        )\n" +
                    ")\n" +
                    "\n" +
                    "INTERSECT\n" +
                    "\n" +
                    "SELECT * FROM people\n" +
                    "WHERE\n" +
                    "phone_number IN\n" +
                    "(\n" +
                    "    SELECT caller FROM phone_calls\n" +
                    "    WHERE month  = 7 AND day  = 28 AND duration < 60\n" +
                    ")\n" +
                    "\n" +
                    "INTERSECT\n" +
                    "\n" +
                    "SELECT * FROM people\n" +
                    "WHERE\n" +
                    "passport_number IN\n" +
                    "(\n" +
                    "    SELECT passport_number FROM passengers\n" +
                    "    WHERE flight_id =\n" +
                    "        (\n" +
                    "            SELECT flights.id\n" +
                    "            FROM flights\n" +
                    "            WHERE flights.origin_airport_id = (\n" +
                    "            SELECT airports.id FROM airports\n" +
                    "            WHERE airports.city = \"Fiftyville\")\n" +
                    "            AND flights.year = 2021 AND flights.day = 29 AND flights.month = 7\n" +
                    "            ORDER BY flights.hour,flights.minute\n" +
                    "            LIMIT 1\n" +
                    "        )\n" +
                    ")\n" +
                    "\n" +
                    "INTERSECT\n" +
                    "\n" +
                    "SELECT * FROM people\n" +
                    "WHERE\n" +
                    "license_plate IN\n" +
                    "(\n" +
                    "    SELECT license_plate FROM bakery_security_logs\n" +
                    "    WHERE year  = 2021 AND month  = 7 AND day = 28 AND hour = 10 AND minute >= 15 AND minute <=25  AND activity = \"exit\"\n" +
                    ")");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void  testAllMethods(){
//        this.getAtmLocation();
//        this.getAccountNumbers();
//        this.getAirport();
//        this.getCallerId();
//        this.getEarliestFlight();
//        this.getPassportNo();
//        this.getLicensePlate();
//        this.getPersonId();
//        this.getCulprit();
    }
}
