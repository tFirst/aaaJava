import java.util.ArrayList;
import java.util.Objects;

/**
 * Этот класс используется при авторизации пользователя.
 * Он имеет поля login, куда будет записываться логин пользователя,
 * также поле res, куда будет записываться ресурс для данного пользователя
 * и role, куда будет записываться право доступа данному пользователю для данного ресурса.
 */
public class Autorise {

    protected String login;
    protected String res;
    protected Roles role;

    /**
     * Эта функция отвечает за заполнение полей класса
     */

    public Autorise(String login, String res, Roles role) {

        this.login = login;
        this.res = res;
        this.role = role;

    }

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

    public static void checkRes(String login, String res, Roles role,  ArrayList<Autorise> aut) {

        String parse[] = res.split("\\.");

        int index = 0;

        for (int i = 0; i < aut.size(); i++) {

            if (login.equals(aut.get(i).login)) {

                index = i;

            }

        }

        String[] atrStr = aut.get(index).res.split("\\.");

        if (parse.length >= atrStr.length) {

            for (int j = 0; j < atrStr.length; j++) {

                if (!Objects.equals(parse[j], atrStr[j])) {

                    System.out.println("4");
                    System.exit(4);

                }

            }
        }
        else {
            System.out.println("4");
            System.exit(4);
        }

        if (role != aut.get(index).role) {
            System.out.println("4");
            System.exit(1);
        }

    }

}
