package com.spring;

import com.spring.properties.ConfigProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class Application implements CommandLineRunner {

    /* --- Static members --- */

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /* --- Members --- */

    @Autowired
    private ConfigProperties properties;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.setLogStartupInfo(false);
        application.run(args);
    }

    /* --- Overridden methods --- */

    @Override
    public void run(String... args) throws IllegalAccessException {
        System.out.println("__          ___     _ _        _____                         \n" +
                "\\ \\        / / |   (_) |      / ____|                        \n" +
                " \\ \\  /\\  / /| |__  _| |_ ___| (___   ___  _   _ _ __ ___ ___\n" +
                "  \\ \\/  \\/ / | '_ \\| | __/ _ \\\\___ \\ / _ \\| | | | '__/ __/ _ \\\n" +
                "   \\  /\\  /  | | | | | ||  __/____) | (_) | |_| | | | (_|  __/\n" +
                "    \\/  \\/   |_| |_|_|\\__\\___|_____/ \\___/ \\__,_|_|  \\___\\___");
        validateAndPrintParams();
    }

    /* --- Private methods --- */

    private void validateAndPrintParams() throws IllegalAccessException {
        System.out.println("=================================================================");
        System.out.println("Starting validating parameters");
        StringBuilder errorMessages = new StringBuilder();
        String slackUrl = properties.getSlackUrl();
        String wssUrl = properties.getWssUrl();
        //String orgToken = properties.getOrgToken();
        String productToken = properties.getProductToken();
        String userKey = properties.getUserKey();
        String scheduling = properties.getScheduling();
        String fromDate = properties.getFromDate();
        Float minScore = properties.getMinScore();
        if (StringUtils.isEmpty(slackUrl)) {
            errorMessages.append("Missing Slack URL\n");
        }
        if (StringUtils.isEmpty(wssUrl)) {
            errorMessages.append("Missing WhiteSource URL\n");
        }
//        if (StringUtils.isEmpty(orgToken)) {
//            errorMessages.append("Missing orgToken\n");
//        }
        if (StringUtils.isEmpty(productToken)) {
            errorMessages.append("Missing productToken\n");
        }
        if (StringUtils.isEmpty(userKey)) {
            errorMessages.append("Missing userKey\n");
        }
        if (StringUtils.isEmpty(scheduling)) {
            errorMessages.append("Missing scheduling\n");
        }
        if (minScore != null && (minScore < 0 || minScore > 10)) {
            errorMessages.append("Wrong min score. Range is between 0 - 10\n");
        }
        if (StringUtils.isNotEmpty(fromDate)) {
            // validate date
            if (!isDateValid(fromDate)) {
                errorMessages.append("Invalid date format\n");
            }
        }


        if (errorMessages.length() > 0) {
            System.out.println(errorMessages.toString());
            System.out.println("Exiting");
            System.exit(0);
        } else {
            System.out.println("Starting Slack integration service with the following parameters:");
            System.out.println("Service running on port: " + properties.getServerPort());
            System.out.println("Slack URL: " + slackUrl);
            System.out.println("WhiteSource URL: " + wssUrl);
            //System.out.println("WhiteSource orgToken: " + orgToken);
            System.out.println("WhiteSource productToken: " + productToken);
            System.out.println("WhiteSource userKey: " + userKey);
            if (properties.isSchedulingSecurityAlertsEnabled()) {
                System.out.println("Scheduled for: " + scheduling);
            }
            if (StringUtils.isNotBlank(fromDate)) {
                System.out.println("Fetching vulnerabilities after: " + fromDate);
            }
            if (minScore != null) {
                System.out.println("Fetching vulnerabilities above score: " + minScore);
            }
            System.out.println("=================================================================\n");
        }
    }

    private boolean isDateValid(String fromDate) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        format.setLenient(false);
        try {
            format.parse(fromDate);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
