package work;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import work.domain.Accounting;
import work.domain.Auth;
import work.domain.Autorise;
import work.domain.Roles;

import java.sql.SQLException;

public class Main {

    private static int checkAll = 0;
     private static final Logger logger = LogManager.getLogger(Main.class);

    public static int work(String[] args) throws SQLException, ParseException {

        logger.trace("Start application");
        logger.trace("Enter parameters {}", (Object) args);

        Options options = new Options()
                .addOption("login", true, "Login")
                .addOption("pass", true, "Password")
                .addOption("res", true, "Resource")
                .addOption("role", true, "Role")
                .addOption("ds", true, "StartDate")
                .addOption("de", true, "EndDate")
                .addOption("vol", true, "Volume")
                .addOption("h", false, "Help");

        CommandLineParser cmdLineParser = new DefaultParser();
        CommandLine commandLine;

        try {
            commandLine = cmdLineParser.parse(options, args);
        }
        catch (ParseException e) {
            printHelp(options);
            return 0;
        }

        if (commandLine.hasOption("h") || args.length == 0 || !commandLine.hasOption("login")) {
            printHelp(options);
            logger.trace("Print help");
            return 0;
        }

        Auth auth = new Auth();
        checkAll = auth.checkUser(commandLine.getOptionValue("login"), commandLine.getOptionValue("pass"));

        if (checkAll != 0) {
            return checkAll;
        }

        if (commandLine.hasOption("role") || commandLine.hasOption("res")) {
            Autorise autorise = new Autorise();

            Roles r;
            try {
                r = Roles.valueOf(commandLine.getOptionValue("role"));
            }
            catch (Exception e) {
                logger.error("Incorrect role {}", commandLine.getOptionValue("role"));
                return 3;
            }

            checkAll = autorise.checkRes(commandLine.getOptionValue("login"), commandLine.getOptionValue("res"), Roles.valueOf(commandLine.getOptionValue("role")));

            if (checkAll != 0) {
                return checkAll;
            }

            if (commandLine.hasOption("ds") || commandLine.hasOption("de") || commandLine.hasOption("vol")) {
                Accounting accounting = new Accounting();
                checkAll = accounting.checkDateAndVolume(commandLine.getOptionValue("login"), commandLine.getOptionValue("ds"), commandLine.getOptionValue("de"), commandLine.getOptionValue("vol"));

                if (checkAll != 0) {
                    return checkAll;
                }

                return 0;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws SQLException, ParseException {
        System.exit(work(args));
    }

    private static void printHelp(Options options) {
        String header = "Enter some parameters\n\n";
        String footer = "\nPlease report issues at trane349@mail.ru";

        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("aaaJava", header, options, footer, true);
    }

}