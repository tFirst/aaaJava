/**
 * Created by ���� on 07.10.2015.
 */
import java.security.*;
import java.math.BigInteger;

/** �����, ����������� �������������� �� ������ � ������ */
public class Auth {

    private String login;
    private String hash;
    private String salt;

    public Auth ( String login, String hash, String salt ) { /** �����, ������� ��������� ����� � ������ �� ��������� ������ */

        this.login = login;
        this.hash = hash;
        this.salt = salt;

        //Check( login, pass );



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
