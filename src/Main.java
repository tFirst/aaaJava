import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Стас on 07.10.2015.
 */
public class Main {

    public static void main(String[] args) throws ParseException {

        ArrayList<String> arguments = new ArrayList<String>();

        Option optionLogin = new Option("login", true, "Login");
        Option optionPass = new Option("pass", true, "Password");
        Option optionRes = new Option("res", true, "Resource");
        Option optionRole = new Option("role", true, "Role");
        Option optionStartDate = new Option("ds", true, "StartDate");
        Option optionFinishDate = new Option("df", true, "FinishDate");
        Option optionVolume = new Option("vol", true, "Volume");
        Option optionHelp = new Option("h", true, "Help");

        optionLogin.setOptionalArg(true);
        optionPass.setOptionalArg(true);
        optionRes.setOptionalArg(true);
        optionRole.setOptionalArg(true);
        optionStartDate.setOptionalArg(true);
        optionFinishDate.setOptionalArg(true);
        optionVolume.setOptionalArg(true);
        optionHelp.setOptionalArg(true);

        optionLogin.setArgName("login ");
        optionPass.setArgName("password ");
        optionRes.setArgName("resource ");
        optionRole.setArgName("role ");
        optionStartDate.setArgName("startDate ");
        optionFinishDate.setArgName("finishDate ");
        optionVolume.setArgName("volume ");
        optionHelp.setArgName("help");

        Options posixOptions = new Options();
        posixOptions.addOption(optionLogin);
        posixOptions.addOption(optionPass);
        posixOptions.addOption(optionRes);
        posixOptions.addOption(optionRole);
        posixOptions.addOption(optionStartDate);
        posixOptions.addOption(optionFinishDate);
        posixOptions.addOption(optionVolume);
        posixOptions.addOption(optionHelp);

        CommandLineParser cmdLineParser = new PosixParser();
        CommandLine commandLine = cmdLineParser.parse(posixOptions, args);

        if ( commandLine.hasOption("h") ) {
            arguments.add(commandLine.getOptionValue("h"));

            System.out.println("[-h] - Show help\n" +
                                "[-login login] - Enter login\n" +
                                "[-pass password] - Enter password\n" +
                                "Login may be entered just with the password" +
                                "[-res resource] - Enter resource\n" +
                                "[-role role] - Enter role\n" +
                                "Resource may be entered just with the role" +
                                "[-ds date] - Enter date1\n" +
                                "[-df date] - Enter date2\n" +
                                "[-vol volume] - Enter volume\n" +
                                "Dates and volume may be entered just together");
            System.exit(0);
        }

        if ( commandLine.hasOption("login") ) {

            arguments.add(commandLine.getOptionValue("login"));
            //System.out.println(commandLine.getOptionValue("login"));

        }

        if ( commandLine.hasOption("pass") ) {

            arguments.add(commandLine.getOptionValue("pass"));
            //System.out.println(commandLine.getOptionValue("pass"));

        }

        if ( commandLine.hasOption("res") ) {

            arguments.add(commandLine.getOptionValue("res"));
            //System.out.println(commandLine.getOptionValue("res"));

        }

        if ( commandLine.hasOption("role") ) {

            arguments.add(commandLine.getOptionValue("role"));
            //System.out.println(commandLine.getOptionValue("role"));

        }

        if ( commandLine.hasOption("ds") ) {

            arguments.add(commandLine.getOptionValue("ds"));
           // System.out.println(commandLine.getOptionValue("ds"));

        }

        if ( commandLine.hasOption("df") ) {

            arguments.add(commandLine.getOptionValue("df"));
           // System.out.println(commandLine.getOptionValue("df"));

        }

        if ( commandLine.hasOption("vol") ) {

            arguments.add(commandLine.getOptionValue("vol"));
            //System.out.println(commandLine.getOptionValue("vol"));

        }

        ArrayList<Auth> auth = new ArrayList<Auth>();
        ArrayList<Autorise> autorise = new ArrayList<Autorise>();
        ArrayList<Accounting> acct = new ArrayList<Accounting>();

        Auth a = new Auth("jdoe", Auth.hashMake(Auth.hashMake("sup3rpaZZ") + "5gth987hc6"), "5gth987hc6");

        auth.add(a);

        Auth.checkUser(arguments.get(0), arguments.get(1), auth);

        Autorise atr = new Autorise("jdoe", "a", Roles.READ);

        autorise.add(atr);

        if (args.length > 2) {

            if (((Objects.equals(arguments.get(2), "")) || (Objects.equals(arguments.get(3), "")) ||
                    (arguments.get(2) == null) || (arguments.get(3) == null)))
                System.exit(0);

            else {

                String login = "";

                for (int i = 0; i < auth.size(); i++) {

                    if (auth.get(i).getLogin().equals(arguments.get(0)))
                        login = (String) arguments.get(0);

                }

                if (login.equals(""))
                    System.exit(1);

//                if ((Roles.valueOf(arguments[3])!=Roles.EXEC) &&
//                        (Roles.valueOf(arguments[3])!=Roles.READ) &&
//                        (Roles.valueOf(arguments[3])!=Roles.WRITE))
                if (!(arguments.get(3).equals("EXEC")) &&
                        !(arguments.get(3).equals("READ")) &&
                        !(arguments.get(3).equals("WRITE")))
                    System.exit(3);
                else {

                    Autorise.checkRes(login, (String) arguments.get(2), Roles.valueOf((String) arguments.get(3)), autorise);

                    if (args.length > 4) {

                        if ((Objects.equals(arguments.get(4), "")) || (Objects.equals(arguments.get(5), "")) ||
                                (arguments.get(4) == null) || (arguments.get(5) == null) || (arguments.get(6) == null))
                            System.exit(0);
                        else {
                            Accounting.checkDateAndVolume(arguments.get(4), arguments.get(5), arguments.get(6));
                            Accounting acc = new Accounting(arguments.get(0), arguments.get(4), arguments.get(5), Integer.parseInt(arguments.get(6)));

                            acct.add(acc);
                        }
                    }
                }
            }
        }
    }

}
