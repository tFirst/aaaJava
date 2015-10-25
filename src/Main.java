import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by Стас on 07.10.2015.
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<Auth> auth = new ArrayList<Auth>();
        ArrayList<Autorise> autorise = new ArrayList<Autorise>();

        Auth a = new Auth("admin", hashMake(hashMake("admin") + "5gth987hc6"), "5gth987hc6");
        CheckUser(a, a.getLogin(), a.getHash());
//            a.setLogin("admin");
//            a.setSalt("5gth987hc6");
//            a.setHash(a.hashMake ( a.hashMake ( "admin" ) + a.getSalt()) );


        //System.out.println(a.login);
        // System.out.println(a.salt);
        //System.out.println(a.hash);

        //System.out.println(args[0]);
        //System.out.println(args[1]);

        auth.add(a);

        //a.AuthManager( args[0], args[1] );

        Autorise atr = new Autorise();

        atr.login = "admin";
        atr.res = "A-B";
        atr.role = Roles.EXEC;

        autorise.add(atr);

        System.out.println(autorise.get(0).login);

        //System.out.println(auth.get(0).login);
        //System.out.println(autorise.get(0));

        if ((args[2] == "") || (args[3] == "") || (args[2] == null) || (args[3] == null))
            System.exit(0);

        else {

            //int index = auth.indexOf(a);

            //System.out.println(index);

            String login = "";

            for (int i = 0; i < auth.size(); i++) {

                if (auth.get(i).getLogin().equals(args[0]))
                    login = args[0];

            }

            if (login.equals(""))
                System.exit(1);

            //CheckResource(autorise, login, args[2], Roles.valueOf(args[3]));
            checkRes(autorise.get(0).login, args[2], autorise);

        }
    }

    public static void CheckResource(ArrayList<Autorise> a, String login, String res, Roles role) {

        for (Autorise anA : a) {

            //if (anA.login.equals(login) && anA.role == role)


        }

        //if ( !this.res.equals(res) )
        //System.exit(4);

        //else if ( !this.role.equals(role) )
        //System.exit(3);

    }

    public static String hashMake(String pass) { /** метод для хеширования пароля */

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

    private static void CheckUser(Auth a, String login, String hash) { /** Метод, непосредственно, проверки данных на достоверность */

        if (!a.getLogin().equals(login))
            System.exit(1);

        else if (!a.getHash().equals(hash))
            System.exit(2);

    }

    private static void checkRes(String a, String b, ArrayList<Autorise> aut) {

        //String o = "1.2.3.4";

        String parse[] = b.split("-");

        int temp = 0;

        for (int i = 0; i < parse.length; i++)
            System.out.println(parse[i]);

        for (int i = 0; i < aut.size(); i++) {

            if (a.equals(aut.get(i).login)) {

                temp = i;

            }

        }

        String atrStr[] = aut.get(temp).res.split("-");

        for (int i = 0; i < atrStr.length; i++)
            System.out.println(atrStr[i]);
        System.out.println(aut.get(temp).res);

        if (parse.length > atrStr.length) {

            for (int j = 0; j < atrStr.length; j++) {

                if (parse[j] == atrStr[j])
                    continue;
                else
                    System.exit(4);

            }

        }
        else
            System.exit(4);

    }

}
