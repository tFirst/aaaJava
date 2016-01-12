import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public Autorise() { logger.trace("-------Starting autorisation-------"); }

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
        connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

        PreparedStatement preparedStatement = connection.prepareStatement("select a.id, a.login, a.resource, r.role from autorise a, roles r where a.role = r.id and login = ?");
        preparedStatement.setString(1, login);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(!resultSet.next()) {
            logger.error("Not data");
            System.exit(4);
        }

        String parse[] = res.split("\\.");

        String[] atrStr = resultSet.getString("resource").split("\\.");

        if (parse.length >= atrStr.length) {

            for (int j = 0; j < atrStr.length; j++) {

                if (!Objects.equals(parse[j], atrStr[j])) {
                    logger.error("Not access to this resource. Bad resource name");
                    System.exit(4);

                }

            }
        }
        else {
            logger.error("Not access to this resource. Bad resource name");
            System.exit(4);
        }

        if (!(resultSet.getString("role").equals(role.toString()))) {
            logger.error("Not access to this resource. Bad role");
            System.exit(3);
        }

        logger.trace("Checking OK");

    }

}
