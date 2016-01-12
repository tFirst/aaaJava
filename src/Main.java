import org.apache.commons.cli.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.SQLException;

public class Main {
     private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException, ParseException {

        logger.trace("-------Enter console-------");

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
        CommandLine commandLine = cmdLineParser.parse(options, args);

        if (commandLine.hasOption("h") || args.length == 0) {
            printHelp(options);
            logger.trace("-------Print help-------");
            System.exit(0);
        }

        Auth auth = new Auth();
        auth.checkUser(commandLine.getOptionValue("login"), commandLine.getOptionValue("pass"));

        if (args.length > 4) {
            Autorise autorise = new Autorise();

            Roles r = null;
            try {
                r = Roles.valueOf(commandLine.getOptionValue("role"));
            }
            catch (Exception e) {
                logger.error("Incorrect role");
                System.exit(3);
            }

            autorise.checkRes(commandLine.getOptionValue("login"), commandLine.getOptionValue("res"), Roles.valueOf(commandLine.getOptionValue("role")));

            if (args.length > 8) {
                Accounting accounting = new Accounting();
                accounting.checkDateAndVolume(commandLine.getOptionValue("login"), commandLine.getOptionValue("ds"), commandLine.getOptionValue("de"), commandLine.getOptionValue("vol"));
                System.exit(0);
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