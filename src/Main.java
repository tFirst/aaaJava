import java.util.ArrayList;

/**
 * Created by Стас on 07.10.2015.
 */
public class Main {

        public static void main( String[] args) {
            ArrayList<Auth> auth = new ArrayList();

            Auth a = new Auth();
            a.login = "admin";
            a.salt = "5gth987hc6";
            a.hash = a.hashMake ( a.hashMake ( "admin" ) + a.salt );

            auth.add(a);

            Autorise atr = new Autorise();

            atr.role = "EXEC";
            atr.res = "A.B";

            a.AuthManager(args[0],args[1]);

            System.out.println(auth.get(0));

            if( ( args[2] == "" ) || ( args[3] == "" ) || ( args[2] == null ) || ( args[3] == null ) )
                System.exit(0);

            else {

                int index = auth.indexOf(a);

                System.out.println(index);

                //atr.autoriseManager( index, args[2], args[3] );

            }
        }

}
