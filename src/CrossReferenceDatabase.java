import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CrossReferenceDatabase extends Database{
    TextArea textArea;
    int year;
    int month;
    int day;
    String street;
    
    int hour;
    int startingMinutes;
    int endingMinutes;
    String activity;

    String atm_street;
    String city;
    
    String duration;

    public CrossReferenceDatabase(TextArea textArea) throws SQLException {
        this.textArea = textArea;
    }

    public void printOnScreen(ResultSet resultSet, TextArea textArea) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int noOfColumns = resultSetMetaData.getColumnCount();

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
            this.month=month;
            this.year=year;
            this.day=day;
            this.street=street;

            ResultSet set = statement.executeQuery("SELECT id,description FROM crime_scene_reports WHERE month = " + month + " AND day = " + day  + " AND year  = " + year  + " AND street =  '" + street  + "'");
            System.out.println(month);
            System.out.println("SELECT id,description FROM crime_scene_reports WHERE month = " + month + " AND day = " + day  + " AND year  = " + year  + " AND street =  '" + street  + "'");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT id,description FROM crime_scene_reports WHERE month = " + month + " AND day = " + day  + " AND year  = " + year  + " AND street =  '" + street  + "'");
            textArea.setText("HELLO");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void getTranscripts() throws SQLException {
        try {
            ResultSet set = statement.executeQuery("SELECT name,transcript from interviews WHERE month = " + month + " AND day = " + day  + " AND year  = " + year  + "");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT name,transcript from interviews WHERE month = " + month + " AND day = " + day  + " AND year  = " + year  + "");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//
    }

    public void getLicensePlate(int hour, int startingMinutes, int endingMinutes, String activity){
        try {
            this.hour = hour;
            this.startingMinutes = startingMinutes;
            this.endingMinutes = endingMinutes;
            this.activity = activity;
            
            ResultSet set = statement.executeQuery("SELECT license_plate FROM bakery_security_logs\n" +
                    "WHERE year  = " + year  + " AND month  = " + month + " AND day = " + day  + " AND hour =  " + hour  + " AND minute >=  " + startingMinutes  + " AND minute <= " + endingMinutes  + "  AND activity = \"" + activity  + "\" ");

            System.out.println("SELECT license_plate FROM bakery_security_logs\n" +                   "WHERE year  = " + year  + " AND month  = " + month + " AND day = " + day  + " AND hour =  " + hour  + " AND minute >=  " + startingMinutes  + " AND minute <= " + endingMinutes  + "  AND activity = \""+ activity  + "\" ");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT license_plate FROM bakery_security_logs\n" +
                    "WHERE year  = " + year  + " AND month  = " + month + " AND day = " + day  + " AND hour =  " + hour  + " AND minute >=  " + startingMinutes  + " AND minute <= " + endingMinutes  + "  AND activity = \"" + activity  + "\"");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void getAtmLocation(String atm_street){
        try {
            this.atm_street = atm_street;
            ResultSet set = statement.executeQuery("SELECT account_number, atm_location FROM atm_transactions\n" +
                    "WHERE month  = " + month + " AND day  = " + day  + " AND atm_location = \"" + atm_street  + "\"");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT account_number, atm_location FROM atm_transactions\n" +
                    "WHERE month  = " + month + " AND day  = " + day  + " AND atm_location = \"" + atm_street  + "\"");
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
                    "WHERE month  = " + month + " AND day  = " + day  + " AND atm_location = \"" + atm_street  + "\"\n" +
                    ")\n");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT bank_accounts.account_number,person_id, creation_year FROM bank_accounts\n" +
                    "WHERE  account_number in (\n" +
                    "SELECT account_number FROM atm_transactions\n" +
                    "WHERE month  = " + month + " AND day  = " + day  + " AND atm_location = \"" + atm_street  + "\"\n" +
                    ")\n");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAirport(String city){
        try {
            this.city=city;
            ResultSet set = statement.executeQuery("SELECT id, full_name, city FROM airports\n" +
                    "WHERE city = \""+ city +"\"");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT id, full_name, city name FROM airports\n" +
                    "WHERE city = \""+ city +"\"");
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
                    "WHERE city = \""+ city +"\")\n" +
                    "AND year = " + year  + " AND day = 29 AND month = " + month + "\n" +
                    "ORDER BY hour,minute\n" +
                    "LIMIT 1");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT id,destination_airport_id, hour, minute\n" +
                    "FROM flights\n" +
                    "WHERE origin_airport_id = (\n" +
                    "SELECT id FROM airports\n" +
                    "WHERE city = \""+ city +"\")\n" +
                    "AND year = " + year  + " AND day = 29 AND month = " + month + "\n" +
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
                    "    WHERE airports.city = \""+ city +"\")\n" +
                    "    AND flights.year = " + year  + " AND flights.day = 29 AND flights.month = " + month + "\n" +
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
                    "    WHERE airports.city = \""+ city +"\")\n" +
                    "    AND flights.year = " + year  + " AND flights.day = 29 AND flights.month = " + month + "\n" +
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
                    "    WHERE month  = " + month + " AND day  = " + day  + " AND atm_location = \" " + atm_street  + "\"\n" +
                    ")\n");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT person_id FROM bank_accounts\n" +
                    "WHERE  bank_accounts.account_number in\n" +
                    "(\n" +
                    "    SELECT atm_transactions.account_number FROM atm_transactions\n" +
                    "    WHERE month  = " + month + " AND day  = " + day  + " AND atm_location = \" " + atm_street  + "\"\n" +
                    ")\n");
            this.printOnScreen(set2,textArea);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getCallerId(String duration){
        try {
            this.duration = duration;
            ResultSet set = statement.executeQuery("SELECT caller FROM phone_calls\n" +
                    "WHERE month  = " + month + " AND day  = " + day  + " AND duration "+ duration +"");
            WriteFile.writeData(set);

            ResultSet set2 = statement.executeQuery("SELECT caller FROM phone_calls\n" +
                    "WHERE month  = " + month + " AND day  = " + day  + " AND duration "+ duration +"");
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
                    "            WHERE month  = " + month + " AND day  = " + day  + " AND atm_location = \" "+ atm_street  + "\"\n" +
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
                    "    WHERE month  = " + month + " AND day  = " + day  + " AND duration "+ duration +"\n" +
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
                    "            WHERE airports.city = \""+ city +"\")\n" +
                    "            AND flights.year = " + year  + " AND flights.day = 29 AND flights.month = "+ month + "\n" +
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
                    "    WHERE year  = " + year  + " AND month  = " + month + " AND day = " + day  + " AND hour =  " + hour  + " AND minute >=  " + startingMinutes  + " AND minute <= " + endingMinutes  + "  AND activity = \" "+ activity  + "\"\n" +
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
                    "            WHERE month  = " + month + " AND day  = " + day  + " AND atm_location = \""+ atm_street  + "\"\n" +
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
                    "    WHERE month  = " + month + " AND day  = " + day  + " AND duration "+ duration +"\n" +
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
                    "            WHERE airports.city = \""+ city +"\")\n" +
                    "            AND flights.year = " + year  + " AND flights.day = 29 AND flights.month = " + month + "\n" +
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
                    "    WHERE year  = " + year  + " AND month  = " + month + " AND day = " + day  + " AND hour =  " + hour  + " AND minute >=  " + startingMinutes  + " AND minute <= " + endingMinutes  + "  AND activity = \"" + activity  + "\"\n" +
                    ")");

            System.out.println("SELECT * FROM people\n" +
                    "WHERE\n" +
                            "id IN\n" +
                            "(\n" +
                            "    SELECT person_id FROM bank_accounts\n" +
                            "        WHERE  bank_accounts.account_number in\n" +
                            "        (\n" +
                            "            SELECT atm_transactions.account_number FROM atm_transactions\n" +
                            "            WHERE month  = " + month + " AND day  = " + day  + " AND atm_location = \""+ atm_street  + "\"\n" +
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
                            "    WHERE month  = " + month + " AND day  = " + day  + " AND duration "+ duration +"\n" +
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
                            "            WHERE airports.city = \""+ city +"\")\n" +
                            "            AND flights.year = " + year  + " AND flights.day = 29 AND flights.month = " + month + "\n" +
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
                            "    WHERE year  = " + year  + " AND month  = " + month + " AND day = " + day  + " AND hour =  " + hour  + " AND minute >=  " + startingMinutes  + " AND minute <= " + endingMinutes  + "  AND activity = \"" + activity  + "\"\n" +
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
