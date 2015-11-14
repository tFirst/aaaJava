import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> arguments = new ArrayList<>();

        Options options = new Options()
                .addOption("login", true, "Login")
                .addOption("pass", true, "Password")
                .addOption("res", true, "Resource")
                .addOption("role", true, "Role")
                .addOption("ds", true, "StartDate")
                .addOption("de", true, "EndhDate")
                .addOption("vol", true, "Volume")
                .addOption("h", false, "Help");

        CommandLineParser cmdLineParser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = cmdLineParser.parse(options, args);
        } catch (ParseException e) {
            printHelp(options);
            System.exit(0);
        }

        if (commandLine.hasOption("h") || args.length == 0) {

            printHelp(options);
            System.exit(0);

        }

        if (commandLine.hasOption("login")) {

            arguments.add(commandLine.getOptionValue("login"));

        }

        if (commandLine.hasOption("pass")) {

            arguments.add(commandLine.getOptionValue("pass"));

        }

        if (commandLine.hasOption("res")) {

            arguments.add(commandLine.getOptionValue("res"));

        }

        if (commandLine.hasOption("role")) {

            arguments.add(commandLine.getOptionValue("role"));

        }

        if (commandLine.hasOption("ds")) {

            arguments.add(commandLine.getOptionValue("ds"));

        }

        if (commandLine.hasOption("de")) {

            arguments.add(commandLine.getOptionValue("de"));

        }

        if (commandLine.hasOption("vol")) {

            arguments.add(commandLine.getOptionValue("vol"));

        }

        ArrayList<Auth> auth = new ArrayList<>();
        ArrayList<Autorise> autorise = new ArrayList<>();
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<Accounting> acct = new ArrayList<>();

        Auth a = new Auth("jdoe", Auth.hashMake(Auth.hashMake("sup3rpaZZ") + "5gth987hc6"), "5gth987hc6");

        auth.add(a);

        Auth.checkUser(arguments.get(0), arguments.get(1), auth);

        Autorise atr = new Autorise("jdoe", "a", Roles.READ);

        autorise.add(atr);

        if (args.length > 2) {

            if (((Objects.equals(commandLine.getOptionValue("res"), "")) || (Objects.equals(commandLine.getOptionValue("role"), "")) ||
                    (commandLine.getOptionValue("res") == null) || (commandLine.getOptionValue("role") == null))) {
                System.out.println("0");
                System.exit(0);
            }

            else {

                String login = "";

                for (Auth anAuth : auth) {

                    if (anAuth.getLogin().equals(commandLine.getOptionValue("login")))
                        login = commandLine.getOptionValue("login");

                }

                if (login.equals("")) {
                    System.out.println("1");
                    System.exit(1);
                }

                if (!(commandLine.getOptionValue("role").equals("EXEC")) &&
                        !(commandLine.getOptionValue("role").equals("READ")) &&
                        !(commandLine.getOptionValue("role").equals("WRITE"))) {
                    System.out.println("3");
                    System.exit(3);
                }
                else {

                    Autorise.checkRes(login, commandLine.getOptionValue("res"), Roles.valueOf(commandLine.getOptionValue("role")), autorise);

                    if (args.length > 4) {

                        if ((Objects.equals(commandLine.getOptionValue("ds"), "")) || (Objects.equals(commandLine.getOptionValue("de"), "")) ||
                                (commandLine.getOptionValue("ds") == null) || (commandLine.getOptionValue("de") == null) || (commandLine.getOptionValue("vol") == null)) {
                            System.out.println("0");
                            System.exit(0);
                        }
                        else {
                            Accounting.checkDateAndVolume(commandLine.getOptionValue("ds"), commandLine.getOptionValue("de"), commandLine.getOptionValue("vol"));
                            Accounting acc = new Accounting(commandLine.getOptionValue("login"), commandLine.getOptionValue("ds"), commandLine.getOptionValue("de"), Integer.parseInt(commandLine.getOptionValue("vol")));

                            acct.add(acc);
                            System.out.println("0");
                            System.exit(0);
                        }
                    }
                }
            }
        }
    }

    private static void printHelp(Options options) {
        String header = "Enter some parameters\n\n";
        String footer = "\nPlease report issues at trane349@mail.ru";

        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("aaaJava", header, options, footer, true);
    }

}