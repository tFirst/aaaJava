/**
 * Created by —тас on 07.10.2015.
 */
import java.security.*;
import java.math.BigInteger;
import java.util.ArrayList;

/**  ласс, описывающий аутентификацию по логину и паролю */
public class Auth {

    private String login;
    private String hash;
    private String salt;

    public Auth ( String login, String hash, String salt ) { /** метод, который принимает логин и пароль из командной строки */

        this.login = login;
        this.hash = hash;
        this.salt = salt;

    }

    public static String hashMake(String pass) { /** метод дл€ хешировани€ парол€ */

        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(pass.getBytes(), 0, pass.length());
            String hash = new BigInteger(1, md5.digest()).toString(16);
            return hash;

        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static void checkUser(Object login, Object pass, ArrayList<Auth> auth) { /** ћетод, непосредственно, проверки данных на достоверность */

        int temp = -1;

        for ( int i = 0; i < auth.size(); i++ ) {

            if (login.equals(auth.get(i).login))
                temp = i;

        }

        if ( temp == -1 )
            System.exit(1);

        pass = hashMake(hashMake((String) pass)+auth.get(temp).salt);

        if (!pass.equals(auth.get(temp).hash))
            System.exit(2);

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
