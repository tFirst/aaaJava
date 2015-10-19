/**
 * Created by —тас on 07.10.2015.
 */
import java.security.*;
import java.math.BigInteger;

/**  ласс, описывающий аутентификацию по логину и паролю */
public class Auth {

    protected String login;
    protected String hash;
    protected String salt;

    public void AuthManager ( String login, String pass ) { /** метод, который принимает логин и пароль из командной строки */

        login = login;
        pass = hashMake( hashMake( pass ) + salt );

        Check( login, pass );



    }

    public String hashMake( String pass ) { /** метод дл€ хешировани€ парол€ */

        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(pass.getBytes(), 0, pass.length());
            String hash = new BigInteger(1,md5.digest()).toString(16);
            return hash;

        }

        catch ( final NoSuchAlgorithmException e ) {
            e.printStackTrace();
            return "";
        }

    }

    private void Check ( String login, String hash ) { /** ћетод, непосредственно, проверки данных на достоверность */

        if ( !this.login.equals(login) )
            System.exit(1);

        else if ( !this.hash.equals(hash) )
            System.exit(2);

    }

}
