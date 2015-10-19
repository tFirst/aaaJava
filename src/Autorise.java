/**
 * Created by Стас on 07.10.2015.
 */
public class Autorise {

    protected String login;
    protected String res;

    public enum Roles {

        READ("1"), WRITE("2"), EXEC("4");

    }

    /**public void autoriseManager( String res, String role ) {

        res = res;
        role = role;

    }*/

    public void Check( String login, String res, Roles role ) {

        if ( !this.res.equals(res) )
            System.exit(4);

        else if ( !this.Roles.equals(role) )
            System.exit(3);

    }

}
