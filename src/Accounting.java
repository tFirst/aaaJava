import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Данный класс отвечает за аккаунтинг.
 * Он имеет поля login, куда записывается логин пользователя,
 * startDate - дата начала пользования ресурсом,
 * finishDate - дата окончания пользования ресурсом
 * и volume - объем, который может быть использован.
 */
public class Accounting {

    protected String login;
    protected String startDate;
    protected String finishDate;
    protected int volume;

    /**
     * Данный метод отвечает за заполнение полей класса
     */

    public Accounting(String login, String sD, String fD, int vol) {

        this.login = login;
        this.startDate = sD;
        this.finishDate = fD;
        this.volume = vol;

    }

    /**
     * Данный метод отвечает за проверку введенных данных, т.е.,
     * например, чтобы дата соответствовала формату ГГГГ-ММ-ДД, а также,
     * чтобы значение volume было числом, а не срокой.
     */

    public static void checkDateAndVolume(String sD, String fD, String vol) {

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateFormat.parse(sD);
        } catch (ParseException e) {
            System.exit(5);
        }

        try {
            dateFormat.parse(fD);
        } catch (ParseException e) {
            System.exit(5);
        }

        try {
            //noinspection ResultOfMethodCallIgnored
            Integer.parseInt(vol);
        } catch (NumberFormatException e) {
            System.exit(5);
        }

    }

}
