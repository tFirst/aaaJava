import java.util.ArrayList;

/**
 * Created by Стас on 07.10.2015.
 */
public class Main {

        public static void main( String[] args) {

            ArrayList<Auth> auth = new ArrayList();
            ArrayList<Autorise> autorise = new ArrayList();

            Auth a = new Auth();
            a.login = "admin";
            a.salt = "5gth987hc6";
            a.hash = a.hashMake ( a.hashMake ( "admin" ) + a.salt );

            //System.out.println(a.login);
            // System.out.println(a.salt);
            //System.out.println(a.hash);

            //System.out.println(args[0]);
            //System.out.println(args[1]);

            auth.add(a);

            a.AuthManager( args[0], args[1] );

            Autorise atr = new Autorise();

            atr.res = "A.B";

            autorise.add(atr);

            //System.out.println(auth.get(0).login);
            //System.out.println(autorise.get(0));

            if( ( args[2] == "" ) || ( args[3] == "" ) || ( args[2] == null ) || ( args[3] == null ) )
                System.exit(0);

            else {

                //int index = auth.indexOf(a);

                //System.out.println(index);

                String login = "";

                for ( int i = 0; i < auth.size(); i++ ) {

                    if ( auth.get(i).login.equals(args[0]) )
                        login = args[0];

                }

                if ( login.equals("") )
                    System.exit(1);

                atr.Check( login, args[2], args[3] );

            }
        }

}
