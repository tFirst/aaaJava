import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Данный класс отвечает за аккаунтинг.
 * Он имеет поля login, куда записывается логин пользователя,
 * startDate - дата начала пользования ресурсом,
 * finishDate - дата окончания пользования ресурсом
 * и volume - объем, который может быть использован.
 */
public class Accounting {

    private static final Logger logger = LogManager.getLogger(Accounting.class);
    Connection connection;

    /**
     * Данный метод отвечает за заполнение полей класса
     */

    public Accounting() {
        logger.trace("-------Starting accounting-------");
    }

    /**
     * Данный метод отвечает за проверку введенных данных, т.е.,
     * например, чтобы дата соответствовала формату ГГГГ-ММ-ДД, а также,
     * чтобы значение volume было числом, а не строкой.
     */

    public void checkDateAndVolume(String login, String sD, String eD, String vol) throws SQLException {

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        dateFormat.setLenient(false);

        try {
            dateFormat.parse(sD);
        } catch (ParseException e) {
            logger.error("Bad start date format");
            System.exit(5);
        }

        try {
            dateFormat.parse(eD);
        } catch (ParseException e) {
            logger.error("Bad end date format");
            System.exit(5);
        }

        try {
            Integer.parseInt(vol);
        } catch (NumberFormatException e) {
            logger.error("!!! Bad volume !!!");
            System.exit(5);
        }

        logger.trace("Connectiong with data base");
        migrate();
        connection = DriverManager.getConnection("jdbc:h2:./aaaJava", "root", "root");

        PreparedStatement statement = connection.prepareStatement("select * from autorise where login = ?");
        statement.setString(1, login);
        ResultSet result = statement.executeQuery();
        result.first();

        logger.trace("Adding to Accounting");
        PreparedStatement preparedStatement = connection.prepareStatement("insert into accounting (id, login, start_date, end_date, volume, resource)" +
                                                                            " values (acc_seq.nextval, ?, ?, ?, ?, ?)");

        preparedStatement.setString(1, login);
        preparedStatement.setDate(2, Date.valueOf(sD));
        preparedStatement.setDate(3, Date.valueOf(eD));
        preparedStatement.setInt(4, Integer.parseInt(vol));
        preparedStatement.setString(5, result.getString("resource"));

        preparedStatement.executeUpdate();

        logger.trace("Adding OK");

        connection.close();

    }

    public void migrate() {
        logger.info("Trying to migrate DB");
        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource("jdbc:h2:./aaaJava", "root", "root");

        // Start the migration
        try {
            flyway.migrate();
        } catch (FlywayException e) {
            logger.error("Cannot migrate DB", e);
            throw e;
        }
    }

}
