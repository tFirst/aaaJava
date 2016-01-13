import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Класс предназначен для аутентификации пользователя.
 * Он имеет поля login, куда записывается введенный логин пользователя,
 * hash, куда записывается хэш(хэш(пароль)+соль)
 * и salt, куда записывается соль для данного пользователя.
 * */
public class Auth {

    private static final Logger logger = LogManager.getLogger(Auth.class);
    Connection connection;

        /**
         * Данный метод отвечает за заполнение полей класса
         */

    public Auth() { logger.trace("Start authentification"); }

        /**
         * Функция hashMake отвечает за хеширование введенного пароля
         */

    public static String hashMake(String pass) {

        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(pass.getBytes(), 0, pass.length());
            return new BigInteger(1, md5.digest()).toString(16);

        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }

        /**
         * Данный метод отвечает за проверку введенных данных на достоверность.
         * Сначала проверяется есть ли пользователь с введенным логином в базе,
         * если все в порядке, то проверяется соответствие введенного пароля с паролем для этого пользователя, имеющимся в базе.
         * Если неверный логин или пароль, то программа завершается с соответствующим кодом.
         */

    public void checkUser(String login, String pass) throws SQLException {

        logger.trace("Connecting with data base");
        migrate();
        connection = DriverManager.getConnection("jdbc:h2:./aaaJava", "root", "root");

        logger.trace("Checking login and password");
        PreparedStatement statement = connection.prepareStatement("select * from auth where login = ?");
        statement.setString(1, login);

        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            logger.error("Incorrect login {}", login);
            connection.close();
            System.exit(1);
        }

        pass = hashMake(hashMake(pass) + resultSet.getString("salt"));

        if (!pass.equals(resultSet.getString("hash"))) {
            logger.error("Incorrect password {} for user {}", pass, login);
            connection.close();
            System.exit(2);
        }

        connection.close();

        logger.trace("User {} with password {} entered", login, pass);

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
