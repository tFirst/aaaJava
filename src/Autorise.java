import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Стас on 07.10.2015.
 */
public class Autorise {

    protected String login;
    protected String res;
    protected Roles role;

    public Autorise(String login, String res, Roles role) {

        this.login = login;
        this.res = res;
        this.role = role;

    }

    public static void checkRes(String login, String res, Roles role,  ArrayList<Autorise> aut) {

        String parse[] = res.split("\\.");

        int temp = 0;

        for (int i = 0; i < aut.size(); i++) {

            if (login.equals(aut.get(i).login)) {

                temp = i;

            }

        }

        String[] atrStr = aut.get(temp).res.split("\\.");

        if (parse.length >= atrStr.length) {

            for (int j = 0; j < atrStr.length; j++) {

                if (Objects.equals(parse[j], atrStr[j]))
                    continue;
                else
                    System.exit(4);

            }

        }
        else
            System.exit(4);

        if (role != aut.get(temp).role)
            System.exit(4);

    }

}
