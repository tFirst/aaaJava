import java.util.ArrayList;

/**
 * Created by Стас on 07.10.2015.
 */
public class Accounting {

    protected String login;
    protected String startDate;
    protected String finishDate;
    protected int volume;

    public Accounting(String login, String sD, String fD, int vol) {

        this.login = login;
        this.startDate = sD;
        this.finishDate = fD;
        this.volume = vol;

    }

    public static void checkDateAndVolume(String sD, String fD, String vol) {

        String[] stDate = sD.split("-");
        String[] finDate = fD.split("-");

        if (( stDate[0].length() != 4 ) || ( finDate[0].length() != 4 ))
            System.exit(5);

        else if ((stDate[1].length() != 2) || (finDate[1].length() != 2))
            System.exit(5);

        else if ((stDate[2].length() != 2) || (finDate[2].length() != 2))
            System.exit(5);

        try {
            int t = Integer.parseInt((String) vol);
        } catch (NumberFormatException e) {
            System.exit(5);
        }

    }

    public String getLogin() {
        return login;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public int getVolume() {
        return volume;
    }
}
