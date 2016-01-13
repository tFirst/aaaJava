import org.apache.commons.cli.ParseException;
import work.Main;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class TestMain {

    @Test
    public void test1() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login XXX -pass XXX".split(" ")),1);
    }

    @Test
    public void test2() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login jdoe -pass XXX".split(" ")),2);
    }

    @Test
    public void test3() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login jdoe -pass sup3rpaZZ".split(" ")),0);
    }

    @Test
    public void test4() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login jdoe -pass sup3rpaZZ -role READ -res a".split(" ")),0);
    }

    @Test
    public void test5() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login jdoe -pass sup3rpaZZ -role READ -res a.b".split(" ")),0);
    }

    @Test
    public void test6() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login jdoe -pass sup3rpaZZ -role XXX -res a.b".split(" ")),3);
    }

    @Test
    public void test7() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login jdoe -pass sup3rpaZZ -role READ -res XXX".split(" ")),4);
    }

    @Test
    public void test8() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login jdoe -pass sup3rpaZZ -role WRITE -res a".split(" ")),4);
    }

    @Test
    public void test9() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login jdoe -pass sup3rpaZZ -role WRITE -res a.bc".split(" ")),4);
    }

    @Test
    public void test10() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login jdoe -pass sup3rpaZZ -role READ -res a -ds 2015-05-01 -de 2015-05-02 -vol 100".split(" ")),0);
    }

    @Test
    public void test11() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login jdoe -pass sup3rpaZZ -role READ -res a -ds XXX -de XXX -vol XXX".split(" ")),5);
    }

    @Test
    public void test12() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-login jdoe -pass sup3rpaZZ -role READ -res a -ds 2015-05-01 -de 2015-05-02 -vol XXX".split(" ")),5);
    }

    @Test
    public void test13() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("".split(" ")),0);
    }

    @Test
    public void test14() throws SQLException, ParseException {
        assertEquals("Incorrect code",Main.work("-h".split(" ")),0);
    }
}