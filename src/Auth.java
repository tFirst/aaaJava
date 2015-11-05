import java.security.*;
import java.math.BigInteger;
import java.util.ArrayList;

    /**
     * Класс предназначен для аутентификации пользователя.
     * Он имеет поля login, куда записывается введенный логин пользователя,
     * hash, куда записывается хэш(хэш(пароль)+соль)
     * и salt, куда записывается соль для данного пользователя.
     * */
public class Auth {

    private String login;
    private String hash;
    private String salt;

        /**
         * Данный метод отвечает за заполнение полей класса
         */

    public Auth ( String login, String hash, String salt ) {

        this.login = login;
        this.hash = hash;
        this.salt = salt;

    }

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

    public static void checkUser(Object login, Object pass, ArrayList<Auth> auth) {

        int index = -1;

        for ( int i = 0; i < auth.size(); i++ ) {

            if (login.equals(auth.get(i).login))
                index = i;

        }

        if ( index == -1 ) {
            System.out.println("1");
            System.exit(1);
        }

        pass = hashMake(hashMake((String) pass)+auth.get(index).salt);

        if (!pass.equals(auth.get(index).hash)) {
            System.out.println("2");
            System.exit(2);
        }

    }

    public String getLogin() {
        return login;
    }
}
