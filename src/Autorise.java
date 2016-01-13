import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

import java.sql.*;
import java.util.Objects;

/**
 * Этот класс используется при авторизации пользователя.
 * Он имеет поля login, куда будет записываться логин пользователя,
 * также поле res, куда будет записываться ресурс для данного пользователя
 * и role, куда будет записываться право доступа данному пользователю для данного ресурса.
 */
public class Autorise {

    private static final Logger logger = LogManager.getLogger(Autorise.class);
    Connection connection;

    /**
     * Эта функция отвечает за заполнение полей класса
     */

    public Autorise() { logger.trace("Start autorisation"); }

    /**
     * Данная функция отвечает за проверку введенных данных.
     * Сперва проверяется наличие введенного логина в базе.
     * Если логин найден, то его индекс записывается в переменную index, поскольку пригодится для
     * проверки ресурса, которая происходит следующим образом:
     * программа находит ресурс(по индексу, который программа нашла ранее), который предназначен заданному логину,
     * а затем проверяет, есть ли у заданного пользователя заданное право доступа к заданному ресурсу.
     * Если все в порядке, то программа продолжит свою работу,
     * в противном случае - выход из программы с соответствующим кодом.
     */

    public void checkRes(String login, String res, Roles role) throws SQLException {

        logger.trace("Connecting with data base");
        migrate();
        connection = DriverManager.getConnection("jdbc:h2:./aaaJava", "root", "root");

        PreparedStatement preparedStatement = connection.prepareStatement("select a.id, a.login, a.resource as resource, a.role as role from autorise a where a.login = ?");
        preparedStatement.setString(1, login);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(!resultSet.next()) {
            logger.error("Not data for user {}", login);
            connection.close();
            System.exit(4);
        }

        String parse[] = res.split("\\.");

        boolean check = false;

        while (resultSet.next()) {
            if (resultSet.getString("role").equals(role.toString())) {
                check = true;
                String[] atrStr = resultSet.getString("resource").split("\\.");
                if (parse.length >= atrStr.length) {

                    for (int j = 0; j < atrStr.length; j++) {

                        if (!Objects.equals(parse[j], atrStr[j])) {
                            logger.error("Not access to resource {}", res);
                            connection.close();
                            System.exit(4);
                        }

                    }
                } else {
                    logger.error("Not access to resource {}", res);
                    connection.close();
                    System.exit(4);
                }
                break;
            }
        }

        if (!check) {
            logger.error("Not access to resource {} with role {}", res, role);
            connection.close();
            System.exit(3);
        }

        logger.trace("User {} get access to resource {} with role {}", login, res, role);
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
